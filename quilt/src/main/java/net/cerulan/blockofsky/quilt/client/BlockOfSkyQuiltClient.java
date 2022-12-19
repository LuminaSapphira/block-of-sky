package net.cerulan.blockofsky.quilt.client;

import net.cerulan.blockofsky.fablike.client.BlockOfSkyFablikeClient;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class BlockOfSkyQuiltClient implements ClientModInitializer {
    @Override
    public void onInitializeClient(ModContainer mod) {
        BlockOfSkyFablikeClient.init();
    }
}
