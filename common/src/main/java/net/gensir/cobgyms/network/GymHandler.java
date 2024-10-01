package net.gensir.cobgyms.network;

import net.gensir.cobgyms.CobGyms;
import net.gensir.cobgyms.gym.Gym;
import net.gensir.cobgyms.util.CachedDataClearer;
import net.gensir.cobgyms.util.JSONHandler;
import net.gensir.cobgyms.util.TeleportHelper;
import net.gensir.cobgyms.util.GymUtils;
import net.gensir.cobgyms.world.dimension.ModDimensions;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.WorldSavePath;
import net.minecraft.util.math.Vec3d;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;

public class GymHandler {

    public static int initGym(ServerPlayerEntity serverPlayer, ServerWorld serverWorld, int level, String fixedTheme) {
        long startTime = System.currentTimeMillis();

        if (level < 5) level = 5;
        if (level > 100) level = 100;


        ServerWorld cobGymDimension = Objects.requireNonNull(serverPlayer.getServer()).getWorld(ModDimensions.COBGYMS_LEVEL_KEY);
        if(serverWorld.getRegistryKey() != ModDimensions.COBGYMS_LEVEL_KEY){

            String playerUUID = serverPlayer.getUuidAsString();

            Path worldSavePath = serverPlayer.getServer().getSavePath(WorldSavePath.PLAYERDATA).getParent();
            String playerJSONpath = worldSavePath.resolve("cobgyms/" + playerUUID + ".json").toString();
            Map<String, Object> JSONcontent = JSONHandler.readJSON(playerJSONpath);

            boolean firstTime = JSONcontent.isEmpty();

            double adjustX = JSONHandler.getAdjustX(worldSavePath, playerUUID);
            JSONcontent.put("adjustX",adjustX);

            int intAdjustX = (int) Math.floor(adjustX);

            CachedDataClearer.clearTrainerCache(JSONcontent);

            String originalDim = serverWorld.getRegistryKey().getValue().toString();
            Vec3d originalPos = serverPlayer.getPos();

            Callable<Gym> selectedGymCallable;
            if (!Objects.equals(fixedTheme, "random")){
                selectedGymCallable = GymUtils.getFixedThemeGymCallable(fixedTheme);
            } else {
                selectedGymCallable = GymUtils.getRandomGymCallable();
            }
            try {
                Gym selectedGym = selectedGymCallable.call();
                Map<String, Object> gymData = selectedGym.buildGym(cobGymDimension, intAdjustX, level);

                if (gymData.get("relativePlayerSpawn") instanceof List<?> playerSpawn){
                    TeleportHelper.teleportPlayer(
                            serverPlayer,
                            cobGymDimension,
                            (double) playerSpawn.get(0) + intAdjustX,
                            (double) playerSpawn.get(1),
                            (double) playerSpawn.get(2),
                            (float) gymData.get("playerYaw"),
                            0.0F
                    );

                    if (firstTime){
                        serverPlayer.sendMessage(Text.translatable("cobgyms.lang.gym.first_time.part_one"));
                        serverPlayer.sendMessage(Text.translatable("cobgyms.lang.gym.first_time.part_two"));
                        serverPlayer.sendMessage(Text.translatable("cobgyms.lang.gym.first_time.part_three"));
                        serverPlayer.sendMessage(Text.translatable("cobgyms.lang.gym.first_time.part_four"));
                        serverPlayer.sendMessage(Text.translatable("cobgyms.lang.gym.first_time.part_five"));
                        serverPlayer.sendMessage(Text.translatable("cobgyms.lang.gym.first_time.part_six"));
                    }

                    JSONcontent.put("trainers", gymData.get("trainers"));
                    JSONcontent.put("adjustX", adjustX);
                    JSONcontent.put("originalDim", originalDim);

                    List<Double> parsedOriginalPos = new ArrayList<>();
                    parsedOriginalPos.add(originalPos.getX());
                    parsedOriginalPos.add(originalPos.getY());
                    parsedOriginalPos.add(originalPos.getZ());

                    JSONcontent.put("originalPos", parsedOriginalPos);
                    JSONHandler.writeJSON(JSONcontent, playerJSONpath);

                    long endTime = System.currentTimeMillis();
                    CobGyms.LOGGER.info("Gym initialised, took: "+(endTime-startTime)+"ms");

                    return 1;
                } else {
                    CobGyms.LOGGER.info("Issue with configured player spawn for gym.");
                    return -1;
                }

            } catch (Exception e) {
                CobGyms.LOGGER.info(String.valueOf(e));
                return -1;
            }

        } else {
            serverPlayer.sendMessage(Text.translatable("cobgyms.lang.message.cannot_inside_gym"));
        }

        return -1;

    }
}
