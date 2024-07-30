package net.zam.zammod.datagen;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.integrations.ForgeItemTags;
import net.zam.zammod.registry.ZAMBlocks;
import net.zam.zammod.registry.ZAMItems;
import net.zam.zammod.registry.ZAMTags;

import java.util.function.Consumer;

public class ZAMRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ZAMRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.WHITE_CONCRETE_SLAB.get(), 6)
                .pattern("CCC")
                .define('C', Blocks.WHITE_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.WHITE_CONCRETE).build())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.LIGHT_GRAY_CONCRETE_SLAB.get(), 6)
                .pattern("CCC")
                .define('C', Blocks.LIGHT_GRAY_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.LIGHT_GRAY_CONCRETE).build())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.GRAY_CONCRETE_SLAB.get(), 6)
                .pattern("CCC")
                .define('C', Blocks.GRAY_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.GRAY_CONCRETE).build())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.BLACK_CONCRETE_SLAB.get(), 6)
                .pattern("CCC")
                .define('C', Blocks.BLACK_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.BLACK_CONCRETE).build())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.BROWN_CONCRETE_SLAB.get(), 6)
                .pattern("CCC")
                .define('C', Blocks.BROWN_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.BROWN_CONCRETE).build())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.RED_CONCRETE_SLAB.get(), 6)
                .pattern("CCC")
                .define('C', Blocks.RED_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.RED_CONCRETE).build())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.ORANGE_CONCRETE_SLAB.get(), 6)
                .pattern("CCC")
                .define('C', Blocks.ORANGE_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.ORANGE_CONCRETE).build())).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.YELLOW_CONCRETE_SLAB.get(), 6)
                .pattern("CCC")
                .define('C', Blocks.YELLOW_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.YELLOW_CONCRETE).build())).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.LIME_CONCRETE_SLAB.get(), 6)
                .pattern("CCC")
                .define('C', Blocks.LIME_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.LIME_CONCRETE).build())).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.GREEN_CONCRETE_SLAB.get(), 6)
                .pattern("CCC")
                .define('C', Blocks.GREEN_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.GREEN_CONCRETE).build())).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.CYAN_CONCRETE_SLAB.get(), 6)
                .pattern("C  ")
                .pattern("CC ")
                .pattern("CCC")
                .define('C', Blocks.CYAN_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.CYAN_CONCRETE).build())).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.LIGHT_BLUE_CONCRETE_SLAB.get(), 6)
                .pattern("CCC")
                .define('C', Blocks.LIGHT_BLUE_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.LIGHT_BLUE_CONCRETE).build())).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.BLUE_CONCRETE_SLAB.get(), 6)
                .pattern("CCC")
                .define('C', Blocks.BLUE_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.BLUE_CONCRETE).build())).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.PURPLE_CONCRETE_SLAB.get(), 6)
                .pattern("CCC")
                .define('C', Blocks.PURPLE_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.PURPLE_CONCRETE).build())).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.MAGENTA_CONCRETE_SLAB.get(), 6)
                .pattern("CCC")
                .define('C', Blocks.MAGENTA_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.MAGENTA_CONCRETE).build())).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.PINK_CONCRETE_SLAB.get(), 6)
                .pattern("CCC")
                .define('C', Blocks.PINK_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.PINK_CONCRETE).build())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.WHITE_CONCRETE_STAIRS.get(), 4)
                .pattern("C  ")
                .pattern("CC ")
                .pattern("CCC")
                .define('C', Blocks.WHITE_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.WHITE_CONCRETE).build())).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.LIGHT_GRAY_CONCRETE_STAIRS.get(), 4)
                .pattern("C  ")
                .pattern("CC ")
                .pattern("CCC")
                .define('C', Blocks.LIGHT_GRAY_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.LIGHT_GRAY_CONCRETE).build())).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.GRAY_CONCRETE_STAIRS.get(), 4)
                .pattern("C  ")
                .pattern("CC ")
                .pattern("CCC")
                .define('C', Blocks.GRAY_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.GRAY_CONCRETE).build())).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.BLACK_CONCRETE_STAIRS.get(), 4)
                .pattern("C  ")
                .pattern("CC ")
                .pattern("CCC")
                .define('C', Blocks.BLACK_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.BLACK_CONCRETE).build())).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.BROWN_CONCRETE_STAIRS.get(), 4)
                .pattern("C  ")
                .pattern("CC ")
                .pattern("CCC")
                .define('C', Blocks.BROWN_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.BROWN_CONCRETE).build())).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.RED_CONCRETE_STAIRS.get(), 4)
                .pattern("C  ")
                .pattern("CC ")
                .pattern("CCC")
                .define('C', Blocks.RED_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.RED_CONCRETE).build())).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.ORANGE_CONCRETE_STAIRS.get(), 4)
                .pattern("C  ")
                .pattern("CC ")
                .pattern("CCC")
                .define('C', Blocks.ORANGE_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.ORANGE_CONCRETE).build())).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.YELLOW_CONCRETE_STAIRS.get(), 4)
                .pattern("C  ")
                .pattern("CC ")
                .pattern("CCC")
                .define('C', Blocks.YELLOW_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.YELLOW_CONCRETE).build())).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.LIME_CONCRETE_STAIRS.get(), 4)
                .pattern("C  ")
                .pattern("CC ")
                .pattern("CCC")
                .define('C', Blocks.LIME_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.LIME_CONCRETE).build())).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.GREEN_CONCRETE_STAIRS.get(), 4)
                .pattern("C  ")
                .pattern("CC ")
                .pattern("CCC")
                .define('C', Blocks.GREEN_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.GREEN_CONCRETE).build())).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.CYAN_CONCRETE_STAIRS.get(), 4)
                .pattern("C  ")
                .pattern("CC ")
                .pattern("CCC")
                .define('C', Blocks.CYAN_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.CYAN_CONCRETE).build())).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.LIGHT_BLUE_CONCRETE_STAIRS.get(), 4)
                .pattern("C  ")
                .pattern("CC ")
                .pattern("CCC")
                .define('C', Blocks.LIGHT_BLUE_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.LIGHT_BLUE_CONCRETE).build())).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.BLUE_CONCRETE_STAIRS.get(), 4)
                .pattern("C  ")
                .pattern("CC ")
                .pattern("CCC")
                .define('C', Blocks.BLUE_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.BLUE_CONCRETE).build())).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.PURPLE_CONCRETE_STAIRS.get(), 4)
                .pattern("C  ")
                .pattern("CC ")
                .pattern("CCC")
                .define('C', Blocks.PURPLE_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.PURPLE_CONCRETE).build())).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.MAGENTA_CONCRETE_STAIRS.get(), 4)
                .pattern("C  ")
                .pattern("CC ")
                .pattern("CCC")
                .define('C', Blocks.MAGENTA_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.MAGENTA_CONCRETE).build())).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.PINK_CONCRETE_STAIRS.get(), 4)
                .pattern("C  ")
                .pattern("CC ")
                .pattern("CCC")
                .define('C', Blocks.PINK_CONCRETE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.PINK_CONCRETE).build())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.SAND_STAIRS.get(), 4)
                .pattern("C  ")
                .pattern("CC ")
                .pattern("CCC")
                .define('C', Blocks.SAND)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.SAND).build())).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.SAND_SLAB.get(), 6)
                .pattern("CCC")
                .define('C', Blocks.SAND)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.SAND).build())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.PACKED_ICE_STAIRS.get(), 4)
                .pattern("C  ")
                .pattern("CC ")
                .pattern("CCC")
                .define('C', Blocks.PACKED_ICE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.PACKED_ICE).build())).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.PACKED_ICE_SLAB.get(), 6)
                .pattern("CCC")
                .define('C', Blocks.PACKED_ICE)
                .unlockedBy("has_concrete", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Blocks.PACKED_ICE).build())).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZAMBlocks.ARCADE_MACHINE.get(),1)
                .pattern("IRI")
                .pattern("ESE")
                .pattern("IRI")
                .define('I', Blocks.IRON_BLOCK)
                .define('R', Blocks.REDSTONE_BLOCK)
                .define('S', ZAMItems.STARDROP.get())
                .define('E', ZAMItems.EMERALD_SHARD.get())
                .unlockedBy("has_stardrop", inventoryTrigger(ItemPredicate.Builder.item().
                        of(ZAMItems.STARDROP.get()).build())).save(pWriter);


        smithingTools(pWriter, Items.NETHERITE_SWORD, ZAMItems.STARDEW_SWORD.get(), ZAMItems.STARDEW_SMITHING_TEMPLATE.get(), ForgeItemTags.OPAL, RecipeCategory.TOOLS, new ResourceLocation("zammod", "stardew_sword_trim"));
        smithingTools(pWriter, Items.NETHERITE_AXE, ZAMItems.STARDEW_AXE.get(), ZAMItems.STARDEW_SMITHING_TEMPLATE.get(), ForgeItemTags.OPAL, RecipeCategory.TOOLS, new ResourceLocation("zammod", "stardew_axe_trim"));
        smithingTools(pWriter, Items.NETHERITE_PICKAXE, ZAMItems.STARDEW_PICKAXE.get(), ZAMItems.STARDEW_SMITHING_TEMPLATE.get(), ForgeItemTags.OPAL, RecipeCategory.TOOLS, new ResourceLocation("zammod", "stardew_pickaxe_trim"));
        smithingTools(pWriter, Items.NETHERITE_SHOVEL, ZAMItems.STARDEW_SHOVEL.get(), ZAMItems.STARDEW_SMITHING_TEMPLATE.get(), ForgeItemTags.OPAL, RecipeCategory.TOOLS, new ResourceLocation("zammod", "stardew_shovel_trim"));
        smithingTools(pWriter, Items.NETHERITE_HOE, ZAMItems.STARDEW_HOE.get(), ZAMItems.STARDEW_SMITHING_TEMPLATE.get(), ForgeItemTags.OPAL, RecipeCategory.TOOLS, new ResourceLocation("zammod", "stardew_hoe_trim"));
    }

    protected static void smithingTools(Consumer<FinishedRecipe> consumer, Item baseItem, Item resultItem, Item templateItem, TagKey<Item> trimMaterial, RecipeCategory category, ResourceLocation recipeId) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(templateItem), Ingredient.of(baseItem), Ingredient.of(trimMaterial), category, resultItem)
                .unlocks("has_" + templateItem.getDescriptionId(), has(templateItem))
                .save(consumer, recipeId);
    }
}
