package net.gensir.cobgyms.gym.gyms;

import net.gensir.cobgyms.gym.Gym;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GymWater {
    public static Gym prepGym(){
        List<Map<String, Object>> trainerArgs = new ArrayList<>();

        trainerArgs.add(Map.of(
                "name", Text.translatable("cobgyms.lang.npc.name.gym_trainer"),
                "maxPokemonCount",4,
                "npcLoc", new Vec3d(19.5, -48, 13.5),
                "rotations", new float[]{-90.0F, -90.0F},
                "isLeader", false,
                "basePokemon",new String[] {
                        "squirtle", // blastoise
                        "tentacool", // tentacruel
                        "wingull", // pelipper
                        "abra", // alakazam
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
                        "omanyte", // omastar
                    }
        ));

        trainerArgs.add(Map.of(
                "name", Text.translatable("cobgyms.lang.npc.name.gym_trainer"),
                "maxPokemonCount",5,
                "npcLoc", new Vec3d(10.5, -47, 33.5),
                "rotations", new float[]{180.0F, 180.0F},
                "isLeader", false,
                "basePokemon",new String[] {
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
                }
        ));

        trainerArgs.add(Map.of(
                "name", Text.translatable("cobgyms.lang.npc.name.gym_leader"),
                "maxPokemonCount",6,
                "npcLoc", new Vec3d(29.5, -47, 33.5),
                "rotations", new float[]{90.0F, 90.0F},
                "isLeader", true,
                "defeatRequirementIndicies", new int[]{0, 1},
                "basePokemon",new String[] {
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
                }
        ));

        List<Double> playerSpawn = new ArrayList<>();
        playerSpawn.add(41.5);
        playerSpawn.add(-49.0);
        playerSpawn.add(17.5);

        List<Float> playerRotations = new ArrayList<>();
        playerRotations.add(115.0F);
        playerRotations.add(0.0F);

        return new Gym(trainerArgs, playerSpawn, "gym_water", playerRotations, new BlockPos(32, -47, 33));
    }
}
