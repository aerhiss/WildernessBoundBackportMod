package fr.eclipseria.wildernessboundbackport.item;

import fr.eclipseria.wildernessboundbackport.WildernessBoundBackport;
import fr.eclipseria.wildernessboundbackport.block.ModBlocks;
import fr.eclipseria.wildernessboundbackport.block.custom.SlabAndStairs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WildernessBoundBackport.MODID);

    public static final Supplier<CreativeModeTab> WOOLSTAIRSANDSLABS = CREATIVE_MODE_TAB.register(
            "wool_stairs_and_slabs_tab",
            () -> CreativeModeTab.builder().icon(ModBlocks.WHITE_WOOL.getStair()::toStack)
                    .title(Component.translatable("creativetab.wildernessboundbackport.wool_stairs_and_slabs"))
                    .displayItems((itemDisplayParameters, output) -> {
                        for (SlabAndStairs pair : ModBlocks.WOOLSLABSANDSTAIRS) {
                            output.accept(pair.getStair().get());
                            output.accept(pair.getSlab().get());
                        }
                    })
                    .build()
    );

    public static final Supplier<CreativeModeTab> CONCRETESTAIRSANDSLABS = CREATIVE_MODE_TAB.register(
            "concrete_stairs_and_slabs_tab",
            () -> CreativeModeTab.builder().icon(ModBlocks.WHITE_CONCRETE.getStair()::toStack)
                    .title(Component.translatable("creativetab.wildernessboundbackport.concrete_stairs_and_slabs"))
                    .displayItems((itemDisplayParameters, output) -> {
                        for (SlabAndStairs pair : ModBlocks.CONCRETESLABSANDSTAIRS) {
                            output.accept(pair.getStair().get());
                            output.accept(pair.getSlab().get());
                        }
                    })
                    .build()
    );

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
