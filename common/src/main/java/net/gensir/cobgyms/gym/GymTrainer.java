package net.gensir.cobgyms.gym;

import com.cobblemon.mod.common.pokemon.Pokemon;
import com.selfdot.cobblemontrainers.CobblemonTrainers;
import com.selfdot.cobblemontrainers.trainer.Trainer;
import com.selfdot.cobblemontrainers.util.DataKeys;
import net.gensir.cobgyms.entity.TrainerVillager;
import net.gensir.cobgyms.registry.ModEntityRegistry;
import net.gensir.cobgyms.util.PokemonUtils;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.MutableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.*;


public class GymTrainer {
    private final MutableText name;
    private final String[] basePokemon;
    private Trainer trainer;
    private int pokemonLevel;
    private int maxPokemonCount;
    private final Boolean isLeader;

    public GymTrainer(MutableText name, String[] basePokemon, int pokemonLevel, int maxPokemonCount, boolean isLeader){
        this.name = name;
        this.basePokemon = basePokemon;
        this.pokemonLevel = pokemonLevel;
        this.maxPokemonCount = maxPokemonCount;
        this.isLeader = isLeader;
    }

    public String buildTrainer(
            ServerWorld cobGymDimension,
            Vec3d npcLoc,
            float[] rotations){

        String trainerUUID = UUID.randomUUID().toString();

        CobblemonTrainers.INSTANCE.getTrainerRegistry().removeTrainer(trainerUUID);

        this.trainer = new Trainer(CobblemonTrainers.INSTANCE, trainerUUID, DataKeys.UNGROUPED);
        this.trainer.setCanOnlyBeatOnce(true);
        this.trainer.setLossCommand("cobgyms whiteout_helper_command %player%");

        List<String> basePokemonList = Arrays.asList(this.basePokemon);
        Collections.shuffle(basePokemonList);

        int pokemonCount = basePokemonList.size();

        if(pokemonLevel < 20){
            maxPokemonCount = (int) Math.floor(maxPokemonCount * 0.7);
        }else if (pokemonLevel >= 20 && pokemonLevel < 30){
            maxPokemonCount = (int) Math.floor(maxPokemonCount * 0.9);
        }

        if(pokemonCount > maxPokemonCount){
            pokemonCount = maxPokemonCount;
        }

        List<Integer> highLevelIndicies = new ArrayList<>();
        List<Integer> lowLevelIndicies = new ArrayList<>();

        List<Integer> shuffledIndicies = new ArrayList<>();
        for (int i = 0; i <= pokemonCount-1; i++) {
            shuffledIndicies.add(i);
        }

        Collections.shuffle(shuffledIndicies);

        if (isLeader){
            for (int i = 0; i < Math.floor(pokemonCount*0.5); i++) {
                highLevelIndicies.add(shuffledIndicies.get(i));
            }
        } else {
            lowLevelIndicies.add(shuffledIndicies.get(0));
        }

        for (int i = 0; i < pokemonCount; i++) {
            String identifier = basePokemonList.get(i);
            int currentLevel = this.pokemonLevel;
            if (highLevelIndicies.contains(i)){
                currentLevel += 1;
            } else if (lowLevelIndicies.contains(i)){
                currentLevel -= 1;
            }
            Pokemon currentPokemon = PokemonUtils.getEvolvedPokemonFromIdentifier(identifier, currentLevel);
            if (currentPokemon != null){
                this.trainer.addPokemon(currentPokemon);
            }
        }

        CobblemonTrainers.INSTANCE.getTrainerRegistry().addTrainer(this.trainer);

        TrainerVillager trainerVillager = new TrainerVillager(ModEntityRegistry.TRAINER_VILLAGER.get(), cobGymDimension);
        trainerVillager.headYaw = rotations[0];
        trainerVillager.bodyYaw = rotations[1];
        trainerVillager.setPosition(npcLoc);
        trainerVillager.setTrainer(this.trainer);
        trainerVillager.setCustomName(this.name);
        trainerVillager.setCustomNameVisible(true);
        cobGymDimension.spawnEntityAndPassengers(trainerVillager);

        return trainerUUID;
    }

    public void buildLeaderInfo(List<String> defeatRequirements, BlockPos exitCoords){
        if (!defeatRequirements.isEmpty()){
            this.trainer.setWinCommand("cobgyms complete_helper_command %player%"+String.format(" %d %d %d %d",this.pokemonLevel, exitCoords.getX(), exitCoords.getY(), exitCoords.getZ()));
            for (String defeatRequirement : defeatRequirements) {
                this.trainer.addDefeatRequirement(defeatRequirement);
            }
        }
    }
}
