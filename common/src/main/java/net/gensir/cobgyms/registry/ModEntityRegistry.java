package net.gensir.cobgyms.registry;

import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.gensir.cobgyms.entity.TrainerVillager;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;

import static net.gensir.cobgyms.CobGyms.MOD_ID;

public class ModEntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(MOD_ID, RegistryKeys.ENTITY_TYPE);

    public static final RegistrySupplier<EntityType<TrainerVillager>> TRAINER_VILLAGER = ENTITY_TYPES.register("trainer_villager", TrainerVillager.TYPE);


    public static void initialize() {
        ENTITY_TYPES.register();
        EntityAttributeRegistry.register(TRAINER_VILLAGER, TrainerVillager::createVillagerAttributes);
    }
}
