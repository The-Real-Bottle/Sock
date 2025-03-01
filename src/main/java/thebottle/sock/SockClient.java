package thebottle.sock;

import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import thebottle.sock.block.SockBlocks;
import thebottle.sock.block.screen.SockHandledScreens;
import thebottle.sock.item.SockItems;
import thebottle.sock.model.TheBottleRenderers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SockClient implements ClientModInitializer {
    private static final List<String> messages;

    static {
        //Choose a random message
        messages = new ArrayList<>();
        messages.add("Hello");
        messages.add("Sock is sponsored by The Bottle™");
        messages.add("Wake up Mr. Freeman. Wake up and drink The Water.");
        messages.add("Water administered...");
        Collections.shuffle(messages);
    }

    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        TrinketRendererRegistry.registerRenderer(SockItems.BLUE_SOCK, SockItems.BLUE_SOCK);
        TrinketRendererRegistry.registerRenderer(SockItems.GREEN_SOCK, SockItems.GREEN_SOCK);
        TrinketRendererRegistry.registerRenderer(SockItems.VOID_SOCK, SockItems.VOID_SOCK);

        SockHandledScreens.register();
        BlockEntityRendererFactories.register(SockBlocks.THE_BOTTLE_ENTITY, context -> new TheBottleRenderers.TheBottleBlockRenderer());

        Sock.LOGGER.info(messages.getFirst());
    }
}