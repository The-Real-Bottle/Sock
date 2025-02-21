package thebottle.sock;

import dev.emi.trinkets.api.client.TrinketRenderer;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import thebottle.sock.item.SockItem;
import thebottle.sock.item.SockItems;
import thebottle.sock.model.SockRenderer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SockClient implements ClientModInitializer {
    private static final List<String> messages;

    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
//        TrinketRendererRegistry.registerRenderer(SockItems.SOCK,
//                (stack, slotReference, contextModel, matrices, vertexConsumers, light, state, limbAngle, limbDistance) -> {
//                    if (contextModel instanceof BipedEntityModel<?> bipedEntityModel && state instanceof BipedEntityRenderState bipedEntityRenderState) {
//                        // TODO: multiple socks
//                        SockRenderer renderer = new SockRenderer("animated_leaf_core");
//
//                        matrices.push();
//                        TrinketRenderer.translateToLeftLeg(matrices, bipedEntityModel, bipedEntityRenderState);
//                        renderer.defaultRender(
//                                matrices,
//                                SockItems.SOCK,
//                                vertexConsumers,
//                                null,
//                                null,
//                                MinecraftClient.getInstance().getRenderTime(),
//                                light
//                        );
//
//                        matrices.pop();
//                    }
//                }
//        );

        Sock.LOGGER.info(messages.getFirst());
    }

    static {
        //Choose a random message
        messages = new ArrayList<>();
        messages.add("Hello");
        Collections.shuffle(messages);
    }
}