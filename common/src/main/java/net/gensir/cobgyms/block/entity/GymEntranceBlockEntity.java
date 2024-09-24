//package net.gensir.cobgyms.block.entity;
//
//import com.google.common.base.Suppliers;
//import net.gensir.cobgyms.registry.ModBlockEntityRegistry;
//import net.gensir.cobgyms.registry.ModBlockRegistry;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.entity.BlockEntity;
//import net.minecraft.block.entity.BlockEntityType;
//import net.minecraft.nbt.NbtCompound;
//import net.minecraft.nbt.NbtList;
//import net.minecraft.util.math.BlockPos;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//import java.util.function.Supplier;
//
//public class GymEntranceBlockEntity extends BlockEntity {
//    public static final BlockEntityType<GymEntranceBlockEntity> tt = BlockEntityType.Builder.create(GymEntranceBlockEntity::new, ModBlockRegistry.GYM_ENTRANCE.get()).build(null);
//    public static final Supplier<BlockEntityType<GymEntranceBlockEntity>> TYPE = Suppliers.memoize(() -> tt);
//
//    private final Map<UUID, Integer> playerClickCounts = new HashMap<>();
//
//    public GymEntranceBlockEntity(BlockPos pos, BlockState state) {
//        super(ModBlockEntityRegistry.GYM_ENTRANCE_ENTITY.get(), pos, state);
//    }
//
//    public void incrementClickCount(UUID playerUUID) {
//        playerClickCounts.put(playerUUID, playerClickCounts.getOrDefault(playerUUID, 0) + 1);
//        markDirty();  // Ensures the TileEntity saves its state
//    }
//
//    public int getClickCount(UUID playerUUID) {
//        return playerClickCounts.getOrDefault(playerUUID, 0);
//    }
//
//    @Override
//    public void readNbt(NbtCompound nbt) {
//        super.readNbt(nbt);
//
//        // Deserialize the player click count map from NBT
//        NbtList clickList = nbt.getList("PlayerClickCounts", 10);
//        for (int i = 0; i < clickList.size(); i++) {
//            NbtCompound entry = clickList.getCompound(i);
//            UUID playerUUID = entry.getUuid("PlayerUUID");
//            int clickCount = entry.getInt("ClickCount");
//            playerClickCounts.put(playerUUID, clickCount);
//        }
//    }
//
//    @Override
//    public void writeNbt(NbtCompound compound) {
//        super.writeNbt(compound);
//
//        // Serialize the player click count map into NBT
//        NbtList clickList = new NbtList();
//        for (Map.Entry<UUID, Integer> entry : playerClickCounts.entrySet()) {
//            NbtCompound entryNBT = new NbtCompound();
//            entryNBT.putUuid("PlayerUUID", entry.getKey());
//            entryNBT.putInt("ClickCount", entry.getValue());
//            clickList.add(entryNBT);
//        }
//        compound.put("PlayerClickCounts", clickList);
//    }
//}
