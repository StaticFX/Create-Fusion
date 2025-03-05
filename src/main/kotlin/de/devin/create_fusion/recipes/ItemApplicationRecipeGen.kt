package de.devin.create_fusion.recipes

import com.simibubi.create.AllRecipeTypes
import com.simibubi.create.content.processing.recipe.ProcessingRecipe
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo
import de.devin.create_fusion.CreateFusion
import de.devin.create_fusion.blocks.AllBlocks
import de.devin.create_fusion.items.AllItems
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import net.neoforged.neoforge.common.Tags
import java.util.concurrent.CompletableFuture
import java.util.function.Supplier
import java.util.function.UnaryOperator

open class ItemApplicationRecipeGen(generator: PackOutput,
                                    registries: CompletableFuture<HolderLookup.Provider>?
) : ProcessingRecipeGen(generator, registries) {


    var TUNGSTEN: GeneratedRecipe = fromBrassCasing(
        "tungsten",
        { AllItems.TUNGSTEN_INGOT.get() },
        { AllBlocks.TUNGSTEN_CASING.get() })


    fun fromBrassCasing(type: String, ingrident: Supplier<ItemLike>, output: Supplier<ItemLike>): GeneratedRecipe =
        brassCasingIngredient(type, { Ingredient.of(ingrident.get()) }, output)

    private fun woodCasing(
        type: String,
        ingredient: Supplier<ItemLike>,
        output: Supplier<ItemLike>
    ): GeneratedRecipe {
        return woodCasingIngredient(type, { Ingredient.of(ingredient.get()) }, output)
    }

    private fun brassCasingIngredient(type: String, ingredient: Supplier<Ingredient>, output: Supplier<ItemLike>): GeneratedRecipe {
        return create(CreateFusion.asResource("${type}_casing_from_brass_casing")) { b: ProcessingRecipeBuilder<ProcessingRecipe<*>?> ->
            b.require(com.simibubi.create.AllBlocks.BRASS_CASING.get())
                .require(ingredient.get())
                .output(output.get())
        }
    }


    fun woodCasingIngredient(
        type: String, ingredient: Supplier<Ingredient>,
        output: Supplier<ItemLike>
    ): GeneratedRecipe {
        create(CreateFusion.asResource(type + "_casing_from_log")) { b: ProcessingRecipeBuilder<ProcessingRecipe<*>?> ->
            b.require(Tags.Items.STRIPPED_LOGS)
                .require(ingredient.get())
                .output(output.get())
        }

        return create(CreateFusion.asResource(type + "_casing_from_wood")) { b: ProcessingRecipeBuilder<ProcessingRecipe<*>?> ->
            b.require(Tags.Items.STRIPPED_WOODS)
                .require(ingredient.get())
                .output(output.get())
        }
    }

    override fun getRecipeType(): IRecipeTypeInfo = AllRecipeTypes.ITEM_APPLICATION
}