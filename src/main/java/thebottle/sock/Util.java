package thebottle.sock;

import net.minecraft.util.Identifier;

public final class Util {
    private Util() {
    }

    public static Identifier of(String path) {
        return Identifier.of(Sock.MOD_ID, path);
    }

    public static Identifier ofBlock(String path) {
        return Identifier.of(Sock.MOD_ID, "block/" + path);
    }
}
