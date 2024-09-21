package net.gensir.cobgyms.block.entity;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.RotationAxis;


public class GymExitBlockEntityRenderer implements BlockEntityRenderer<GymExitBlockEntity> {
    private final TextRenderer textRenderer;

    public GymExitBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        this.textRenderer = MinecraftClient.getInstance().textRenderer;
    }

    @Override
    public void render(GymExitBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        Text text = Text.translatable("cobgyms.lang.gym_exit.message");

        matrices.push();
        matrices.translate(0.5, 1.5, 0.5);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180.0f));

        float scale = 0.018f;
        matrices.scale(scale, scale, scale);

        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        if (player != null) {
            double dx = player.getX() - blockEntity.getPos().getX();
            double dz = player.getZ() - blockEntity.getPos().getZ();
            double dy = player.getY() - blockEntity.getPos().getY();
            double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);

            if (distance <= 10.0) {
                float yaw = (float) (Math.atan2(dz, dx) * (180 / Math.PI)) - 90.0f;

                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(yaw));

                float f = (float) (-this.textRenderer.getWidth(text) / 2);
                float g = (float) (-this.textRenderer.fontHeight / 2);

                this.textRenderer.draw(
                        text, // text
                        f + 0.5f, // x
                        g + 0.5f, // y
                        0xFFFFFF, // text colour
                        false, // shadow
                        matrices.peek().getPositionMatrix(),
                        vertexConsumers,
                        TextRenderer.TextLayerType.SEE_THROUGH,
                        0, // bg colour
                        1); // light
            }
        }

        matrices.pop();
    }
}
