package thebottle.sock.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import static thebottle.sock.Util.of;

public class SockSounds {
    public static final SoundEvent WATER_ADMINISTERED_EVENT = register("h2o_suit.water_administered.water");
    public static final SoundEvent H2O_ADMINISTERED_EVENT = register("h2o_suit.water_administered.h2o");
    public static final SoundEvent DIHYDROGEN_MONOXIDE_EVENT =  register("h2o_suit.water_administered.dihydrogen_monoxide");
    public static final SoundEvent DIHYDRIDO_OXYGEN_EVENT = register("h2o_suit.water_administered.dihydrido_oxygen");
    public static final SoundEvent DEUTERIUM_OXIDE_EVENT = register("h2o_suit.water_administered.deuterium_oxide");
    public static SoundEvent DRINK_THE_WATER_EVENT = register("the_bottle.drink_the_water");

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
