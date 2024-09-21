package net.gensir.cobgyms.forge;

import dev.architectury.platform.forge.EventBuses;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import net.gensir.cobgyms.block.entity.GymExitBlockEntityRenderer;
import net.gensir.cobgyms.registry.ModBlockEntityRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import net.gensir.cobgyms.CobGyms;

@Mod(CobGyms.MOD_ID)
public final class CobGymsForge {
    public CobGymsForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(CobGyms.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        CobGyms.init();

    }
}
