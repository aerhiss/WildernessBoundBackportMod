package fr.eclipseria.wildernessboundbackport.datagen;

import fr.eclipseria.wildernessboundbackport.block.ModBlocks;
import fr.eclipseria.wildernessboundbackport.block.register.SlabAndStairs;
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

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.POPLAR.getPlanks(), 4)
                .requires(Ingredient.of(ModTags.Items.POPLAR_LOGS))
                .unlockedBy("has_poplar", has(ModBlocks.POPLAR.getLog()))
                .save(recipeOutput);

        stairBuilder(ModBlocks.POPLAR.getStair().get(), Ingredient.of(ModBlocks.POPLAR.getPlanks()))
                .group("poplar")
                .unlockedBy("has_poplar", has(ModBlocks.POPLAR.getPlanks()))
                .save(recipeOutput);

        slab(recipeOutput,RecipeCategory.MISC,ModBlocks.POPLAR.getSlab().get(),ModBlocks.POPLAR.getPlanks().get());

        buttonBuilder(ModBlocks.POPLAR.getButton().get(), Ingredient.of(ModBlocks.POPLAR.getPlanks())).group("poplar")
                .unlockedBy("has_poplar", has(ModBlocks.POPLAR.getPlanks()))
                .save(recipeOutput);

        pressurePlate(recipeOutput,ModBlocks.POPLAR.getPressurePlate().get(),ModBlocks.POPLAR.getPlanks().get());

        fenceBuilder(ModBlocks.POPLAR.getFence().get(), Ingredient.of(ModBlocks.POPLAR.getPlanks())).group("poplar")
                .unlockedBy("has_poplar", has(ModBlocks.POPLAR.getPlanks()))
                .save(recipeOutput);

        fenceGateBuilder(ModBlocks.POPLAR.getFenceGate().get(), Ingredient.of(ModBlocks.POPLAR.getPlanks())).group("poplar")
                .unlockedBy("has_poplar", has(ModBlocks.POPLAR.getPlanks()))
                .save(recipeOutput);

        trapdoorBuilder(ModBlocks.POPLAR.getTrapdoor().get(), Ingredient.of(ModBlocks.POPLAR.getPlanks())).group("poplar")
                .unlockedBy("has_poplar", has(ModBlocks.POPLAR.getPlanks()))
                .save(recipeOutput);

        doorBuilder(ModBlocks.POPLAR.getDoor().get(), Ingredient.of(ModBlocks.POPLAR.getPlanks())).group("poplar")
                .unlockedBy("has_poplar", has(ModBlocks.POPLAR.getPlanks()))
                .save(recipeOutput);

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
