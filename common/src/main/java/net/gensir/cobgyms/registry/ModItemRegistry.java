package net.gensir.cobgyms.registry;

import dev.architectury.core.item.ArchitecturyBucketItem;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.gensir.cobgyms.item.custom.GymCacheItem;
import net.gensir.cobgyms.item.custom.GymKeyItem;
import net.gensir.cobgyms.item.custom.RarityItem;
import net.gensir.cobgyms.util.ModItemGroup;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;

import static net.gensir.cobgyms.CobGyms.MOD_ID;

public class ModItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, RegistryKeys.ITEM);


    // Gym Key
    public static final RegistrySupplier<Item> GYM_KEY = ITEMS.register("gym_key", () ->
            new GymKeyItem(new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB),
                    Text.translatable("item.cobgyms.gym_key"),
                    new Text[]{Text.translatable("tooltip.cobgyms.gym_key.tooltip")},
                    Formatting.GOLD,
                    true));


    // Safe Lava Bucket
    public static final RegistrySupplier<Item> SAFE_LAVA_BUCKET = ITEMS.register("safe_lava_bucket", () -> new ArchitecturyBucketItem(ModFluidRegistry.SAFE_LAVA, new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB)));


    // Shards
    public static final RegistrySupplier<Item> LESSER_SHARD = ITEMS.register("lesser_shard",
            () -> new RarityItem(new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB),
                    Text.translatable("cobgyms.lang.poke_shard", Text.translatable("cobgyms.lang.lesser")), // name
                    new Text[]{Text.translatable("tooltip.cobgyms.poke_shard.tooltip", Text.translatable("cobgyms.lang.lesser"))}, // tooltips
                    Formatting.RED,
                    false));

    public static final RegistrySupplier<Item> ADEPT_SHARD = ITEMS.register("adept_shard",
            () -> new RarityItem(new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB),
                    Text.translatable("cobgyms.lang.poke_shard", Text.translatable("cobgyms.lang.adept")), // name
                    new Text[]{Text.translatable("tooltip.cobgyms.poke_shard.tooltip", Text.translatable("cobgyms.lang.adept"))}, // tooltips
                    Formatting.BLUE,
                    false));

    public static final RegistrySupplier<Item> MASTER_SHARD = ITEMS.register("master_shard",
            () -> new RarityItem(new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB),
                    Text.translatable("cobgyms.lang.poke_shard", Text.translatable("cobgyms.lang.master")), // name
                    new Text[]{Text.translatable("tooltip.cobgyms.poke_shard.tooltip", Text.translatable("cobgyms.lang.master"))}, // tooltips
                    Formatting.LIGHT_PURPLE,
                    true));

    public static final RegistrySupplier<Item> LEGENDARY_SHARD = ITEMS.register("legendary_shard",
            () -> new RarityItem(new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB),
                    Text.translatable("cobgyms.lang.poke_shard", Text.translatable("cobgyms.lang.legendary")), // name
                    new Text[]{Text.translatable("tooltip.cobgyms.poke_shard.tooltip", Text.translatable("cobgyms.lang.legendary"))}, // tooltips
                    Formatting.GOLD,
                    true));


    // Fragments
    public static final RegistrySupplier<Item> LESSER_FRAGMENTS = ITEMS.register("lesser_fragments",
            () -> new RarityItem(new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB),
                    Text.translatable("cobgyms.lang.poke_fragments", Text.translatable("cobgyms.lang.lesser")), // name
                    new Text[]{Text.translatable("tooltip.cobgyms.poke_fragments.tooltip", Text.translatable("cobgyms.lang.lesser"))}, // tooltips
                    Formatting.RED,
                    false));

    public static final RegistrySupplier<Item> ADEPT_FRAGMENTS = ITEMS.register("adept_fragments",
            () -> new RarityItem(new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB),
                    Text.translatable("cobgyms.lang.poke_fragments", Text.translatable("cobgyms.lang.adept")), // name
                    new Text[]{Text.translatable("tooltip.cobgyms.poke_fragments.tooltip", Text.translatable("cobgyms.lang.adept"))}, // tooltips
                    Formatting.BLUE,
                    false));

    public static final RegistrySupplier<Item> MASTER_FRAGMENTS = ITEMS.register("master_fragments",
            () -> new RarityItem(new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB),
                    Text.translatable("cobgyms.lang.poke_fragments", Text.translatable("cobgyms.lang.master")), // name
                    new Text[]{Text.translatable("tooltip.cobgyms.poke_fragments.tooltip", Text.translatable("cobgyms.lang.master"))}, // tooltips
                    Formatting.LIGHT_PURPLE,
                    true));


    // Lesser Caches
    public static final RegistrySupplier<Item> EMPTY_LESSER_CACHE = ITEMS.register("empty_lesser_cache", () ->
            new RarityItem(new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB),
                    Text.translatable("cobgyms.lang.empty_poke_cache", Text.translatable("cobgyms.lang.lesser")),
                    null,
                    Formatting.RED,
                    false));

    public static final List<RegistrySupplier<Item>> LESSER_WOODLAND_CACHE = registerCache(
            "lesser",
            "woodland",
            Formatting.RED);

    public static final List<RegistrySupplier<Item>> LESSER_VOLCANIC_CACHE = registerCache(
            "lesser",
            "volcanic",
            Formatting.RED);

    public static final List<RegistrySupplier<Item>> LESSER_AQUATIC_CACHE = registerCache(
            "lesser",
            "aquatic",
            Formatting.RED);


    // Adept Caches
    public static final RegistrySupplier<Item> EMPTY_ADEPT_CACHE = ITEMS.register("empty_adept_cache", () ->
            new RarityItem(new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB),
                    Text.translatable("cobgyms.lang.empty_poke_cache", Text.translatable("cobgyms.lang.adept")),
                    null,
                    Formatting.BLUE,
                    false));

    public static final List<RegistrySupplier<Item>> ADEPT_WOODLAND_CACHE = registerCache(
            "adept",
            "woodland",
            Formatting.BLUE);

    public static final List<RegistrySupplier<Item>> ADEPT_VOLCANIC_CACHE = registerCache(
            "adept",
            "volcanic",
            Formatting.BLUE);

    public static final List<RegistrySupplier<Item>> ADEPT_AQUATIC_CACHE = registerCache(
            "adept",
            "aquatic",
            Formatting.BLUE);


    // Master Caches
    public static final RegistrySupplier<Item> EMPTY_MASTER_CACHE = ITEMS.register("empty_master_cache", () ->
            new RarityItem(new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB),
                    Text.translatable("cobgyms.lang.empty_poke_cache", Text.translatable("cobgyms.lang.master")),
                    null,
                    Formatting.LIGHT_PURPLE,
                    false));

    public static final List<RegistrySupplier<Item>> MASTER_WOODLAND_CACHE = registerCache(
            "master",
            "woodland",
            Formatting.LIGHT_PURPLE);

    public static final List<RegistrySupplier<Item>> MASTER_VOLCANIC_CACHE = registerCache(
            "master",
            "volcanic",
            Formatting.LIGHT_PURPLE);

    public static final List<RegistrySupplier<Item>> MASTER_AQUATIC_CACHE = registerCache(
            "master",
            "aquatic",
            Formatting.LIGHT_PURPLE);


    // Legendary Cache
    public static final RegistrySupplier<Item> EMPTY_LEGENDARY_CACHE = ITEMS.register("empty_legendary_cache", () ->
            new RarityItem(new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB),
                    Text.translatable("cobgyms.lang.empty_poke_cache", Text.translatable("cobgyms.lang.legendary")),
                    null,
                    Formatting.GOLD,
                    false));

    public static final List<RegistrySupplier<Item>> LEGENDARY_CACHE = registerCache(
            "legendary",
            null,
            Formatting.GOLD);



    private static List<RegistrySupplier<Item>> registerCache(String rarity, String cacheType, Formatting nameFormatting) {
        String cacheKey;
        Text[] tooltips;
        MutableText displayName;
        MutableText shinyDisplayName;

        if (cacheType == null){
            cacheKey = String.format("%s_cache", rarity);

            displayName = Text.translatable("cobgyms.lang.poke_cache_typeless", Text.translatable("cobgyms.lang."+rarity));
            shinyDisplayName = Text.translatable("cobgyms.lang.poke_cache_typeless_shiny", Text.translatable("cobgyms.lang."+rarity));

            tooltips = new Text[]{Text.translatable("tooltip.cobgyms.poke_cache_typeless.tooltip", Text.translatable("cobgyms.lang."+rarity))};

        } else {
            cacheKey = String.format("%s_%s_cache", rarity, cacheType);

            tooltips = new Text[]{Text.translatable("tooltip.cobgyms.poke_cache.tooltip", Text.translatable("cobgyms.lang.rarity."+rarity), Text.translatable("cobgyms.lang."+cacheType))};

            displayName = Text.translatable("cobgyms.lang.poke_cache", Text.translatable("cobgyms.lang."+rarity), Text.translatable("cobgyms.lang."+cacheType));

            shinyDisplayName = Text.translatable("cobgyms.lang.poke_cache_shiny", Text.translatable("cobgyms.lang."+rarity), Text.translatable("cobgyms.lang."+cacheType));
        }

        List<RegistrySupplier<Item>> itemList = new ArrayList<>();
        RegistrySupplier<Item> item = ITEMS.register(cacheKey, () ->
                new GymCacheItem(new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB).maxDamage(1),
                        cacheKey,
                        false,
                        displayName,
                        nameFormatting,
                        tooltips));

        RegistrySupplier<Item> shinyItem = ITEMS.register(cacheKey+"_shiny", () ->
                new GymCacheItem(new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB).maxDamage(1),
                        cacheKey,
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
