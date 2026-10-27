package fr.eclipseria.wildernessboundbackport.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;

import java.util.function.Consumer;

public class ModOverworldRegion extends Region {

    public ModOverworldRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper,modifiedVanillaOverworldBuilder -> {
            //modifiedVanillaOverworldBuilder.replaceBiome(Biomes.FOREST,ModBiomes.DAPPLED_FOREST);

            VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();
            // Overlap Vanilla's parameters with our own for our DAPPLED_FOREST biome.
            // The parameters for this biome are chosen arbitrarily.
            new ParameterUtils.ParameterPointListBuilder()
                    .temperature(ParameterUtils.Temperature.span(ParameterUtils.Temperature.COOL, ParameterUtils.Temperature.NEUTRAL))
                    .humidity(ParameterUtils.Humidity.span(ParameterUtils.Humidity.NEUTRAL, ParameterUtils.Humidity.HUMID))
                    .continentalness(ParameterUtils.Continentalness.span(ParameterUtils.Continentalness.NEAR_INLAND,ParameterUtils.Continentalness.FAR_INLAND))
                    .erosion(ParameterUtils.Erosion.EROSION_0, ParameterUtils.Erosion.EROSION_4)
                    .depth(ParameterUtils.Depth.SURFACE)
                    .weirdness(ParameterUtils.Weirdness.MID_SLICE_NORMAL_ASCENDING, ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING)
                    .build().forEach(point -> builder.add(point, ModBiomes.DAPPLED_FOREST));

            // Add our points to the mapper
            builder.build().forEach(mapper);
        });
    }
}
