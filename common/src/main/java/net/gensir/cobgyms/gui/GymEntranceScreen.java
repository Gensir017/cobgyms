package net.gensir.cobgyms.gui;

import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import net.gensir.cobgyms.CobGyms;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.glfw.GLFW;

import java.util.Objects;

import static net.gensir.cobgyms.network.ServerPacketHandler.GYM_ENTRANCE_PACKET_ID;

public class GymEntranceScreen extends Screen {

    private final int timesUsed;
    private final BlockPos pos;
    private final String theme;
    private int integerValue = 0;
    private TextFieldWidget integerField;
    private boolean tooLowLevel = false;

    public GymEntranceScreen(int timesUsed, BlockPos pos, String theme) {
        super(Text.translatable("cobgyms.lang.menu.start.title"));
        this.timesUsed = timesUsed;
        this.pos = pos;
        this.theme = theme;
    }

    @Override
    protected void init() {
        int fieldWidth = 40;
        int fieldHeight = 20;
        int buttonWidth = 30;
        int bigButtonWidth = 120;
        int buttonHeight = 20;
        int x = (this.width - fieldWidth) / 2;
        int y = (this.height - fieldHeight - 60) / 2;
        int bigButtonX = (this.width) / 2;

        integerValue = 5;

        integerField = new TextFieldWidget(this.textRenderer, x, y, fieldWidth, fieldHeight, Text.of(""));
        integerField.setMaxLength(3);
        integerField.setEditableColor(0xFFFFFF);
        integerField.setText(String.valueOf(integerValue));
        this.addSelectableChild(integerField);

        // Decrease by 10 button
        this.addDrawableChild(ButtonWidget.builder(Text.of("-10"), button -> {
            integerValue -= 10;
            updateIntegerField();
        }).dimensions(x - buttonWidth - 40, y, buttonWidth, buttonHeight).build());

        // Decrease by 1 button
        this.addDrawableChild(ButtonWidget.builder(Text.of("-1"), button -> {
            integerValue -= 1;
            updateIntegerField();
        }).dimensions(x - buttonWidth - 5, y, buttonWidth, buttonHeight).build());

        // Increase by 1 button
        this.addDrawableChild(ButtonWidget.builder(Text.of("+1"), button -> {
            integerValue += 1;
            updateIntegerField();
        }).dimensions(x + fieldWidth + 5, y, buttonWidth, buttonHeight).build());

        // Increase by 10 button
        this.addDrawableChild(ButtonWidget.builder(Text.of("+10"), button -> {
            integerValue += 10;
            updateIntegerField();
        }).dimensions(x + fieldWidth + 40, y, buttonWidth, buttonHeight).build());


        this.addDrawableChild(ButtonWidget.builder(Text.translatable("cobgyms.lang.menu.start.title"), button -> {
            sendGymEntrancePacket(integerField.getText());
        }).dimensions(bigButtonX - bigButtonWidth - 5, y + fieldHeight + 30, bigButtonWidth, buttonHeight).build());

        this.addDrawableChild(ButtonWidget.builder(Text.translatable("cobgyms.lang.menu.cancel"), button -> {
            this.close();
        }).dimensions(bigButtonX + 5, y + fieldHeight + 30, bigButtonWidth, buttonHeight).build());
    }

    private void sendGymEntrancePacket(String stringLevel) {
        try {
            int level = checkLevel(Integer.parseInt(stringLevel));
            if (level >= 5){
                this.close();
                PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
                buf.writeInt(level);
                buf.writeBlockPos(this.pos);
                buf.writeString(this.theme);

                NetworkManager.sendToServer(GYM_ENTRANCE_PACKET_ID, buf);
            }
        } catch (Throwable e) {
            CobGyms.LOGGER.info(String.valueOf(e));
        }
    }

    private void updateIntegerField() {
        integerValue = checkLevel(integerValue);
        integerField.setText(String.valueOf(integerValue));
    }

    private int checkLevel(int level){
        if(level > 100){
            return 100;
        }
        tooLowLevel = level < 5;
        if (level < 0){
            return 0;
        }
        return level;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);

        try {
            int currentValue = integerField.getText().isEmpty() ? -1 : Integer.parseInt(integerField.getText());
            if (currentValue > 100) {
                integerField.setText("100");
            } else if (currentValue < 0) {
                integerField.setText("");
            }
            tooLowLevel = currentValue < 5;
            integerValue = checkLevel(currentValue);
        } catch (Throwable e) {
            CobGyms.LOGGER.info(String.valueOf(e));

        }

        integerField.render(context, mouseX, mouseY, delta);

        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("cobgyms.lang.menu.entrance.enter_theme",Text.translatable("cobgyms.lang."+this.theme)), this.width / 2, ((this.height - 60) / 2) - 65, 0xFFFFFF);

        if (CobGyms.GYM_ENTRANCE_USAGES != -1){
            int timesAvailable = CobGyms.GYM_ENTRANCE_USAGES - timesUsed;
            if (timesAvailable > 0){
                context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("cobgyms.lang.menu.entrance.times_available",timesAvailable), this.width / 2, ((this.height - 60) / 2) - 45, 0xFFFFFF);
            } else {
                context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("cobgyms.lang.menu.entrance.limit"), this.width / 2, ((this.height - 60) / 2) - 45, Colors.RED);
            }
        }

        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("cobgyms.lang.menu.start.level"), this.width / 2, ((this.height - 60) / 2) - 25, 0xFFFFFF);

        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("cobgyms.lang.menu.start.rewards"), this.width / 2, ((this.height - 60) / 2) + 18, 0xFFFFFF);

        if(tooLowLevel){
            context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("cobgyms.lang.menu.start.level_min"), this.width / 2, ((this.height - 60) / 2) + 65, Colors.RED);
        }

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
            this.close();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (Character.isDigit(chr)) {
            return integerField.charTyped(chr, modifiers);
        }
        return false;
    }

    @Override
    public void tick() {
        integerField.tick();
    }
}
