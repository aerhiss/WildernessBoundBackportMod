package fr.eclipseria.wildernessboundbackport.datagen;

import fr.eclipseria.wildernessboundbackport.block.ModBlocks;
import fr.eclipseria.wildernessboundbackport.block.custom.ModWoodSet;
import fr.eclipseria.wildernessboundbackport.block.custom.SlabAndStairs;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {

        for (SlabAndStairs block : ModBlocks.ALLSLABANDSTAIRS){
            dropSelf(block.getSlab().get());
            dropSelf(block.getStair().get());
        }

        for (ModWoodSet set : ModBlocks.WOODSET){
            dropSelf(set.getLog().get());
            dropSelf(set.getStrippedLog().get());
            dropSelf(set.getWood().get());
            dropSelf(set.getStrippedWood().get());
            dropSelf(set.getPlanks().get());
            dropSelf(set.getStair().get());
            dropSelf(set.getSlab().get());
            dropSelf(set.getDoor().get());
            dropSelf(set.getTrapdoor().get());
            dropSelf(set.getButton().get());
            dropSelf(set.getPressurePlate().get());
            dropSelf(set.getFence().get());
            dropSelf(set.getFenceGate().get());
        }

        dropSelf(ModBlocks.POPLAR_SAPLING.get());

        add(ModBlocks.RED_POPLAR_LEAVES.get(), block ->
                createLeavesDrops(
                        block,
                        ModBlocks.POPLAR_SAPLING.get(),
                        NORMAL_LEAVES_SAPLING_CHANCES
                )
        );
        add(ModBlocks.ORANGE_POPLAR_LEAVES.get(), block ->
                createLeavesDrops(
                        block,
                        ModBlocks.POPLAR_SAPLING.get(),
                        NORMAL_LEAVES_SAPLING_CHANCES
                )
        );
        add(ModBlocks.YELLOW_POPLAR_LEAVES.get(), block ->
                createLeavesDrops(
                        block,
                        ModBlocks.POPLAR_SAPLING.get(),
                        NORMAL_LEAVES_SAPLING_CHANCES
                )
        );
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
