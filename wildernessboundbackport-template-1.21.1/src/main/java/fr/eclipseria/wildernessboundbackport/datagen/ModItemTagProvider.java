package fr.eclipseria.wildernessboundbackport.datagen;

import fr.eclipseria.wildernessboundbackport.WildernessBoundBackport;
import fr.eclipseria.wildernessboundbackport.block.ModBlocks;
import fr.eclipseria.wildernessboundbackport.block.custom.SlabAndStairs;
import fr.eclipseria.wildernessboundbackport.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, WildernessBoundBackport.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {


        for (SlabAndStairs block : ModBlocks.WOOLSLABSANDSTAIRS){
            addMult(ItemTags.DAMPENS_VIBRATIONS,block.getSlab().get().asItem());
            addMult(ItemTags.DAMPENS_VIBRATIONS,block.getStair().get().asItem());
            addMult(ItemTags.SLABS,block.getSlab().get().asItem());
            addMult(ItemTags.STAIRS,block.getStair().get().asItem());
            addMult(ItemTags.WOOL,block.getSlab().get().asItem());
            addMult(ItemTags.WOOL,block.getStair().get().asItem());
            addMult(ModTags.Items.WOOL_SLABS,block.getSlab().get().asItem());
            addMult(ModTags.Items.WOOL_STAIRS,block.getStair().get().asItem());
        }

        for (SlabAndStairs block : ModBlocks.CONCRETESLABSANDSTAIRS){
            addMult(ItemTags.SLABS,block.getSlab().get().asItem());
            addMult(ItemTags.STAIRS,block.getStair().get().asItem());
            addMult(ModTags.Items.CONCRETE,block.getSlab().get().asItem());
            addMult(ModTags.Items.CONCRETE,block.getStair().get().asItem());
            addMult(ModTags.Items.CONCRETE_SLABS,block.getSlab().get().asItem());
            addMult(ModTags.Items.CONCRETE_STAIRS,block.getStair().get().asItem());
        }

    }

    public void addMult(TagKey<Item> tag, Item item) {
        tag(tag).add(item);
    }
}
