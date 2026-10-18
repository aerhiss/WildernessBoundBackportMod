package fr.eclipseria.wildernessboundbackport.datagen;

import fr.eclipseria.wildernessboundbackport.WildernessBoundBackport;
import fr.eclipseria.wildernessboundbackport.block.ModBlocks;
import fr.eclipseria.wildernessboundbackport.block.custom.SlabAndStairs;
import net.minecraft.data.PackOutput;
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
