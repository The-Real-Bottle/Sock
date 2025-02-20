package thebottle.sock;

import net.minecraft.util.Identifier;

public final class Util {
    private Util() {}

    public static Identifier of(String path) {
        return Identifier.of(Sock.MOD_ID, path);
    }
}
