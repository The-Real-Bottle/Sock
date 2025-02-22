package thebottle.sock.block.networking;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

@Environment(EnvType.SERVER)
public class SockC2SPacketReciever {
    public static void registerC2SReceivers() {
        ServerPlayNetworking.PlayPayloadHandler<SockC2SPackets.SockCraftPacket> craftSock = ((sockCraftPacket, context) -> {

        });

        ServerPlayNetworking.registerGlobalReceiver(SockC2SPackets.SockCraftPacket.PACKET_ID, craftSock);
    }
}
