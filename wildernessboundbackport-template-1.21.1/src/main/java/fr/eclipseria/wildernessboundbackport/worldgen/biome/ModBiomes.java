package fr.eclipseria.wildernessboundbackport.worldgen.biome;

import fr.eclipseria.wildernessboundbackport.WildernessBoundBackport;
import fr.eclipseria.wildernessboundbackport.worldgen.ModPlacedFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModBiomes {

    public static final ResourceKey<Biome> DAPPLED_FOREST = registerKey("dappled_forest");

    public static void bootstrap(BootstrapContext<Biome> context) {

        context.register(DAPPLED_FOREST, dappledForest(context));
    }

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder){
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addDefaultOres(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    public static Biome dappledForest(BootstrapContext<Biome> context) {
        // 1. Spawns d'entités
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        //Créature
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SHEEP,12,4,4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN,10,4,4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PIG,10,4,4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.COW,8,4,4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX,8,2,4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT,4,2,4));

        //Créature sous marine
        spawnBuilder.addSpawn(MobCategory.UNDERGROUND_WATER_CREATURE, new MobSpawnSettings.SpawnerData(EntityType.GLOW_SQUID,1,4,6));

        //Créaure Ambiente
        spawnBuilder.addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.BAT,1,8,8));

        // 2. Génération du terrain et features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        // Végétation par défaut
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        // Arbres de peuplier (utiliser les PLACED_KEY avec résolution via le lookup)
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.ORANGE_POPLAR_PLACED);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.RED_POPLAR_PLACED);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.YELLOW_POPLAR_PLACED);

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
                .generationSettings(biomeBuilder.build())
                .build();
    }

    public static ResourceKey<Biome> registerKey(String name) {
        return ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(WildernessBoundBackport.MODID, name));
    }
}