package fr.eclipseria.wildernessboundbackport.block.custom;

import fr.eclipseria.wildernessboundbackport.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.NotNull;

public class ModWoodSet {

    protected String NAME;
    protected WoodType WOOD_TYPE;
    protected DeferredBlock<Block> LOG;
    protected DeferredBlock<Block> STRIPPED_LOG;
    protected DeferredBlock<Block> WOOD;
    protected DeferredBlock<Block> STRIPPED_WOOD;
    protected DeferredBlock<Block> PLANKS;
    protected DeferredBlock<StairBlock> STAIR;
    protected DeferredBlock<SlabBlock> SLAB;
    protected DeferredBlock<DoorBlock> DOOR;
    protected DeferredBlock<TrapDoorBlock> TRAPDOOR;
    protected DeferredBlock<ButtonBlock> BUTTON;
    protected DeferredBlock<PressurePlateBlock> PRESSURE_PLATE;
    protected DeferredBlock<FenceBlock> FENCE;
    protected DeferredBlock<FenceGateBlock> FENCE_GATE;

    public String getName(){
        return NAME;
    }

    public WoodType getWoodType() { return WOOD_TYPE; }

    public DeferredBlock<Block> getLog(){ return LOG; }

    public DeferredBlock<Block> getStrippedLog(){ return STRIPPED_LOG; }

    public DeferredBlock<Block> getWood(){ return WOOD; }

    public DeferredBlock<Block> getStrippedWood(){ return STRIPPED_WOOD; }

    public DeferredBlock<Block> getPlanks(){ return PLANKS; }

    public DeferredBlock<SlabBlock> getSlab(){ return SLAB; }

    public DeferredBlock<StairBlock> getStair() { return STAIR; }

    public DeferredBlock<DoorBlock> getDoor(){ return DOOR; }

    public DeferredBlock<TrapDoorBlock> getTrapdoor(){ return TRAPDOOR; }

    public DeferredBlock<ButtonBlock> getButton(){ return BUTTON; }

    public DeferredBlock<PressurePlateBlock> getPressurePlate(){ return PRESSURE_PLATE; }

    public DeferredBlock<FenceBlock> getFence(){ return FENCE; }

    public DeferredBlock<FenceGateBlock> getFenceGate(){ return FENCE_GATE; }

    public ModWoodSet(String name, WoodType woodType){
        this.NAME = name;

        this.WOOD_TYPE = woodType;

        this.LOG = ModBlocks.registerBlock(
                name + "_log",
                () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG))
        );

        this.STRIPPED_LOG = ModBlocks.registerBlock(
                "stripped_" + name + "_log",
                () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG))
        );

        this.WOOD = ModBlocks.registerBlock(
                name + "_wood",
                () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD))
        );

        this.STRIPPED_WOOD = ModBlocks.registerBlock(
                "stripped_" + name + "_wood",
                () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD))
        );

        this.PLANKS = ModBlocks.registerBlock(
                name + "_planks",
                () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS))
                {

                    @Override
                    public boolean isFlammable(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull Direction direction) {
                        return true;
                    }

                    @Override
                    public int getFlammability(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull Direction direction) {
                        return 60;
                    }

                    @Override
                    public int getFireSpreadSpeed(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull Direction direction) {
                        return 30;
                    }
                }
        );

        this.STAIR =  ModBlocks.registerBlock(
                name + "_stairs",
                () -> new StairBlock(
                        this.PLANKS.get().defaultBlockState(),
                        BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)
                )
        );

        this.SLAB = ModBlocks.registerBlock(
                name + "_slab",
                () -> new SlabBlock(
                        BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)
                )
        );

        this.DOOR = ModBlocks.registerBlock(
                name + "_door",
                () -> new DoorBlock(
                        this.WOOD_TYPE.setType(),
                        BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)
                )
        );

        this.TRAPDOOR = ModBlocks.registerBlock(
                name + "_trapdoor",
                () -> new TrapDoorBlock(
                        this.WOOD_TYPE.setType(),
                        BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)
                )
        );

        this.BUTTON = ModBlocks.registerBlock(
                name + "_button",
                () -> new ButtonBlock(
                        this.WOOD_TYPE.setType(),
                        20,
                        BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)
                )
        );

        this.PRESSURE_PLATE = ModBlocks.registerBlock(
                name + "_pressure_plate",
                () -> new PressurePlateBlock(
                        this.WOOD_TYPE.setType(),
                        BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)
                )
        );

        this.FENCE = ModBlocks.registerBlock(
                name + "_fence",
                () -> new FenceBlock(
                        BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)
                )
        );

        this.FENCE_GATE = ModBlocks.registerBlock(
                name + "_fence_gate",
                () -> new FenceGateBlock(
                        this.WOOD_TYPE,
                        BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)
                )
        );
    }

}
