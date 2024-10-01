package net.gensir.cobgyms.gym.lootTable;

import com.cobblemon.mod.common.CobblemonItems;
import net.gensir.cobgyms.registry.ModItemRegistry;
import net.minecraft.item.Item;
import net.minecraft.server.network.ServerPlayerEntity;

import static net.minecraft.item.Items.IRON_INGOT;

public class LesserLootTable {

    public static void generateLesserLootTable(int level, ServerPlayerEntity player, int breakVal){
        GymLootTable.giveItem(
                player,
                ModItemRegistry.LESSER_SHARD.get(),
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
                    CobblemonItems.POKE_BALL,
                    GymLootTable.randomGen(
                            level,
                            breakVal,
                            0.5,
                            3,
                            6,
                            0)
        );

        GymLootTable.giveItem(
                player,
                        IRON_INGOT,
                        2* GymLootTable.randomGen(
                                level,
                                breakVal,
                                0.5,
                                5,
                                8,
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
                        1,
                        0)
        );
        GymLootTable.giveItem(
                player,
                        GymLootTable.randomItem(
                                new Item[] {
                                        CobblemonItems.HEAL_BALL,
                                        CobblemonItems.SAFARI_BALL,
                                        CobblemonItems.FRIEND_BALL
                                }),
                        GymLootTable.randomGen(
                                level,
                                breakVal,
                                0.4,
                                5,
                                5,
                                0)
        );
        GymLootTable.giveItem(
                player,
                        CobblemonItems.EXPERIENCE_CANDY_S,
                        GymLootTable.randomGen(
                                level,
                                breakVal,
                                0.3,
                                4,
                                3,
                                0)
        );
        GymLootTable.giveItem(
                player,
                CobblemonItems.REVIVE,
                GymLootTable.randomGen(
                        level,
                        breakVal,
                        0.4,
                        12,
                        1,
                        0)
        );

        GymLootTable.giveItem(
                player,
                        CobblemonItems.POTION,
                        GymLootTable.randomGen(
                                level,
                                breakVal,
                                0.4,
                                4,
                                4,
                                0)
        );
    }
}
