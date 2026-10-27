package fr.eclipseria.wildernessboundbackport.datagen;

import fr.eclipseria.wildernessboundbackport.WildernessBoundBackport;
import fr.eclipseria.wildernessboundbackport.block.ModBlocks;
import fr.eclipseria.wildernessboundbackport.block.register.ModWoodSet;
import fr.eclipseria.wildernessboundbackport.block.register.SlabAndStairs;
import fr.eclipseria.wildernessboundbackport.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
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
            Block slab = block.getSlab().get();
            Block stair = block.getStair().get();

            //VANILLA

            tag(BlockTags.DAMPENS_VIBRATIONS).add(slab, stair);
            tag(BlockTags.WOOL).add(slab, stair);

            tag(BlockTags.SLABS).add(slab);

            tag(BlockTags.STAIRS).add(stair);

            //CUSTOM

            tag(ModTags.Blocks.WOOL_SLABS).add(slab);

            tag(ModTags.Blocks.WOOL_STAIRS).add(stair);
        }

        for (SlabAndStairs block : ModBlocks.CONCRETESLABSANDSTAIRS){
                Block slab = block.getSlab().get();
                Block stair = block.getStair().get();

            //VANILLA

            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(slab, stair);

            tag(BlockTags.SLABS).add(slab);

            tag(BlockTags.STAIRS).add(stair);

            //CUSTOM

            tag(ModTags.Blocks.CONCRETE).add(slab, stair);

            tag(ModTags.Blocks.CONCRETE_SLABS).add(slab);

            tag(ModTags.Blocks.CONCRETE_STAIRS).add(stair);
        }

        for (ModWoodSet set : ModBlocks.WOODSET){
            Block log = set.getLog().get();
            Block strippedLog = set.getStrippedLog().get();
            Block wood = set.getWood().get();
            Block strippedWood = set.getStrippedWood().get();
            Block planks = set.getPlanks().get();
            Block stair = set.getStair().get();
            Block slab = set.getSlab().get();
            Block door = set.getDoor().get();
            Block trapdoor = set.getTrapdoor().get();
            Block button = set.getButton().get();
            Block pressurePlate = set.getPressurePlate().get();
            Block fence = set.getFence().get();
            Block fenceGate = set.getFenceGate().get();

            //VANILLA

            tag(BlockTags.LOGS).add(log, strippedLog, wood, strippedWood);
            tag(BlockTags.LOGS_THAT_BURN).add(log, strippedLog, wood, strippedWood);
            tag(BlockTags.MINEABLE_WITH_AXE).add(log, strippedLog, wood, strippedWood, planks, stair, slab);

            tag(BlockTags.PLANKS).add(planks);

            tag(BlockTags.STAIRS).add(stair);
            tag(BlockTags.WOODEN_STAIRS).add(stair);

            tag(BlockTags.SLABS).add(slab);
            tag(BlockTags.WOODEN_SLABS).add(slab);

            tag(BlockTags.DOORS).add(door);
            tag(BlockTags.WOODEN_DOORS).add(door);
            tag(BlockTags.MOB_INTERACTABLE_DOORS).add(door);

            tag(BlockTags.TRAPDOORS).add(trapdoor);
            tag(BlockTags.WOODEN_TRAPDOORS).add(trapdoor);

            tag(BlockTags.BUTTONS).add(button);
            tag(BlockTags.WOODEN_BUTTONS).add(button);

            tag(BlockTags.PRESSURE_PLATES).add(pressurePlate);
            tag(BlockTags.WOODEN_PRESSURE_PLATES).add(pressurePlate);
            tag(BlockTags.WALL_POST_OVERRIDE).add(pressurePlate);

            tag(BlockTags.FENCES).add(fence);
            tag(BlockTags.WOODEN_FENCES).add(fence);

            tag(BlockTags.FENCE_GATES).add(fenceGate);
            tag(BlockTags.UNSTABLE_BOTTOM_CENTER).add(fenceGate);


            //CUSTOM

            tag(ModTags.Blocks.POPLAR_LOGS).add(log, strippedLog, wood, strippedWood);

        }

    }

}
