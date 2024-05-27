package net.zam.zammod.registry;

import com.google.common.base.CaseFormat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.mixin.gamerule.BooleanValueInvoker;
import net.zam.zammod.mixin.gamerule.IntegerValueInvoker;
import net.zam.zammod.util.network.BooleanGameRuleChangedPacket;
import net.zam.zammod.util.network.IntegerGameRuleChangedPacket;
import net.zam.zammod.util.network.NetworkHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ZAMGameRules {

    private static final Map<String, BooleanValue> BOOLEAN_VALUES = new HashMap<>();
    private static final Map<String, IntegerValue> INTEGER_VALUES = new HashMap<>();

 //   public static final BooleanValue
    //        ONION_RING_ENABLED = booleanValue(createName(ZAMItems.KRABBY_PATTY, "enabled"));


   // public static final IntegerValue
    //        ONION_RING_COOLDOWN = integerValue(createName(ZAMItems.KRABBY_PATTY, "cooldown"), 20);


    private static String createName(RegistryObject<? extends Item> item, String name) {
        return String.format("%s.%s.%s",
                ZAMMod.MOD_ID,
                CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, item.getId().getPath()),
                name
        );
    }

    private static BooleanValue booleanValue(String name) {
        BooleanValue result = new BooleanValue();
        GameRules.Type<GameRules.BooleanValue> type = BooleanValueInvoker.invokeCreate(true, (server, value) -> {
            result.update(value.get());
            NetworkHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), new BooleanGameRuleChangedPacket(name, value.get()));
        });
        result.key = GameRules.register(name, GameRules.Category.PLAYER, type);
        BOOLEAN_VALUES.put(name, result);
        return result;
    }

    private static IntegerValue integerValue(String name, int defaultValue) {
        return integerValue(name, defaultValue, (server, value) -> { });
    }

    private static IntegerValue integerValue(String name, int defaultValue, BiConsumer<MinecraftServer, GameRules.IntegerValue> onChanged) {
        IntegerValue result = new IntegerValue();
        result.update(defaultValue);
        GameRules.Type<GameRules.IntegerValue> type = IntegerValueInvoker.invokeCreate(defaultValue, (server, value) -> {
            result.update(value.get());
            NetworkHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), new IntegerGameRuleChangedPacket(name, value.get()));
            onChanged.accept(server, value);
        });
        result.key = GameRules.register(name, GameRules.Category.PLAYER, type);

        INTEGER_VALUES.put(name, result);
        return result;
    }

    public static void updateValue(String key, boolean value) {
        BOOLEAN_VALUES.get(key).update(value);
    }

    public static void updateValue(String key, int value) {
        INTEGER_VALUES.get(key).update(value);
    }

    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            BOOLEAN_VALUES.forEach((key, value) -> NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new BooleanGameRuleChangedPacket(key, value.get())));
            INTEGER_VALUES.forEach((key, value) -> NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new IntegerGameRuleChangedPacket(key, value.get())));
        }
    }

    public static void onServerStarted(ServerStartedEvent event) {
        BOOLEAN_VALUES.values().forEach(value -> value.update(event.getServer()));
        INTEGER_VALUES.values().forEach(value -> value.update(event.getServer()));
    }

    public static class BooleanValue implements Supplier<Boolean> {

        private Boolean value = true;
        private GameRules.Key<GameRules.BooleanValue> key;

        public Boolean get() {
            return value;
        }

        private void update(MinecraftServer server) {
            update(server.getGameRules().getBoolean(key));
        }

        private void update(boolean value) {
            this.value = value;
        }
    }

    public static class IntegerValue implements Supplier<Integer> {

        private Integer value;
        private GameRules.Key<GameRules.IntegerValue> key;

        public Integer get() {
            return value;
        }

        private void update(MinecraftServer server) {
            update(server.getGameRules().getInt(key));
        }

        private void update(int value) {
            this.value = value;
        }
    }
}
