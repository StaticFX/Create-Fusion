package de.devin.create_fusion.tabs


import com.tterrag.registrate.util.entry.RegistryEntry
import de.devin.create_fusion.CreateFusion
import de.devin.create_fusion.items.AllItems
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab

object AllCreativeModeTabs {

    val BASE_MOD_TAB: RegistryEntry<CreativeModeTab, CreativeModeTab> = CreateFusion.REGISTRATE.defaultCreativeTab("base_creative_tab") {
        CreativeModeTab
            .builder()
            .icon { AllItems.TUNGSTEN_INGOT.asStack() }
            .title(Component.translatable("itemGroup.create_fusion.base_creative_tab"))
            .build()
    }.register()


}