package de.devin.create_fusion

import com.simibubi.create.Create
import com.simibubi.create.foundation.data.CreateRegistrate
import de.devin.create_fusion.blocks.AllBlocks
import de.devin.create_fusion.datagen.CreateFusionDatagen
import de.devin.create_fusion.items.AllItems
import de.devin.create_fusion.tabs.AllCreativeModeTabs
import net.minecraft.client.Minecraft
import net.minecraft.resources.ResourceLocation
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import net.neoforged.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import net.neoforged.neoforge.common.NeoForge
import net.neoforged.neoforge.data.event.GatherDataEvent
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.neoforge.forge.FORGE_BUS
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.neoforge.forge.runForDist

/**
 * Main mod class.
 *
 * An example for blocks is in the `blocks` package of this mod.
 */
@Mod(CreateFusion.ID)
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
object CreateFusion {

    const val ID = "create_fusion"

    val REGISTRATE: CreateRegistrate = CreateRegistrate.create(ID)

    // the logger for our mod
    val LOGGER: Logger = LogManager.getLogger(ID)

    init {
        REGISTRATE.registerEventListeners(MOD_BUS)

        AllCreativeModeTabs
        AllItems
        AllBlocks

        MOD_BUS.addListener<GatherDataEvent> { CreateFusionDatagen.gatherData(it) }

//        val obj = runForDist(clientTarget = {
//            MOD_BUS.addListener(::onClientSetup)
//            Minecraft.getInstance()
//        }, serverTarget = {
//            MOD_BUS.addListener(::onServerSetup)
//            "test"
//        })
    }

    fun asResource(path: String): ResourceLocation {
        return ResourceLocation.fromNamespaceAndPath(ID, path)
    }

    /**
     * This is used for initializing client specific
     * things such as renderers and keymaps
     * Fired on the mod specific event bus.
     */
    private fun onClientSetup(event: FMLClientSetupEvent) {
        LOGGER.log(Level.INFO, "Initializing client...")
    }

    /**
     * Fired on the global Forge bus.
     */
    private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {
        LOGGER.log(Level.INFO, "Server starting...")
    }

    @SubscribeEvent
    fun onCommonSetup(event: FMLCommonSetupEvent) {
        LOGGER.log(Level.INFO, "Hello! This is working!")
    }
}
