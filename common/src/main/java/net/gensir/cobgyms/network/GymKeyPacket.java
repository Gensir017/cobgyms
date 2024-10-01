package net.gensir.cobgyms.network;

import net.gensir.cobgyms.CobGyms;
import net.gensir.cobgyms.registry.ModItemRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

public class GymKeyPacket {
    public static void handleGymKeyPacket(ServerPlayerEntity serverPlayer, ServerWorld serverWorld, int level){
        ItemStack itemStack = serverPlayer.getMainHandStack();
        if (itemStack.getItem() == ModItemRegistry.GYM_KEY.get()){
            int response = GymHandler.initGym(serverPlayer, serverWorld, level, "random");
            if (response == 1){
                itemStack.decrement(1);
            }
        } else {
            serverPlayer.sendMessage(Text.of("Gym key must be in your main hand"));
        }
    }
}
