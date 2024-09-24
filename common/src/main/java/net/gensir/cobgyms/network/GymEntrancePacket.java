package net.gensir.cobgyms.network;

import net.gensir.cobgyms.CobGyms;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GymEntrancePacket {
    public static void handleGymEntrancePacket(ServerPlayerEntity serverPlayer, ServerWorld serverWorld, int level, BlockPos pos){
//        serverPlayer.getServer().execute(() ->{
//
//            BlockEntity blockEntity = serverWorld.getBlockEntity(pos);
//            if (blockEntity instanceof GymEntranceBlockEntity gymEntranceBlockEntity) {
//                gymEntranceBlockEntity.incrementClickCount(serverPlayer.getUuid());
//            }
//        });
    }
}
