package net.zam.zamaquaticadditions.util.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.zam.zamaquaticadditions.registry.ZAMGameRules;

import java.util.function.Supplier;

public class BooleanGameRuleChangedPacket {
    private final String key;
    private final boolean value;

    @SuppressWarnings("unused")
    public BooleanGameRuleChangedPacket(FriendlyByteBuf buffer) {
        key = buffer.readUtf();
        value = buffer.readBoolean();
    }

    public BooleanGameRuleChangedPacket(String key, boolean value) {
        this.key = key;
        this.value = value;
    }

    @SuppressWarnings("unused")
    void encode(FriendlyByteBuf buffer) {
        buffer.writeUtf(key);
        buffer.writeBoolean(value);
    }

    void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> ZAMGameRules.updateValue(key, value));
        context.get().setPacketHandled(true);
    }
}

