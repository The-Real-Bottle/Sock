package thebottle.sock.item;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import static thebottle.sock.Util.of;

public abstract class SockItems {
    public static final SockItem BLUE_SOCK = register(
            "blue_sock",
            SockItem::new,
            new Item.Settings().enchantable(5),
            new SockItem.SockData(
                    "blue",
                    List.of(
                            new Pair<>(
                                    EntityAttributes.MAX_HEALTH,
                                    new EntityAttributeModifier(
                                            of("sock.max_health"),
                                            1,
                                            EntityAttributeModifier.Operation.ADD_VALUE
                                    )
                            )
                    )
            )
    );

    public static final SockItem GREEN_SOCK = register(
            "green_sock",
            SockItem::new,
            new Item.Settings().enchantable(3),
            new SockItem.SockData(
                    "green",
                    List.of(
                            new Pair<>(
                                    EntityAttributes.ATTACK_DAMAGE,
                                    new EntityAttributeModifier(
                                            of("sock.attack_damage"),
                                            2,
                                            EntityAttributeModifier.Operation.ADD_VALUE
                                    )
                            ),
                            new Pair<>(
                                    EntityAttributes.ATTACK_SPEED,
                                    new EntityAttributeModifier(
                                            of("sock.attack_speed"),
                                            0.2,
                                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                                    )
                            )
                    )
            )
    );

    public static final SockItem VOID_SOCK = register(
            "void_sock",
            SockItem::new,
            new Item.Settings().enchantable(7),
            new SockItem.SockData(
                    "void",
                    List.of(
                            new Pair<>(
                                    EntityAttributes.GRAVITY,
                                    new EntityAttributeModifier(
                                            of("sock.gravity"),
                                            -0.33,
                                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                                    )
                            ),
                            new Pair<>(
                                    EntityAttributes.OXYGEN_BONUS,
                                    new EntityAttributeModifier(
                                            of("sock.oxygen_bonus"),
                                            2,
                                            EntityAttributeModifier.Operation.ADD_VALUE
                                    )
                            ),
                            new Pair<>(
                                    EntityAttributes.JUMP_STRENGTH,
                                    new EntityAttributeModifier(
                                            of("sock.jump_strength"),
                                            0.1,
                                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                                    )
                            )
                    )
            )
    );

    private static <T extends Item> T register(String name, Function<Item.Settings, T> itemFunction, Item.Settings settings) {
        Identifier id = of(name);
        RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, id);
        return Registry.register(
                Registries.ITEM,
                id,
                itemFunction.apply(settings.registryKey(registryKey))
        );
    }

    //Extended register so we can still use ItemClass::new for items that need more than just settings
    //If you need more than 1 extra object of data idk use a record or something
    private static <T extends Item, S> T register(String name, BiFunction<Item.Settings, S, T> itemFunctionWithExtraData, Item.Settings settings, S extraData) {
        Identifier id = of(name);
        RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, id);
        return Registry.register(
                Registries.ITEM,
                id,
                itemFunctionWithExtraData.apply(settings.registryKey(registryKey), extraData)
        );
    }

    public static void init() {
    }
}
