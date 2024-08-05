package net.zam.zammod.util;

import javax.annotation.Nullable;


import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.KnowledgeBookItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

public class Util {
    public static boolean isBook(ItemStack stack) {
        Item i = stack.getItem();
        String n = ForgeRegistries.ITEMS.getKey(i).getPath();

        TagKey<Item> FORGE_BOOKS_TAG = ItemTags.create(new ResourceLocation("forge", "books"));

        return (i instanceof EnchantedBookItem) || (i instanceof KnowledgeBookItem) || i == Items.BOOK || i == Items.WRITABLE_BOOK || i == Items.WRITTEN_BOOK
                || n.endsWith("book") || n.endsWith("manual") || n.endsWith("journal") || n.endsWith("tome")  || n.startsWith("tome") || n.endsWith("lexicon")  || n.endsWith("codex")
                || n.endsWith("guide") || n.startsWith("guide") || n.startsWith("handbook") || n.endsWith("chronicle") || n.endsWith("companion") || n.endsWith("binder") || n.endsWith("nomicon")
                || n.endsWith("dictionary") || n.startsWith("dictionary") || n.endsWith("materials_and_you") || n.endsWith("binder") || n.startsWith("binder")
                || stack.is(FORGE_BOOKS_TAG);
    }

    public static BlockState crackedState(BlockState cur) {
        if(cur.is(Blocks.STONE_BRICKS))
            return Blocks.CRACKED_STONE_BRICKS.defaultBlockState();
        else if(cur.is(Blocks.NETHER_BRICKS))
            return Blocks.CRACKED_NETHER_BRICKS.defaultBlockState();
        else if(cur.is(Blocks.POLISHED_BLACKSTONE_BRICKS))
            return Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.defaultBlockState();
        else
            return null;
    }

    public static boolean isntSolid(BlockState state, BlockGetter reader, BlockPos pos) {
        return false;
    }

    public static boolean isntSolid(BlockState state, BlockGetter reader, BlockPos pos, EntityType<?> ent) {
        return false;
    }

    @Nullable
    public static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> p_152133_, BlockEntityType<E> p_152134_, BlockEntityTicker<? super E> p_152135_) {
        return p_152134_ == p_152133_ ? (BlockEntityTicker<A>)p_152135_ : null;
    }
}