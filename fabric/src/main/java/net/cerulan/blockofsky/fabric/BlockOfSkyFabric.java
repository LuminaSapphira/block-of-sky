package net.cerulan.blockofsky.fabric;

import net.cerulan.blockofsky.fablike.BlockOfSkyFablike;
import net.fabricmc.api.ModInitializer;

public class BlockOfSkyFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        BlockOfSkyFablike.init();
    }
}
