package net.gensir.cobgyms.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import net.gensir.cobgyms.CobGyms;
import net.gensir.cobgyms.gui.button.ImageTextButtonWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import static net.gensir.cobgyms.network.ServerPacketHandler.CACHE_LIST_PACKET_ID;
import static net.gensir.cobgyms.network.ServerPacketHandler.CACHE_OPEN_PACKET_ID;

public class GymCacheScreen extends Screen {
    private final String rarity;
    private final boolean increasedShinyChance;

    private int scrollOffset = 0;
    private int totalWidth = 0; // The total width of all buttons combined
    private int buttonWidth = 100; // Width of each button (50 pixels)
    private int buttonHeight = 115; // Height of each button (70 pixels)
    private int visibleWidth = 330; // Width of the visible scroll area
    private int visibleHeight = 145; // Height of the visible scroll area (adjust as needed)
    private int buttonSpacing = 10; // Space between buttons in pixels


    public GymCacheScreen(String rarity, boolean increasedShinyChance) {
        super(Text.translatable("cobgyms.lang.menu.start.title"));
        this.rarity = rarity;
        this.increasedShinyChance = increasedShinyChance;
    }

    @Override
    protected void init() {
        int buttonCount = CobGyms.cacheThemes.size(); // Number of buttons to display
        int x = (this.width - visibleWidth) / 2; // Starting X position for scrollbox
        int y = (this.height - visibleHeight) / 2; // Starting Y position for scrollbox

        totalWidth = buttonCount * (buttonWidth + buttonSpacing); // Calculate total width of all buttons

        this.clearChildren();

        for (int i = 0; i < CobGyms.cacheThemes.size(); i++) {
            final int buttonIndex = i;

            MutableText cacheTranslatable = Text.translatable("cobgyms.lang.menu.cache.button",
                    Text.translatable("cobgyms.lang." + this.rarity),
                    Text.translatable("cobgyms.lang." + CobGyms.cacheThemes.get(i))
            );

            // Add buttons with their positions adjusted for horizontal scrolling
            Identifier image = new Identifier(CobGyms.MOD_ID, "textures/gui/"+CobGyms.cacheThemes.get(i)+"_cache_icon.png");

            int buttonX = x + i * (buttonWidth + buttonSpacing) - scrollOffset;

            this.addDrawableChild(new ImageTextButtonWidget(
                    buttonX+(buttonSpacing/2),
                    y,
                    buttonWidth,
                    buttonHeight,
                    cacheTranslatable, // Button text
                    image,
                    buttonWidth-40,  // Image width
                    buttonWidth-40,  // Image height
                    button -> {
                        sendGymCacheOpenPacket(CobGyms.cacheThemes.get(buttonIndex));
                    }
            ));

            this.addDrawableChild(ButtonWidget.builder(Text.of("?"), button -> {
                sendGymCacheListPacket(CobGyms.cacheThemes.get(buttonIndex));
            }).dimensions(buttonX+(buttonSpacing/2)+((buttonWidth/2)-20), y+buttonHeight+5, 40, 20).build());
        }
    }

    private void sendGymCacheOpenPacket(String cacheTheme) {
        this.close();
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeString(this.rarity);
        buf.writeString(cacheTheme);
        buf.writeBoolean(this.increasedShinyChance);

        NetworkManager.sendToServer(CACHE_OPEN_PACKET_ID, buf);
    }


    private void sendGymCacheListPacket(String cacheTheme) {
        this.close();
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeString(this.rarity);
        buf.writeString(cacheTheme);
        buf.writeBoolean(this.increasedShinyChance);

        NetworkManager.sendToServer(CACHE_LIST_PACKET_ID, buf);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);

        int x = (this.width - visibleWidth) / 2;
        int y = (this.height - visibleHeight) / 2;

        // Render scroll box background
        int borderThickness = 2;
        context.fill(x-buttonSpacing-borderThickness, y - 15-borderThickness, x + visibleWidth + (buttonSpacing*2) + (borderThickness), y + visibleHeight + 15 + (borderThickness), 0x88AAAAAA);
        context.fill(x-buttonSpacing, y - 15, x + visibleWidth + (buttonSpacing*2), y + visibleHeight + 15, 0x88000000); // Transparent black background

        // Scissoring (clipping) to the scrollable area
        enableScissor(x, y, visibleWidth, visibleHeight);

        // Render the buttons and other children within the scissored area
        super.render(context, mouseX, mouseY, delta);

        disableScissor();

        // Draw the horizontal scrollbar
        this.drawScrollbar(context);
    }

    private void drawScrollbar(DrawContext context) {
        if (totalWidth > visibleWidth) {
            int scrollbarWidth = (int) ((float) visibleWidth / totalWidth * visibleWidth);
            int scrollbarX = ((this.width - visibleWidth) / 2) + (int) ((float) scrollOffset / totalWidth * visibleWidth);

            int scrollbarYStart = ((this.height - visibleHeight) / 2) + visibleHeight;
            int scrollbarYEnd = scrollbarYStart + 10;

            context.fill(scrollbarX, scrollbarYStart, scrollbarX + scrollbarWidth, scrollbarYEnd, 0x33AAAAAA);
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        // Adjust the scroll offset based on mouse wheel movement (horizontal scrolling)
        scrollOffset -= (int) (amount * 20);
        scrollOffset = Math.max(0, Math.min(scrollOffset, totalWidth - visibleWidth));

        // Re-init the buttons to update their positions
        init();

        return super.mouseScrolled(mouseX, mouseY, amount);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
            this.close();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    // Helper methods to enable and disable scissoring
    private void enableScissor(int x, int y, int width, int height) {
        double scaleFactor = this.client.getWindow().getScaleFactor();
        RenderSystem.enableScissor((int) (x * scaleFactor), (int) ((this.height - (y + height)) * scaleFactor),
                (int) (width * scaleFactor), (int) (height * scaleFactor));
    }

    private void disableScissor() {
        RenderSystem.disableScissor();
    }
}
