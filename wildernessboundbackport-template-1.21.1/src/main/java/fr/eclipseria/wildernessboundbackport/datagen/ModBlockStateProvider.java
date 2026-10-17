package fr.eclipseria.wildernessboundbackport.datagen;

import fr.eclipseria.wildernessboundbackport.WildernessBoundBackport;
import fr.eclipseria.wildernessboundbackport.block.ModBlocks;
import fr.eclipseria.wildernessboundbackport.block.custom.SlabAndStairs;
import net.minecraft.data.PackOutput;
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
