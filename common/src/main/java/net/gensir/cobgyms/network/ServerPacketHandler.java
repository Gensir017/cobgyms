package net.gensir.cobgyms.network;

import dev.architectury.networking.NetworkManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

import static net.gensir.cobgyms.CobGyms.MOD_ID;


public class ServerPacketHandler {
    public static final Identifier START_GYM_PACKET_ID = new Identifier(MOD_ID, "start_gym");
    public static final Identifier LEAVE_GYM_PACKET_ID = new Identifier(MOD_ID, "leave_gym");
    public static final Identifier SPAWN_SCALED_PACKET_ID = new Identifier(MOD_ID, "spawn_scaled");
    public static final Identifier GYM_ENTRANCE_PACKET_ID = new Identifier(MOD_ID, "gym_entrance");

    public static void register() {
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, START_GYM_PACKET_ID, (buf, context) -> {
            int level = buf.readInt();
            PlayerEntity player = context.getPlayer();
            World world = player.getWorld();

            if (player instanceof ServerPlayerEntity serverPlayer && world instanceof ServerWorld serverWorld){
                serverWorld.getServer().execute(() -> StartGymPacket.handleStartGymPacket(serverPlayer, serverWorld, level));
            } else {
                player.sendMessage(Text.translatable("cobgyms.lang.message.no_response"));
            }
        });

        NetworkManager.registerReceiver(NetworkManager.Side.C2S, LEAVE_GYM_PACKET_ID, (buf, context) -> {
            PlayerEntity player = context.getPlayer();
            World world = player.getWorld();

            if (player instanceof ServerPlayerEntity serverPlayer && world instanceof ServerWorld serverWorld){
                serverWorld.getServer().execute(() -> LeaveGymPacket.handleLeaveGymPacket(serverPlayer, serverWorld));
            } else {
                player.sendMessage(Text.translatable("cobgyms.lang.message.no_response"));
            }
        });

        NetworkManager.registerReceiver(NetworkManager.Side.C2S, SPAWN_SCALED_PACKET_ID, (buf, context) -> {
            PlayerEntity player = context.getPlayer();
            World world = player.getWorld();

            if (player instanceof ServerPlayerEntity serverPlayer && world instanceof ServerWorld serverWorld){
                serverWorld.getServer().execute(() -> SpawnScaledPacket.handleSpawnScaledPacket(serverPlayer, serverWorld));
            }
        });

        NetworkManager.registerReceiver(NetworkManager.Side.C2S, GYM_ENTRANCE_PACKET_ID, (buf, context) -> {

            int level = buf.readInt();
            BlockPos pos = buf.readBlockPos();

            PlayerEntity player = context.getPlayer();
            World world = player.getWorld();

            if (player instanceof ServerPlayerEntity serverPlayer && world instanceof ServerWorld serverWorld){
                serverWorld.getServer().execute(() -> GymEntrancePacket.handleGymEntrancePacket(serverPlayer, serverWorld, level, pos));
            }
        });
    }
}
