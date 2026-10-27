package fr.eclipseria.wildernessboundbackport.worldgen.biome;

import fr.eclipseria.wildernessboundbackport.WildernessBoundBackport;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerraBlender {

    public static void registerBiomes(){
        Regions.register(new ModOverworldRegion(ResourceLocation.fromNamespaceAndPath(WildernessBoundBackport.MODID,"overworld"), 15));
    }
}
