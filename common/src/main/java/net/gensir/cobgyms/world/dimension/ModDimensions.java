package net.gensir.cobgyms.world.dimension;

import net.gensir.cobgyms.CobGyms;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ModDimensions {
    public static final RegistryKey<World> COBGYMS_LEVEL_KEY = RegistryKey.of(RegistryKeys.WORLD,
            new Identifier(CobGyms.MOD_ID, CobGyms.MOD_ID+"_dim"));

    public static void initialize(){
    }
}
