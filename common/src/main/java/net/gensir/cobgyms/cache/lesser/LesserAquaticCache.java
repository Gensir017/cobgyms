package net.gensir.cobgyms.cache.lesser;

import net.gensir.cobgyms.cache.Cache;

public class LesserAquaticCache {

    public static Cache prepCache() {
        String[] cachePokemon = {
                "dwebble",
                "krabby",
                "crabrawler",
                "wingull",
                "qwilfish",
                "carbink",
                "tentacool",
                "relicanth",
                "psyduck",
                "poliwag",
                "yanma",
                "seel",
                "shuckle",
                "carvanha",
                "frillish",
                "arrokuda",
                "goldeen",
                "barboach",
                "clamperl",
                "luvdisc",
                "pyukumuku",
                "paras"
        };
        return new Cache(cachePokemon);
    }
}
