package thebottle.sock.block.networking;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

@Environment(EnvType.CLIENT)
public class SockC2SPacketSender {
    public static void sendSockCraftPacket() {
        ClientPlayNetworking.send(new SockC2SPackets.SockCraftPacket());
    }
}
