package net.gensir.cobgyms.gym;


import net.gensir.cobgyms.world.StructurePlacer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.MutableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.Identifier;

import java.util.*;

import static net.gensir.cobgyms.CobGyms.MOD_ID;

public class Gym {
    private final List<Map<String, Object>> trainerArgs;
    private final List<Double> playerSpawn;
    private final String structureName;
    private final List<Float> playerRotations;
    private final BlockPos exitCoords;
    public final String theme;

    public Gym(List<Map<String, Object>> trainerArgs,
               List<Double> playerSpawn,
               String structureName,
               List<Float> playerRotations,
               BlockPos exitCoords,
               String theme){
        this.trainerArgs = trainerArgs;
        this.playerSpawn = playerSpawn;
        this.structureName = structureName;
        this.playerRotations = playerRotations;
        this.exitCoords = exitCoords;
        this.theme = theme;
    }

    public Map<String, Object> buildGym(int pokemonLevel,
                                              ServerWorld cobGymDimension,
                                              double adjustX){



        List<String> trainerUuidArray = new ArrayList<>();

        for(Map<String, Object> trainerArg : trainerArgs){
            GymTrainer trainer = new GymTrainer(
                    (MutableText) trainerArg.get("name"),
                    (String[]) trainerArg.get("basePokemon"),
                    pokemonLevel,
                    (int) trainerArg.get("maxPokemonCount"),
                    (Boolean) trainerArg.get("isLeader")
            );



            Vec3d npcLoc = (Vec3d) trainerArg.get("npcLoc");
            Vec3d adjustedNpcLoc = new Vec3d(npcLoc.getX()+adjustX, npcLoc.getY(), npcLoc.getZ());


            String trainerUUID = trainer.buildTrainer(
                    cobGymDimension,
                    adjustedNpcLoc,
                    (float[]) trainerArg.get("rotations")
            );

            trainerUuidArray.add(trainerUUID);


            if((Boolean) trainerArg.get("isLeader")){
                List<String> defeatRequirements = new ArrayList<>();
                for(int defeatRequirementIndex : (int[]) trainerArg.get("defeatRequirementIndicies")){
                    defeatRequirements.add(trainerUuidArray.get(defeatRequirementIndex));
                }
                trainer.buildLeaderInfo(defeatRequirements, new BlockPos((int) (this.exitCoords.getX()+adjustX), this.exitCoords.getY(), this.exitCoords.getZ()));
            }
        }

        StructurePlacer.placeStructure(
                cobGymDimension,
                new BlockPos((int) adjustX, -55, 0),
                new Identifier(MOD_ID, this.structureName));


        Map<String, Object> returnData = Map.of(
                "trainers",String.join(",", trainerUuidArray),
                "playerSpawn",this.playerSpawn,
                "playerRotations",this.playerRotations
        );

        return returnData;
    }
}
