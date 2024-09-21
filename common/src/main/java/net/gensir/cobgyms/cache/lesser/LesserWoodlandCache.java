package net.gensir.cobgyms.cache.lesser;

import net.gensir.cobgyms.cache.Cache;

public class LesserWoodlandCache{

    public static Cache prepCache() {
        String[] cachePokemon = {
                "weedle",
                "combee",
                "bonsly",
                "rattata",
                "oddish",
                "seedot",
                "spinarak",
                "pidgey",
                "seedot",
                "spearow",
                "seedot",
                "ekans",
                "natu",
                "murkrow",
                "nuzleaf",
                "cottonee",
                "chingling",
                "nickit",
                "gossifleur",
                "farfetchd",
                "pinsir",
                "bidoof",
                "hoothoot",
                "pineco",
                "shroomish",
                "rowlet",
                "deerling",
                "pumpkaboo",
                "exeggcute",
                "nincada",
                "tangela",
                "miltank",
                "venonat",
                "starly",
                "happiny",
                "igglybuff",
                "lileep",
                "lillipup",
                "pidove",
                "petilil",
                "joltik",
                "scatterbug",
                "flabebe",
                "aipom"
        };
        return new Cache(cachePokemon);
    }
}
