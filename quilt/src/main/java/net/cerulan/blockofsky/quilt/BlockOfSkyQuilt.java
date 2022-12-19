package net.cerulan.blockofsky.quilt;

import net.cerulan.blockofsky.fablike.BlockOfSkyFablike;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class BlockOfSkyQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        BlockOfSkyFablike.init();
    }
}
