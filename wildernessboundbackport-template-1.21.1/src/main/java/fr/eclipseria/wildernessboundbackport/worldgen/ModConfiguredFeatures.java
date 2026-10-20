package fr.eclipseria.wildernessboundbackport.worldgen;

import fr.eclipseria.wildernessboundbackport.WildernessBoundBackport;
import fr.eclipseria.wildernessboundbackport.block.ModBlocks;
import fr.eclipseria.wildernessboundbackport.worldgen.tree.PoplarFoliagePlacer;
import fr.eclipseria.wildernessboundbackport.worldgen.tree.PoplarTrunkPlacer;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> POPLAR_KEY = registerKey("poplar");

    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_POPLAR_KEY = registerKey("red_poplar");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_POPLAR_KEY = registerKey("orange_poplar");
    public static final ResourceKey<ConfiguredFeature<?, ?>> YELLOW_POPLAR_KEY = registerKey("yellow_poplar");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        // Enregistrement des 3 arbres individuels
        register(context, RED_POPLAR_KEY, Feature.TREE, createPoplarConfig(ModBlocks.RED_POPLAR_LEAVES.get()));
        register(context, ORANGE_POPLAR_KEY, Feature.TREE, createPoplarConfig(ModBlocks.ORANGE_POPLAR_LEAVES.get()));
        register(context, YELLOW_POPLAR_KEY, Feature.TREE, createPoplarConfig(ModBlocks.YELLOW_POPLAR_LEAVES.get()));

        // Enregistrement du sélecteur aléatoire pour le Sapling
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        register(context, POPLAR_KEY, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(
                        new WeightedPlacedFeature(placedFeatures.getOrThrow(ModPlacedFeatures.RED_POPLAR_CHECKED), 0.33333334F),
                        new WeightedPlacedFeature(placedFeatures.getOrThrow(ModPlacedFeatures.ORANGE_POPLAR_CHECKED), 0.33333334F)
                ),
                placedFeatures.getOrThrow(ModPlacedFeatures.YELLOW_POPLAR_CHECKED)
        ));
    }

    private static TreeConfiguration createPoplarConfig(Block leavesBlock) {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.POPLAR.getLog().get()),
                new PoplarTrunkPlacer(7, 1, 0, ConstantInt.of(2), ConstantInt.of(2)),
                BlockStateProvider.simple(leavesBlock),
                new PoplarFoliagePlacer(
                        new WeightedListInt(
                                SimpleWeightedRandomList.<IntProvider>builder()
                                        .add(ConstantInt.of(5), 5)
                                        .add(ConstantInt.of(6), 5)
                                        .add(ConstantInt.of(7), 1)
                                        .add(ConstantInt.of(8), 1)
                                        .build()
                        ),
                        ConstantInt.of(0),
                        UniformInt.of(5, 6),
                        0.15F
                ),
                new TwoLayersFeatureSize(1, 0, 2)
        ).build();
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(WildernessBoundBackport.MODID, name));
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
            BootstrapContext<ConfiguredFeature<?, ?>> context,
            ResourceKey<ConfiguredFeature<?, ?>> key,
            F feature,
            FC configuration
    ) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}