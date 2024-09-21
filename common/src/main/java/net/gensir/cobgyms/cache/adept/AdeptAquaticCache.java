package net.gensir.cobgyms.cache.adept;

import net.gensir.cobgyms.cache.Cache;

public class AdeptAquaticCache {

    public static Cache prepCache() {
        String[] cachePokemon = {
                "lotad",
                "surskit",
                "staryu",
                "shellder",
                "chinchou",
                "wailmer",
                "alomomola",
                "cryogonal",
                "magikarp",
                "buizel",
                "basculin",
                "lapras",
                "cubchoo",
                "bergmite",
                "eiscue",
                "cetoddle",
                "omanyte",
                "wooper",
                "skrelp",
                "slowpoke",
                "quaxly",
                "sneasel",
                "swinub",
                "vaporeon",
                "arctovish"
        };
        return new Cache(cachePokemon);
    }
}
