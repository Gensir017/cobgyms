package net.gensir.cobgyms.cache.master;

import net.gensir.cobgyms.cache.Cache;

public class MasterWoodlandCache {

    public static Cache prepCache() {
        String[] cachePokemon = {
                "bulbasaur",
                "treecko",
                "impidimp",
                "scyther",
                "munchlax",
                "gligar",
                "grookey",
                "snivy",
                "pichu",
                "grookey",
                "honedge",
                "duskull",
                "sprigatito",
                "gastly",
                "larvesta",
                "tinkatink",
                "misdreavus",
                "ferroseed",
                "teddiursa",
                "turtwig",
                "larvitar"
        };
        return new Cache(cachePokemon);
    }
}
