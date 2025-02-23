package thebottle.sock.block.networking;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

import static thebottle.sock.Util.of;

public class SockC2SPackets {
    public record SockCraftPacket() implements CustomPayload {
        public static final CustomPayload.Id<SockCraftPacket> PACKET_ID = new CustomPayload.Id<>(of("sockworking_table_craft"));
        public static final PacketCodec<RegistryByteBuf, SockCraftPacket> PACkET_CODEC = PacketCodec.of((value, buf) -> {
            }, buf -> new SockCraftPacket());

        @Override
        public Id<? extends CustomPayload> getId() {
            return PACKET_ID;
        }
    }
}
