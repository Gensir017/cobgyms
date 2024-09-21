package net.gensir.cobgyms.block.entity;

import com.google.common.base.Suppliers;
import net.gensir.cobgyms.registry.ModBlockEntityRegistry;
import net.gensir.cobgyms.registry.ModBlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

import java.util.function.Supplier;

public class GymExitBlockEntity extends BlockEntity {
    public static final Supplier<BlockEntityType<GymExitBlockEntity>> TYPE = Suppliers.memoize(() -> BlockEntityType.Builder.create(GymExitBlockEntity::new, ModBlockRegistry.GYM_EXIT.get()).build(null));

    public GymExitBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityRegistry.GYM_EXIT_ENTITY.get(), pos, state);
    }
}
