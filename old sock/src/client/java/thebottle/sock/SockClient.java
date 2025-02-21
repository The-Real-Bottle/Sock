package thebottle.sock;

import dev.emi.trinkets.api.client.TrinketRenderer;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexRendering;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.renderer.GeoObjectRenderer;
import thebottle.sock.item.SockItems;
import thebottle.sock.model.SockGeoModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SockClient implements ClientModInitializer {
	private static final List<String> messages;

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		TrinketRendererRegistry.registerRenderer(SockItems.SOCK,
				(stack, slotReference, contextModel, matrices, vertexConsumers, light, state, limbAngle, limbDistance) -> {
                    if (contextModel instanceof BipedEntityModel<?> bipedEntityModel && state instanceof BipedEntityRenderState bipedEntityRenderState) {
                        GeoObjectRenderer<SingletonGeoAnimatable> renderer = new GeoObjectRenderer<>(new SockGeoModel("test"));

                        matrices.push();
						TrinketRenderer.translateToLeftLeg(matrices, bipedEntityModel, bipedEntityRenderState);
						
                        matrices.pop();
                    }
                }
		);

		Sock.LOGGER.info(messages.getFirst());
	}

	static {
		//Choose a random message
		messages = new ArrayList<>();
		messages.add("Hello");
		Collections.shuffle(messages);
	}
}