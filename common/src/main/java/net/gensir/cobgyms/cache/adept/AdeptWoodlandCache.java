package net.gensir.cobgyms.cache.adept;

import net.gensir.cobgyms.cache.Cache;

public class AdeptWoodlandCache {

    public static Cache prepCache() {
        String[] cachePokemon = {
                "lotad",
                "ralts",
                "budew",
                "venipede",
                "rookidee",
                "fletchling",
                "chespin",
                "mankey",
                "nidoranm",
                "nidoranf",
                "shinx",
                "snubbull",
                "drowzee",
                "tauros",
                "cleffa",
                "tauros",
                "heracross",
                "timburr",
                "heracross",
                "fomantis",
                "morelull",
                "bounsweet",
                "eevee",
                "tropius",
                "comfey",
                "mimikyu",
                "elekid",
                "phantump",
                "bunnelby",
                "ponyta",
                "vulpix",
                "sizzlipede",
                "drifloon",
                "taillow",
                "ditto",
                "chikorita",
                "bellsprout",
                "squawkabilly",
                "carnivine",
                "stufful"
        };
        return new Cache(cachePokemon);
    }
}
