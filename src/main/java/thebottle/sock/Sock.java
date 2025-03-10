package thebottle.sock;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import thebottle.sock.block.SockBlocks;
import thebottle.sock.block.networking.SockC2SPacketReceiver;
import thebottle.sock.block.networking.SockPayloadTypes;
import thebottle.sock.block.screen.SockScreenhandlerTypes;
import thebottle.sock.enchantment.SockEnchantments;
import thebottle.sock.item.SockItems;
import thebottle.sock.item.TheBottleItem;
import thebottle.sock.item.groups.SockItemGroups;
import thebottle.sock.recipe.SockRecipes;
import thebottle.sock.sound.SockSounds;

public class Sock implements ModInitializer {
    public static final String MOD_ID = "sock";

    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        SockSounds.init();
        SockScreenhandlerTypes.init();
        SockSounds.init();
        SockBlocks.init();
        SockItems.init();
        TheBottleItem.registerCauldronHandler();
        SockRecipes.init();
        SockEnchantments.init();
        SockItemGroups.init();

        SockPayloadTypes.registerC2SPayloadTypes();
        SockC2SPacketReceiver.registerC2SReceivers();
    }
}
