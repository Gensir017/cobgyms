package net.gensir.cobgyms.item.custom;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.storage.NoPokemonStoreException;
import com.cobblemon.mod.common.api.storage.party.PlayerPartyStore;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.gensir.cobgyms.cache.Cache;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class GymCacheItemOld extends Item {
    private final Boolean increasedShinyChance;
    private final MutableText displayName;
    private final Formatting nameFormatting;
    private final Text[] tooltips;
    private final String cacheKey;

    public GymCacheItemOld(Settings settings, String cacheKey, Boolean increasedShinyChance, MutableText displayName, Formatting nameFormatting, Text[] tooltips) {
        super(settings);
        this.cacheKey = cacheKey;
        this.increasedShinyChance = increasedShinyChance;
        this.displayName = displayName;
        this.nameFormatting = nameFormatting;
        this.tooltips = tooltips;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(!world.isClient()){
            Pokemon poke = Cache.getCachePokemon(this.cacheKey, this.increasedShinyChance);
            if(poke != null){
                try {
                    if(poke.getShiny()){
                        user.sendMessage(Text.translatable("cobgyms.lang.poke_cache_received_shiny",poke.getDisplayName().getString()));
                    } else {
                        user.sendMessage(Text.translatable("cobgyms.lang.poke_cache_received",poke.getDisplayName().getString()));
                    }
                    PlayerPartyStore party = Cobblemon.INSTANCE.getStorage().getParty(user.getUuid());
                    party.add(poke);

                    user.getStackInHand(hand).damage(1, user, playerEntity -> playerEntity.sendToolBreakStatus((playerEntity.getActiveHand())));
                } catch (NoPokemonStoreException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return super.use(world, user, hand);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return this.increasedShinyChance;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(this.tooltips != null){
            tooltip.addAll(Arrays.asList(this.tooltips));
        }
        if(this.increasedShinyChance){
            tooltip.add(Text.translatable("tooltip.cobgyms.shiny_cache.tooltip"));
        }

        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public Text getName(ItemStack stack) {
        return displayName.formatted(this.nameFormatting);
    }
}
