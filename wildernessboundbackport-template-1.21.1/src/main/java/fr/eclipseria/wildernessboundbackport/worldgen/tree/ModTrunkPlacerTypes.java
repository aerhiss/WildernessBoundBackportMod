package fr.eclipseria.wildernessboundbackport.worldgen.tree;

import fr.eclipseria.wildernessboundbackport.WildernessBoundBackport;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModTrunkPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, WildernessBoundBackport.MODID);

    public static final Supplier<TrunkPlacerType<PoplarTrunkPlacer>> POPLAR_TRUNK_PLACER =
            TRUNK_PLACERS.register("poplar_trunk_placer", () -> new TrunkPlacerType<>(PoplarTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus) {
        TRUNK_PLACERS.register(eventBus);
    }
}
