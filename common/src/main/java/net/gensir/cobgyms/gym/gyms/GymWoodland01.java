package net.gensir.cobgyms.gym.gyms;

import net.gensir.cobgyms.gym.Gym;
import net.gensir.cobgyms.gym.GymTrainer;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.Arrays;
import java.util.Map;

public class GymWoodland01 {
    public static Gym newGymWoodland01(){
        Gym gym = new Gym(
                "gym_woodland01", // Structure nbt name
                Arrays.asList(10.5, 11.0, 7.5), // Relative Player Spawn
                -90.0F, // Player Yaw

                // Relative exit coords.
                // This is the coords of the gym exit block that spawns when you beat the gym leader
                new BlockPos(14, 2, 15)
        );


        GymTrainer trainer01 = new GymTrainer();
        trainer01.setEntityInfo(
                Text.translatable("cobgyms.lang.npc.name.gym_trainer"), // Display Name
                new Vec3d(26.5, 11, 6.5), // Pos relative to 0, 0, 0
                90.0F // Yaw
        );
        trainer01.setTeamInfo(
                // Available Pokemon species array
                // A random subset of available Pokemon will be chosen as the team
                Arrays.asList(
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
                ),
                // Pokemon Count Level Mapper
                // If the Gym Pokemon level is less than the map key then set the maxPokemonCount to the map value
                // For example Map.of(20,2, 100, 3) will cause the trainer to have 2 pokemon if below level 20
                // and 3 pokemon if above level 20
                Map.of(
                        20,2,
                        30, 3,
                        100, 4
                ),
                false // Is Leader
        );
        gym.addTrainer(trainer01);




        GymTrainer trainer02 = new GymTrainer();
        trainer02.setEntityInfo(
                Text.translatable("cobgyms.lang.npc.name.gym_trainer"), // Display Name
                new Vec3d(26.5, 11, 35.5), // Pos relative to 0, 0, 0
                180.0F // Yaw
        );
        trainer02.setTeamInfo(
                // Available Pokemon species array
                // A random subset of available Pokemon will be chosen as the team
                Arrays.asList(
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
                        "sizzlipede" //centiskorch
                ),
                // Pokemon Count Level Mapper
                // If the Gym Pokemon level is less than the map key then set the maxPokemonCount to the map value
                // For example Map.of(20,2, 100, 3) will cause the trainer to have 2 pokemon if below level 20
                // and 3 pokemon if above level 20
                Map.of(
                        20,3,
                        30, 4,
                        100, 5
                ),
                false // Is Leader
        );
        gym.addTrainer(trainer02);



        GymTrainer leader01 = new GymTrainer();
        leader01.setEntityInfo(
                Text.translatable("cobgyms.lang.npc.name.gym_leader"), // Display Name
                new Vec3d(14.5, 2, 18.5), // Pos relative to 0, 0, 0
                0.0F // Yaw
        );
        leader01.setTeamInfo(
                // Available Pokemon species array
                // A random subset of available Pokemon will be chosen as the team
                Arrays.asList(
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
                ),
                // Pokemon Count Level Mapper
                // If the Gym Pokemon level is less than the map key then set the maxPokemonCount to the map value
                // For example Map.of(20,2, 100, 3) will cause the trainer to have 2 pokemon if below level 20
                // and 3 pokemon if above level 20
                Map.of(
                        20,4,
                        30, 5,
                        100, 6
                ),
                true // Is Leader
        );
        leader01.addDefeatRequirement(trainer01);
        leader01.addDefeatRequirement(trainer02);
        gym.addTrainer(leader01);


        return gym;
    }
}
