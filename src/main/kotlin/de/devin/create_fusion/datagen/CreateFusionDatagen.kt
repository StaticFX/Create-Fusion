package de.devin.create_fusion.datagen

import com.simibubi.create.infrastructure.data.GeneratedEntriesProvider
import de.devin.create_fusion.recipes.ProcessingRecipeGen
import net.neoforged.neoforge.data.event.GatherDataEvent

object CreateFusionDatagen {

    fun gatherData(event: GatherDataEvent) {
        val generator = event.generator
        val output = generator.packOutput

        var lookupProvider = event.lookupProvider
        val generatedEntriesProvider = GeneratedEntriesProvider(output, lookupProvider)
        lookupProvider = generatedEntriesProvider.registryProvider

        if (event.includeServer()) {
            ProcessingRecipeGen.registerAll(generator, output, lookupProvider)
        }
    }
}