package fr.eclipseria.wildernessboundbackport.datagen;

import fr.eclipseria.wildernessboundbackport.WildernessBoundBackport;
import fr.eclipseria.wildernessboundbackport.block.ModBlocks;
import fr.eclipseria.wildernessboundbackport.block.custom.SlabAndStairs;
import fr.eclipseria.wildernessboundbackport.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, WildernessBoundBackport.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {

        for (SlabAndStairs block : ModBlocks.WOOLSLABSANDSTAIRS){
            addMult(BlockTags.DAMPENS_VIBRATIONS,block.getSlab().get());
            addMult(BlockTags.DAMPENS_VIBRATIONS,block.getStair().get());
            addMult(BlockTags.SLABS,block.getSlab().get());
            addMult(BlockTags.STAIRS,block.getStair().get());
            addMult(BlockTags.WOOL,block.getSlab().get());
            addMult(BlockTags.WOOL,block.getStair().get());
            addMult(ModTags.Blocks.WOOL_SLABS,block.getSlab().get());
            addMult(ModTags.Blocks.WOOL_STAIRS,block.getStair().get());
        }

    }


    public void addMult(TagKey<Block> tag, Block block) {
        tag(tag).add(block);
    }

}
