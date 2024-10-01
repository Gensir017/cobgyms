package net.gensir.cobgyms.item.custom;

import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import net.gensir.cobgyms.util.ClientUtils;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static net.gensir.cobgyms.network.ServerPacketHandler.CACHE_OPEN_PACKET_ID;

public class GymCacheItem extends Item {
    private final Boolean increasedShinyChance;
    private final MutableText displayName;
    private final Formatting nameFormatting;
    private final Text[] tooltips;
    private final String rarity;

    public GymCacheItem(Settings settings, String rarity, Boolean increasedShinyChance, MutableText displayName, Formatting nameFormatting, Text[] tooltips) {
        super(settings);
        this.rarity = rarity;
        this.increasedShinyChance = increasedShinyChance;
        this.displayName = displayName;
        this.nameFormatting = nameFormatting;
        this.tooltips = tooltips;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(world.isClient()){
            if (!Objects.equals(this.rarity, "legendary")){
                ClientUtils.openGymCacheScreen(this.rarity, this.increasedShinyChance);
            } else {
                PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
                buf.writeString(this.rarity);
                buf.writeString("no_theme");
                buf.writeBoolean(this.increasedShinyChance);

                NetworkManager.sendToServer(CACHE_OPEN_PACKET_ID, buf);
            }
        }
        return super.use(world, user, hand);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return this.increasedShinyChance;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(this.tooltips != null){
            tooltip.addAll(Arrays.asList(this.tooltips));
        }
        if(this.increasedShinyChance){
            tooltip.add(Text.translatable("tooltip.cobgyms.shiny_cache.tooltip"));
        }

        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public Text getName(ItemStack stack) {
        return this.displayName.formatted(this.nameFormatting);
    }
}
