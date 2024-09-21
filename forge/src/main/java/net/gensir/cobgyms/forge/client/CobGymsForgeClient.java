package net.gensir.cobgyms.forge.client;

import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import net.gensir.cobgyms.block.entity.GymExitBlockEntityRenderer;
import net.gensir.cobgyms.registry.ModBlockEntityRegistry;
import net.gensir.cobgyms.registry.ModBlockRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = "cobgyms", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CobGymsForgeClient {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        BlockEntityRendererRegistry.register(ModBlockEntityRegistry.GYM_EXIT_ENTITY.get(), GymExitBlockEntityRenderer::new);

    }

    @SubscribeEvent
    public static void onRegisterBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((state, world, pos, tintIndex) -> {
            if (world == null || pos == null) {
                return FoliageColors.getDefaultColor();
            }
            return BiomeColors.getFoliageColor(world, pos);
        }, ModBlockRegistry.PERSISTENT_LEAVES_SHADED.get());

        event.register((state, world, pos, tintIndex) -> {
            if (world == null || pos == null) {
                return FoliageColors.getDefaultColor();
            }
            return BiomeColors.getFoliageColor(world, pos);
        }, ModBlockRegistry.PERSISTENT_LEAVES.get());
    }
}
