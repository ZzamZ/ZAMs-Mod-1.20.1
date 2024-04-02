package net.zam.zammod.util.network;

import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.zam.zammod.ZAMMod;

public class NetworkHandler {

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            ZAMMod.id("textures/entity/curio/%s.png"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void register() {
        INSTANCE.registerMessage(0, BooleanGameRuleChangedPacket.class, BooleanGameRuleChangedPacket::encode, BooleanGameRuleChangedPacket::new, BooleanGameRuleChangedPacket::handle);
     //   INSTANCE.registerMessage(1, ToggleCharmPacket.class, ToggleCharmPacket::encode, ToggleCharmPacket::new, ToggleCharmPacket::handle);
      //  INSTANCE.registerMessage(2, DoubleJumpPacket.class, DoubleJumpPacket::encode, DoubleJumpPacket::new, DoubleJumpPacket::handle);
      //  INSTANCE.registerMessage(3, SinkPacket.class, SinkPacket::encode, SinkPacket::new, SinkPacket::handle);
    }
}
