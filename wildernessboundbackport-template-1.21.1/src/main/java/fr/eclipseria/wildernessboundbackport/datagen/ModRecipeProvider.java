package fr.eclipseria.wildernessboundbackport.datagen;

import fr.eclipseria.wildernessboundbackport.block.ModBlocks;
import fr.eclipseria.wildernessboundbackport.block.custom.SlabAndStairs;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {

        for (SlabAndStairs block : ModBlocks.WOOLSLABSANDSTAIRS){

            stairBuilder(block.getStair().asItem(), Ingredient.of(block.getBlockType()))
                    .group("wool")
                    .unlockedBy("has_wool",has(block.getBlockType()))
                    .save(recipeOutput);

            slab(recipeOutput,RecipeCategory.BUILDING_BLOCKS,block.getSlab(),block.getBlockType());


            //stonecutterResultFromBase(block.getSlab(), RecipeCategory.BUILDING_BLOCKS, block.getBlockType().asItem(),block.getBlockType().asItem(),2);
            //stonecutterResultFromBase(block.getSlab(), RecipeCategory.BUILDING_BLOCKS, block.getBlockType().asItem(),block.getBlockType().asItem(),2);

        }


    }
}
