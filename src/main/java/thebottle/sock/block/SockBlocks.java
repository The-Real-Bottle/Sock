package thebottle.sock.block;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.TintedGlassBlock;
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
    public static final String THE_BOTTLE_ID = "the_bottle";
    public static final TheBottle THE_BOTTLE = register(THE_BOTTLE_ID, TheBottle::new, AbstractBlock.Settings.copy(Blocks.TINTED_GLASS));
    public static final BlockEntityType<TheBottleEntity> THE_BOTTLE_ENTITY = register(THE_BOTTLE_ID, FabricBlockEntityTypeBuilder.create(TheBottleEntity::new, THE_BOTTLE).build());

    private static <T extends BlockEntity> BlockEntityType<T> register(String id, BlockEntityType<T> type) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, of(id), type);
    }

    private static <T extends Block> T register(String name, Function<AbstractBlock.Settings, T> blockFunction, AbstractBlock.Settings blockSettings) {
        Identifier identifier = of(name);
        RegistryKey<Block> blockRegistryKey = RegistryKey.of(RegistryKeys.BLOCK, identifier);
        T block = Registry.register(Registries.BLOCK, identifier, blockFunction.apply(blockSettings.registryKey(blockRegistryKey)));
        return block;
    }

    public static void init() {
    }
}
