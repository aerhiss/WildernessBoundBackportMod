package fr.eclipseria.wildernessboundbackport.util;

import fr.eclipseria.wildernessboundbackport.WildernessBoundBackport;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {

        public static final TagKey<Block> WOOL_SLABS = createTag("wool_slabs");

        public static final TagKey<Block> WOOL_STAIRS = createTag("wool_stairs");

        public static final TagKey<Block> CONCRETE_SLABS = createTag("concrete_slabs");

        public static final TagKey<Block> CONCRETE_STAIRS = createTag("concrete_stairs");

        public static final TagKey<Block> CONCRETE = createTag("concrete");

        public static final TagKey<Block> POPLAR = createTag("poplar");


        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(WildernessBoundBackport.MODID,name));
        }
    }

    public static class Items {

        public static final TagKey<Item> WOOL_SLABS = createTag("wool_slabs");

        public static final TagKey<Item> WOOL_STAIRS = createTag("wool_stairs");

        public static final TagKey<Item> CONCRETE_SLABS = createTag("concrete_slabs");

        public static final TagKey<Item> CONCRETE_STAIRS = createTag("concrete_stairs");

        public static final TagKey<Item> CONCRETE = createTag("concrete");

        public static final TagKey<Item> POPLAR = createTag("poplar");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(WildernessBoundBackport.MODID,name));
        }

    }
}
