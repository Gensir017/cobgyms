package net.gensir.cobgyms.gym.gyms;

import net.gensir.cobgyms.gym.Gym;
import net.gensir.cobgyms.gym.GymTrainer;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.Arrays;
import java.util.Map;

public class GymAquatic01{
    public static Gym newGymAquatic01(){
        Gym gym = new Gym(
                "gym_aquatic01", // Structure nbt name
                Arrays.asList(41.5, 6.0, 17.5), // Relative Player Spawn
                90.0F, // Player Yaw

                // Relative exit coords.
                // This is the coords of the gym exit block that spawns when you beat the gym leader
                new BlockPos(32, 8, 33)
        );

        GymTrainer trainer01 = new GymTrainer();
        trainer01.setEntityInfo(
                Text.translatable("cobgyms.lang.npc.name.gym_trainer"), // Display Name
                new Vec3d(19.5, 7, 13.5), // Pos relative to 0, 0, 0
                -90.0F // Yaw
        );
        trainer01.setTeamInfo(
                // Available Pokemon species array
                // A random subset of available Pokemon will be chosen as the team
                Arrays.asList(
                        "squirtle", // blastoise
                        "tentacool", // tentacruel
                        "wingull", // pelipper
                        "misdreavus", // mismagius
                        "psyduck", // golduck
                        "poliwag", // poliwhirl
                        "seel", // dewgong
                        "krabby", // kingler
                        "carvanha", // sharpedo
                        "dhelmise",
                        "barboach", //whiscash
                        "goldeen", // seaking,
                        "crabrawler",
                        "omanyte" // omastar
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
                new Vec3d(10.5, 8, 33.5), // Pos relative to 0, 0, 0
                180.0F // Yaw
        );
        trainer02.setTeamInfo(
                // Available Pokemon species array
                // A random subset of available Pokemon will be chosen as the team
                Arrays.asList(
                        "totodile", // feraligatr
                        "chinchou", // lanturn
                        "slowpoke", // slowbro
                        "swinub", // mamoswine
                        "sneasel", // weavile
                        "magikarp", // gydrados
                        "piplup", // empoleon
                        "frillish", // jellicent
                        "basculin", // basculegion
                        "wailmer", // wailord
                        "dewott", // samurott
                        "tirtouga", // carracosta
                        "nacli", // garganacl
                        "lapras",
                        "staryu", // starmie
                        "beldum" // metagross
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
                new Vec3d(29.5, 8, 33.5), // Pos relative to 0, 0, 0
                90.0F // Yaw
        );
        leader01.setTeamInfo(
                // Available Pokemon species array
                // A random subset of available Pokemon will be chosen as the team
                Arrays.asList(
                        "popplio", //primarina
                        "gible", // garchomp
                        "skrelp", // dragalge
                        "arctozolt",
                        "goomy", // goodra
                        "froakie", // greninja
                        "mudkip", // swampert
                        "shellder", // cloyster
                        "horsea", // kingdra
                        "dratini", // dragonite
                        "dracovish",
                        "riolu" // lucario
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
