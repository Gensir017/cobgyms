package net.gensir.cobgyms.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class RarityItem extends Item {

    private final Boolean giveGlint;
    private final MutableText displayName;
    private final Text[] tooltips;
    private final Formatting nameFormatting;


    public RarityItem(Settings settings, MutableText displayName, Text[] tooltips, Formatting nameFormatting, Boolean giveGlint) {
        super(settings);
        this.giveGlint = giveGlint;
        this.displayName = displayName;
        this.tooltips = tooltips;
        this.nameFormatting = nameFormatting;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return giveGlint;
    }

    @Override
    public Text getName(ItemStack stack) {
        if (this.nameFormatting != null){
            return this.displayName.formatted(this.nameFormatting);
        } else {
            return this.displayName;
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        
        if(this.tooltips != null){
            tooltip.addAll(Arrays.asList(this.tooltips));
        }
    }
}


