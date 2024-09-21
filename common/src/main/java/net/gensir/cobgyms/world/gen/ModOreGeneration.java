package net.gensir.cobgyms.world.gen;

import dev.architectury.registry.level.biome.BiomeModifications;
import net.gensir.cobgyms.world.ModPlacedFeatures;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.gen.GenerationStep;

public class ModOreGeneration {
    public static void generateOres() {

        BiomeModifications.addProperties((ctx, mutable) -> {
            if(ctx.hasTag(BiomeTags.IS_OVERWORLD)){
                mutable.getGenerationProperties().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ANCIENT_RELIC_PLACED_KEY);
            }
        });

        BiomeModifications.addProperties((ctx, mutable) -> {
            if(ctx.hasTag(BiomeTags.IS_NETHER)){
                mutable.getGenerationProperties().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.NETHER_ANCIENT_RELIC_PLACED_KEY);
            }
        });

        BiomeModifications.addProperties((ctx, mutable) -> {
            if(ctx.hasTag(BiomeTags.IS_END)){
                mutable.getGenerationProperties().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.END_ANCIENT_RELIC_PLACED_KEY);
            }
        });

    }
}
