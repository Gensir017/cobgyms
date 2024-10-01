package net.gensir.cobgyms.util;

import dev.architectury.event.events.common.LootEvent;
import net.gensir.cobgyms.registry.ModItemRegistry;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;

public class ModLootTableModifiers {

    private static void weightedAddGymKey(Identifier identifier, int desiredWeight, int fillerWeight){
        LootEvent.MODIFY_LOOT_TABLE.register((lootTables, id, context, builtin) -> {
            if (builtin && identifier.equals(id)) {
                LootPool.Builder pool = LootPool.builder().with(ItemEntry.builder(ModItemRegistry.GYM_KEY.get()).weight(desiredWeight)).with(ItemEntry.builder(Items.AIR).weight(fillerWeight));
                context.addPool(pool);
            }
        });
    }

    private static void addGymKey(Identifier identifier){
        LootEvent.MODIFY_LOOT_TABLE.register((lootTables, id, context, builtin) -> {
            if (builtin && identifier.equals(id)) {
                LootPool.Builder pool = LootPool.builder().with(ItemEntry.builder(ModItemRegistry.GYM_KEY.get()));
                context.addPool(pool);
            }
        });
    }

    public static void modifyLootTables() {
        // Villages
        // 50% chance
        weightedAddGymKey(new Identifier("minecraft", "chests/village/village_plains_house"), 1, 1);
        weightedAddGymKey(new Identifier("minecraft", "chests/village/village_taiga_house"), 1, 1);
        weightedAddGymKey(new Identifier("minecraft", "chests/village/village_snowy_house"), 1, 1);
        weightedAddGymKey(new Identifier("minecraft", "chests/village/village_savanna_house"), 1, 1);
        weightedAddGymKey(new Identifier("minecraft", "chests/village/village_desert_house"), 1, 1);
        // 100% chance
        addGymKey(new Identifier("minecraft", "chests/village/village_weaponsmith"));

        // Ruined Portal
        // 75% chance
        weightedAddGymKey(new Identifier("minecraft", "chests/ruined_portal"), 3, 1);

        // Gilded chests (Gimmi towers)
        //cobblemon:ruins/gilded_chests/ruins
        // 100% chance
        addGymKey(new Identifier("cobblemon", "ruins/gilded_chests/ruins"));

        // Jungle temple
        // 75% chance
        weightedAddGymKey(new Identifier("minecraft", "chests/jungle_temple"), 3, 1);

        // Abandoned Mineshaft
        // 50% chance
        weightedAddGymKey(new Identifier("minecraft", "chests/abandoned_mineshaft"), 1, 1);

        // Ancient City
        // 75% chance
        weightedAddGymKey(new Identifier("minecraft", "chests/ancient_city"), 3, 1);

        // End City
        // 75% chance
        weightedAddGymKey(new Identifier("minecraft", "chests/end_city_treasure"), 3, 1);

        // Desert Pyramid
        // 75% chance
        weightedAddGymKey(new Identifier("minecraft", "chests/desert_pyramid"), 3, 1);

        // Stronghold
        // 75% chance
        weightedAddGymKey(new Identifier("minecraft", "chests/stronghold_corridor"), 3, 1);
        weightedAddGymKey(new Identifier("minecraft", "chests/stronghold_crossing"), 3, 1);

        // Bastion
        // 75% chance
        weightedAddGymKey(new Identifier("minecraft", "chests/bastion_treasure"), 3, 1);
        weightedAddGymKey(new Identifier("minecraft", "chests/bastion_other"), 3, 1);
        weightedAddGymKey(new Identifier("minecraft", "chests/bastion_bridge"), 3, 1);

        // Pillager Outpost
        // 75% chance
        weightedAddGymKey(new Identifier("minecraft", "chests/pillager_outpost"), 3, 1);

        // Underwater Ruin
        // 75% chance
        weightedAddGymKey(new Identifier("minecraft", "chests/underwater_ruin_big"), 3, 1);
        weightedAddGymKey(new Identifier("minecraft", "chests/underwater_ruin_small"), 3, 1);

        // Shipwreck
        // 75% chance
        weightedAddGymKey(new Identifier("minecraft", "chests/shipwreck_treasure"), 3, 1);

        // Mob Spawner
        // 75% chance
        weightedAddGymKey(new Identifier("minecraft", "chests/simple_dungeon"), 3, 1);

        // Nether Fortress
        // 75% chance
        weightedAddGymKey(new Identifier("minecraft", "chests/nether_bridge"), 3, 1);

    }
}
