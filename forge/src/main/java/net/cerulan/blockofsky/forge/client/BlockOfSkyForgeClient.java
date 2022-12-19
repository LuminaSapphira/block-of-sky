package net.cerulan.blockofsky.forge.client;

import net.cerulan.blockofsky.client.BOSClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class BlockOfSkyForgeClient {

    @SubscribeEvent
    public void render(RenderLevelStageEvent event) {
        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_TRIPWIRE_BLOCKS)
            BOSClient.renderSky(new BOSClient.RenderData(event.getPoseStack(), event.getPartialTick(), event.getProjectionMatrix()));
    }

}
