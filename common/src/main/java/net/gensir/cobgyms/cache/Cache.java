package net.gensir.cobgyms.cache;

import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
import com.cobblemon.mod.common.pokemon.Gender;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.cobblemon.mod.common.pokemon.Species;
import net.gensir.cobgyms.CobGyms;
import net.gensir.cobgyms.cache.adept.AdeptAquaticCache;
import net.gensir.cobgyms.cache.adept.AdeptVolcanicCache;
import net.gensir.cobgyms.cache.adept.AdeptWoodlandCache;
import net.gensir.cobgyms.cache.legendary.LegendaryCache;
import net.gensir.cobgyms.cache.lesser.LesserAquaticCache;
import net.gensir.cobgyms.cache.lesser.LesserVolcanicCache;
import net.gensir.cobgyms.cache.lesser.LesserWoodlandCache;
import net.gensir.cobgyms.cache.master.MasterAquaticCache;
import net.gensir.cobgyms.cache.master.MasterVolcanicCache;
import net.gensir.cobgyms.cache.master.MasterWoodlandCache;
import net.gensir.cobgyms.util.LangUtils;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public class Cache {
    Random random = new Random();

    private static final Map<String, Cache> cacheMap = Map.of(
            "lesser_woodland_cache",LesserWoodlandCache.prepCache(),
            "adept_woodland_cache", AdeptWoodlandCache.prepCache(),
            "master_woodland_cache", MasterWoodlandCache.prepCache(),

            "lesser_volcanic_cache", LesserVolcanicCache.prepCache(),
            "adept_volcanic_cache", AdeptVolcanicCache.prepCache(),
            "master_volcanic_cache", MasterVolcanicCache.prepCache(),

            "lesser_aquatic_cache", LesserAquaticCache.prepCache(),
            "adept_aquatic_cache", AdeptAquaticCache.prepCache(),
            "master_aquatic_cache", MasterAquaticCache.prepCache(),

            "legendary_cache", LegendaryCache.prepCache()
    );

    private final String[] cachePokemon;

    public Cache(String[] cachePokemon){
        this.cachePokemon = cachePokemon;
    }

    public static Pokemon getCachePokemon(String cacheType, Boolean increasedShinyChance){
        return cacheMap.get(cacheType).getRandomPokemon(increasedShinyChance);
    }

    public static String[] getCacheKeys(){
        return cacheMap.keySet().toArray(new String[0]);
    }

    public static String[] getCacheList(String cacheName){
        if(cacheMap.containsKey(cacheName)){
            return cacheMap.get(cacheName).cachePokemon;
        } else {
            return null;
        }
    }

    public static ArrayList<MutableText> whichCache(String pokemonToFind){
        ArrayList<MutableText> foundList = new ArrayList<>();
        for (String key : cacheMap.keySet()) {
            Cache value = cacheMap.get(key);
            if (Arrays.asList(value.cachePokemon).contains(pokemonToFind)){
                double chance = ((double) 1 /value.cachePokemon.length)*100;
                String chanceString = String.format("%.2f", chance)+"%";
                MutableText itemName = LangUtils.getCacheName(key);

                foundList.add(Text.translatable("cobgyms.lang.command.cache_chance", itemName, chanceString));
            }
        }
        return foundList;
    }

    public Pokemon getRandomPokemon(Boolean increasedShinyChance){

        int randomIndex = random.nextInt(this.cachePokemon.length);
        String identifier = this.cachePokemon[randomIndex];

        Species species = PokemonSpecies.INSTANCE.getByIdentifier(new Identifier("cobblemon", identifier));

        if(species != null) {

            boolean isMale = random.nextBoolean();
            Gender gender = isMale ? Gender.MALE : Gender.FEMALE;

            Pokemon poke = new Pokemon();
            poke.setSpecies(species);
            poke.setGender(gender);
            poke.setLevel(1);

            if(increasedShinyChance){
                double randomDouble = random.nextDouble();
                if (randomDouble <= CobGyms.SHINY_FROM_CACHE_CHANCE){
                    poke.setShiny(true);
                }
            } else {
                int randomNumber = random.nextInt(4096);
                if(randomNumber == 0){
                    poke.setShiny(true);
                }
            }

            return poke;
        } else {
            return null;
        }
    }
}
