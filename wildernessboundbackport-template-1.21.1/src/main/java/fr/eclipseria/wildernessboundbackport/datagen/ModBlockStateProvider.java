package fr.eclipseria.wildernessboundbackport.datagen;

import fr.eclipseria.wildernessboundbackport.WildernessBoundBackport;
import fr.eclipseria.wildernessboundbackport.block.ModBlocks;
import fr.eclipseria.wildernessboundbackport.block.custom.ModLeavesBlock;
import fr.eclipseria.wildernessboundbackport.block.register.SlabAndStairs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {


    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, WildernessBoundBackport.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (SlabAndStairs block : ModBlocks.ALLSLABANDSTAIRS){
            slabBlock(block.getSlab().get(), blockTexture(block.getBlockType()), blockTexture(block.getBlockType()));
            blockItem(block.getSlab());
            stairsBlock(block.getStair().get(), blockTexture(block.getBlockType()));
            blockItem(block.getStair());
        }

            logBlock(((RotatedPillarBlock) ModBlocks.POPLAR.getLog().get()));
            axisBlock(((RotatedPillarBlock) ModBlocks.POPLAR.getWood().get()), blockTexture(ModBlocks.POPLAR.getLog().get()), blockTexture(ModBlocks.POPLAR.getLog().get()));

            logBlock(((RotatedPillarBlock) ModBlocks.POPLAR.getStrippedLog().get()));
            axisBlock(((RotatedPillarBlock) ModBlocks.POPLAR.getStrippedWood().get()), blockTexture(ModBlocks.POPLAR.getStrippedLog().get()), blockTexture(ModBlocks.POPLAR.getStrippedLog().get()));

            blockItem(ModBlocks.POPLAR.getLog());
            blockItem(ModBlocks.POPLAR.getStrippedLog());
            blockItem(ModBlocks.POPLAR.getWood());
            blockItem(ModBlocks.POPLAR.getStrippedWood());

            blockWithItem(ModBlocks.POPLAR.getPlanks());

            blockItem(ModBlocks.POPLAR.getSlab());
            blockItem(ModBlocks.POPLAR.getStair());
            blockItem(ModBlocks.POPLAR.getPressurePlate());
            blockItem(ModBlocks.POPLAR.getTrapdoor(), "_bottom");
            blockItem(ModBlocks.POPLAR.getFenceGate());

            stairsBlock(ModBlocks.POPLAR.getStair().get(), blockTexture(ModBlocks.POPLAR.getPlanks().get()));

            slabBlock(ModBlocks.POPLAR.getSlab().get(), blockTexture(ModBlocks.POPLAR.getPlanks().get()), blockTexture(ModBlocks.POPLAR.getPlanks().get()));

            buttonBlock(ModBlocks.POPLAR.getButton().get(),blockTexture(ModBlocks.POPLAR.getPlanks().get()));

            pressurePlateBlock(ModBlocks.POPLAR.getPressurePlate().get(),blockTexture(ModBlocks.POPLAR.getPlanks().get()));

            fenceBlock(ModBlocks.POPLAR.getFence().get(),blockTexture(ModBlocks.POPLAR.getPlanks().get()));

            fenceGateBlock(ModBlocks.POPLAR.getFenceGate().get(),blockTexture(ModBlocks.POPLAR.getPlanks().get()));

            doorBlockWithRenderType(ModBlocks.POPLAR.getDoor().get(), modLoc("block/poplar_door_bottom"),modLoc("block/poplar_door_top"),"cutout");

            trapdoorBlockWithRenderType(ModBlocks.POPLAR.getTrapdoor().get(), modLoc("block/poplar_trapdoor"),true,"cutout");

            leavesBlock(ModBlocks.RED_POPLAR_LEAVES);
            leavesBlock(ModBlocks.ORANGE_POPLAR_LEAVES);
            leavesBlock(ModBlocks.YELLOW_POPLAR_LEAVES);

            saplingBlock(ModBlocks.POPLAR_SAPLING);
    }

    private void saplingBlock(DeferredBlock<Block> blockRegisteryObject){
        simpleBlock(
                blockRegisteryObject.get(),
                models()
                        .cross(BuiltInRegistries.BLOCK.getKey(
                                        blockRegisteryObject.get()).getPath(),
                                blockTexture(blockRegisteryObject.get())
                        ).renderType("cutout")
        );
    }

    private void leavesBlock(DeferredBlock<ModLeavesBlock> blockRegisteryObject){
        simpleBlockWithItem(blockRegisteryObject.get(),models().singleTexture(BuiltInRegistries.BLOCK.getKey(
                        blockRegisteryObject.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                "all",blockTexture(blockRegisteryObject.get())).renderType("cutout"));
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock){
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("wildernessboundbackport:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix){
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("wildernessboundbackport:block/" + deferredBlock.getId().getPath() + appendix));
    }
}
