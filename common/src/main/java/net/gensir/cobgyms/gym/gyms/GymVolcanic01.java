package net.gensir.cobgyms.gym.gyms;

import net.gensir.cobgyms.gym.Gym;
import net.gensir.cobgyms.gym.GymTrainer;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.Arrays;
import java.util.Map;

public class GymVolcanic01 {
    public static Gym newGymVolcanic01(){
        Gym gym = new Gym(
                "gym_volcanic01", // Structure nbt name
                Arrays.asList(8.5, 10.0, 11.5), // Relative Player Spawn
                0.0F, // Player Yaw

                // Relative exit coords.
                // This is the coords of the gym exit block that spawns when you beat the gym leader
                new BlockPos(25, 4, 20)
        );


        GymTrainer trainer01 = new GymTrainer();
        trainer01.setEntityInfo(
                Text.translatable("cobgyms.lang.npc.name.gym_trainer"), // Display Name
                new Vec3d(14.5, 10, 36.5), // Pos relative to 0, 0, 0
                150.0F // Yaw
        );
        trainer01.setTeamInfo(
                // Available Pokemon species array
                // A random subset of available Pokemon will be chosen as the team
                Arrays.asList(
                        "aron", // aggron
                        "magby", // magmortar
                        "zubat", // crobat
                        "charcadet", // ceruledge
                        "nosepass", // probopass
                        "geodude", // golem
                        "aerodactyl",
                        "machop", // machamp
                        "chimchar", // infernape
                        "torkoal",
                        "diglett", // dugtrio
                        "phanpy", // donphan
                        "numel", // camerupt
                        "salandit", // salazzle
                        "cranidos" // rampardos
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
                new Vec3d(43.5, 4, 25.5), // Pos relative to 0, 0, 0
                18.0F // Yaw
        );
        trainer02.setTeamInfo(
                // Available Pokemon species array
                // A random subset of available Pokemon will be chosen as the team
                Arrays.asList(
                        "rhyhorn", // rhyperior
                        "fletchling", // talonflame
                        "timburr", // conkeldurr
                        "dracozolt",
                        "torchic", // blaziken
                        "honedge", // aegislash
                        "roggenrola", // gigalith
                        "gible", // garchomp
                        "riolu", // lucario
                        "growlithe", // arcanine
                        "turtonator",
                        "cyndaquil", // typhlosion
                        "nacli", // garganacl
                        "shieldon", // bastiodon
                        "anorith", // armaldo
                        "fuecoco" // skeledirge
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
                new Vec3d(28.5, 4, 20.5), // Pos relative to 0, 0, 0
                -135.0F // Yaw
        );
        leader01.setTeamInfo(
                // Available Pokemon species array
                // A random subset of available Pokemon will be chosen as the team
                Arrays.asList(
                        "darumaka", // darmanitan
                        "litten", // incineroar
                        "nidoranm", // nidoking
                        "duskull", // dusknoir
                        "mankey", // annihilape
                        "tyrunt", // tyrantrum
                        "jangmoo", // kommo-o
                        "beldum", // metagross
                        "deino", // hydreigon
                        "charmander", // charizard
                        "dracovish",
                        "mawile"
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
