package net.zam.zammod.util.network.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncInventoryClientPacket {
    public SyncInventoryClientPacket() {}

    public SyncInventoryClientPacket(FriendlyByteBuf buf) {}

    public void toBytes(FriendlyByteBuf buf) {}

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                player.inventoryMenu.broadcastChanges();
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
