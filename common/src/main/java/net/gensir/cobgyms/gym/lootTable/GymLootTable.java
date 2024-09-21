package net.gensir.cobgyms.gym.lootTable;

import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class GymLootTable {
    private static final Random random = new Random();

    public static final int adeptBreak = 30;
    public static final int masterBreak = 60;
    public static final int legendaryBreak = 90;

    public static void generateLoot(int level, ServerPlayerEntity player){
        if(level < adeptBreak){
            LesserLootTable.generateLesserLootTable(level, player, 0);
        } else if(level >= adeptBreak && level < masterBreak){
            AdeptLootTable.generateAdeptLootTable(level, player, adeptBreak);
        } else if(level >= masterBreak && level < legendaryBreak){
            MasterLootTable.generateMasterLootTable(level, player, masterBreak);
        } else if(level >= legendaryBreak){
            LegendaryLootTable.generateLegendaryLootTable(level, player, legendaryBreak);
        }
    }

    public static int randomGen(
            int level,
            int breakVal,
            double scalar, int chanceBoost, int iterations, int levelBoost){

        int itemCount = 0;
        for (int i = 0; i < iterations; i++) {
            itemCount += singularGen(level, breakVal, scalar, chanceBoost, levelBoost);
        }
        return itemCount;
    }

    public static int singularGen(
            int level,
            int breakVal,
            double scalar, int chanceBoost, int levelBoost){

        level += levelBoost;

        int randomNumber = random.nextInt(31);
        if (randomNumber <= (((level-breakVal)*scalar)+chanceBoost)) {
            return 1;
        }

        return 0;
    }

    public static int randomGen(
            int level,
            int breakVal,
            double scalar, int chanceBoost, int iterations, int levelBoost, boolean reverse){
        if (reverse){
            return randomGen(((breakVal*2)+30)-level, breakVal, scalar, chanceBoost, iterations, levelBoost);
        } else {
            return randomGen(level, breakVal, scalar, chanceBoost, iterations, levelBoost);
        }

    }

    public static void giveItem(ServerPlayerEntity player, Item item, int amount){
        if (amount > 0){
            World world = player.getEntityWorld();
            ItemStack itemStack = new ItemStack(
                    item,
                    amount);
            if(!player.giveItemStack(itemStack)){
                BlockPos pos = player.getBlockPos();
                ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), itemStack);
                world.spawnEntity(itemEntity);
            }
        }

    }

    public static Item randomItem(Item[] itemArr){
        int randomIndex = random.nextInt(itemArr.length);

        return  itemArr[randomIndex];
    }
}
