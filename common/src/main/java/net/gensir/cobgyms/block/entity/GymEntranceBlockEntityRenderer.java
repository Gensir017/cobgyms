package net.gensir.cobgyms.block.entity;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;


public class GymEntranceBlockEntityRenderer implements BlockEntityRenderer<GymEntranceBlockEntity> {
    private final TextRenderer textRenderer;

    public GymEntranceBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        this.textRenderer = MinecraftClient.getInstance().textRenderer;
    }

    @Override
    public void render(GymEntranceBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        String theme = blockEntity.getTheme();
        Text text = Text.translatable("cobgyms.lang.gym_entrance.message", Text.translatable("cobgyms.lang." + theme));

        matrices.push();
        matrices.translate(0.5, 1.5, 0.5);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180.0f));

        float scale = 0.018f;
        matrices.scale(scale, scale, scale);

        Camera camera = MinecraftClient.getInstance().gameRenderer.getCamera();
        Vec3d cameraPos = camera.getPos();

        double dx = cameraPos.getX() - blockEntity.getPos().getX() - 0.5;
        double dz = cameraPos.getZ() - blockEntity.getPos().getZ() - 0.5;
        double dy = cameraPos.getY() - blockEntity.getPos().getY() - 0.5;

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
                    0xFFFFFF, // text color
                    false, // shadow
                    matrices.peek().getPositionMatrix(),
                    vertexConsumers,
                    TextRenderer.TextLayerType.SEE_THROUGH,
                    0, // background color
                    light); // light level
        }

        matrices.pop();
    }
}
