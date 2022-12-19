package net.cerulan.blockofsky.forge;

import dev.architectury.platform.forge.EventBuses;
import net.cerulan.blockofsky.BlockOfSkyMod;
import net.cerulan.blockofsky.client.BOSClient;
import net.cerulan.blockofsky.forge.client.BlockOfSkyForgeClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BlockOfSkyMod.MOD_ID)
public class BlockOfSkyForge {
    public BlockOfSkyForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(BlockOfSkyMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientInit);
        BlockOfSkyMod.init();
    }

    @OnlyIn(Dist.CLIENT)
    public void clientInit(FMLClientSetupEvent setupEvent) {
        MinecraftForge.EVENT_BUS.register(new BlockOfSkyForgeClient());
        BOSClient.init();
    }
}