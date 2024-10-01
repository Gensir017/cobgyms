package net.gensir.cobgyms.event;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.events.CobblemonEvents;
import com.cobblemon.mod.common.api.events.pokemon.PokemonCapturedEvent;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import com.cobblemon.mod.common.pokemon.Pokemon;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.BlockEvent;
import dev.architectury.event.events.common.EntityEvent;
import dev.architectury.event.events.common.InteractionEvent;
import kotlin.Unit;
import net.gensir.cobgyms.CobGyms;
import net.gensir.cobgyms.registry.ModBlockRegistry;
import net.gensir.cobgyms.registry.ModItemRegistry;
import net.gensir.cobgyms.world.dimension.ModDimensions;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class ModEvents {
    private static final Random random = new Random();

    public static void registerEvents() {
        CobblemonEvents.BATTLE_FAINTED.subscribe(Priority.NORMAL, ModEvents::onBattleFainted);
        CobblemonEvents.POKEMON_CAPTURED.subscribe(Priority.NORMAL, ModEvents::onCaptured);

        BlockEvent.BREAK.register((world, pos, state, player, xp) -> {
            if (world.getRegistryKey() == ModDimensions.COBGYMS_LEVEL_KEY){
                if (state.getBlock() != Blocks.TORCH && state.getBlock() != Blocks.WALL_TORCH) {
                    return EventResult.interrupt(false);
                }
            }
            if (state.getBlock() == ModBlockRegistry.GYM_ENTRANCE.get() ||
                    state.getBlock() == ModBlockRegistry.GYM_ENTRANCE_WOODLAND.get() ||
                    state.getBlock() == ModBlockRegistry.GYM_ENTRANCE_VOLCANIC.get() ||
                    state.getBlock() == ModBlockRegistry.GYM_ENTRANCE_AQUATIC.get()){
                boolean cancelBreak = false;
                if (!player.isSneaking()){
                    player.sendMessage(Text.of("- You must be sneaking to break a Gym Entrance"));
                    cancelBreak = true;
                }
                if (CobGyms.BREAK_ENTRANCE_REQUIRES_PERMISSION && !player.hasPermissionLevel(2)){
                    player.sendMessage(Text.of("- You must have op permissions to break a Gym Entrance"));
                    cancelBreak = true;
                }
                if (cancelBreak){
                    player.sendMessage(Text.of("- Gym Entrances do not drop when broken, if you break this then all players will lose access to this entrance"));
                    return EventResult.interrupt(false);
                }
            }
            return EventResult.pass();
        });
        BlockEvent.PLACE.register((world, pos, state, placer) -> {
            if (world.getRegistryKey() == ModDimensions.COBGYMS_LEVEL_KEY){
                if (state.getBlock() != Blocks.TORCH && state.getBlock() != Blocks.WALL_TORCH) {
                    return EventResult.interrupt(false);
                }
            }
            return EventResult.pass();
        });

        InteractionEvent.RIGHT_CLICK_BLOCK.register((player, hand, pos, face) -> {
            World world = player.getWorld();
            if (world.getRegistryKey() == ModDimensions.COBGYMS_LEVEL_KEY){
                if (player.getStackInHand(hand).getItem() != Items.TORCH &&
                        world.getBlockState(pos).getBlock() != ModBlockRegistry.GYM_EXIT.get()) {
                    return EventResult.interrupt(false);
                }
            }
            return EventResult.pass();
        });

        EntityEvent.LIVING_HURT.register((entity, source, amount) -> {
            if (entity instanceof PlayerEntity && entity.getWorld().getRegistryKey() == ModDimensions.COBGYMS_LEVEL_KEY) {
                if(source.isOf(DamageTypes.FALL)){
                    return EventResult.interrupt(false);
                }
            }
            return EventResult.pass();
        });

    }


    private static Unit onCaptured(PokemonCapturedEvent event) {
        randomGymKey(event.getPlayer(), event.getPokemon(), true);

        return Unit.INSTANCE;
    }

    private static Unit onBattleFainted(com.cobblemon.mod.common.api.events.battles.BattleFaintedEvent event) {
        List<ServerPlayerEntity> players = event.getBattle().getPlayers();
        if (players.size() == 1) {
            ServerPlayerEntity playerEntity = players.get(0);

            BattlePokemon killed = event.getKilled();
            Pokemon poke = killed.getEffectedPokemon();

            if (!poke.isPlayerOwned() && !poke.isUncatchable()) {
                randomGymKey(playerEntity, poke, false);
            }
        }

        return Unit.INSTANCE;
    }


    private static void randomGymKey(PlayerEntity player, Pokemon poke, boolean isCapture) {
        BlockPos pos;
        World world = player.getEntityWorld();

        if (poke.getEntity()==null) {
            pos = player.getBlockPos();
        } else {
            pos = poke.getEntity().getBlockPos();
        }

        double randomChance;
        if(isCapture){
            randomChance = CobGyms.KEY_DROP_CHANCE*(1+((double) poke.getLevel() /100))*1.2;
        } else {
            randomChance = CobGyms.KEY_DROP_CHANCE*(1+((double) poke.getLevel() /100));
        }

        double randomDouble = random.nextDouble();
        if (randomDouble <= randomChance){
            ItemStack itemStack = new ItemStack(ModItemRegistry.GYM_KEY.get(), 1);
            ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), itemStack);
            PlayerInventory playerInventory = player.getInventory();
            if (!world.spawnEntity(itemEntity)) {
                playerInventory.insertStack(itemStack);
            }
            player.sendMessage(Text.translatable("cobgyms.lang.message.dropped_key", poke.getDisplayName().getString()));
        }
    }


}
