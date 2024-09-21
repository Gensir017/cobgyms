package net.gensir.cobgyms.gym.lootTable;

import com.cobblemon.mod.common.CobblemonItems;
import net.gensir.cobgyms.registry.ModItemRegistry;
import net.minecraft.item.Item;
import net.minecraft.server.network.ServerPlayerEntity;

import static net.gensir.cobgyms.gym.lootTable.GymLootTable.giveItem;
import static net.minecraft.item.Items.*;

public class AdeptLootTable {
    public static void generateAdeptLootTable(int level, ServerPlayerEntity player, int breakVal) {
        giveItem(
                player,
                ModItemRegistry.ADEPT_SHARD.get(),
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
                ModItemRegistry.LESSER_SHARD.get(),
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
                CobblemonItems.GREAT_BALL,
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
                CobblemonItems.POKE_BALL,
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
                GOLD_INGOT,
                2* GymLootTable.randomGen(
                        level,
                        breakVal,
                        0.5,
                        5,
                        8,
                        0)
        );
        giveItem(
                player,
                IRON_INGOT,
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
                        0)
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
                        12,
                        2,
                        0)
        );
        giveItem(
                player,
                GymLootTable.randomItem(
                        new Item[] {
                                CobblemonItems.NET_BALL,
                                CobblemonItems.DIVE_BALL,
                                CobblemonItems.PARK_BALL,
                                CobblemonItems.HEAL_BALL,
                                CobblemonItems.LEVEL_BALL,
                                CobblemonItems.SAFARI_BALL,
                                CobblemonItems.FRIEND_BALL,
                                CobblemonItems.MOON_BALL,
                                CobblemonItems.HEAVY_BALL,
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
                CobblemonItems.EXPERIENCE_CANDY_M,
                GymLootTable.randomGen(
                        level,
                        breakVal,
                        0.3,
                        6,
                        3,
                        0)
        );
        giveItem(
                player,
                CobblemonItems.REVIVE,
                GymLootTable.randomGen(
                        level,
                        breakVal,
                        0.2,
                        12,
                        2,
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
                        0)
        );
        giveItem(
                player,
                CobblemonItems.POTION,
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