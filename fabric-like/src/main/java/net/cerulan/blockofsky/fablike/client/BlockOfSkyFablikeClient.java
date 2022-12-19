package net.cerulan.blockofsky.fablike.client;

import net.cerulan.blockofsky.client.BOSClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;

public class BlockOfSkyFablikeClient {
    public static void init() {
        BOSClient.init();
        WorldRenderEvents.END.register(context -> { BOSClient.renderSky(new BOSClient.RenderData(context.matrixStack(), context.tickDelta(), context.projectionMatrix())); });
    }
}
