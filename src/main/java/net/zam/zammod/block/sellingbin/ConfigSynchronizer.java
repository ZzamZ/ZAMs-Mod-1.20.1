package net.zam.zammod.block.sellingbin;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.zam.zammod.ZAMMod;

import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = ZAMMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ConfigSynchronizer {

    public static final String PROTOCOL_VERSION = "1";
    public static final ResourceLocation CHANNEL_NAME = new ResourceLocation(ZAMMod.MOD_ID, "init");

    public static final SimpleChannel CHANNEL_INSTANCE = NetworkRegistry.newSimpleChannel(
            CHANNEL_NAME,
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    static {
        CHANNEL_INSTANCE.registerMessage(0, SyncPacket.class, SyncPacket::encode, SyncPacket::decode, SyncPacket::handle);
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer) {
            ServerPlayer player = (ServerPlayer) event.getEntity();
            CHANNEL_INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SyncPacket(ZAMMod.trades));
        }
    }

    public static void sync(SyncPacket syncPacket) {
        ZAMMod.matches.removeIf(match -> match instanceof Trade);
        ZAMMod.matches.addAll(syncPacket.trades);
    }

    public static class SyncPacket {
        public final List<Trade> trades;

        public SyncPacket(List<Trade> trades) {
            this.trades = trades;
        }

        public SyncPacket(FriendlyByteBuf buf) {
            this.trades = buf.readList(Trade::new);
        }

        public void encode(FriendlyByteBuf buf) {
            buf.writeCollection(this.trades, (bufInner, trade) -> trade.toNetwork(bufInner));
        }

        public static SyncPacket decode(FriendlyByteBuf buf) {
            return new SyncPacket(buf);
        }

        public static void handle(SyncPacket msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                if (ctx.get().getDirection().getReceptionSide().isClient()) {
                    sync(msg);
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
