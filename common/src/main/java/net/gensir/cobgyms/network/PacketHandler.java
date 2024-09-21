package net.gensir.cobgyms.network;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import dev.architectury.networking.NetworkManager;
import net.gensir.cobgyms.CobGyms;
import net.gensir.cobgyms.delayUtils.DelayedCall;
import net.gensir.cobgyms.delayUtils.DelayedFunctions;
import net.gensir.cobgyms.delayUtils.QueueChecker;
import net.gensir.cobgyms.registry.ModItemRegistry;
import net.gensir.cobgyms.util.CachedDataClearer;
import net.gensir.cobgyms.util.JSONHandler;
import net.gensir.cobgyms.world.dimension.ModDimensions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.WorldSavePath;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import static net.gensir.cobgyms.CobGyms.MOD_ID;
import static net.gensir.cobgyms.CobGyms.delayedCalls;
import static net.gensir.cobgyms.world.dimension.ModDimensions.COBGYMS_LEVEL_KEY;

public class PacketHandler {
    public static final Identifier START_GYM_PACKET_ID = new Identifier(MOD_ID, "start_gym");
    public static final Identifier LEAVE_GYM_PACKET_ID = new Identifier(MOD_ID, "leave_gym");
    public static final Identifier SPAWN_SCALED_PACKET_ID = new Identifier(MOD_ID, "spawn_scaled");
    private static final Random random = new Random();


    public static void register() {
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, START_GYM_PACKET_ID, (buf, context) -> {
            int level = buf.readInt();
            PlayerEntity player = context.getPlayer();
            World world = player.getWorld();

            if (player instanceof ServerPlayerEntity serverPlayer && world instanceof ServerWorld serverWorld){
                handleStartGymPacket(serverPlayer, serverWorld, level);
            } else {
                player.sendMessage(Text.translatable("cobgyms.lang.message.no_response"));
            }
        });

        NetworkManager.registerReceiver(NetworkManager.Side.C2S, LEAVE_GYM_PACKET_ID, (buf, context) -> {
            PlayerEntity player = context.getPlayer();
            World world = player.getWorld();

            if (player instanceof ServerPlayerEntity serverPlayer && world instanceof ServerWorld serverWorld){
                handleLeaveGymPacket(serverPlayer, serverWorld);
            } else {
                player.sendMessage(Text.translatable("cobgyms.lang.message.no_response"));
            }
        });

