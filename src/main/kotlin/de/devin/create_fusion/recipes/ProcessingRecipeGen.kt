package de.devin.create_fusion.recipes

import com.simibubi.create.Create
import com.simibubi.create.content.processing.recipe.ProcessingRecipe
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder
import com.simibubi.create.content.processing.recipe.ProcessingRecipeSerializer
import com.simibubi.create.foundation.data.recipe.CreateRecipeProvider
import com.simibubi.create.foundation.data.recipe.CreateRecipeProvider.GeneratedRecipe
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo
import net.createmod.catnip.registry.RegisteredObjectsHelper
import net.minecraft.core.HolderLookup
import net.minecraft.data.DataGenerator
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import java.util.concurrent.CompletableFuture
import java.util.function.Supplier
import java.util.function.UnaryOperator

abstract class ProcessingRecipeGen(generator: PackOutput, registries: CompletableFuture<HolderLookup.Provider>?): CreateRecipeProvider(generator, registries) {


    companion object {
        val generators = mutableListOf<ProcessingRecipeGen>()


        fun registerAll(gen: DataGenerator,output: PackOutput, registries: CompletableFuture<HolderLookup.Provider>) {
            generators += ItemApplicationRecipeGen(output, registries)
        }
    }

    fun <T : ProcessingRecipe<*>?> create(
        namespace: String,
        singleIngredient: Supplier<ItemLike>, transform: UnaryOperator<ProcessingRecipeBuilder<T>>
    ): GeneratedRecipe {
        val serializer = getSerializer<T>()
        val generatedRecipe = GeneratedRecipe { c: RecipeOutput? ->
            val itemLike = singleIngredient.get()
            transform
                .apply(
                    ProcessingRecipeBuilder(
                        serializer.factory,
                        ResourceLocation.fromNamespaceAndPath(
                            namespace, RegisteredObjectsHelper.getKeyOrThrow(itemLike.asItem())
                                .path
                        )
                    ).withItemIngredients(Ingredient.of(itemLike))
                )
                .build(c)
        }
        all.add(generatedRecipe)
        return generatedRecipe
    }

    /**
     * Create a processing recipe with a single itemstack ingredient, using its id
     * as the name of the recipe
     */
    fun <T : ProcessingRecipe<*>?> create(
        singleIngredient: Supplier<ItemLike>,
        transform: UnaryOperator<ProcessingRecipeBuilder<T>>
    ): GeneratedRecipe {
        return create(Create.ID, singleIngredient, transform)
    }

    fun <T : ProcessingRecipe<*>?> createWithDeferredId(
        name: Supplier<ResourceLocation?>,
        transform: UnaryOperator<ProcessingRecipeBuilder<T>>
    ): GeneratedRecipe {
        val serializer = getSerializer<T>()
        val generatedRecipe =
            GeneratedRecipe { c: RecipeOutput? ->
                transform.apply(ProcessingRecipeBuilder(serializer.factory, name.get()))
                    .build(c)
            }
        all.add(generatedRecipe)
        return generatedRecipe
    }

    /**
     * Create a new processing recipe, with recipe definitions provided by the
     * function
     */
    fun <T : ProcessingRecipe<*>?> create(
        name: ResourceLocation?,
        transform: UnaryOperator<ProcessingRecipeBuilder<T>>
    ): GeneratedRecipe {
        return createWithDeferredId({ name }, transform)
    }

    /**
     * Create a new processing recipe, with recipe definitions provided by the
     * function
     */
    fun <T : ProcessingRecipe<*>?> create(
        name: String,
        transform: UnaryOperator<ProcessingRecipeBuilder<T>>
    ): GeneratedRecipe {
        return create(Create.asResource(name), transform)
    }

    abstract fun getRecipeType(): IRecipeTypeInfo

    fun <T : ProcessingRecipe<*>?> getSerializer(): ProcessingRecipeSerializer<T> {
        return getRecipeType().getSerializer()
    }

    fun idWithSuffix(item: Supplier<ItemLike>, suffix: String): Supplier<ResourceLocation> {
        return Supplier {
            val registryName = RegisteredObjectsHelper.getKeyOrThrow(
                item.get()
                    .asItem()
            )
            Create.asResource(registryName.path + suffix)
        }
    }
}