package net.gensir.cobgyms.cache.adept;

import net.gensir.cobgyms.cache.Cache;

public class AdeptVolcanicCache {

    public static Cache prepCache() {
        String[] cachePokemon = {
                "growlithe",
                "fletchling",
                "riolu",
                "dracozolt",
                "aerodactyl",
                "roggenrola",
                "tyrogue",
                "turtonator",
                "falinks",
                "nacli",
                "varoom",
                "heatmor",
                "mawile",
                "spiritomb",
                "lunatone",
                "solrock",
                "mankey",
                "litwick",
                "koffing",
                "flareon",
                "snubbull",
                "kangaskhan",
                "trapinch",
                "yamask",
                "elgyem",
                "sigilyph",
                "abra",
                "timburr",
                "sandshrew",
                "skarmory",
                "flittle"
        };
        return new Cache(cachePokemon);
    }
}
