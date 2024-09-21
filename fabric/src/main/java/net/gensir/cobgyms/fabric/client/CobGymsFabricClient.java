package net.gensir.cobgyms.fabric.client;

import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.gensir.cobgyms.block.entity.GymExitBlockEntityRenderer;
import net.gensir.cobgyms.registry.ModBlockEntityRegistry;
import net.gensir.cobgyms.registry.ModBlockRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.render.RenderLayer;

public final class CobGymsFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlockRegistry.PERSISTENT_LEAVES.get(), RenderLayer.getCutout());

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            if (world == null || pos == null) {
                return FoliageColors.getDefaultColor();
            }
            return BiomeColors.getFoliageColor(world, pos);
        }, ModBlockRegistry.PERSISTENT_LEAVES_SHADED.get());

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            if (world == null || pos == null) {
                return FoliageColors.getDefaultColor();
            }
            return BiomeColors.getFoliageColor(world, pos);
        }, ModBlockRegistry.PERSISTENT_LEAVES.get());

        BlockEntityRendererRegistry.register(ModBlockEntityRegistry.GYM_EXIT_ENTITY.get(), GymExitBlockEntityRenderer::new);
    }
}
