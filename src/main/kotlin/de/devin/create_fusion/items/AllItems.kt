package de.devin.create_fusion.items

import com.tterrag.registrate.util.entry.ItemEntry
import de.devin.create_fusion.CreateFusion
import de.devin.create_fusion.tabs.AllCreativeModeTabs
import net.minecraft.world.item.Item
import net.minecraft.world.item.Rarity


object AllItems {

    init {
        CreateFusion.REGISTRATE.setCreativeTab(AllCreativeModeTabs.BASE_MOD_TAB)
    }

    val TUNGSTEN_INGOT: ItemEntry<Item> = CreateFusion.REGISTRATE.item<Item>(
        "tungsten_ingot", ::Item
    )
        .properties { it.rarity(Rarity.RARE) }
        .register()

    val REFINED_TUNGSTEN_STEEL_INGOT: ItemEntry<Item> = CreateFusion.REGISTRATE.item<Item>(
        "refined_tungsten_steel_ingot", ::Item
    )
        .properties { it.rarity(Rarity.EPIC) }
        .register()

}