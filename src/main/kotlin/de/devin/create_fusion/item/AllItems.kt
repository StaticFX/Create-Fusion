package de.devin.create_fusion.item

import com.simibubi.create.AllCreativeModeTabs
import com.tterrag.registrate.util.entry.ItemEntry
import de.devin.create_fusion.CreateFusion
import net.minecraft.world.item.Item


object AllItems {

    init {
        CreateFusion.REGISTRATE.setCreativeTab(AllCreativeModeTabs.BASE_CREATIVE_TAB)
    }

    val CARDBOARD: ItemEntry<Item> = CreateFusion.REGISTRATE.item<Item>(
        "hurensohn", ::Item
    )
        .properties { it.stacksTo(16) }
        .register()

}