package fr.eclipseria.wildernessboundbackport.datagen;

import fr.eclipseria.wildernessboundbackport.block.ModBlocks;
import fr.eclipseria.wildernessboundbackport.block.custom.SlabAndStairs;
import fr.eclipseria.wildernessboundbackport.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
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

            DyeColor color = getColorFromName(block.getName());
            if (color == null) continue;

            DyeItem dyeItem = DyeItem.byColor(color);

            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, block.getSlab().get())
                    .requires(Ingredient.of(ModTags.Items.WOOL_SLABS))
                    .requires(dyeItem)
                    .group("wool_slab_dyeing")
                    .unlockedBy("has_needed_dye", has(dyeItem))
                    .save(recipeOutput, block.getName() + "_slab_from_dyeing");

            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, block.getStair().get())
                    .requires(Ingredient.of(ModTags.Items.WOOL_STAIRS))
                    .requires(dyeItem)
                    .group("wool_stairs_dyeing")
                    .unlockedBy("has_needed_dye", has(dyeItem))
                    .save(recipeOutput, block.getName() + "_stairs_from_dyeing");
            

            stairBuilder(block.getStair().asItem(), Ingredient.of(block.getBlockType()))
                    .group("wool")
                    .unlockedBy("has_wool",has(block.getBlockType()))
                    .save(recipeOutput);

            slab(recipeOutput,RecipeCategory.BUILDING_BLOCKS,block.getSlab(),block.getBlockType());

        }

        for (SlabAndStairs block : ModBlocks.CONCRETESLABSANDSTAIRS) {

            stairBuilder(block.getStair().asItem(), Ingredient.of(block.getBlockType()))
                    .group("wool")
                    .unlockedBy("has_wool",has(block.getBlockType()))
                    .save(recipeOutput);

            slab(recipeOutput,RecipeCategory.BUILDING_BLOCKS,block.getSlab(),block.getBlockType());


            stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, block.getStair().asItem().asItem(),block.getBlockType().asItem(),1);
            stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, block.getSlab().asItem(),block.getBlockType().asItem(),2);
        }

    }

    private DyeColor getColorFromName(String name) {
        for (DyeColor color : DyeColor.values()) {
            if (name.startsWith(color.getName())) {
                return color;
            }
        }
        return null;
    }
}
