package net.gensir.cobgyms.world;

import net.gensir.cobgyms.CobGyms;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.PlacedFeature;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> ANCIENT_RELIC_PLACED_KEY = registerKey("ancient_relic_placed");
    public static final RegistryKey<PlacedFeature> NETHER_ANCIENT_RELIC_PLACED_KEY = registerKey("nether_ancient_relic_placed");
    public static final RegistryKey<PlacedFeature> END_ANCIENT_RELIC_PLACED_KEY = registerKey("end_ancient_relic_placed");


    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(CobGyms.MOD_ID, name));
    }

}