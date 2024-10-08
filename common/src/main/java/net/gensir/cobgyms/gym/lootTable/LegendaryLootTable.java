package net.gensir.cobgyms.gym.lootTable;

import com.cobblemon.mod.common.CobblemonItems;
import net.gensir.cobgyms.registry.ModItemRegistry;
import net.minecraft.item.Item;
import net.minecraft.server.network.ServerPlayerEntity;

import static net.minecraft.item.Items.*;

public class LegendaryLootTable {
    public static void generateLegendaryLootTable(int level, ServerPlayerEntity player, int breakVal) {
        GymLootTable.giveItem(
                player,
                ModItemRegistry.LEGENDARY_SHARD.get(),
                GymLootTable.randomGen(
                        level,
                        breakVal,
                        0.5,
                        4,
                        4,
                        0)
        );
        GymLootTable.giveItem(
                player,
                ModItemRegistry.MASTER_SHARD.get(),
                GymLootTable.randomGen(
                        level,
                        breakVal,
                        0.5,
                        4,
                        4,
                        0,
                        true)
        );
        GymLootTable.giveItem(
                player,
                CobblemonItems.ULTRA_BALL,
                GymLootTable.randomGen(
                        level,
                        breakVal,
                        0.25,
                        18,
                        6,
                        0)
        );
        GymLootTable.giveItem(
                player,
                CobblemonItems.MASTER_BALL,
                GymLootTable.randomGen(
                        level,
                        breakVal,
                        0.1,
                        0,
                        1,
                        0)
        );
        GymLootTable.giveItem(
                player,
                DIAMOND,
                GymLootTable.randomGen(
                        level,
                        breakVal,
                        0.4,
                        6,
                        8,
                        0,
                        true)
        );
        GymLootTable.giveItem(
                player,
                DIAMOND,
                GymLootTable.randomGen(
                        level,
                        breakVal,
                        0.6,
                        10,
                        3,
                        0)
        );
        GymLootTable.giveItem(
                player,
                GymLootTable.randomItem(
                        new Item[]{
                                CobblemonItems.ANTIDOTE,
                                CobblemonItems.AWAKENING,
                                CobblemonItems.BURN_HEAL,
                                CobblemonItems.ICE_HEAL,
                                CobblemonItems.PARALYZE_HEAL,
                                CobblemonItems.FULL_HEAL
                        }),
                GymLootTable.randomGen(
                        level,
                        breakVal,
                        0.2,
                        28,
                        2,
                        0)
        );
        GymLootTable.giveItem(
                player,
                GymLootTable.randomItem(
                        new Item[] {
                                CobblemonItems.TIMER_BALL,
                                CobblemonItems.DUSK_BALL,
                                CobblemonItems.MOON_BALL,
                                CobblemonItems.HEAVY_BALL,
                                CobblemonItems.NET_BALL,
                                CobblemonItems.DIVE_BALL,
                                CobblemonItems.PARK_BALL,
                                CobblemonItems.LEVEL_BALL,
                                CobblemonItems.QUICK_BALL,
                                CobblemonItems.DREAM_BALL
                        }),
                GymLootTable.randomGen(
                        level,
                        breakVal,
                        0.4,
                        20,
                        5,
                        0)
        );
        GymLootTable.giveItem(
                player,
                CobblemonItems.EXPERIENCE_CANDY_XL,
                GymLootTable.randomGen(
                        level,
                        breakVal,
                        0.3,
                        8,
                        3,
                        0)
        );
        GymLootTable.giveItem(
                player,
                CobblemonItems.MAX_REVIVE,
                GymLootTable.randomGen(
                        level,
                        breakVal,
                        0.2,
                        12,
                        2,
                        0)
        );
        GymLootTable.giveItem(
                player,
                CobblemonItems.MAX_POTION,
                GymLootTable.randomGen(
                        level,
                        breakVal,
                        0.4,
                        4,
                        3,
                        0)
        );
        GymLootTable.giveItem(
                player,
                CobblemonItems.HYPER_POTION,
                GymLootTable.randomGen(
                        level,
                        breakVal,
                        0.4,
                        4,
                        4,
                        0,
                        true)
        );
    }

}
