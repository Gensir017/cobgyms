package net.gensir.cobgyms.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.gensir.cobgyms.gui.GymEntranceScreen;
import net.gensir.cobgyms.gui.GymKeyScreen;
import net.gensir.cobgyms.gui.LeaveGymScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;

@Environment(EnvType.CLIENT)
public class ClientUtils {
    @Environment(EnvType.CLIENT)
    public static void openStartGymScreen() {
        MinecraftClient.getInstance().execute(() -> {
            MinecraftClient.getInstance().setScreen(new GymKeyScreen());
        });
    }

    @Environment(EnvType.CLIENT)
    public static void openLeaveGymScreen() {
        MinecraftClient.getInstance().execute(() -> {
            MinecraftClient.getInstance().setScreen(new LeaveGymScreen());
        });
    }

    @Environment(EnvType.CLIENT)
    public static void openGymEntranceScreen(int level, BlockPos pos, String theme) {
        MinecraftClient.getInstance().execute(() -> {
            MinecraftClient.getInstance().setScreen(new GymEntranceScreen(level, pos, theme));
        });
    }
}
