package net.gensir.cobgyms.network;

import net.gensir.cobgyms.cache.Cache;
import net.gensir.cobgyms.util.LangUtils;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class CacheListPacket {
    public static void handleCacheListPacket(ServerPlayerEntity serverPlayer, String cacheRarity, String cacheTheme){
        String cacheName = cacheRarity+"_"+cacheTheme+"_cache";
        List<MutableText> textArray = cacheList(cacheName);
        if (!textArray.isEmpty()){
            for (MutableText text : textArray){
                serverPlayer.sendMessage(text);
            }
        } else {
            serverPlayer.sendMessage(Text.translatable("cobgyms.lang.command.list_cache.invalid_cache"));
        }
    }

    public static List<MutableText> cacheList(String cacheName){
        List<MutableText> textArray = new ArrayList<>();

        String[] cachePokemon = Cache.getCacheList(cacheName);
        if (cachePokemon != null) {
            double chance = ((double) 1 / cachePokemon.length) * 100;

            String chanceString = String.format("%.2f", chance) + "%";
            MutableText itemName = LangUtils.getCacheName(cacheName);

            textArray.add(Text.translatable("cobgyms.lang.command.cache_chance", itemName, chanceString));

            String cachePokemonString = "";
            for (int i = 0; i < cachePokemon.length; i++) {
                cachePokemonString += Text.translatable(String.format("cobblemon.species.%s.name", cachePokemon[i])).getString();
                if (i < cachePokemon.length - 1) {
                    cachePokemonString += " : ";
                }
            }
            textArray.add(Text.literal(cachePokemonString));
        }
        return textArray;
    }
}
