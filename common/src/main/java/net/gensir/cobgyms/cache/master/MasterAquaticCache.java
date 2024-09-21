package net.gensir.cobgyms.cache.master;

import net.gensir.cobgyms.cache.Cache;

public class MasterAquaticCache {

    public static Cache prepCache() {
        String[] cachePokemon = {
                "oshawott",
                "popplio",
                "wimpod",
                "dreepy",
                "froakie",
                "sobble",
                "piplup",
                "horsea",
                "kabuto",
                "amaura",
                "squirtle",
                "totodile",
                "mudkip",
                "goomy",
                "dracovish",
                "arctozolt",
                "dratini",
        };
        return new Cache(cachePokemon);
    }
}
