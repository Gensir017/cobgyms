package net.gensir.cobgyms.network;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.gensir.cobgyms.util.JSONHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.WorldSavePath;
import net.minecraft.util.math.Box;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class SpawnScaledPacket {
    private static final Random random = new Random();

    public static void handleSpawnScaledPacket(ServerPlayerEntity player, ServerWorld serverWorld){

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
