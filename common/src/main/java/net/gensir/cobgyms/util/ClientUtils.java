package net.gensir.cobgyms.util;

import net.gensir.cobgyms.gui.GymEntranceScreen;
import net.gensir.cobgyms.gui.StartGymScreen;
import net.gensir.cobgyms.gui.LeaveGymScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;

public class ClientUtils {
    public static void openStartGymScreen() {
        MinecraftClient.getInstance().execute(() -> {
            MinecraftClient.getInstance().setScreen(new StartGymScreen());
        });
    }

    public static void openLeaveGymScreen() {
        MinecraftClient.getInstance().execute(() -> {
            MinecraftClient.getInstance().setScreen(new LeaveGymScreen());
        });
    }

    public static void openGymEntranceScreen(int timesUsed, BlockPos pos) {
        MinecraftClient.getInstance().execute(() -> {
            MinecraftClient.getInstance().setScreen(new GymEntranceScreen(timesUsed, pos));
        });
    }
}
