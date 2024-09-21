package net.gensir.cobgyms.gui;

import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import static net.gensir.cobgyms.network.PacketHandler.LEAVE_GYM_PACKET_ID;

public class LeaveGymScreen extends Screen {

    public LeaveGymScreen() {
        super(Text.translatable("cobgyms.lang.menu.leave.title"));
    }

    @Override
    protected void init() {

        int buttonWidth = 120;
        int buttonHeight = 20;
        int x = (this.width) / 2;
        int y = (this.height + 20) / 2;


        this.addDrawableChild(ButtonWidget.builder(Text.translatable("cobgyms.lang.menu.yes"), button -> {
            sendLeaveGymPacket();
            this.close();
        }).dimensions(x - buttonWidth - 5, y, buttonWidth, buttonHeight).build());


        this.addDrawableChild(ButtonWidget.builder(Text.translatable("cobgyms.lang.menu.no"), button -> {
            this.close();
        }).dimensions(x + 5, y, buttonWidth, buttonHeight).build());

    }

    private void sendLeaveGymPacket() {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        NetworkManager.sendToServer(LEAVE_GYM_PACKET_ID, buf);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);

        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("cobgyms.lang.menu.leave.confirmation"), this.width / 2, ((this.height + 20) / 2) - 30, 0xFFFFFF);

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
}
