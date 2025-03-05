package de.devin.create_fusion.datagen

import com.simibubi.create.Create
import com.simibubi.create.infrastructure.data.GeneratedEntriesProvider
import com.tterrag.registrate.providers.RegistrateDataProvider
import de.devin.create_fusion.CreateFusion
import de.devin.create_fusion.recipes.ProcessingRecipeGen
import net.neoforged.neoforge.data.event.GatherDataEvent

object CreateFusionDatagen {

    fun gatherData(event: GatherDataEvent) {
        val generator = event.generator
        val output = generator.packOutput

        CreateFusion.LOGGER.info("Gathering data...")

        var lookupProvider = event.lookupProvider
        val generatedEntriesProvider = GeneratedEntriesProvider(output, lookupProvider)
        lookupProvider = generatedEntriesProvider.registryProvider


        ProcessingRecipeGen.registerAll(generator, output, lookupProvider)


        event.generator.addProvider(
            true,
            CreateFusion.REGISTRATE.setDataProvider(RegistrateDataProvider(Create.REGISTRATE, Create.ID, event))
        )

    }
}