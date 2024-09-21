package net.gensir.cobgyms.cache.legendary;

import net.gensir.cobgyms.cache.Cache;

public class LegendaryCache {

    public static Cache prepCache() {
        String[] cachePokemon = {
                "mewtwo",
                "mew",
                "zapdos",
                "articuno",
                "moltres",
                "rayquaza",
                "xerneas",
                "poipole",
                "walkingwake",
                "ironleaves"
        };
        return new Cache(cachePokemon);
    }
}
