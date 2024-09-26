package net.gensir.cobgyms.gym.gyms;

import net.gensir.cobgyms.gym.Gym;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GymLava {
    private static final String theme = "volcanic";

    public static Gym prepGym(){

        List<Map<String, Object>> trainerArgs = new ArrayList<>();

        trainerArgs.add(Map.of(
                "name", Text.translatable("cobgyms.lang.npc.name.gym_trainer"),
                "maxPokemonCount",4,
                "npcLoc", new Vec3d(14.5, -45, 36.5),
                "rotations", new float[]{150.0F, 150.0F},
                "isLeader", false,
                "basePokemon",new String[] {
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
                        "cranidos", // rampardos
                    }
        ));

        trainerArgs.add(Map.of(
                "name", Text.translatable("cobgyms.lang.npc.name.gym_trainer"),
                "maxPokemonCount",5,
                "npcLoc", new Vec3d(43.5, -51, 25.5),
                "rotations", new float[]{18.0F, 18.0F},
                "isLeader", false,
                "basePokemon",new String[] {
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
                }
        ));

        trainerArgs.add(Map.of(
                "name", Text.translatable("cobgyms.lang.npc.name.gym_leader"),
                "maxPokemonCount",6,
                "npcLoc", new Vec3d(28.5, -51, 20.5),
                "rotations", new float[]{-135.0F, -135.0F},
                "isLeader", true,
                "defeatRequirementIndicies", new int[]{0, 1},
                "basePokemon",new String[] {
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
                }
        ));

        List<Double> playerSpawn = new ArrayList<>();
        playerSpawn.add(8.5);
        playerSpawn.add(-45.0);
        playerSpawn.add(11.5);

        List<Float> playerRotations = new ArrayList<>();
        playerRotations.add(0.0F);
        playerRotations.add(0.0F);

        return new Gym(trainerArgs, playerSpawn, "gym_lava", playerRotations, new BlockPos(25, -51, 20), theme);
    }
}
