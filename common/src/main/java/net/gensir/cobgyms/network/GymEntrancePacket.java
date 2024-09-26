package net.gensir.cobgyms.network;

import net.gensir.cobgyms.CobGyms;
import net.gensir.cobgyms.block.entity.GymEntranceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public class GymEntrancePacket {
    public static void handleGymEntrancePacket(ServerPlayerEntity serverPlayer, ServerWorld serverWorld, int level, BlockPos pos, String theme){
        BlockEntity blockEntity = serverWorld.getBlockEntity(pos);
        if (blockEntity instanceof GymEntranceBlockEntity gymEntranceBlockEntity) {
            int timesUsed = gymEntranceBlockEntity.getTimesUsed(serverPlayer.getUuid());

            if (CobGyms.GYM_ENTRANCE_USAGES == -1 || (CobGyms.GYM_ENTRANCE_USAGES - timesUsed)>0){
                int response = GymHandler.initGym(serverPlayer, serverWorld, level, theme);
                if (response == 1){
                    gymEntranceBlockEntity.incrementTimesUsed(serverPlayer.getUuid());
                }
            } else {
                serverPlayer.sendMessage(Text.translatable("cobgyms.lang.menu.entrance.limit"));
            }
        }
    }
}
