package net.gensir.cobgyms.gym;

import com.cobblemon.mod.common.pokemon.Pokemon;
import com.selfdot.cobblemontrainers.CobblemonTrainers;
import com.selfdot.cobblemontrainers.trainer.Trainer;
import com.selfdot.cobblemontrainers.util.DataKeys;
import net.gensir.cobgyms.entity.TrainerVillager;
import net.gensir.cobgyms.registry.ModEntityRegistry;
import net.gensir.cobgyms.util.PokemonUtils;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

import java.util.*;


public class GymTrainer {
    private Text displayName;
    private Vec3d relativeNpcLoc;
    private float yaw;
    private List<String> availablePokemonSpeciesArray;
    private Map<Integer, Integer> pokemonCountLevelMapper;
    private boolean isLeader;
    private List<GymTrainer> defeatRequirements = new ArrayList<>();
    private Trainer cobblemonTrainer;
    private String cobblemonTrainerId;

    public GymTrainer(){

    }

    public void setEntityInfo(Text displayName,
                           Vec3d relativeNpcLoc,
                           float yaw){
        this.displayName = displayName;
        this.relativeNpcLoc = relativeNpcLoc;
        this.yaw = yaw;
    }

    public void setTeamInfo(List<String> availablePokemonSpeciesArray, 
                            Map<Integer, Integer> pokemonCountLevelMapper,
                            boolean isLeader){
        this.availablePokemonSpeciesArray = availablePokemonSpeciesArray;
        this.pokemonCountLevelMapper = pokemonCountLevelMapper;
        this.isLeader = isLeader;
    }

    public void addDefeatRequirement(GymTrainer trainer){
        this.defeatRequirements.add(trainer);
    }

    public void buildEntity(ServerWorld cobGymDimension, int adjustX){
        TrainerVillager trainerVillager = new TrainerVillager(ModEntityRegistry.TRAINER_VILLAGER.get(), cobGymDimension);
        trainerVillager.headYaw = this.yaw;
        trainerVillager.bodyYaw = this.yaw;

        Vec3d adjustedNpcLoc = new Vec3d(this.relativeNpcLoc.getX() + adjustX, this.relativeNpcLoc.getY(), this.relativeNpcLoc.getZ());
        trainerVillager.setPosition(adjustedNpcLoc);

        trainerVillager.setTrainer(this.cobblemonTrainer);

        trainerVillager.setCustomName(this.displayName);
        trainerVillager.setCustomNameVisible(true);
        cobGymDimension.spawnEntityAndPassengers(trainerVillager);
    }

    public String getCobblemonTrainerId(){
        return this.cobblemonTrainerId;
    }

    public boolean getIsLeader(){
        return this.isLeader;
    }

    public void buildCobblemonTrainer(int pokemonLevel){
        this.cobblemonTrainerId = UUID.randomUUID().toString();

        CobblemonTrainers.INSTANCE.getTrainerRegistry().removeTrainer(this.cobblemonTrainerId);

        this.cobblemonTrainer = new Trainer(CobblemonTrainers.INSTANCE, this.cobblemonTrainerId, DataKeys.UNGROUPED);
        this.cobblemonTrainer.setCanOnlyBeatOnce(true);
        this.cobblemonTrainer.setLossCommand("cobgyms whiteout_helper_command %player%");

        if (this.isLeader){
            for (GymTrainer defeatRequirementGymTrainer : this.defeatRequirements){
                String defeatRequirementCobblemonTrainerId = defeatRequirementGymTrainer.getCobblemonTrainerId();
                if (defeatRequirementCobblemonTrainerId != null){
                    this.cobblemonTrainer.addDefeatRequirement(defeatRequirementGymTrainer.getCobblemonTrainerId());
                }
            }
        }

        this.addRandomPokemon(pokemonLevel);

        CobblemonTrainers.INSTANCE.getTrainerRegistry().addTrainer(this.cobblemonTrainer);
    }

    private void addRandomPokemon(int pokemonLevel){
        List<String> availablePokemonSpeciesArray = this.availablePokemonSpeciesArray;
        Collections.shuffle(availablePokemonSpeciesArray);

        int pokemonCount = 1;
        List<Integer> mapperLevels = new ArrayList<>(this.pokemonCountLevelMapper.keySet());
        Collections.sort(mapperLevels);

        for (int mapperLevel : mapperLevels){
            if (pokemonLevel <= mapperLevel){
                pokemonCount = pokemonCountLevelMapper.get(mapperLevel);
                break;
            }
        }

        for (int i = 0; i < pokemonCount; i++) {
            Pokemon poke = PokemonUtils.getEvolvedPokemonFromSpecies(availablePokemonSpeciesArray.get(i), pokemonLevel);
            this.cobblemonTrainer.addPokemon(poke);
        }
    }

    public void setWinCommand(String winCommand){
        this.cobblemonTrainer.setWinCommand(winCommand);
    }
}
