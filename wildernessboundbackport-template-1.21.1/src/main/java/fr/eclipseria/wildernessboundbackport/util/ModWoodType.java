package fr.eclipseria.wildernessboundbackport.util;

import fr.eclipseria.wildernessboundbackport.WildernessBoundBackport;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodType {

    public static final WoodType POPLAR = WoodType.register(
            new WoodType(
                    WildernessBoundBackport.MODID + ":poplar",
                    BlockSetType.register( new BlockSetType("poplar"))
            )
    );

}
