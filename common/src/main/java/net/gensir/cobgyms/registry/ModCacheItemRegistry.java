package net.gensir.cobgyms.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.gensir.cobgyms.item.custom.GymCacheItem;
import net.gensir.cobgyms.util.ModItemGroup;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;

import static net.gensir.cobgyms.CobGyms.MOD_ID;

public class ModCacheItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, RegistryKeys.ITEM);

    public static final List<RegistrySupplier<Item>> LESSER_CACHE = registerCache(
            "lesser",
            Formatting.RED);

    public static final List<RegistrySupplier<Item>> ADEPT_CACHE = registerCache(
            "adept",
            Formatting.BLUE);

    public static final List<RegistrySupplier<Item>> MASTER_CACHE = registerCache(
            "master",
            Formatting.LIGHT_PURPLE);

    public static final List<RegistrySupplier<Item>> LEGENDARY_CACHE = registerCache(
            "legendary",
            Formatting.GOLD);

    private static List<RegistrySupplier<Item>> registerCache(String rarity, Formatting nameFormatting) {
        String cacheKey = String.format("%s_cache", rarity);

        MutableText displayName = Text.translatable("cobgyms.lang.poke_cache", Text.translatable("cobgyms.lang."+rarity));
        MutableText shinyDisplayName = Text.translatable("cobgyms.lang.poke_cache_shiny", Text.translatable("cobgyms.lang."+rarity));

        Text[] tooltips = new Text[]{Text.translatable("tooltip.cobgyms.poke_cache.tooltip", Text.translatable("cobgyms.lang."+rarity))};

        List<RegistrySupplier<Item>> itemList = new ArrayList<>();
        RegistrySupplier<Item> item = ITEMS.register(cacheKey, () ->
                new GymCacheItem(new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB),
                        rarity,
                        false,
                        displayName,
                        nameFormatting,
                        tooltips));

        RegistrySupplier<Item> shinyItem = ITEMS.register(cacheKey+"_shiny", () ->
                new GymCacheItem(new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB),
                        rarity,
                        true,
                        shinyDisplayName,
                        nameFormatting,
                        tooltips));

        itemList.add(item);
        itemList.add(shinyItem);
        return itemList;
    }

    public static void initialize() {
        ITEMS.register();
    }
}
