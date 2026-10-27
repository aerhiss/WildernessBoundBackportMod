package fr.eclipseria.wildernessboundbackport.datagen;

import fr.eclipseria.wildernessboundbackport.WildernessBoundBackport;
import fr.eclipseria.wildernessboundbackport.block.ModBlocks;
import fr.eclipseria.wildernessboundbackport.block.register.ModWoodSet;
import fr.eclipseria.wildernessboundbackport.block.register.SlabAndStairs;
import fr.eclipseria.wildernessboundbackport.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
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
            Item slab = block.getSlab().get().asItem();
            Item stair = block.getStair().get().asItem();

            //VANILLA

            tag(ItemTags.DAMPENS_VIBRATIONS).add(slab, stair);
            tag(ItemTags.WOOL).add(slab, stair);

            tag(ItemTags.SLABS).add(slab);

            tag(ItemTags.STAIRS).add(stair);

            //CUSTOM

            tag(ModTags.Items.WOOL_SLABS).add(slab);

            tag(ModTags.Items.WOOL_STAIRS).add(stair);
        }

        for (SlabAndStairs block : ModBlocks.CONCRETESLABSANDSTAIRS){
            Item slab = block.getSlab().get().asItem();
            Item stair = block.getStair().get().asItem();

            //VANILLA

            tag(ItemTags.SLABS).add(slab);

            tag(ItemTags.STAIRS).add(stair);

            //CUSTOM

            tag(ModTags.Items.CONCRETE).add(slab, stair);

            tag(ModTags.Items.CONCRETE_SLABS).add(slab);
            tag(ModTags.Items.CONCRETE_STAIRS).add(stair);
        }

        for (ModWoodSet set : ModBlocks.WOODSET) {
            Item log = set.getLog().get().asItem();
            Item strippedLog = set.getStrippedLog().get().asItem();
            Item wood = set.getWood().get().asItem();
            Item strippedWood = set.getStrippedWood().get().asItem();
            Item planks = set.getPlanks().get().asItem();
            Item stair = set.getStair().get().asItem();
            Item slab = set.getSlab().get().asItem();
            Item door = set.getDoor().get().asItem();
            Item trapdoor = set.getTrapdoor().get().asItem();
            Item button = set.getButton().get().asItem();
            Item pressurePlate = set.getPressurePlate().get().asItem();
            Item fence = set.getFence().get().asItem();

            //VANILLA

            tag(ItemTags.LOGS).add(log,strippedLog,wood,strippedWood);
            tag(ItemTags.LOGS_THAT_BURN).add(log,strippedLog,wood,strippedWood);

            tag(ItemTags.PLANKS).add(planks);

            tag(ItemTags.STAIRS).add(stair);
            tag(ItemTags.WOODEN_STAIRS).add(stair);

            tag(ItemTags.SLABS).add(slab);
            tag(ItemTags.WOODEN_SLABS).add(slab);

            tag(ItemTags.DOORS).add(door);
            tag(ItemTags.WOODEN_DOORS).add(door);

            tag(ItemTags.TRAPDOORS).add(trapdoor);
            tag(ItemTags.WOODEN_TRAPDOORS).add(trapdoor);

            tag(ItemTags.BUTTONS).add(button);
            tag(ItemTags.WOODEN_BUTTONS).add(button);

            tag(ItemTags.WOODEN_PRESSURE_PLATES).add(pressurePlate);

            tag(ItemTags.FENCES).add(fence);
            tag(ItemTags.WOODEN_FENCES).add(fence);

            //CUSTOM

            tag(ModTags.Items.POPLAR_LOGS).add(log,strippedLog,wood,strippedWood);
        }

    }
}
