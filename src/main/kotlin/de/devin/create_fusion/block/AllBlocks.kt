package de.devin.create_fusion.block

import de.devin.create_fusion.CreateFusion
import net.minecraft.world.level.block.Blocks

// THIS LINE IS REQUIRED FOR USING PROPERTY DELEGATES

object AllBlocks {

    val MYBLOCK = CreateFusion.REGISTRATE.block<MyBlock>("myblock", ::MyBlock)
        .initialProperties { Blocks.STONE }
        .simpleItem()
        .register()

}
