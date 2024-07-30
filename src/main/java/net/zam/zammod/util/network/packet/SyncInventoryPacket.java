package net.zam.zammod.util.network.packet;

import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundContainerSetContentPacket;
import net.minecraft.network.protocol.game.ClientboundSetCarriedItemPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncInventoryPacket {

    public SyncInventoryPacket() {
    }

    public SyncInventoryPacket(FriendlyByteBuf buf) {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                player.inventoryMenu.broadcastChanges(); // Sync the player's inventory

                // Force a full inventory sync to the client
                NonNullList<ItemStack> items = NonNullList.withSize(player.inventoryMenu.slots.size(), ItemStack.EMPTY);
                for (int i = 0; i < player.inventoryMenu.slots.size(); i++) {
                    items.set(i, player.inventoryMenu.getSlot(i).getItem());
                }
                player.connection.send(new ClientboundContainerSetContentPacket(player.inventoryMenu.containerId, player.inventoryMenu.getStateId(), items, ItemStack.EMPTY));
                player.connection.send(new ClientboundSetCarriedItemPacket(player.getInventory().selected));
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
