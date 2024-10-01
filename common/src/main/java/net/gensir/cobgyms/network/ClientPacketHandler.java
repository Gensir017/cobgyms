package net.gensir.cobgyms.network;

import dev.architectury.networking.NetworkManager;
import net.gensir.cobgyms.util.ClientUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import static net.gensir.cobgyms.CobGyms.MOD_ID;

public class ClientPacketHandler {
    public static final Identifier GYM_ENTRANCE_SCREEN_PACKET_ID = new Identifier(MOD_ID, "gym_entrance_screen");

    public static void register() {
        NetworkManager.registerReceiver(NetworkManager.Side.S2C, GYM_ENTRANCE_SCREEN_PACKET_ID, (buf, context) -> {
            int timesUsed = buf.readInt();
            BlockPos pos = buf.readBlockPos();
            String theme = buf.readString();
            PlayerEntity player = context.getPlayer();

            ClientUtils.openGymEntranceScreen(timesUsed, pos, theme, player);
        });

    }
}
