package net.zam.zamaquaticadditions.item;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.zam.zamaquaticadditions.registry.ZAMEffects;
import net.zam.zamaquaticadditions.registry.ZAMItems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZAMArmorItem extends ArmorItem {
    private static final Map<ArmorMaterial, MobEffectInstance[]> MATERIAL_TO_EFFECT_MAP = new HashMap<>();

    static {
        MATERIAL_TO_EFFECT_MAP.put(ZAMArmorMaterials.GUARDIAN, new MobEffectInstance[]{
                new MobEffectInstance(ZAMEffects.MIPHAS_GRACE_EFFECT.get(), 0, 0),

        });
    }

    public ZAMArmorItem(ArmorMaterial material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (!level.isClientSide) {
            // Check if the full suit state has changed
            boolean hasFullSuit = hasFullSuitOfGuardianArmorOn(player);

            if (hasFullSuit != player.getTags().contains("full_guardian_armor_suit")) {
                if (hasFullSuit) {
                    // Apply effects when the full GUARDIAN suit is equipped
                    player.getTags().add("full_guardian_armor_suit");
                    evaluateArmorEffects(player);
                } else {
                    // Remove effects when any piece of the GUARDIAN armor is removed
                    player.getTags().remove("full_guardian_armor_suit");
                    removeArmorEffects(player);
                }
            }
        }
    }


    private void evaluateArmorEffects(Player player) {
        MobEffectInstance[] armorEffects = MATERIAL_TO_EFFECT_MAP.get(this.getMaterial());

        if (armorEffects != null) {
            for (MobEffectInstance armorEffect : armorEffects) {
                addEffectToPlayer(player, armorEffect);
            }
        }
    }

    private void addEffectToPlayer(Player player, MobEffectInstance armorEffect) {
        MobEffect effect = armorEffect.getEffect();
        int amplifier = armorEffect.getAmplifier();

        boolean hasPlayerEffect = player.hasEffect(effect);

        if (!hasPlayerEffect || player.getEffect(effect).getAmplifier() < amplifier) {
            player.addEffect(new MobEffectInstance(effect, MobEffectInstance.INFINITE_DURATION, amplifier, false, false));
        }
    }

    private void removeArmorEffects(Player player) {
        List<MobEffectInstance> effectsToRemove = new ArrayList<>();

        for (MobEffectInstance effectInstance : player.getActiveEffects()) {
            if (effectInstance.getEffect() == ZAMEffects.MIPHAS_GRACE_EFFECT.get() ||
                    effectInstance.getEffect() == MobEffects.DOLPHINS_GRACE) {
                effectsToRemove.add(effectInstance);
            }
        }

        for (MobEffectInstance effectInstance : effectsToRemove) {
            player.removeEffect(effectInstance.getEffect());
        }
    }

    private boolean hasFullSuitOfGuardianArmorOn(Player player) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        Item guardianBootsItem = ZAMItems.GUARDIAN_BOOTS.get();
        Item guardianLeggingsItem = ZAMItems.GUARDIAN_LEGGING.get();
        Item guardianChestplateItem = ZAMItems.GUARDIAN_CHESTPLATE.get();
        Item guardianHelmetItem = ZAMItems.GUARDIAN_HELMET.get();

        return !boots.isEmpty() && boots.getItem() == guardianBootsItem &&
                !leggings.isEmpty() && leggings.getItem() == guardianLeggingsItem &&
                !chestplate.isEmpty() && chestplate.getItem() == guardianChestplateItem &&
                !helmet.isEmpty() && helmet.getItem() == guardianHelmetItem;

    }
}