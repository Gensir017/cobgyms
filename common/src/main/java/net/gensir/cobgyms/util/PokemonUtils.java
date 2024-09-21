package net.gensir.cobgyms.util;

import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
import com.cobblemon.mod.common.api.pokemon.evolution.Evolution;
import com.cobblemon.mod.common.api.pokemon.evolution.requirement.EvolutionRequirement;
import com.cobblemon.mod.common.pokemon.Gender;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.cobblemon.mod.common.pokemon.Species;
import net.gensir.cobgyms.CobGyms;
import net.minecraft.util.Identifier;

import java.util.*;

public class PokemonUtils {
    static Random random = new Random();

    public static Pokemon getEvolvedPokemonFromIdentifier(String identifier, int pokemonLevel){

        PokemonSpecies speciesInstance = PokemonSpecies.INSTANCE;

        if(pokemonLevel > 100){
            pokemonLevel = 100;
        }

        if(CobGyms.forceEvolutionMapper.containsKey(identifier)) {
            List<Map<String, Object>> forceEvolutions = CobGyms.forceEvolutionMapper.get(identifier);
            for (Map<String, Object> forceEvolution : forceEvolutions){
                int forceEvolutionLevel = (int) forceEvolution.get("level");
                if (pokemonLevel >= forceEvolutionLevel){
                    identifier = forceEvolution.get("species").toString();
                    break;
                }
            }
        }

        Species species = speciesInstance.getByIdentifier(new Identifier("cobblemon", identifier));


        if(species != null) {
            boolean isMale = PokemonUtils.random.nextBoolean();
            Gender gender = isMale ? Gender.MALE : Gender.FEMALE;

            Pokemon poke = new Pokemon();
            poke.setSpecies(species);
            poke.setGender(gender);
            poke.setLevel(pokemonLevel);

            boolean canEvolve = true;

            while (canEvolve) {
                Iterable<Evolution> evolutions = poke.getEvolutions();
                canEvolve = false;

                for (Evolution evolution : evolutions) {
                    Iterable<EvolutionRequirement> requirements = evolution.getRequirements();

                    for (EvolutionRequirement requirement : requirements) {
                        if (requirement.check(poke)) {
                            Species newSpecies = speciesInstance.getByIdentifier(new Identifier("cobblemon", Objects.requireNonNull(evolution.getResult().getSpecies())));
                            if (newSpecies != null) {
                                poke.setSpecies(newSpecies);
                                canEvolve = true;
                            }
                            break;
                        }
                    }
                    if (canEvolve) {
                        break;
                    }
                }
            }

            poke.initializeMoveset(true);
            return poke;

        } else {
            CobGyms.LOGGER.info("ERROR: SPECIES NULL FOR "+identifier);
            return null;
        }
    }
}
