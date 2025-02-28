package thebottle.sock.block.networking;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class SockPayloadTypes {
    public static void registerC2SPayloadTypes() {
        PayloadTypeRegistry.playC2S().register(SockC2SPackets.SockCraftPacket.PACKET_ID, SockC2SPackets.SockCraftPacket.PACkET_CODEC);
    }
}
