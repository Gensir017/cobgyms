package net.gensir.cobgyms.delayUtils;

import net.gensir.cobgyms.util.JSONHandler;
import net.gensir.cobgyms.util.TeleportHelper;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.WorldSavePath;
import net.minecraft.util.math.BlockPos;

import java.nio.file.Path;
import java.util.Map;

public class DelayedFunctions {
    public static void leaveGym(DelayedCall delayedCall){
        ServerPlayerEntity serverPlayer = delayedCall.player;
        Path worldSavePath = serverPlayer.getServer().getSavePath(WorldSavePath.PLAYERDATA).getParent();
        String playerJSONpath = worldSavePath.resolve("cobgyms/" + serverPlayer.getUuidAsString() + ".json").toString();
        Map<String, Object> JSONcontent = JSONHandler.readJSON(playerJSONpath);

        if(JSONcontent.isEmpty() || !JSONcontent.containsKey("originalDim") || !JSONcontent.containsKey("originalPos")){
            ServerWorld overworld = serverPlayer.getServer().getOverworld();
            BlockPos overworldSpawn = overworld.getSpawnPos();

            TeleportHelper.teleportPlayer(
                    serverPlayer,
                    overworld,
                    overworldSpawn.getX(),
                    overworldSpawn.getY(),
                    overworldSpawn.getZ(),
                    0.0F,
                    0.0F);

        } else {
            ServerWorld originalDim = (serverPlayer.getServer().getWorld(RegistryKey.of(RegistryKeys.WORLD, new Identifier(JSONcontent.get("originalDim").toString()))));
            String[] originalPos = JSONcontent.get("originalPos").toString().split(",");

            TeleportHelper.teleportPlayer(serverPlayer,
                    originalDim,
                    Double.parseDouble(originalPos[0]),
                    Double.parseDouble(originalPos[1]),
                    Double.parseDouble(originalPos[2]),
                    0.0F,
                    0.0F);
        }
    }

}
