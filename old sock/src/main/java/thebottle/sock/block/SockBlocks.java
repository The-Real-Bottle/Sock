package thebottle.sock.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

import static thebottle.sock.Util.of;

public abstract class SockBlocks {
    private static <T extends BlockEntity> BlockEntityType<T> register(String id, BlockEntityType<T> type) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, of(id), type);
    }

    private static <T extends Block> T register(String name, Function<AbstractBlock.Settings, T> blockFunction, AbstractBlock.Settings blockSettings, boolean registerItem) {
        Identifier identifier = of(name);
        RegistryKey<Block> blockRegistryKey = RegistryKey.of(RegistryKeys.BLOCK, identifier);
        T block = Registry.register(Registries.BLOCK, identifier, blockFunction.apply(blockSettings.registryKey(blockRegistryKey)));
        if (registerItem) {
            RegistryKey<Item> itemRegistryKey = RegistryKey.of(RegistryKeys.ITEM, identifier);
            Registry.register(Registries.ITEM, identifier, new BlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey().registryKey(itemRegistryKey)));
        }
        return block;
    }

    public static void init() {}
}
