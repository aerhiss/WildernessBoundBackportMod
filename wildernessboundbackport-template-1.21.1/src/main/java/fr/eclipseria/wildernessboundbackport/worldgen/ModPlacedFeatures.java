package fr.eclipseria.wildernessboundbackport.worldgen;

import fr.eclipseria.wildernessboundbackport.WildernessBoundBackport;
import fr.eclipseria.wildernessboundbackport.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {

    // Clés checked pour la pousse du sapling
    public static final ResourceKey<PlacedFeature> RED_POPLAR_CHECKED = registerKey("red_poplar_checked");
    public static final ResourceKey<PlacedFeature> ORANGE_POPLAR_CHECKED = registerKey("orange_poplar_checked");
    public static final ResourceKey<PlacedFeature> YELLOW_POPLAR_CHECKED = registerKey("yellow_poplar_checked");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(
                context,
                RED_POPLAR_CHECKED,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.RED_POPLAR_KEY),
                List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.POPLAR_SAPLING.get()))
        );

        register(
                context,
                ORANGE_POPLAR_CHECKED,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.ORANGE_POPLAR_KEY),
                List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.POPLAR_SAPLING.get()))
        );

        register(
                context,
                YELLOW_POPLAR_CHECKED,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.YELLOW_POPLAR_KEY),
                List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.POPLAR_SAPLING.get()))
        );
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(WildernessBoundBackport.MODID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}