package net.gensir.cobgyms.block;

import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import net.gensir.cobgyms.CobGyms;
import net.gensir.cobgyms.block.entity.GymEntranceBlockEntity;
import net.gensir.cobgyms.network.ClientPacketHandler;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class GymEntranceBlock extends BlockWithEntity implements BlockEntityProvider {
    private final String theme;

    public GymEntranceBlock(Settings settings, String theme) {
        super(settings);
        this.theme = theme;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new GymEntranceBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world instanceof ServerWorld serverWorld && player instanceof ServerPlayerEntity serverPlayer){
            BlockEntity blockEntity = serverWorld.getBlockEntity(pos);
            if (blockEntity instanceof GymEntranceBlockEntity gymEntranceBlockEntity){
                int timesUsed = gymEntranceBlockEntity.getTimesUsed(serverPlayer.getUuid());

                PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
                buf.writeInt(timesUsed);
                buf.writeBlockPos(pos);
                buf.writeString(this.theme);
                NetworkManager.sendToPlayer(serverPlayer, ClientPacketHandler.GYM_ENTRANCE_SCREEN_PACKET_ID, buf);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public String getTheme(){
        return this.theme;
    }
}
