package net.gensir.cobgyms.delayUtils;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.function.Consumer;

public class DelayedCall {
    private final Consumer<DelayedCall> function;
    public int timer;
    public int delayTime;
    public ServerPlayerEntity player;
    public BlockPos pos;

    public DelayedCall(int delayTime, ServerPlayerEntity player, Consumer<DelayedCall> function, BlockPos pos){
        this.delayTime = delayTime;
        this.player = player;
        this.function = function;
        this.timer = 0;
        this.pos = pos;
    }

    public void executeFunction(DelayedCall arg) {
        if (function != null) {
            function.accept(arg);
        }
    }
}
