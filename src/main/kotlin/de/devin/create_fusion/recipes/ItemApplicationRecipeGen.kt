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


    var TUNGSTEN: GeneratedRecipe = woodCasing(
        "tungsten",
        { AllItems.TUNGSTEN_INGOT.get() },
        { AllBlocks.TUNGSTEN_CASING.get() })

    private fun woodCasing(
        type: String,
        ingredient: Supplier<ItemLike>,
        output: Supplier<ItemLike>
    ): GeneratedRecipe {
        CreateFusion.LOGGER.info("Creating wood casing recipe")
        return woodCasingIngredient(type, { Ingredient.of(ingredient.get()) }, output)
    }

    fun woodCasingIngredient(
        type: String, ingredient: Supplier<Ingredient>,
        output: Supplier<ItemLike>
    ): GeneratedRecipe {
        create(
            type + "_casing_from_log",
            UnaryOperator { b: ProcessingRecipeBuilder<ProcessingRecipe<*>?> ->
                b.require(Tags.Items.STRIPPED_LOGS)
                    .require(ingredient.get())
                    .output(output.get())
            })
        return create(
            type + "_casing_from_wood"
        ) { b: ProcessingRecipeBuilder<ProcessingRecipe<*>?> ->
            b.require(Tags.Items.STRIPPED_WOODS)
                .require(ingredient.get())
                .output(output.get())
        }
    }

    override fun getRecipeType(): IRecipeTypeInfo = AllRecipeTypes.ITEM_APPLICATION
}