package net.gensir.cobgyms.registry;

import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.gensir.cobgyms.CobGyms;
import net.gensir.cobgyms.block.entity.GymExitBlockEntity;
import net.gensir.cobgyms.block.entity.GymExitBlockEntityRenderer;
import net.gensir.cobgyms.entity.TrainerVillager;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import static net.gensir.cobgyms.CobGyms.MOD_ID;

public class ModBlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(MOD_ID, RegistryKeys.BLOCK_ENTITY_TYPE);

    public static final RegistrySupplier<BlockEntityType<GymExitBlockEntity>> GYM_EXIT_ENTITY = BLOCK_ENTITY_TYPES.register(new Identifier(MOD_ID,"gym_exit_be"), GymExitBlockEntity.TYPE);

    public static void initialize() {
        BLOCK_ENTITY_TYPES.register();
    }
}
