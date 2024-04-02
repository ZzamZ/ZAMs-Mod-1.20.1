package net.zam.zammod.item;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class MarlinLanceTiers {
    public static final Tier MARLIN_LANCE = new Tier() {
        public int getUses() {
            return 1400;
        }
        public float getSpeed() {
            return 6f;
        }
        public float getAttackDamageBonus() {
            return 5f;
        }
        public int getLevel() {
            return 3;
        }
        public int getEnchantmentValue() {
            return 9;
        }
        public @NotNull Ingredient getRepairIngredient() {
            return Ingredient.of(Items.PRISMARINE_CRYSTALS.getDefaultInstance());
        }
    };
}