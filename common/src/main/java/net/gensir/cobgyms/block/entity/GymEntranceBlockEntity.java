package net.gensir.cobgyms.block.entity;

import com.google.common.base.Suppliers;
import net.gensir.cobgyms.block.GymEntranceBlock;
import net.gensir.cobgyms.registry.ModBlockEntityRegistry;
import net.gensir.cobgyms.registry.ModBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

public class GymEntranceBlockEntity extends BlockEntity {
    public static final Supplier<BlockEntityType<GymEntranceBlockEntity>> TYPE = Suppliers.memoize(() -> BlockEntityType.Builder.create(GymEntranceBlockEntity::new,
            ModBlockRegistry.GYM_ENTRANCE.get(),
            ModBlockRegistry.GYM_ENTRANCE_WOODLAND.get(),
            ModBlockRegistry.GYM_ENTRANCE_VOLCANIC.get(),
            ModBlockRegistry.GYM_ENTRANCE_AQUATIC.get()).build(null));
    private final Map<UUID, Integer> playerTimesUsed = new HashMap<>();
    private String theme;

    public GymEntranceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityRegistry.GYM_ENTRANCE_ENTITY.get(), pos, state);
        Block block = state.getBlock();
        if (block instanceof GymEntranceBlock gymEntranceBlock){
            this.theme = gymEntranceBlock.getTheme();
        } else {
            this.theme = "random";
        }
    }

    public void incrementTimesUsed(UUID playerUUID) {
        playerTimesUsed.put(playerUUID, playerTimesUsed.getOrDefault(playerUUID, 0) + 1);
        markDirty();  // Ensures the TileEntity saves its state
    }

    public int getTimesUsed(UUID playerUUID) {
        return playerTimesUsed.getOrDefault(playerUUID, 0);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        // Deserialize the player click count map from NBT
        NbtList clickList = nbt.getList("PlayerTimesUsed", 10);
        for (int i = 0; i < clickList.size(); i++) {
            NbtCompound entry = clickList.getCompound(i);
            UUID playerUUID = entry.getUuid("PlayerUUID");
            int timesUsed = entry.getInt("TimesUsed");
            playerTimesUsed.put(playerUUID, timesUsed);
        }
    }

    @Override
    public void writeNbt(NbtCompound compound) {
        super.writeNbt(compound);

        // Serialize the player click count map into NBT
        NbtList clickList = new NbtList();
        for (Map.Entry<UUID, Integer> entry : playerTimesUsed.entrySet()) {
            NbtCompound entryNBT = new NbtCompound();
            entryNBT.putUuid("PlayerUUID", entry.getKey());
            entryNBT.putInt("TimesUsed", entry.getValue());
            clickList.add(entryNBT);
        }
        compound.put("PlayerTimesUsed", clickList);
    }

    public String getTheme(){
        return this.theme;
    }
}
