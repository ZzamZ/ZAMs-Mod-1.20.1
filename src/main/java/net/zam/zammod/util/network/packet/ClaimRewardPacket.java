package net.zam.zammod.util.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
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
                player.getInventory().placeItemBackInInventory(reward.getItemStack().copy(), true);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
