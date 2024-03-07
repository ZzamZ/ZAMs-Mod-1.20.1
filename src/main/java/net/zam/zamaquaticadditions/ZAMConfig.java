package net.zam.zamaquaticadditions;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.zam.zamaquaticadditions.enchantment.SoulboundEnchantment;
import net.zam.zamaquaticadditions.util.config.ConfigHolder;
import net.zam.zamaquaticadditions.util.config.ServerConfig;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ZAMConfig {

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

    private static final ForgeConfigSpec.BooleanValue CONFLICT_WITH_VANISHING_CURSE = BUILDER
            .comment("Whether it conflicts with the Vanishing Curse")
            .define("conflictWithVanishingCurse", true);

    private static final ForgeConfigSpec.BooleanValue IS_TREASURE = BUILDER
            .comment("Whether it is treasure enchantment (i.e. will not appear in the Enchantment Table)")
            .define("isTreasure", true);

    private static final ForgeConfigSpec.BooleanValue ALLOW_BREAK_ITEM = BUILDER
            .comment("Whether to allow the item to disappear after being damaged")
            .define("allowBreakItem", false);

    private static final ForgeConfigSpec.IntValue MAX_DAMAGE_PERCENT = BUILDER
            .comment("Max percentage of damage")
            .defineInRange("maxDamagePercent", 20, 0, 100);

    private static final ForgeConfigSpec.IntValue MIN_POWER = BUILDER
            .comment("Min power of enchantment")
            .defineInRange("minPower", 25, 0, 100);

    private static final ForgeConfigSpec.IntValue POWER_RANGE = BUILDER
            .comment("Power range of enchantment")
            .defineInRange("powerRange", 50, 1, 100);


    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> WHITELIST = BUILDER
            .comment("Example:(Only vanilla items support omitting prefixes)")
            .comment("[\"minecraft:diamond_sword{Damage:0}\", \"minecraft:diamond_sword\", \"diamond_sword\", \"minecraft:diamond_sword*\", \"diamond_sword*\"]")
            .comment("1.minecraft:diamond_sword{Damage:0}","2.minecraft:diamond_sword","3.diamond_sword","4.minecraft:diamond_sword*","5.diamond_sword*")
            .comment("Whitelist")
            .defineList("whitelist", new ArrayList<>(), o -> isValidString(o.toString()));

    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> BLACKLIST = BUILDER
            .comment("Blacklist")
            .defineList("blacklist", new ArrayList<>(), o -> isValidString(o.toString()));


    public static boolean conflictWithVanishingCurse;
    public static boolean isTreasure;
    public static boolean allowBreakItem;
    public static int maxDamagePercent;
    public static int minPower;
    public static int powerRange;
    public static List<? extends String> whitelist;
    public static List<? extends String> blacklist;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {

        conflictWithVanishingCurse = CONFLICT_WITH_VANISHING_CURSE.get();
        isTreasure = IS_TREASURE.get();
        allowBreakItem = ALLOW_BREAK_ITEM.get();
        maxDamagePercent = MAX_DAMAGE_PERCENT.get();
        minPower = MIN_POWER.get();
        powerRange = POWER_RANGE.get();
        whitelist = WHITELIST.get();
        blacklist = BLACKLIST.get();
    }

    public static boolean isValidString(String s) {
        if (s.endsWith(SoulboundEnchantment.IGNORED_NBT)) {
            String id = s.split("\\*", 2)[0];
            ResourceLocation identifier = ResourceLocation.tryParse(id);
            if (identifier == null) {
                identifier = ResourceLocation.tryParse(ResourceLocation.DEFAULT_NAMESPACE + ":" + id);
            }
            if (identifier == null) {
                return false;
            }
            @Nullable Item item = ForgeRegistries.ITEMS.getValue(identifier);
            return item != null;
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
                    return false;
                }
            }
            @Nullable Item item = ForgeRegistries.ITEMS.getValue(identifier);
            if (item != null) {
                ItemStack itemStack = new ItemStack(item);
                if (nbt != null) {
                    itemStack.save(nbt);
                }
                return !itemStack.isEmpty();
            }
        }
        return false;
    }
}
