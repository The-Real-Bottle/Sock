package thebottle.sock.sound;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import thebottle.sock.Sock;
import thebottle.sock.Util;

import java.util.function.Function;

import static thebottle.sock.Util.of;

public class SockSounds {
    public static SoundEvent DRINK_THE_WATER_EVENT = register("drink_the_water");

    private static SoundEvent register(String name) {
        Identifier id = of(name);
        SoundEvent event = SoundEvent.of(id);
        return Registry.register(
                Registries.SOUND_EVENT,
                id,
                event
        );
    }

    public static void init() {
    }
}
