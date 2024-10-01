package net.gensir.cobgyms.registry;

import dev.architectury.core.block.ArchitecturyLiquidBlock;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.gensir.cobgyms.block.GymEntranceBlock;
import net.gensir.cobgyms.block.GymExitBlock;
import net.gensir.cobgyms.item.custom.RarityBlockItem;
import net.gensir.cobgyms.util.ModItemGroup;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static net.gensir.cobgyms.CobGyms.MOD_ID;

public class ModBlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, RegistryKeys.BLOCK);

    public static final RegistrySupplier<Block> ANCIENT_RELIC = BLOCKS.register("ancient_relic", () ->
            new Block(Block.Settings.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistrySupplier<Item> ANCIENT_RELIC_ITEM = ModItemRegistry.ITEMS.register("ancient_relic", () ->
            new BlockItem(ANCIENT_RELIC.get(), new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB)));


    public static final RegistrySupplier<Block> DEEPSLATE_ANCIENT_RELIC = BLOCKS.register("deepslate_ancient_relic", () ->
            new Block(Block.Settings.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistrySupplier<Item> DEEPSLATE_ANCIENT_RELIC_ITEM = ModItemRegistry.ITEMS.register("deepslate_ancient_relic", () ->
            new BlockItem(DEEPSLATE_ANCIENT_RELIC.get(), new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB)));


    public static final RegistrySupplier<Block> NETHER_ANCIENT_RELIC = BLOCKS.register("nether_ancient_relic", () ->
            new Block(Block.Settings.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistrySupplier<Item> NETHER_ANCIENT_RELIC_ITEM = ModItemRegistry.ITEMS.register("nether_ancient_relic", () ->
            new BlockItem(NETHER_ANCIENT_RELIC.get(), new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB)));


    public static final RegistrySupplier<Block> END_ANCIENT_RELIC = BLOCKS.register("end_ancient_relic", () ->
            new Block(Block.Settings.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistrySupplier<Item> END_ANCIENT_RELIC_ITEM = ModItemRegistry.ITEMS.register("end_ancient_relic", () ->
            new BlockItem(END_ANCIENT_RELIC.get(), new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB)));



    public static final RegistrySupplier<Block> PERSISTENT_LEAVES_SHADED = BLOCKS.register("persistent_leaves_shaded", () ->
            new Block(Block.Settings.copy(Blocks.GLASS).sounds(BlockSoundGroup.GRASS)));

    public static final RegistrySupplier<Item> PERSISTENT_LEAVES_SHADED_ITEM = ModItemRegistry.ITEMS.register("persistent_leaves_shaded", () ->
            new BlockItem(PERSISTENT_LEAVES_SHADED.get(), new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB)));


    public static final RegistrySupplier<Block> PERSISTENT_LEAVES = BLOCKS.register("persistent_leaves", () ->
            new Block(Block.Settings.copy(Blocks.GLASS).sounds(BlockSoundGroup.GRASS)));

    public static final RegistrySupplier<Item> PERSISTENT_LEAVES_ITEM = ModItemRegistry.ITEMS.register("persistent_leaves", () ->
            new BlockItem(PERSISTENT_LEAVES.get(), new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB)));


    public static final RegistrySupplier<Block> GYM_EXIT = BLOCKS.register("gym_exit", () ->
            new GymExitBlock(Block.Settings.copy(Blocks.BEDROCK)));

    public static final RegistrySupplier<Item> GYM_EXIT_ITEM = ModItemRegistry.ITEMS.register("gym_exit", () ->
            new BlockItem(GYM_EXIT.get(), new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB)));


    public static final RegistrySupplier<Block> SAFE_MAGMA = BLOCKS.register("safe_magma", () ->
            new Block(Block.Settings.copy(Blocks.MAGMA_BLOCK)));

    public static final RegistrySupplier<Item> SAFE_MAGMA_ITEM = ModItemRegistry.ITEMS.register("safe_magma", () ->
            new BlockItem(SAFE_MAGMA.get(), new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB)));

    public static final RegistrySupplier<FluidBlock> SAFE_LAVA_BLOCK = BLOCKS.register("safe_lava", () -> new ArchitecturyLiquidBlock(ModFluidRegistry.SAFE_LAVA, AbstractBlock.Settings.copy(Blocks.LAVA)));

    public static final RegistrySupplier<Block> WARPED_BLACKSTONE = BLOCKS.register("warped_blackstone", () ->
            new Block(Block.Settings.copy(Blocks.DIRT).sounds(BlockSoundGroup.GRASS)));

    public static final RegistrySupplier<Item> WARPED_BLACKSTONE_ITEM = ModItemRegistry.ITEMS.register("warped_blackstone", () ->
            new BlockItem(WARPED_BLACKSTONE.get(), new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB)));

    public static final RegistrySupplier<Block> LESSER_SHARD_BLOCK = BLOCKS.register("lesser_shard_block", () ->
            new Block(Block.Settings.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistrySupplier<Item> LESSER_SHARD_BLOCK_ITEM = ModItemRegistry.ITEMS.register("lesser_shard_block",
            () -> new RarityBlockItem(LESSER_SHARD_BLOCK.get(), new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB),
                    Text.translatable("cobgyms.lang.poke_shard_block", Text.translatable("cobgyms.lang.lesser")), // name
                    null, // tooltips
                    Formatting.RED,
                    false));


    public static final RegistrySupplier<Block> ADEPT_SHARD_BLOCK = BLOCKS.register("adept_shard_block", () ->
            new Block(Block.Settings.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistrySupplier<Item> ADEPT_SHARD_BLOCK_ITEM = ModItemRegistry.ITEMS.register("adept_shard_block",
            () -> new RarityBlockItem(ADEPT_SHARD_BLOCK.get(), new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB),
                    Text.translatable("cobgyms.lang.poke_shard_block", Text.translatable("cobgyms.lang.adept")), // name
                    null, // tooltips
                    Formatting.BLUE,
                    false));


    public static final RegistrySupplier<Block> MASTER_SHARD_BLOCK = BLOCKS.register("master_shard_block", () ->
            new Block(Block.Settings.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistrySupplier<Item> MASTER_SHARD_BLOCK_ITEM = ModItemRegistry.ITEMS.register("master_shard_block",
            () -> new RarityBlockItem(MASTER_SHARD_BLOCK.get(), new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB),
                    Text.translatable("cobgyms.lang.poke_shard_block", Text.translatable("cobgyms.lang.master")), // name
                    null, // tooltips
                    Formatting.LIGHT_PURPLE,
                    true));


    public static final RegistrySupplier<Block> LEGENDARY_SHARD_BLOCK = BLOCKS.register("legendary_shard_block", () ->
            new Block(Block.Settings.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistrySupplier<Item> LEGENDARY_SHARD_BLOCK_ITEM = ModItemRegistry.ITEMS.register("legendary_shard_block",
            () -> new RarityBlockItem(LEGENDARY_SHARD_BLOCK.get(), new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB),
                    Text.translatable("cobgyms.lang.poke_shard_block", Text.translatable("cobgyms.lang.legendary")), // name
                    null, // tooltips
                    Formatting.GOLD,
                    true));




    // Gym entrances
    public static final RegistrySupplier<Block> GYM_ENTRANCE = BLOCKS.register("gym_entrance", () ->
            new GymEntranceBlock(Block.Settings.copy(Blocks.DIAMOND_BLOCK), "random"));

    public static final RegistrySupplier<Item> GYM_ENTRANCE_ITEM = ModItemRegistry.ITEMS.register("gym_entrance", () ->
            new BlockItem(GYM_ENTRANCE.get(), new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB)));


    public static final RegistrySupplier<Block> GYM_ENTRANCE_WOODLAND = BLOCKS.register("gym_entrance_woodland", () ->
            new GymEntranceBlock(Block.Settings.copy(Blocks.DIAMOND_BLOCK), "woodland"));

    public static final RegistrySupplier<Item> GYM_ENTRANCE_WOODLAND_ITEM = ModItemRegistry.ITEMS.register("gym_entrance_woodland", () ->
            new BlockItem(GYM_ENTRANCE_WOODLAND.get(), new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB)));

    public static final RegistrySupplier<Block> GYM_ENTRANCE_VOLCANIC = BLOCKS.register("gym_entrance_volcanic", () ->
            new GymEntranceBlock(Block.Settings.copy(Blocks.DIAMOND_BLOCK), "volcanic"));

    public static final RegistrySupplier<Item> GYM_ENTRANCE_VOLCANIC_ITEM = ModItemRegistry.ITEMS.register("gym_entrance_volcanic", () ->
            new BlockItem(GYM_ENTRANCE_VOLCANIC.get(), new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB)));

    public static final RegistrySupplier<Block> GYM_ENTRANCE_AQUATIC = BLOCKS.register("gym_entrance_aquatic", () ->
            new GymEntranceBlock(Block.Settings.copy(Blocks.DIAMOND_BLOCK), "aquatic"));

    public static final RegistrySupplier<Item> GYM_ENTRANCE_AQUATIC_ITEM = ModItemRegistry.ITEMS.register("gym_entrance_aquatic", () ->
            new BlockItem(GYM_ENTRANCE_AQUATIC.get(), new Item.Settings().arch$tab(ModItemGroup.COBGYMS_TAB)));


    public static void initialize() {
        BLOCKS.register();
    }
}
