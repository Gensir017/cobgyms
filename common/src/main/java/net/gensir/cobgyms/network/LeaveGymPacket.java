package net.gensir.cobgyms.network;

import net.gensir.cobgyms.delayUtils.DelayedCall;
import net.gensir.cobgyms.delayUtils.DelayedFunctions;
import net.gensir.cobgyms.delayUtils.QueueChecker;
import net.gensir.cobgyms.util.CachedDataClearer;
import net.gensir.cobgyms.util.JSONHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.WorldSavePath;

import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;

import static net.gensir.cobgyms.CobGyms.delayedCalls;
import static net.gensir.cobgyms.world.dimension.ModDimensions.COBGYMS_LEVEL_KEY;

public class LeaveGymPacket {
    public static void handleLeaveGymPacket(ServerPlayerEntity player, ServerWorld serverWorld){

        if (serverWorld.getRegistryKey() == COBGYMS_LEVEL_KEY && !QueueChecker.isWaiting(player)) {
            Path worldSavePath = Objects.requireNonNull(player.getServer()).getSavePath(WorldSavePath.PLAYERDATA).getParent();
            String playerJSONpath = worldSavePath.resolve("cobgyms/" + player.getUuidAsString() + ".json").toString();
            Map<String, Object> JSONcontent = JSONHandler.readJSON(playerJSONpath);

            CachedDataClearer.clearVillagerCache(serverWorld, JSONcontent);
            CachedDataClearer.clearTrainerCache(JSONcontent);

            player.sendMessage(Text.translatable("cobgyms.lang.message.leaving_gym"));

            delayedCalls.add(new DelayedCall(100, player, DelayedFunctions::leaveGym, null));
        }

    }
}
