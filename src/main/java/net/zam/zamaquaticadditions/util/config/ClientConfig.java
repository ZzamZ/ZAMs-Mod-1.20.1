package net.zam.zamaquaticadditions.util.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {
    public final ForgeConfigSpec.BooleanValue synesthesiaShader;

    public ClientConfig(final ForgeConfigSpec.Builder builder) {
        builder.push("general");
        this.synesthesiaShader = buildBoolean(builder, "Synesthesia Shader", true, "If true, being under the effect of Synesthesia will tint your screen yellow.");
    }
    private static ForgeConfigSpec.BooleanValue buildBoolean(ForgeConfigSpec.Builder builder, String name, boolean defaultValue, String comment) {
        return builder.comment(comment).translation(name).define(name, defaultValue);
    }
}
