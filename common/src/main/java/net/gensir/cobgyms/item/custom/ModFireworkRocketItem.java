package net.gensir.cobgyms.item.custom;

import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;

import java.util.List;

public class ModFireworkRocketItem {
    public static ItemStack createFireworkStack(int mainColour, int fadeColour, double flightDuration) {
        ItemStack fireworkStack = new ItemStack(Items.FIREWORK_ROCKET);

        NbtCompound fireworkTag = new NbtCompound();
        NbtCompound explosion = new NbtCompound();
        explosion.putByte("Type", (byte) 0);
        explosion.putBoolean("Flicker", true);
        explosion.putBoolean("Trail", true);
        explosion.putIntArray("Colors", List.of(mainColour));
        explosion.putIntArray("FadeColors", List.of(fadeColour));

        NbtList explosions = new NbtList();
        explosions.add(explosion);
        fireworkTag.put("Explosions", explosions);
        fireworkTag.putByte("Flight", (byte) flightDuration);

        fireworkStack.setSubNbt("Fireworks", fireworkTag);

        return fireworkStack;
    }
}
