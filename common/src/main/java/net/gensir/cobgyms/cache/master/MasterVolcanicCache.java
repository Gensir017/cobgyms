package net.gensir.cobgyms.cache.master;

import net.gensir.cobgyms.cache.Cache;

public class MasterVolcanicCache {

    public static Cache prepCache() {
        String[] cachePokemon = {
                "charmander",
                "torchic",
                "litten",
                "gible",
                "jangmoo",
                "zorua",
                "charcadet",
                "deino",
                "darumaka",
                "fuecoco",
                "beldum",
                "cyndaquil",
                "tepig",
                "chimchar",
                "misdreavus",
                "larvesta",
                "fennekin",
                "scorbunny",
                "tyrunt"
        };
        return new Cache(cachePokemon);
    }
}
