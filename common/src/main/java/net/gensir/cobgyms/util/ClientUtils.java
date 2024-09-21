package net.gensir.cobgyms.util;

import net.gensir.cobgyms.gui.StartGymScreen;
import net.gensir.cobgyms.gui.LeaveGymScreen;
import net.minecraft.client.MinecraftClient;

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
}
