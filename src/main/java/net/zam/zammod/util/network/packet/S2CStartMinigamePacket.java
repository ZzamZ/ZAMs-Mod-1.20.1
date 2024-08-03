package net.zam.zammod.util.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.zam.zammod.event.ClientEvents;
import net.zam.zammod.util.network.FishBehavior;

import java.util.function.Supplier;

public record S2CStartMinigamePacket(FishBehavior behavior) {
    public S2CStartMinigamePacket(FriendlyByteBuf buf) {
        this(new FishBehavior(buf));
    }

    public void encode(FriendlyByteBuf buf) {
        behavior.writeToBuffer(buf);
    }

    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        contextSupplier.get().enqueueWork(() -> ClientEvents.openFishingScreen(behavior));
    }
}