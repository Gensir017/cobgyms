package net.gensir.cobgyms.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.gensir.cobgyms.block.entity.GymEntranceBlockEntity;
import net.gensir.cobgyms.block.entity.GymExitBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import static net.gensir.cobgyms.CobGyms.MOD_ID;

public class ModBlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(MOD_ID, RegistryKeys.BLOCK_ENTITY_TYPE);

    public static final RegistrySupplier<BlockEntityType<GymExitBlockEntity>> GYM_EXIT_ENTITY = BLOCK_ENTITY_TYPES.register(new Identifier(MOD_ID,"gym_exit_be"), GymExitBlockEntity.TYPE);

    public static final RegistrySupplier<BlockEntityType<GymEntranceBlockEntity>> GYM_ENTRANCE_ENTITY = BLOCK_ENTITY_TYPES.register(new Identifier(MOD_ID,"gym_entrance_be"), GymEntranceBlockEntity.TYPE);

    public static void initialize() {
        BLOCK_ENTITY_TYPES.register();
    }
}
