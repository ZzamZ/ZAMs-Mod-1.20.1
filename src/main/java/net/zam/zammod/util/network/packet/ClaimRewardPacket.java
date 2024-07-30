package net.zam.zammod.util.network.packet;

import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundContainerSetContentPacket;
import net.minecraft.network.protocol.game.ClientboundSetCarriedItemPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import net.zam.zammod.util.Rarity;
import net.zam.zammod.util.RarityItem;

import java.util.function.Supplier;

public class ClaimRewardPacket {

    private final RarityItem reward;

    public ClaimRewardPacket(RarityItem reward) {
        this.reward = reward;
    }

    public ClaimRewardPacket(FriendlyByteBuf buf) {
        this.reward = new RarityItem(buf.readItem(), buf.readEnum(Rarity.class));
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeItem(reward.getItemStack());
        buf.writeEnum(reward.getRarity());
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                // Add the item to the player's inventory
                player.getInventory().add(reward.getItemStack());
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
