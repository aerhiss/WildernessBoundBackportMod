package fr.eclipseria.wildernessboundbackport.datagen;

import fr.eclipseria.wildernessboundbackport.WildernessBoundBackport;
import fr.eclipseria.wildernessboundbackport.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, WildernessBoundBackport.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        buttonItem(ModBlocks.POPLAR.getButton(), ModBlocks.POPLAR.getPlanks());
        fenceItem(ModBlocks.POPLAR.getFence(), ModBlocks.POPLAR.getPlanks());

        basicItem(ModBlocks.POPLAR.getDoor().asItem());

        saplingItem(ModBlocks.POPLAR_SAPLING);
    }

    private ItemModelBuilder saplingItem(DeferredBlock<Block> item){
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0", ResourceLocation.fromNamespaceAndPath(WildernessBoundBackport.MODID,"block/"+ item.getId().getPath()));
    }

    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock){
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture(
                        "texture",
                        ResourceLocation.fromNamespaceAndPath(
                                WildernessBoundBackport.MODID,
                                "block/" + baseBlock.getId().getPath()
                        )
                );
    }
    public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock){
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture(
                        "texture",
                        ResourceLocation.fromNamespaceAndPath(
                                WildernessBoundBackport.MODID,
                                "block/" + baseBlock.getId().getPath()
                        )
                );
    }
}
