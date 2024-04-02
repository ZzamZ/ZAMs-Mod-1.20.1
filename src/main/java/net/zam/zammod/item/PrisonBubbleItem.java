package net.zam.zammod.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.zam.zammod.ZAMMod;

import java.util.List;

public class PrisonBubbleItem extends Item {

    public PrisonBubbleItem(Properties properties) {
        super(new Item.Properties().stacksTo(1).durability(12));
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        // Define the tag for blacklisted entities
        ResourceLocation blacklistTag = new ResourceLocation(ZAMMod.MOD_ID, "prison_bubble_blacklist");
        // Check if the stack does not already contain an entity and the entity is not blacklisted
        if (stack.getOrCreateTagElement("entity_data").isEmpty() && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, blacklistTag))) {
            CompoundTag tag = entity.serializeNBT();
            if (!player.level().isClientSide) {
                entity.discard();
                stack.getTag().put("entity_data", tag);
                stack.getTag().putString("name", entity.getDisplayName().getString());
                this.playSound(player);
            }
            return true;
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        CompoundTag tag = ctx.getItemInHand().getOrCreateTagElement("entity_data");
        if (!tag.isEmpty()) {
            BlockPos pos = ctx.getClickedPos().relative(ctx.getClickedFace());
            if (!ctx.getLevel().isClientSide) {
                Entity e = EntityType.loadEntityRecursive(tag, ctx.getLevel(), a -> a);
                if (e != null) {
                    e.setPos(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
                    ctx.getLevel().addFreshEntity(e);
                    ctx.getItemInHand().getTag().remove("entity_data");
                    this.playSound(ctx.getPlayer());
                    ctx.getItemInHand().hurtAndBreak(1, ctx.getPlayer(), pl -> pl.broadcastBreakEvent(ctx.getHand()));
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.FAIL;
    }

    @Override
    public CompoundTag getShareTag(ItemStack stack) {
        CompoundTag tag = super.getShareTag(stack);
        if (tag == null) return null;
        tag = tag.copy();
        CompoundTag entity = new CompoundTag();
        if (tag.getCompound("entity_data").contains("id")) entity.putString("id", tag.getCompound("entity_data").getString("id"));
        tag.put("entity_data", entity);
        return tag;
    }

    void playSound(Player player) {
        player.level().playSound(null, player.blockPosition(), SoundEvents.RESPAWN_ANCHOR_CHARGE, SoundSource.AMBIENT, 1, 1);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn); // Call to the super method if needed

        if (stack.hasTag()) {
            CompoundTag tag = stack.getOrCreateTagElement("entity_data");
            if (tag.isEmpty()) {
                tooltip.add(Component.translatable("info.zammod.noentity").withStyle(ChatFormatting.GRAY));
            } else {
                tooltip.add(Component.translatable("info.zammod.containedentity", stack.getTag().getString("name")).withStyle(ChatFormatting.GRAY));
            }
        }

        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("info.zammod.prison_bubble").withStyle(ChatFormatting.DARK_AQUA));
        } else {
            tooltip.add(Component.translatable("tooltip.zammod.press_shift").withStyle(ChatFormatting.AQUA));
        }
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return stack.hasTag() && !stack.getOrCreateTagElement("entity_data").isEmpty();
    }

}
