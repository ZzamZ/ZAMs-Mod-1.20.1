package net.zam.zammod.util.network;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.util.network.packet.*;


public class NetworkHandler {
    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            ZAMMod.id("network"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals




    );



    public static void register() {
        CHANNEL.messageBuilder(ClaimRewardPacket.class, 0).encoder(ClaimRewardPacket::toBytes).decoder(ClaimRewardPacket::new).consumerMainThread(ClaimRewardPacket::handle).add();
        CHANNEL.messageBuilder(SyncInventoryPacket.class, 1).encoder(SyncInventoryPacket::toBytes).decoder(SyncInventoryPacket::new).consumerMainThread(SyncInventoryPacket::handle).add();
        CHANNEL.registerMessage(2, S2CStartMinigamePacket.class, S2CStartMinigamePacket::encode, S2CStartMinigamePacket::new, S2CStartMinigamePacket::handle);
        CHANNEL.registerMessage(3, C2SCompleteMinigamePacket.class, C2SCompleteMinigamePacket::encode, C2SCompleteMinigamePacket::decode, C2SCompleteMinigamePacket::handle);
    }


    public static <MSG> void sendToPlayer(ServerPlayer player, MSG packet) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), packet);
    }

    public static <MSG> void sendToServer(MSG packet) {
        CHANNEL.send(PacketDistributor.SERVER.noArg(), packet);
    }
}

