package net.zam.zamaquaticadditions.util.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ServerConfig {

    public final ForgeConfigSpec.BooleanValue cheesemaking;
    public final ForgeConfigSpec.IntValue milkCauldronTime;

    public ServerConfig(final ForgeConfigSpec.Builder builder) {
        this.cheesemaking = buildBoolean(builder, "Cheesemaking", true, "True if cheese can be created in cauldrons");
        this.milkCauldronTime = buildInt(builder, "Milk Curdling Time", 150, 20, 1000000, "The time in ticks(20 per second) it takes for milk to turn into cheese in a cauldron");

    }

    private static ForgeConfigSpec.BooleanValue buildBoolean(ForgeConfigSpec.Builder builder, String name, boolean defaultValue, String comment) {
        return builder.comment(comment).translation(name).define(name, defaultValue);
    }

    private static ForgeConfigSpec.IntValue buildInt(ForgeConfigSpec.Builder builder, String name, int defaultValue, int min, int max, String comment) {
        return builder.comment(comment).translation(name).defineInRange(name, defaultValue, min, max);
    }
}
