package fr.eclipseria.wildernessboundbackport.worldgen.tree;

import fr.eclipseria.wildernessboundbackport.WildernessBoundBackport;
import fr.eclipseria.wildernessboundbackport.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower POPLAR = new TreeGrower(WildernessBoundBackport.MODID + ":poplar",
            Optional.empty(),Optional.of(ModConfiguredFeatures.POPLAR_KEY),Optional.empty());
}
