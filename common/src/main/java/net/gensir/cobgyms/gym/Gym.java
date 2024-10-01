package net.gensir.cobgyms.gym;

import net.gensir.cobgyms.CobGyms;
import net.gensir.cobgyms.world.StructurePlacer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Gym {
    private final List<Double> relativePlayerSpawn;
    private final Float playerYaw;
    private final String structureName;
    private BlockPos relativeExitCoords;

    List<GymTrainer> trainers = new ArrayList<>();
    public Gym(
            String structureName,
            List<Double> relativePlayerSpawn,
            Float yaw,
            BlockPos relativeExitCoords){
        this.structureName = structureName;
        this.relativePlayerSpawn = relativePlayerSpawn;
        this.playerYaw = yaw;
        this.relativeExitCoords = relativeExitCoords;
    }

    public void addTrainer(GymTrainer trainer){
        trainers.add(trainer);
    }

    public  Map<String, Object> buildGym(ServerWorld cobGymDimension, int adjustX, int pokemonLevel){
        List<String> cobblemonTrainerIds = new ArrayList<>();
        for (GymTrainer trainer : this.trainers){
            trainer.buildCobblemonTrainer(pokemonLevel);
            trainer.buildEntity(cobGymDimension, adjustX);
            if (trainer.getIsLeader()){
                String winCommand = ("cobgyms complete_helper_command %player%"+String.format(
                        " %d %d %d %d",
                        pokemonLevel,
                        this.relativeExitCoords.getX()+adjustX,
                        this.relativeExitCoords.getY(),
                        this.relativeExitCoords.getZ())
                );
                trainer.setWinCommand(winCommand);

            }
            cobblemonTrainerIds.add(trainer.getCobblemonTrainerId());
        }

        StructurePlacer.placeStructure(
                cobGymDimension,
                new BlockPos(adjustX, 0, 0),
                new Identifier(CobGyms.MOD_ID, this.structureName));

        return Map.of(
                "trainers",cobblemonTrainerIds,
                "relativePlayerSpawn", this.relativePlayerSpawn,
                "playerYaw", this.playerYaw
        );
    }
}
