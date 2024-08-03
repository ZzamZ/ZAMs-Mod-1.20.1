package net.zam.zammod;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ZAMConfig {
    public static final Common COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final ForgeConfigSpec spec; // Define spec here
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();


   // public static final LostOptions LOST_OPTIONS;

    static {
        Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = commonSpecPair.getRight();
        COMMON = commonSpecPair.getLeft();
        spec = COMMON_SPEC; // Assign spec here

     //   LOST_OPTIONS = new LostOptions(new ForgeConfigSpec.Builder());
    }


    public static void loadConfig() {
        final CommentedFileConfig configData = CommentedFileConfig.builder("config/zammod.toml").sync().autosave().writingMode(WritingMode.REPLACE).build();
        configData.load();
        COMMON_SPEC.setConfig(configData);
    }

    public static class Common {
        public final ForgeConfigSpec.BooleanValue fadedShader; // Add fadedShader configuration

        Common(ForgeConfigSpec.Builder builder) {
            builder.comment("General settings")
                    .push("General");

            fadedShader = builder
                    .comment("Enable or disable the faded shader")
                    .define("fadedShader", true);
        }
    }

    public static class LostOptions {
        static final String LOST_OPTIONS = "lost options";

        public final ForgeConfigSpec.BooleanValue addLostBountyToLoot;

        LostOptions(ForgeConfigSpec.Builder builder) {
            builder.push(LOST_OPTIONS);
            addLostBountyToLoot = builder.comment("Should Lost bounty be added as fishing loot? Very rare.").define("Add Lost Bounty as loot?", true);
            builder.pop();
        }
    }
}
