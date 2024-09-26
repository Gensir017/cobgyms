package net.gensir.cobgyms.gym.gyms;

import net.gensir.cobgyms.gym.Gym;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GymForest {
    private static final String theme = "woodland";

    public static Gym prepGym(){
        List<Map<String, Object>> trainerArgs = new ArrayList<>();

        trainerArgs.add(Map.of(
                "name", Text.translatable("cobgyms.lang.npc.name.gym_trainer"),
                "maxPokemonCount",4,
                "npcLoc", new Vec3d(26.5, -44, 6.5),
                "rotations", new float[]{90.0F, 90.0F},
                "isLeader", false,
                "basePokemon",new String[] {
                        "pidgey", //pidgeot
                        "spearow", //fearow
                        "koffing", // weezing
                        "dwebble", // crustle
                        "scyther", // scizor
                        "budew", //roserade
                        "oddish", // vileplume
                        "ferroseed", //ferrothorn
                        "rowlet", //decidueye
                        "tropius",
                        "cottonee",//whimsicott
                        "tangela", //tangrowth
                        "zigzagoon",//obstagoon
                        "cleffa",//clefable
                        "snivy" //
                    }
        ));

        trainerArgs.add(Map.of(
                "name", Text.translatable("cobgyms.lang.npc.name.gym_trainer"),
                "maxPokemonCount",5,
                "npcLoc", new Vec3d(26.5, -44, 35.5),
                "rotations", new float[]{180.0F, 180.0F},
                "isLeader", false,
                "basePokemon",new String[] {
                        "yanma", //yanmega
                        "venipede", //scolipede
                        "froakie",//greninja
                        "bellsprout", // victreebel
                        "pikachu", // raichu
                        "gligar", //gliscor
                        "nidoranf", //nidoqueen
                        "larvesta",//volcarona
                        "starly",//staraptor
                        "sandile", //krookodile
                        "bunnelby",//diggersby
                        "heracross",
                        "pinsir",
                        "farfetchd", // sirfetchd
                        "turtwig",//torterra
                        "sizzlipede", //centiskorch
                }
        ));

        trainerArgs.add(Map.of(
                "name", Text.translatable("cobgyms.lang.npc.name.gym_leader"),
                "maxPokemonCount",6,
                "npcLoc", new Vec3d(14.5, -53, 18.5),
                "rotations", new float[]{0.0F, 0.0F},
                "isLeader", true,
                "defeatRequirementIndicies", new int[]{0, 1},
                "basePokemon",new String[] {
                        "mudkip", //swampert
                        "impidimp",//grimsnarl
                        "nidoranm", //nidoking
                        "tinkatink", // tinkaton
                        "larvitar", //tyranitar
                        "wimpod", //gollisopod
                        "grookey", //rillaboom
                        "bulbasaur", //venusaur
                        "munchlax", // snorlax
                        "happiny",//blissey
                        "lotad",//ludicolo
                        "rookidee" // corviknight
                }
        ));

        List<Double> playerSpawn = new ArrayList<>();
        playerSpawn.add(10.5);
        playerSpawn.add(-44.0);
        playerSpawn.add(7.5);

        List<Float> playerRotations = new ArrayList<>();
        playerRotations.add(-90.0F);
        playerRotations.add(0.0F);

        return new Gym(trainerArgs, playerSpawn, "gym_forest", playerRotations, new BlockPos(14, -53, 15), theme);
    }
}
