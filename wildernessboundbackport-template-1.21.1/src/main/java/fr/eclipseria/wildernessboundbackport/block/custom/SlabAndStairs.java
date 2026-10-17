package fr.eclipseria.wildernessboundbackport.block.custom;

import fr.eclipseria.wildernessboundbackport.block.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

public class SlabAndStairs {

    protected final String NAME;
    protected final Block BLOCK_TYPE;
    protected final DeferredBlock<SlabBlock> SLAB;
    protected final DeferredBlock<StairBlock> STAIR;

    public String getName(){
        return NAME;
    }

    public Block getBlockType(){
        return BLOCK_TYPE;
    }

    public DeferredBlock<SlabBlock> getSlab(){
        return SLAB;
    }

    public DeferredBlock<StairBlock> getStair() {
        return STAIR;
    }

    public SlabAndStairs(String name, Block blockType){
        this.NAME = name;
        this.BLOCK_TYPE = blockType;
        this.SLAB = ModBlocks.registerBlock(
                name + "_slab",
                () -> new SlabBlock(
                        BlockBehaviour.Properties.ofFullCopy(this.BLOCK_TYPE)
                )
        );
        this.STAIR = ModBlocks.registerBlock(
                name + "_stairs",
                () -> new StairBlock(
                        this.BLOCK_TYPE.defaultBlockState(),
                        BlockBehaviour.Properties.ofFullCopy(this.BLOCK_TYPE)
                )
        );
    }

}
