package net.cerulan.blockofsky.fabric.client;

import net.cerulan.blockofsky.fablike.client.BlockOfSkyFablikeClient;
import net.fabricmc.api.ClientModInitializer;

public class BlockOfSkyFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockOfSkyFablikeClient.init();
    }
}
