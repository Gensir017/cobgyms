package net.gensir.cobgyms.gym.lootTable;

import com.cobblemon.mod.common.CobblemonItems;
import net.gensir.cobgyms.registry.ModItemRegistry;
import net.minecraft.item.Item;
import net.minecraft.server.network.ServerPlayerEntity;

import static net.gensir.cobgyms.gym.lootTable.GymLootTable.giveItem;
import static net.minecraft.item.Items.*;
import static net.minecraft.item.Items.LAPIS_LAZULI;

public class MasterLootTable {
    public static void generateMasterLootTable(int level, ServerPlayerEntity player, int breakVal) {
        giveItem(
                player,
                ModItemRegistry.MASTER_SHARD.get(),
                GymLootTable.randomGen(
                        level,
                        breakVal,
                        0.5,
                        4,
                        4,
                        0)
        );
        giveItem(
                player,
                ModItemRegistry.ADEPT_SHARD.get(),
                GymLootTable.randomGen(
                        level,
                        breakVal,
                        0.5,
                        4,
                        4,
                        0,
                        true)
        );
        giveItem(
                player,
                CobblemonItems.ULTRA_BALL,
                GymLootTable.randomGen(
                        level,
                        breakVal,
                        0.5,
                        3,
                        6,
                        0)
        );
        giveItem(
                player,
                CobblemonItems.GREAT_BALL,
                GymLootTable.randomGen(
                        level,
                        breakVal,
                        0.5,
                        3,
                        6,
                        0,
                        true)
        );
        giveItem(
                player,
                DIAMOND,
                GymLootTable.randomGen(
                        level,
                        breakVal,
                        0.4,
                        6,
                        8,
                        0)
        );
        giveItem(
                player,
                GOLD_INGOT,
                2* GymLootTable.randomGen(
                        level,
                        breakVal,
                        0.5,
                        5,
                        8,
                        0,
                        true)
        );
        giveItem(
                player,
                LAPIS_LAZULI,
                3* GymLootTable.randomGen(
                        level,
                        breakVal,
                        0.75,
                        6,
                        8,
                        0,
                        true)
        );

        giveItem(
                player,
                GymLootTable.randomItem(
                        new Item[]{
                                CobblemonItems.ANTIDOTE,
                                CobblemonItems.AWAKENING,
                                CobblemonItems.BURN_HEAL,
                                CobblemonItems.ICE_HEAL,
                                CobblemonItems.PARALYZE_HEAL,
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
                        0.25,
                        20,
                        2,
                        0)
        );
        giveItem(
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
                                CobblemonItems.LEVEL_BALL
                        }),
                GymLootTable.randomGen(
                        level,
                        breakVal,
                        0.4,
                        8,
                        5,
                        0)
        );
        giveItem(
                player,
                CobblemonItems.EXPERIENCE_CANDY_L,
                GymLootTable.randomGen(
                        level,
                        breakVal,
                        0.3,
                        8,
                        3,
                        0)
        );
        giveItem(
                player,
                CobblemonItems.MAX_REVIVE,
                GymLootTable.randomGen(
                        level,
                        breakVal,
                        0.4,
                        12,
                        1,
                        0)
        );
        giveItem(
                player,
                CobblemonItems.HYPER_POTION,
                GymLootTable.randomGen(
                        level,
                        breakVal,
                        0.4,
                        4,
                        4,
                        0)
        );
        giveItem(
                player,
                CobblemonItems.SUPER_POTION,
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
