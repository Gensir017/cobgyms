package net.gensir.cobgyms.gui.button;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ImageTextButtonWidget extends ButtonWidget {
    private final Identifier image; // The image to render
    private final int imageWidth;
    private final int imageHeight;
    protected static final NarrationSupplier DEFAULT_NARRATION_SUPPLIER = (textSupplier) -> {
        return (MutableText)textSupplier.get();
    };
    private final Text buttonMessage;

    public ImageTextButtonWidget(int x, int y, int width, int height, Text buttonMessage, Identifier image, int imageWidth, int imageHeight, PressAction onPress) {
        super(x, y, width, height, Text.of(""), onPress, DEFAULT_NARRATION_SUPPLIER);
        this.image = image;
        this.buttonMessage = buttonMessage;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    @Override
    public void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
        // Render the button background (optional, or you can customize this too)
        super.renderButton(context, mouseX, mouseY, delta);

        // Render the image in the upper part of the button
        int imageX = this.getX() + (this.width - imageWidth) / 2; // Center the image horizontally
        int imageY = (this.getY() + (this.height - imageHeight) / 2) - 15;; // Small padding from the top
        context.drawTexture(image, imageX, imageY, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);

        // Render the button's text at the bottom
        int textY = this.getY() + this.height - (MinecraftClient.getInstance().textRenderer.fontHeight*3); // Adjust to position the text near the bottom
        context.drawCenteredTextWithShadow(MinecraftClient.getInstance().textRenderer, this.buttonMessage, this.getX() + this.width / 2, textY, 0xFFFFFF);
        context.drawCenteredTextWithShadow(MinecraftClient.getInstance().textRenderer, Text.of("Pokémon"), this.getX() + this.width / 2, textY+MinecraftClient.getInstance().textRenderer.fontHeight, 0xFFFFFF);
    }
}
