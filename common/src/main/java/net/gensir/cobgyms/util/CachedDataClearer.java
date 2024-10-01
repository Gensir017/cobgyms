package net.gensir.cobgyms.util;

import com.selfdot.cobblemontrainers.CobblemonTrainers;
import net.gensir.cobgyms.entity.TrainerVillager;
import net.minecraft.entity.Entity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.util.*;

public class CachedDataClearer {
    public static void clearTrainerCache(Map<String, Object> JSONcontent) {
        if (JSONcontent.get("trainers") instanceof List<?> trainers) {
            for (Object trainerUUID : trainers) {
                CobblemonTrainers.INSTANCE.getTrainerRegistry().removeTrainer((String) trainerUUID);
            }
        }
    }

    public static void clearVillagerCache(ServerWorld world, Map<String, Object> JSONcontent) {
        if (JSONcontent.containsKey("adjustX")) {
            double adjustX = (double) JSONcontent.get("adjustX");
            Box box = new Box(new BlockPos((int) Math.floor(adjustX), 0, 0), new BlockPos((int) Math.floor(adjustX + 48), 48, 48));

            List<TrainerVillager> trainerVillagers = world.getEntitiesByClass(TrainerVillager.class, box, EntityPredicates.VALID_ENTITY);
            trainerVillagers.forEach(Entity::discard);
        }
    }
}
