package net.zam.zammod.enchantment;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import net.zam.zammod.ZAMConfig;
import net.zam.zammod.registry.ZAMEnchantments;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.event.DropRulesEvent;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;
import java.util.Map;

public class SoulboundEnchantment extends Enchantment {

    public static final String IGNORED_NBT = "*";
    private static boolean curios = ModList.get().isLoaded("curios");

    public SoulboundEnchantment() {
        super(Rarity.RARE, EnchantmentCategory.BREAKABLE, EquipmentSlot.values());
    }

    @Override
    public int getMinCost(int level) {
        return ZAMConfig.minPower;
    }

    @Override
    public int getMaxCost(int level) {
        int powerRange = ZAMConfig.powerRange;
        if (powerRange < 0) {
            powerRange = 50;
        }
        return this.getMinCost(level) + powerRange;
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    public static boolean hasMatchItemStack(List<? extends String> list, ItemStack stack) {
        return list.stream().anyMatch(s -> match(stack, s));
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isTradeable() {
        return false;
    }

    @Override
    public boolean isDiscoverable() {
        return false;
    }

    public static boolean match(ItemStack stack, String s) {
        if (s.endsWith(IGNORED_NBT)) {
            String id = s.split("\\*", 2)[0];
            ResourceLocation identifier = ResourceLocation.tryParse(id);
            if (identifier == null) {
                identifier = ResourceLocation.tryParse(ResourceLocation.DEFAULT_NAMESPACE + ":" + id);
            }
            if (identifier == null) {
                return false;
            }
            @Nullable Item item = ForgeRegistries.ITEMS.getValue(identifier);
            return stack.is(item);
        }
        String[] split = s.split("\\{", 2);
        if (split.length > 0) {
            ResourceLocation identifier = ResourceLocation.tryParse(split[0]);
            if (identifier == null) {
                identifier = ResourceLocation.tryParse(ResourceLocation.DEFAULT_NAMESPACE + ":" + split[0]);
            }
            if (identifier == null) {
                return false;
            }
            CompoundTag nbt = null;
            if (split.length > 1) {
                try {
                    nbt = TagParser.parseTag("{" + split[1]);
                } catch (CommandSyntaxException ignored) {

                }
            }
            @Nullable Item item = ForgeRegistries.ITEMS.getValue(identifier);
            if (item != null) {
                ItemStack itemStack = new ItemStack(item);
                if (nbt != null) {
                    itemStack.save(nbt);
                }
                return ItemStack.isSameItemSameTags(itemStack, stack);
            }
        }
        return false;
    }

    @Override
    protected boolean checkCompatibility(@NotNull Enchantment other) {
        return (other != Enchantments.VANISHING_CURSE || !ZAMConfig.conflictWithVanishingCurse) && super.checkCompatibility(other);
    }

    public static void copySoulBoundItems(ServerPlayer oldPlayer, ServerPlayer newPlayer, boolean alive) {
        if (!alive && !(newPlayer.level().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY) || oldPlayer.isSpectator())) {
            for (int i = 0; i < oldPlayer.getInventory().getContainerSize(); i++) {
                ItemStack oldStack = oldPlayer.getInventory().getItem(i);
                ItemStack newStack = newPlayer.getInventory().getItem(i);
                int level = EnchantmentHelper.getTagEnchantmentLevel(ZAMEnchantments.SOUL_BOUND_ENCHANTMENT.get(), oldStack);

                if (level > 0 && !ItemStack.matches(oldStack, newStack)) {
                    if (ZAMConfig.maxDamagePercent != 0 && !oldPlayer.isCreative() && oldStack.isDamageableItem()) {
                        oldStack.hurt(oldPlayer.getRandom().nextInt(oldStack.getMaxDamage() * ZAMConfig.maxDamagePercent / 100), oldPlayer.getRandom(), oldPlayer);
                    }
                    Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(oldStack);
                    if (level > 1) {
                        enchantments.put(ZAMEnchantments.SOUL_BOUND_ENCHANTMENT.get(), level - 1);
                    } else {
                        enchantments.remove(ZAMEnchantments.SOUL_BOUND_ENCHANTMENT.get());
                    }
                    EnchantmentHelper.setEnchantments(enchantments, oldStack);

                    if (newStack.isEmpty()) {
                        newPlayer.getInventory().setItem(i, oldStack);
                    } else {
                        newPlayer.getInventory().placeItemBackInInventory(oldStack);
                    }
                }
            }
        }
    }

    public static void addCuriosDropListener () {
        if (curios) {
            MinecraftForge.EVENT_BUS.addListener(event -> {
                if (event instanceof DropRulesEvent rulesEvent) {
                    LivingEntity entity = rulesEvent.getEntity();
                    if (!(entity instanceof ServerPlayer player)) {
                        return;
                    }
                    rulesEvent.addOverride(stack -> {
                        if (EnchantmentHelper.getTagEnchantmentLevel(ZAMEnchantments.SOUL_BOUND_ENCHANTMENT.get(), stack) > 0) {
                            if (ZAMConfig.maxDamagePercent != 0 && !player.isCreative() && stack.isDamageableItem()) {
                                stack.hurt(player.getRandom().nextInt(stack.getMaxDamage() * ZAMConfig.maxDamagePercent / 100), player.getRandom(), player);
                                if (stack.getDamageValue() >= stack.getMaxDamage()) {
                                    if (ZAMConfig.allowBreakItem) {
                                        return false;
                                    } else {
                                        stack.setDamageValue(stack.getMaxDamage() - 1);
                                    }
                                }
                            }
                            return true;
                        }
                        return false;
                    }, ICurio.DropRule.ALWAYS_KEEP);
                }
            });
        }
    }
}