        NetworkManager.registerReceiver(NetworkManager.Side.C2S, SPAWN_SCALED_PACKET_ID, (buf, context) -> {
            PlayerEntity player = context.getPlayer();
            World world = player.getWorld();

            if (player instanceof ServerPlayerEntity serverPlayer && world instanceof ServerWorld serverWorld){
                handleSpawnScaledPacket(serverPlayer, serverWorld);
            }
        });
    }

    @SuppressWarnings("unchecked")
    private static void handleStartGymPacket(ServerPlayerEntity serverPlayer, ServerWorld serverWorld, int level) {

        if (level < 5) level = 5;
        if (level > 100) level = 100;


        ServerWorld cobGymDimension = Objects.requireNonNull(serverPlayer.getServer()).getWorld(ModDimensions.COBGYMS_LEVEL_KEY);
        if(serverWorld.getRegistryKey() != ModDimensions.COBGYMS_LEVEL_KEY){

            ItemStack itemStack = serverPlayer.getMainHandStack();
            if (!itemStack.isEmpty() && itemStack.getItem() == ModItemRegistry.GYM_KEY.get()) {
                String playerUUID = serverPlayer.getUuidAsString();

                Path worldSavePath = serverPlayer.getServer().getSavePath(WorldSavePath.PLAYERDATA).getParent();
                String playerJSONpath = worldSavePath.resolve("cobgyms/" + playerUUID + ".json").toString();
                Map<String, Object> JSONcontent = JSONHandler.readJSON(playerJSONpath);

                boolean firstTime = JSONcontent.isEmpty();

                double adjustX = JSONHandler.getAdjustX(worldSavePath, playerUUID);
                JSONcontent.put("adjustX",adjustX);

                CachedDataClearer.clearTrainerCache(JSONcontent);

                String originalDim = serverWorld.getRegistryKey().getValue().toString();
                Vec3d originalPos = serverPlayer.getPos();
                String originalPosString = originalPos.getX() + "," + originalPos.getY() + "," + originalPos.getZ();


                int randomIndex = random.nextInt(CobGyms.GymRegistry.size());

                Map<String, Object> gymData = CobGyms.GymRegistry.get(randomIndex).buildGym(
                        level,
                        cobGymDimension,
                        adjustX);

                serverPlayer.teleport(
                        cobGymDimension,
                        ((List<Double>) gymData.get("playerSpawn")).get(0) + adjustX,
                        ((List<Double>) gymData.get("playerSpawn")).get(1),
                        ((List<Double>) gymData.get("playerSpawn")).get(2),
                        PositionFlag.getFlags(1),
                        ((List<Float>) gymData.get("playerRotations")).get(0),
                        ((List<Float>) gymData.get("playerRotations")).get(1)
                );

                if (firstTime){
                    serverPlayer.sendMessage(Text.translatable("cobgyms.lang.gym.first_time.part_one"));
                    serverPlayer.sendMessage(Text.translatable("cobgyms.lang.gym.first_time.part_two"));
                    serverPlayer.sendMessage(Text.translatable("cobgyms.lang.gym.first_time.part_three"));
                    serverPlayer.sendMessage(Text.translatable("cobgyms.lang.gym.first_time.part_four"));
                    serverPlayer.sendMessage(Text.translatable("cobgyms.lang.gym.first_time.part_five"));
                }

                JSONcontent.put("trainers", gymData.get("trainers").toString());
                JSONcontent.put("adjustX", adjustX);
                JSONcontent.put("originalDim", originalDim);
                JSONcontent.put("originalPos", originalPosString);

                JSONHandler.writeJSON(JSONcontent, playerJSONpath);

                itemStack.decrement(1);
            }


        } else {
            serverPlayer.sendMessage(Text.translatable("cobgyms.lang.message.cannot_inside_gym"));
        }


    }

    public static void handleLeaveGymPacket(ServerPlayerEntity player, ServerWorld serverWorld){

        if (serverWorld.getRegistryKey() == COBGYMS_LEVEL_KEY && !QueueChecker.isWaiting(player)) {
            Path worldSavePath = Objects.requireNonNull(player.getServer()).getSavePath(WorldSavePath.PLAYERDATA).getParent();
            String playerJSONpath = worldSavePath.resolve("cobgyms/" + player.getUuidAsString() + ".json").toString();
            Map<String, Object> JSONcontent = JSONHandler.readJSON(playerJSONpath);

            CachedDataClearer.clearVillagerCache(serverWorld, JSONcontent);
            CachedDataClearer.clearTrainerCache(JSONcontent);

            player.sendMessage(Text.translatable("cobgyms.lang.message.leaving_gym"));

            delayedCalls.add(new DelayedCall(100, player, DelayedFunctions::leaveGym, null));
        }

    }

    private static void handleSpawnScaledPacket(ServerPlayerEntity player, ServerWorld serverWorld){

        Path worldSavePath = Objects.requireNonNull(player.getServer()).getSavePath(WorldSavePath.PLAYERDATA).getParent();
        String playerJSONpath = worldSavePath.resolve("cobgyms/" + player.getUuidAsString() + ".json").toString();
        Map<String, Object> JSONcontent = JSONHandler.readJSON(playerJSONpath);

        if(JSONcontent.containsKey("highestLevelBeaten")) {
            int highestLevelBeaten = ((Double) JSONcontent.get("highestLevelBeaten")).intValue();
            int selectedLevel = highestLevelBeaten - (5 + random.nextInt(6));

            if (selectedLevel > 0) {
                double playerX = player.getX();
                double playerY = player.getY();
                double playerZ = player.getZ();
                int range = 64;

                Box box = new Box(playerX - range, playerY - range, playerZ - range,
                        playerX + range, playerY + range, playerZ + range);

                List<PokemonEntity> nearbyPokemon = serverWorld.getEntitiesByClass(
                        PokemonEntity.class, box,
                        entity -> entity != null &&
                                !entity.isSpectator() &&
                                !entity.getPokemon().isPlayerOwned() &&
                                !entity.getPokemon().isFainted() &&
                                entity.getPokemon().isWild() &&
                                !entity.getPokemon().isUncatchable() &&
                                !entity.isBattling()
                );

                if (!nearbyPokemon.isEmpty()) {
                    List<PokemonEntity> highLevelPokemon = nearbyPokemon.stream()
                            .filter(entity -> entity.getPokemon().getLevel() >= highestLevelBeaten - 10)
                            .toList();

                    if (highLevelPokemon.isEmpty()) {
                        PokemonEntity selectedEntity = nearbyPokemon.get(random.nextInt(nearbyPokemon.size()));
                        Pokemon selectedPokemon = selectedEntity.getPokemon();
                        selectedPokemon.setLevel(selectedLevel);
                        selectedPokemon.initializeMoveset(true);
                    }
                }
            }

        }
    }
}
