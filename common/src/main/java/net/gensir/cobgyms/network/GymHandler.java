package net.gensir.cobgyms.network;

import net.gensir.cobgyms.CobGyms;
import net.gensir.cobgyms.registry.ModItemRegistry;
import net.gensir.cobgyms.util.CachedDataClearer;
import net.gensir.cobgyms.util.JSONHandler;
import net.gensir.cobgyms.util.TeleportHelper;
import net.gensir.cobgyms.util.ThemeUtils;
import net.gensir.cobgyms.world.dimension.ModDimensions;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.server.command.TeleportCommand;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.WorldSavePath;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class GymHandler {
    private static final Random random = new Random();

    @SuppressWarnings("unchecked")
    public static int initGym(ServerPlayerEntity serverPlayer, ServerWorld serverWorld, int level, String fixedTheme) {

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

            CachedDataClearer.clearTrainerCache(JSONcontent);

            String originalDim = serverWorld.getRegistryKey().getValue().toString();
            Vec3d originalPos = serverPlayer.getPos();
            String originalPosString = originalPos.getX() + "," + originalPos.getY() + "," + originalPos.getZ();

            int gymIndex;
            if (!Objects.equals(fixedTheme, "random")){
                gymIndex = ThemeUtils.getFixedThemeGymIndex(fixedTheme);
            } else {
                gymIndex = ThemeUtils.getRandomGymIndex();
            }

            Map<String, Object> gymData = CobGyms.GymRegistry.get(gymIndex).buildGym(
                    level,
                    cobGymDimension,
                    adjustX);

            TeleportHelper.teleportPlayer(
                    serverPlayer,
                    cobGymDimension,
                    ((List<Double>) gymData.get("playerSpawn")).get(0) + adjustX,
                    ((List<Double>) gymData.get("playerSpawn")).get(1),
                    ((List<Double>) gymData.get("playerSpawn")).get(2),
                    ((List<Float>) gymData.get("playerRotations")).get(0),
                    ((List<Float>) gymData.get("playerRotations")).get(1)
            );


            if (firstTime){
                serverPlayer.sendMessage(Text.translatable("cobgyms.lang.gym.first_time.part_one"));
                serverPlayer.sendMessage(Text.translatable("cobgyms.lang.gym.first_time.part_two"));
                serverPlayer.sendMessage(Text.translatable("cobgyms.lang.gym.first_time.part_three"));
                serverPlayer.sendMessage(Text.translatable("cobgyms.lang.gym.first_time.part_four"));
                serverPlayer.sendMessage(Text.translatable("cobgyms.lang.gym.first_time.part_five"));
            }

            JSONcontent.put("trainers", gymData.get("trainers").toString());
            JSONcontent.put("adjustX", adjustX);
            JSONcontent.put("originalDim", originalDim);
            JSONcontent.put("originalPos", originalPosString);

            JSONHandler.writeJSON(JSONcontent, playerJSONpath);


            return 1;

        } else {
            serverPlayer.sendMessage(Text.translatable("cobgyms.lang.message.cannot_inside_gym"));
        }

        return -1;

    }
}
