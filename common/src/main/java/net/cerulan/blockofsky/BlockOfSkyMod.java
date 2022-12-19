package net.cerulan.blockofsky;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class BlockOfSkyMod {
    public static final String MOD_ID = "blockofsky";
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, Registry.BLOCK_REGISTRY);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registry.ITEM_REGISTRY);
    public static final DeferredRegister<BlockEntityType<?>> BE_TYPES = DeferredRegister.create(MOD_ID, Registry.BLOCK_ENTITY_TYPE_REGISTRY);

    private static final ResourceLocation sky_block = new ResourceLocation(MOD_ID, "sky_block");
    private static final ResourceLocation void_block = new ResourceLocation(MOD_ID, "void_block");
    public static final RegistrySupplier<Block> SKY_BLOCK = BLOCKS.register(sky_block, SkyBlock::new);
    public static final RegistrySupplier<Block> VOID_BLOCK = BLOCKS.register(void_block, SkyBlock.VoidBlock::new);
    public static final RegistrySupplier<BlockEntityType<SkyBlockEntity>> SKY_BE_TYPE = BE_TYPES.register(sky_block, () -> BlockEntityType.Builder.of(SkyBlockEntity::new, SKY_BLOCK.get(), VOID_BLOCK.get()).build(null));

    public static final RegistrySupplier<Item> SKY_BLOCK_ITEM = ITEMS.register(sky_block, () -> new BlockItem(SKY_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistrySupplier<Item> VOID_BLOCK_ITEM = ITEMS.register(void_block, () -> new BlockItem(VOID_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

    public static void init() {
        BLOCKS.register();
        ITEMS.register();
        BE_TYPES.register();
    }
}