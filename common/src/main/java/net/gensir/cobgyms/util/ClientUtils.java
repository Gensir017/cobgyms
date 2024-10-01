package net.gensir.cobgyms.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.gensir.cobgyms.gui.GymCacheScreen;
import net.gensir.cobgyms.gui.GymEntranceScreen;
import net.gensir.cobgyms.gui.GymKeyScreen;
import net.gensir.cobgyms.gui.LeaveGymScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

@Environment(EnvType.CLIENT)
public class ClientUtils {
    @Environment(EnvType.CLIENT)
    public static void openStartGymScreen(PlayerEntity player) {
        MinecraftClient.getInstance().execute(() -> {
            MinecraftClient.getInstance().setScreen(new GymKeyScreen(player));
        });
    }

    @Environment(EnvType.CLIENT)
    public static void openLeaveGymScreen() {
        MinecraftClient.getInstance().execute(() -> {
            MinecraftClient.getInstance().setScreen(new LeaveGymScreen());
        });
    }

    @Environment(EnvType.CLIENT)
    public static void openGymEntranceScreen(int timesUsed, BlockPos pos, String theme, PlayerEntity player) {
        MinecraftClient.getInstance().execute(() -> {
            MinecraftClient.getInstance().setScreen(new GymEntranceScreen(timesUsed, pos, theme, player));
        });
    }

    @Environment(EnvType.CLIENT)
    public static void openGymCacheScreen(String rarity, boolean increasedShinyChance) {
        MinecraftClient.getInstance().execute(() -> {
            MinecraftClient.getInstance().setScreen(new GymCacheScreen(rarity, increasedShinyChance));
        });
    }
}
