package fr.eclipseria.wildernessboundbackport.block.register;

import fr.eclipseria.wildernessboundbackport.block.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredBlock;
import java.util.function.BiFunction;
import java.util.function.Function;

public class SlabAndStairs {

    protected String NAME;
    protected Block BLOCK_TYPE;
    protected DeferredBlock<? extends SlabBlock> SLAB;
    protected DeferredBlock<? extends StairBlock> STAIR;

    public String getName(){
        return NAME;
    }

    public Block getBlockType(){
        return BLOCK_TYPE;
    }

    public DeferredBlock<? extends SlabBlock> getSlab(){ return SLAB; }

    public DeferredBlock<? extends StairBlock> getStair() { return STAIR; }

    public SlabAndStairs(String name, Block blockType){
        this(name,blockType, SlabBlock::new, StairBlock::new, BlockBehaviour.Properties.ofFullCopy(blockType));
    }

    public <S extends SlabBlock, T extends StairBlock> SlabAndStairs(
            String name,
            Block blockType,
            Function<BlockBehaviour.Properties, S> slabFactory,
            BiFunction<BlockState, BlockBehaviour.Properties, T> stairFactory,
            BlockBehaviour.Properties baseProperties){

        this.NAME = name;
        this.BLOCK_TYPE = blockType;

        this.SLAB = ModBlocks.registerBlock(
                name + "_slab",
                () -> slabFactory.apply(baseProperties)
        );

        this.STAIR = ModBlocks.registerBlock(
                name + "_stairs",
                () -> stairFactory.apply(
                        this.BLOCK_TYPE.defaultBlockState(),
                        baseProperties)

        );
    }

}
