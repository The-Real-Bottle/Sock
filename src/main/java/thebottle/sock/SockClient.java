package thebottle.sock;

import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import thebottle.sock.item.SockItems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SockClient implements ClientModInitializer {
    private static final List<String> messages;

    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        TrinketRendererRegistry.registerRenderer(SockItems.SOCK, SockItems.SOCK);
        TrinketRendererRegistry.registerRenderer(SockItems.BLUE_SOCK, SockItems.BLUE_SOCK);

        Sock.LOGGER.info(messages.getFirst());
    }

    static {
        //Choose a random message
        messages = new ArrayList<>();
        messages.add("Hello");
        Collections.shuffle(messages);
    }
}