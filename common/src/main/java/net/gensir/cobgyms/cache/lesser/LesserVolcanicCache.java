package net.gensir.cobgyms.cache.lesser;

import net.gensir.cobgyms.cache.Cache;

public class LesserVolcanicCache {

    public static Cache prepCache() {
        String[] cachePokemon = {
                "rhyhorn",
                "geodude",
                "aron",
                "machop",
                "zubat",
                "magby",
                "nosepass",
                "torkoal",
                "numel",
                "phanpy",
                "durant",
                "cufant",
                "salandit",
                "sableye",
                "glimmet",
                "whismur",
                "golett",
                "bunnelby",
                "baltoy",
                "woobat",
                "makuhita",
                "magcargo",
                "onix",
                "slugma",
                "lechonk",
                "cubone",
                "vulpix",
                "pumpkaboo",
                "ponyta",
                "grimer",
                "rattata",
                "diglett",
                "stonjourner",
                "komala",
                "joltik",
                "sandile",
                "shroomish",
                "wooloo",
                "klink",
                "klefki",
                "poochyena",
                "meowth",
                "mudbray",
                "spinda",
                "grafaiai"
        };
        return new Cache(cachePokemon);
    }
}
