package net.zam.zammod;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.registries.ForgeRegistries;
import net.zam.zammod.util.config.ServerConfig;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

public class ZAMConfig {

    public static boolean fadedShader = true;


    public static ServerConfig server;
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final LostOptions LOST_OPTIONS = new LostOptions(BUILDER);


    public static class Common {
        public final ForgeConfigSpec.ConfigValue<Integer> koiHorizontalSerenityRange;
        public final ForgeConfigSpec.ConfigValue<Integer> koiVerticalSerenityRange;
        public final ForgeConfigSpec.ConfigValue<Boolean> serenityEffect;

        Common(ForgeConfigSpec.Builder builder) {
            koiHorizontalSerenityRange = builder.comment("Horizontal radius of Serenity effect in blocks").define("Horizontal serenity range (radius)", 32);
            koiVerticalSerenityRange = builder.comment("Vertical radius of Serenity effect in blocks").define("Vertical serenity range (radius)", 8);
            serenityEffect = builder.comment("If Koi exude Serenity as a potion effect").define("Serenity potion effect", true);
        }
    }

    public static ForgeConfigSpec spec = BUILDER.build();


    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    static {
        final Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = commonSpecPair.getRight();
        COMMON = commonSpecPair.getLeft();

    }

    public static class LostOptions {
        static final String LOST_OPTIONS = "lost options";

        public ForgeConfigSpec.BooleanValue addLostBountyToLoot;

        LostOptions(ForgeConfigSpec.Builder builder) {
            builder.push(LOST_OPTIONS);
            addLostBountyToLoot = builder.comment("Should Lost bounty be added as fishing loot? Very rare.").define("Add Lost Bounty as loot?", true);
            builder.pop();
        }
    }
}
