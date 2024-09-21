package net.gensir.cobgyms.delayUtils;

import net.minecraft.entity.player.PlayerEntity;

import java.util.Iterator;

import static net.gensir.cobgyms.CobGyms.delayedCalls;

public class QueueChecker {

    public static boolean isWaiting(PlayerEntity player){
        Iterator<DelayedCall> iterator = delayedCalls.iterator();
        while (iterator.hasNext()) {
            DelayedCall delayedCall = iterator.next();
            if (delayedCall.player == player){
                return true;
            }
        }
        return false;
    }

}
