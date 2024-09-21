package net.gensir.cobgyms.registry;

import dev.architectury.core.fluid.ArchitecturyFlowingFluid;
import dev.architectury.core.fluid.ArchitecturyFluidAttributes;
import dev.architectury.core.fluid.SimpleArchitecturyFluidAttributes;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import static net.gensir.cobgyms.CobGyms.MOD_ID;

public class ModFluidRegistry {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(MOD_ID, RegistryKeys.FLUID);

    public static final ArchitecturyFluidAttributes SAFE_LAVA_ATTRIBUTES = SimpleArchitecturyFluidAttributes.ofSupplier(() -> ModFluidRegistry.SAFE_LAVA_FLOWING, () -> ModFluidRegistry.SAFE_LAVA)
            .convertToSource(true)
            .flowingTexture(new Identifier("block/lava_flow"))
            .sourceTexture(new Identifier("block/lava_still"))
            .blockSupplier(() -> ModBlockRegistry.SAFE_LAVA_BLOCK)
            .bucketItemSupplier(() -> ModItemRegistry.SAFE_LAVA_BUCKET);



    public static final RegistrySupplier<FlowableFluid> SAFE_LAVA = FLUIDS.register("safe_lava", () -> new ArchitecturyFlowingFluid.Source(SAFE_LAVA_ATTRIBUTES));

    public static final RegistrySupplier<Fluid> SAFE_LAVA_FLOWING = FLUIDS.register("flowing_safe_lava", () -> new ArchitecturyFlowingFluid.Flowing(SAFE_LAVA_ATTRIBUTES));


    public static void initialize() {
        FLUIDS.register();

    }
}
