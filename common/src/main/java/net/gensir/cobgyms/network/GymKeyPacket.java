package net.gensir.cobgyms.network;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class GymKeyPacket {
    public static void handleGymKeyPacket(ServerPlayerEntity serverPlayer, ServerWorld serverWorld, int level){
        int response = GymHandler.initGym(serverPlayer, serverWorld, level, "random");
        if (response == 1){
            serverPlayer.getMainHandStack().decrement(1);
        }
    }
}
