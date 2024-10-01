package net.gensir.cobgyms.util;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

public class LangUtils {
    public static MutableText getCacheName(String registryKey){
        String[] splitRegistryKey = registryKey.split("_");
        MutableText cacheName = null;
        if(splitRegistryKey.length == 3){
            cacheName = Text.translatable("cobgyms.lang.poke_cache_themed",
                    Text.translatable("cobgyms.lang."+splitRegistryKey[0]),
                    Text.translatable("cobgyms.lang."+splitRegistryKey[1]));
        } else if(splitRegistryKey.length == 2){
            cacheName = Text.translatable("cobgyms.lang.poke_cache",
                    Text.translatable("cobgyms.lang."+splitRegistryKey[0]));
        }

        return cacheName;
    }

}
