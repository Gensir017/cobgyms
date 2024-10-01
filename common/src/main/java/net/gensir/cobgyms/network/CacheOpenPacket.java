package net.gensir.cobgyms.network;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.storage.NoPokemonStoreException;
import com.cobblemon.mod.common.api.storage.party.PlayerPartyStore;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.gensir.cobgyms.CobGyms;
import net.gensir.cobgyms.cache.Cache;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class CacheOpenPacket {
    public static void handleCacheOpenPacket(ServerPlayerEntity serverPlayer, String cacheRarity, String cacheTheme, boolean increasedShinyChance){

        ItemStack itemStack = serverPlayer.getMainHandStack();
        Identifier identifier;
        if (increasedShinyChance){
            identifier = new Identifier(CobGyms.MOD_ID, cacheRarity + "_cache_shiny");
        } else {
            identifier = new Identifier(CobGyms.MOD_ID, cacheRarity + "_cache");
        }

        if (itemStack.getItem() == Registries.ITEM.get(identifier)){
            String cacheType;
            if (!Objects.equals(cacheTheme, "no_theme")){
                cacheType = cacheRarity + "_" + cacheTheme + "_cache";
            } else {
                cacheType = cacheRarity + "_cache";
            }
            Pokemon poke = Cache.getCachePokemon(cacheType, increasedShinyChance);


            if(poke != null){
                try {
                    if(poke.getShiny()){
                        serverPlayer.sendMessage(Text.translatable("cobgyms.lang.poke_cache_received_shiny",poke.getDisplayName().getString()));
                    } else {
                        serverPlayer.sendMessage(Text.translatable("cobgyms.lang.poke_cache_received",poke.getDisplayName().getString()));
                    }
                    PlayerPartyStore party = Cobblemon.INSTANCE.getStorage().getParty(serverPlayer.getUuid());
                    party.add(poke);
                    itemStack.decrement(1);

                } catch (NoPokemonStoreException e) {
                    throw new RuntimeException(e);
                }
            }


        } else {
            serverPlayer.sendMessage(Text.of("cache must be in your main hand"));
        }


    }
}
