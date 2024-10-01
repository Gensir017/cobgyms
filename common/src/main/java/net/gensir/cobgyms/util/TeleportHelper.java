package net.gensir.cobgyms.util;

import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class TeleportHelper {
    public static void teleportPlayer(ServerPlayerEntity serverPlayer,
                                      ServerWorld targetDimension,
                                      double destX,
                                      double destY,
                                      double destZ,
                                      float yaw,
                                      float pitch){
        int xpLevels = serverPlayer.experienceLevel;
        float xpProgress = serverPlayer.experienceProgress;
        int totalExperience = serverPlayer.totalExperience;

        serverPlayer.teleport(
                targetDimension,
                destX,
                destY,
                destZ,
                PositionFlag.getFlags(1),
                yaw,
                pitch
        );

        serverPlayer.setExperienceLevel(xpLevels);
        serverPlayer.experienceProgress = xpProgress;
        serverPlayer.totalExperience = totalExperience;
    }
}
