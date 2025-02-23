package thebottle.sock.block.screen;

import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class SockHandledScreens {
    public static void register() {
        HandledScreens.register(SockScreenhandlerTypes.SOCKWORKING_TABLE, SockworkingTableScreen::new);
    }
}
