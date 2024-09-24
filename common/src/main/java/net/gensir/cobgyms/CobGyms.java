package net.gensir.cobgyms;

import com.google.common.base.Suppliers;
import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.event.events.common.TickEvent;
import dev.architectury.networking.NetworkManager;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.registries.RegistrarManager;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.gensir.cobgyms.command.ModCommands;
import net.gensir.cobgyms.delayUtils.DelayedCall;
import net.gensir.cobgyms.event.ModEvents;
import net.gensir.cobgyms.gym.Gym;
import net.gensir.cobgyms.gym.gyms.GymForest;
import net.gensir.cobgyms.gym.gyms.GymLava;
import net.gensir.cobgyms.gym.gyms.GymWater;
import net.gensir.cobgyms.network.ServerPacketHandler;
import net.gensir.cobgyms.registry.*;
import net.gensir.cobgyms.util.*;
import net.gensir.cobgyms.world.dimension.ModDimensions;

import net.gensir.cobgyms.world.gen.ModWorldGeneration;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.client.render.entity.VillagerEntityRenderer;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import static net.gensir.cobgyms.network.ServerPacketHandler.SPAWN_SCALED_PACKET_ID;

public final class CobGyms {
    public static final String MOD_ID = "cobgyms";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final Supplier<RegistrarManager> MANAGER = Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));

    public static List<Gym> GymRegistry = new ArrayList<>();
    public static Map<String, List<Map<String, Object>>> forceEvolutionMapper = PokemonForceEvolution.getForceEvolutions();

    public static List<DelayedCall> delayedCalls = new ArrayList<>();

//    public static int GYM_ENTRANCE_USAGES = 3;

    public static void init() {
        ModDimensions.initialize();
        ModItemGroup.initialize();
        ModFluidRegistry.initialize();

        ModBlockRegistry.initialize();
        ModBlockEntityRegistry.initialize();
        ModItemRegistry.initialize();
        ModEntityRegistry.initialize();

        ModWorldGeneration.generateModWorldGen();

        GymRegistry.add(GymForest.prepGym());
        GymRegistry.add(GymLava.prepGym());
        GymRegistry.add(GymWater.prepGym());

        ServerPacketHandler.register();
        ModLootTableModifiers.modifyLootTables();
        ModEvents.registerEvents();

        ModCommands.registerCommands();

        TickEvent.SERVER_PRE.register(instance -> {
            try {
                if (!delayedCalls.isEmpty()) {
                    Iterator<DelayedCall> iterator = delayedCalls.iterator();

                    while (iterator.hasNext()) {
                        DelayedCall delayedCall = iterator.next();
                        delayedCall.timer += 1;

                        if (delayedCall.timer > delayedCall.delayTime) {
                            iterator.remove();
                            delayedCall.executeFunction(delayedCall);
                        }
                    }
                }
            } catch (Throwable e) {
                CobGyms.LOGGER.info(String.valueOf(e));
            }
        });

        EnvExecutor.runInEnv(Env.CLIENT, () -> CobGyms.Client::initializeClient);
    }

    @Environment(EnvType.CLIENT)
    public static class Client {
        @Environment(EnvType.CLIENT)
        public static void initializeClient() {
            EntityRendererRegistry.register(ModEntityRegistry.TRAINER_VILLAGER, VillagerEntityRenderer::new);

            int spawnTimerSeconds = 600;
            int spawnTimerTicks = spawnTimerSeconds * 20;

            AtomicInteger timer = new AtomicInteger(0);
            ClientTickEvent.CLIENT_LEVEL_PRE.register(instance -> {
                try {
                    timer.addAndGet(1);
                    if (timer.get() >= spawnTimerTicks){
                        if(MinecraftClient.getInstance().player != null){
                            PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
                            NetworkManager.sendToServer(SPAWN_SCALED_PACKET_ID, buf);
                        }
                        timer.set(0);
                    }

                } catch (Throwable e) {
                    CobGyms.LOGGER.info(String.valueOf(e));
                }
            });
        }


    }


}
