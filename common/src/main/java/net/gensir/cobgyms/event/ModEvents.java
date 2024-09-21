package net.gensir.cobgyms.event;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.events.CobblemonEvents;
import com.cobblemon.mod.common.api.events.pokemon.PokemonCapturedEvent;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import com.cobblemon.mod.common.pokemon.Pokemon;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.BlockEvent;
import dev.architectury.event.events.common.InteractionEvent;
import kotlin.Unit;
import net.gensir.cobgyms.registry.ModBlockRegistry;
import net.gensir.cobgyms.registry.ModItemRegistry;
import net.gensir.cobgyms.util.ClientUtils;
import net.gensir.cobgyms.world.dimension.ModDimensions;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
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
                if (world.getBlockState(pos).getBlock() == ModBlockRegistry.GYM_EXIT.get()){
                    ClientUtils.openLeaveGymScreen();
                    return EventResult.interrupt(false);
                }
                if (player.getStackInHand(hand).getItem() != Items.TORCH) {
                    return EventResult.interrupt(false);
                }
            }
            return EventResult.pass();
        });

    }


    private static Unit onCaptured(PokemonCapturedEvent event) {
        randomGymKey(event.getPlayer(), event.getPokemon(), 26);

        return Unit.INSTANCE;
    }

    private static Unit onBattleFainted(com.cobblemon.mod.common.api.events.battles.BattleFaintedEvent event) {
        List<ServerPlayerEntity> players = event.getBattle().getPlayers();
        if (players.size() == 1) {
            ServerPlayerEntity playerEntity = players.get(0);

            BattlePokemon killed = event.getKilled();
            Pokemon poke = killed.getEffectedPokemon();

            if (!poke.isPlayerOwned() && !poke.isUncatchable()) {
                randomGymKey(playerEntity, poke, 28);
            }
        }

        return Unit.INSTANCE;
    }


    private static void randomGymKey(PlayerEntity player, Pokemon poke, int scalar) {
        BlockPos pos;
        World world = player.getEntityWorld();

        if (poke.getEntity()==null) {
            pos = player.getBlockPos();
        } else {
            pos = poke.getEntity().getBlockPos();
        }

        int randomChance = (int) scalar-(poke.getLevel()/5);
        int randomNumber = random.nextInt(randomChance);

        if(randomNumber == 0){
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
