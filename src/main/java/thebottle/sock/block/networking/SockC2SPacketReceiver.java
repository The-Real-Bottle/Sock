package thebottle.sock.block.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;
import thebottle.sock.block.screen.SockworkingTableScreenHandler;

public class SockC2SPacketReceiver {
    public static void registerC2SReceivers() {
        ServerPlayNetworking.PlayPayloadHandler<SockC2SPackets.SockCraftPacket> craftSock =
                (sockCraftPacket, context) -> {
                    PlayerEntity player = context.player();
                    ScreenHandler screen = player.currentScreenHandler;

                    if (!(screen instanceof SockworkingTableScreenHandler sockworkingTableScreenHandler)) return;

                    sockworkingTableScreenHandler.tryCraft(context.player().getServerWorld());
                };

        ServerPlayNetworking.registerGlobalReceiver(SockC2SPackets.SockCraftPacket.PACKET_ID, craftSock);
    }
}
