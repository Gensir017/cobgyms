package net.gensir.cobgyms.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import java.util.List;


public class TooltipItem extends Item {

    private final Text[] tooltips;

    public TooltipItem(Settings settings, Text[] tooltips) {
        super(settings);
        this.tooltips = tooltips;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

        if(this.tooltips != null){
            tooltip.add(this.tooltips[0]);
        }
    }
}


