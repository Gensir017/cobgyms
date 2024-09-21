package net.gensir.cobgyms.delayUtils;

import net.gensir.cobgyms.util.JSONHandler;
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
        ServerPlayerEntity player = delayedCall.player;
        Path worldSavePath = player.getServer().getSavePath(WorldSavePath.PLAYERDATA).getParent();
        String playerJSONpath = worldSavePath.resolve("cobgyms/" + player.getUuidAsString() + ".json").toString();
        Map<String, Object> JSONcontent = JSONHandler.readJSON(playerJSONpath);

        if(JSONcontent.isEmpty() || !JSONcontent.containsKey("originalDim") || !JSONcontent.containsKey("originalPos")){
            ServerWorld overworld = player.getServer().getOverworld();
            BlockPos overworldSpawn = overworld.getSpawnPos();

            player.teleport(
                    overworld,
                    overworldSpawn.getX(),
                    overworldSpawn.getY(),
                    overworldSpawn.getZ(),
                    PositionFlag.getFlags(1),
                    0.0F,
                    0.0F);
        } else {
            ServerWorld originalDim = (player.getServer().getWorld(RegistryKey.of(RegistryKeys.WORLD, new Identifier(JSONcontent.get("originalDim").toString()))));
            String[] originalPos = JSONcontent.get("originalPos").toString().split(",");

            player.teleport(
                    originalDim,
                    Double.parseDouble(originalPos[0]),
                    Double.parseDouble(originalPos[1]),
                    Double.parseDouble(originalPos[2]),
                    PositionFlag.getFlags(1),
                    0.0F,
                    0.0F);
        }
    }

}
