package fr.eclipseria.wildernessboundbackport.worldgen;

import fr.eclipseria.wildernessboundbackport.WildernessBoundBackport;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModBiomes {

    public static final ResourceKey<Biome> DAPPLED_FOREST_KEY = registerKey("dappled_forest");

    public static void bootstrap(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> worldCarvers = context.lookup(Registries.CONFIGURED_CARVER);

        context.register(DAPPLED_FOREST_KEY, dappledForest(placedFeatures, worldCarvers));
    }

    private static Biome dappledForest(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        // 1. Spawns d'entités
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        // 2. Génération du terrain et features
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);

        // Carvers (grottes et canyons)
        BiomeDefaultFeatures.addDefaultCarversAndLakes(genBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(genBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(genBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(genBuilder);
        BiomeDefaultFeatures.addDefaultSprings(genBuilder);
        BiomeDefaultFeatures.addSurfaceFreezing(genBuilder);
        BiomeDefaultFeatures.addDefaultOres(genBuilder);

        // Végétation par défaut
        BiomeDefaultFeatures.addDefaultMushrooms(genBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(genBuilder);

        // Arbres de peuplier (utiliser les PLACED_KEY avec résolution via le lookup)
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                placedFeatures.getOrThrow(ModPlacedFeatures.ORANGE_POPLAR_PLACED));
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                placedFeatures.getOrThrow(ModPlacedFeatures.RED_POPLAR_PLACED));
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                placedFeatures.getOrThrow(ModPlacedFeatures.YELLOW_POPLAR_PLACED));

        // 3. Ambiance visuelle et sonore
        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
                .grassColorOverride(0xDF6827)          // Grass color (#df6827)
                .foliageColorOverride(0xE68E30)        // Foliage color (#e68e30)
                .waterColor(0x375154)                  // Water color (#375154)
                .waterFogColor(0x1E97F2)               // Water fog color (#1E97F2)
                .skyColor(0x78A7FF)                    // Ciel
                .fogColor(0xC0D8FF)                    // Brouillard
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FOREST))
                .build();

        // 4. Climat & Assemblage final
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.7F)
                .downfall(0.8F)
                .specialEffects(effects)
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }

    public static ResourceKey<Biome> registerKey(String name) {
        return ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(WildernessBoundBackport.MODID, name));
    }
}