package thebottle.sock;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SockClient implements ClientModInitializer {
	private static final List<String> messages;

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		Sock.LOGGER.info(messages.getFirst());
	}

	static {
		//Choose a random message
		messages = new ArrayList<>();
		messages.add("Hello");
		Collections.shuffle(messages);
	}
}