package net.gensir.cobgyms.network;

import dev.architectury.networking.NetworkManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static net.gensir.cobgyms.CobGyms.MOD_ID;


public class ServerPacketHandler {
    public static final Identifier GYM_KEY_PACKET_ID = new Identifier(MOD_ID, "gym_key");
    public static final Identifier LEAVE_GYM_PACKET_ID = new Identifier(MOD_ID, "leave_gym");
    public static final Identifier SPAWN_SCALED_PACKET_ID = new Identifier(MOD_ID, "spawn_scaled");
    public static final Identifier GYM_ENTRANCE_PACKET_ID = new Identifier(MOD_ID, "gym_entrance");
    public static final Identifier CACHE_OPEN_PACKET_ID = new Identifier(MOD_ID, "cache_open");
    public static final Identifier CACHE_LIST_PACKET_ID = new Identifier(MOD_ID, "cache_list");

    public static void register() {
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, GYM_KEY_PACKET_ID, (buf, context) -> {
            int level = buf.readInt();
            PlayerEntity player = context.getPlayer();
            World world = player.getWorld();

            if (player instanceof ServerPlayerEntity serverPlayer && world instanceof ServerWorld serverWorld){
                serverWorld.getServer().execute(() -> GymKeyPacket.handleGymKeyPacket(serverPlayer, serverWorld, level));
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
            String theme = buf.readString();

            PlayerEntity player = context.getPlayer();
            World world = player.getWorld();

            if (player instanceof ServerPlayerEntity serverPlayer && world instanceof ServerWorld serverWorld){
                serverWorld.getServer().execute(() -> GymEntrancePacket.handleGymEntrancePacket(serverPlayer, serverWorld, level, pos, theme));
            }
        });

        NetworkManager.registerReceiver(NetworkManager.Side.C2S, CACHE_OPEN_PACKET_ID, (buf, context) -> {
            String cacheRarity = buf.readString();
            String cacheTheme = buf.readString();
            boolean increasedShinyChance = buf.readBoolean();

            PlayerEntity player = context.getPlayer();
            World world = player.getWorld();

            if (player instanceof ServerPlayerEntity serverPlayer && world instanceof ServerWorld serverWorld){
                serverWorld.getServer().execute(() -> CacheOpenPacket.handleCacheOpenPacket(serverPlayer, cacheRarity, cacheTheme, increasedShinyChance));
            }
        });


        NetworkManager.registerReceiver(NetworkManager.Side.C2S, CACHE_LIST_PACKET_ID, (buf, context) -> {
            String cacheRarity = buf.readString();
            String cacheTheme = buf.readString();

            PlayerEntity player = context.getPlayer();
            World world = player.getWorld();

            if (player instanceof ServerPlayerEntity serverPlayer && world instanceof ServerWorld serverWorld){
                serverWorld.getServer().execute(() -> CacheListPacket.handleCacheListPacket(serverPlayer, cacheRarity, cacheTheme));
            }
        });
    }
}
