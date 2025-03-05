package de.devin.create_fusion.blocks

import com.simibubi.create.content.decoration.encasing.CasingBlock
import com.simibubi.create.foundation.data.BuilderTransformers
import com.tterrag.registrate.util.entry.BlockEntry
import de.devin.create_fusion.CreateFusion
import de.devin.create_fusion.spriteshifts.AllSpriteShifts
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.MapColor


// THIS LINE IS REQUIRED FOR USING PROPERTY DELEGATES

object AllBlocks {



    val TUNGSTEN_CASING: BlockEntry<CasingBlock> = CreateFusion.REGISTRATE.block<CasingBlock>(
        "tungsten_casing", ::CasingBlock
    )
        .properties { p: BlockBehaviour.Properties -> p.mapColor(MapColor.PODZOL) }
        .transform(BuilderTransformers.casing { AllSpriteShifts.TUNGSTEN_CASING })
        .register()

}
