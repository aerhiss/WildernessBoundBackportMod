package fr.eclipseria.wildernessboundbackport.block;

import fr.eclipseria.wildernessboundbackport.WildernessBoundBackport;
import fr.eclipseria.wildernessboundbackport.block.custom.SlabAndStairs;
import fr.eclipseria.wildernessboundbackport.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(WildernessBoundBackport.MODID);


    public static final List<SlabAndStairs> WOOLSLABSANDSTAIRS = new ArrayList<>();

    public static final SlabAndStairs WHITE_WOOL = registerWoolSlabAndStair("white_wool", Blocks.WHITE_WOOL);
    public static final SlabAndStairs LIGHT_GRAY_WOOL = registerWoolSlabAndStair("light_gray_wool", Blocks.LIGHT_GRAY_WOOL);
    public static final SlabAndStairs GRAY_WOOL = registerWoolSlabAndStair("gray_wool", Blocks.GRAY_WOOL);
    public static final SlabAndStairs BLACK_WOOL = registerWoolSlabAndStair("black_wool", Blocks.BLACK_WOOL);
    public static final SlabAndStairs BROWN_WOOL = registerWoolSlabAndStair("brown_wool", Blocks.BROWN_WOOL);
    public static final SlabAndStairs RED_WOOL = registerWoolSlabAndStair("red_wool", Blocks.RED_WOOL);
    public static final SlabAndStairs ORANGE_WOOL = registerWoolSlabAndStair("orange_wool", Blocks.ORANGE_WOOL);
    public static final SlabAndStairs YELLOW_WOOL = registerWoolSlabAndStair("yellow_wool", Blocks.YELLOW_WOOL);
    public static final SlabAndStairs LIME_WOOL = registerWoolSlabAndStair("lime_wool", Blocks.LIME_WOOL);
    public static final SlabAndStairs GREEN_WOOL = registerWoolSlabAndStair("green_wool", Blocks.GREEN_WOOL);
    public static final SlabAndStairs CYAN_WOOL = registerWoolSlabAndStair("cyan_wool", Blocks.CYAN_WOOL);
    public static final SlabAndStairs LIGHT_BLUE_WOOL = registerWoolSlabAndStair("light_blue_wool", Blocks.LIGHT_BLUE_WOOL);
    public static final SlabAndStairs BLUE_WOOL = registerWoolSlabAndStair("blue_wool", Blocks.BLUE_WOOL);
    public static final SlabAndStairs PURPLE_WOOL = registerWoolSlabAndStair("purple_wool", Blocks.PURPLE_WOOL);
    public static final SlabAndStairs MAGENTA_WOOL = registerWoolSlabAndStair("magenta_wool", Blocks.MAGENTA_WOOL);
    public static final SlabAndStairs PINK_WOOL = registerWoolSlabAndStair("pink_wool", Blocks.PINK_WOOL);

    public static SlabAndStairs registerWoolSlabAndStair(String name, Block blockType) {
        SlabAndStairs reg = new SlabAndStairs(name, blockType);
        WOOLSLABSANDSTAIRS.add(reg);
        return reg;
    }

    public static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void  registerBlockItem(String name, DeferredBlock<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
