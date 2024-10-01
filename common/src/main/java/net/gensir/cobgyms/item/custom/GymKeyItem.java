package net.gensir.cobgyms.item.custom;

import net.gensir.cobgyms.util.ClientUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;

import net.minecraft.world.World;


public class GymKeyItem extends RarityItem {

    public GymKeyItem(Settings settings, MutableText displayName, Text[] tooltips, Formatting nameFormatting, Boolean giveGlint) {
        super(settings, displayName, tooltips, nameFormatting, giveGlint);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) {
            ClientUtils.openStartGymScreen(user);
        }
        return super.use(world, user, hand);
    }
}
