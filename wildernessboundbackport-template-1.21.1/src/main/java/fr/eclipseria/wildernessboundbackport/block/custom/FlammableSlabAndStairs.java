package fr.eclipseria.wildernessboundbackport.block.custom;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class FlammableSlabAndStairs extends SlabAndStairs{

    public FlammableSlabAndStairs(String name, Block blockType) {
        super(
                name,
                blockType,
                ModFlammableSlabs::new,
                ModFlammableStairs::new,
                BlockBehaviour.Properties.ofFullCopy(blockType).ignitedByLava()
        );
    }



}
