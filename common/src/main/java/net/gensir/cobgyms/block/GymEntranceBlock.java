//package net.gensir.cobgyms.block;
//
//import net.gensir.cobgyms.block.entity.GymEntranceBlockEntity;
//import net.gensir.cobgyms.util.ClientUtils;
//import net.minecraft.block.BlockEntityProvider;
//import net.minecraft.block.BlockRenderType;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.BlockWithEntity;
//import net.minecraft.block.entity.BlockEntity;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.text.Text;
//import net.minecraft.util.ActionResult;
//import net.minecraft.util.Hand;
//import net.minecraft.util.hit.BlockHitResult;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import org.jetbrains.annotations.Nullable;
//
//public class GymEntranceBlock extends BlockWithEntity implements BlockEntityProvider {
//
//    public GymEntranceBlock(Settings settings) {
//        super(settings);
//    }
//
//    @Nullable
//    @Override
//    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
//        return new GymEntranceBlockEntity(pos, state);
//    }
//
//    @Override
//    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
//        if (world.isClient) {
//            BlockEntity blockEntity = world.getBlockEntity(pos);
//            if (blockEntity instanceof GymEntranceBlockEntity gymEntranceBlockEntity){
//                int timesUsed = gymEntranceBlockEntity.getClickCount(player.getUuid());
//                ClientUtils.openGymEntranceScreen(timesUsed, pos);
//            }
//
//        }
//
//        return ActionResult.SUCCESS;
//    }
//
//    @Override
//    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player){
//        if (!world.isClient) {
//            player.sendMessage(Text.of("Broken"));
//            BlockEntity blockEntity = world.getBlockEntity(pos);
//            if (blockEntity instanceof GymEntranceBlockEntity gymEntranceBlockEntity){
//
//                int clickCount = gymEntranceBlockEntity.getClickCount(player.getUuid());
//                player.sendMessage(Text.of("You have clicked this block " + clickCount + " times!"));
//            }
//        }
//    }
//
//    @Override
//    public BlockRenderType getRenderType(BlockState state) {
//        return BlockRenderType.MODEL;
//    }
//}
