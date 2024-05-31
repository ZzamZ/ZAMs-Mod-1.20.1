package net.zam.zammod.advancement;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.zam.zammod.ZAMMod;

import java.util.HashMap;
import java.util.Map;

public class FishingCatchTrigger extends SimpleCriterionTrigger<FishingCatchTrigger.TriggerInstance> {
    public static final FishingCatchTrigger INSTANCE = new FishingCatchTrigger();
    private static final ResourceLocation ID = new ResourceLocation(ZAMMod.MOD_ID, "fishing_catch");

    private final Map<ServerPlayer, Integer> playerCatchCounts = new HashMap<>();

    private FishingCatchTrigger() {
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    protected TriggerInstance createInstance(JsonObject json, ContextAwarePredicate player, DeserializationContext conditions) {
        MinMaxBounds.Ints catches = MinMaxBounds.Ints.fromJson(json.get("catches"));
        return new TriggerInstance(player, catches);
    }

    public void trigger(ServerPlayer player) {
        playerCatchCounts.put(player, playerCatchCounts.getOrDefault(player, 0) + 1);
        int catches = playerCatchCounts.get(player);
        this.trigger(player, instance -> instance.matches(catches));
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final MinMaxBounds.Ints catches;

        public TriggerInstance(ContextAwarePredicate player, MinMaxBounds.Ints catches) {
            super(FishingCatchTrigger.ID, player);
            this.catches = catches;
        }

        public boolean matches(int catches) {
            return this.catches.matches(catches);
        }

        @Override
        public JsonObject serializeToJson(SerializationContext conditions) {
            JsonObject json = super.serializeToJson(conditions);
            json.add("catches", this.catches.serializeToJson());
            return json;
        }
    }
}
