package net.gensir.cobgyms;

import com.google.common.base.Suppliers;
import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.event.events.common.LifecycleEvent;
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
import net.gensir.cobgyms.gym.gyms.GymAquatic01;
import net.gensir.cobgyms.gym.gyms.GymVolcanic01;
import net.gensir.cobgyms.gym.gyms.GymWoodland01;
import net.gensir.cobgyms.network.ClientPacketHandler;
import net.gensir.cobgyms.network.ServerPacketHandler;
import net.gensir.cobgyms.registry.*;
import net.gensir.cobgyms.util.*;
import net.gensir.cobgyms.world.dimension.ModDimensions;

import net.gensir.cobgyms.world.gen.ModWorldGeneration;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.client.render.entity.VillagerEntityRenderer;

import java.io.File;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import static net.gensir.cobgyms.network.ServerPacketHandler.SPAWN_SCALED_PACKET_ID;

public final class CobGyms {
    public static final String MOD_ID = "cobgyms";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final Supplier<RegistrarManager> MANAGER = Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));

    public static Map<String,List<Callable<Gym>>> GymRegistry = Map.of(
            "aquatic", List.of(GymAquatic01::newGymAquatic01),
            "woodland", List.of(GymWoodland01::newGymWoodland01),
            "volcanic", List.of(GymVolcanic01::newGymVolcanic01)
    );

    public static Map<String, List<Map<String, Object>>> forceEvolutionMapper = PokemonForceEvolution.getForceEvolutions();

    public static List<DelayedCall> delayedCalls = new ArrayList<>();

    public static Map<UUID, Integer> autoFillLevelMapper = new HashMap<>();

    public static List<String> cacheThemes = Arrays.asList(
            "woodland",
            "volcanic",
            "aquatic"
    );

    public static int GYM_ENTRANCE_USAGES = 3;
    public static double KEY_DROP_CHANCE = 0.01;
    public static double SHINY_FROM_CACHE_CHANCE = 0.02;
    public static boolean BREAK_ENTRANCE_REQUIRES_PERMISSION = true;

    public static void init() {

        ModDimensions.initialize();
        ModItemGroup.initialize();
        ModFluidRegistry.initialize();

        ModBlockRegistry.initialize();
        ModBlockEntityRegistry.initialize();
        ModItemRegistry.initialize();
        ModCacheItemRegistry.initialize();
        ModEntityRegistry.initialize();

        ModWorldGeneration.generateModWorldGen();

        ServerPacketHandler.register();
        ModLootTableModifiers.modifyLootTables();
        ModEvents.registerEvents();

        ModCommands.registerCommands();

        TickEvent.SERVER_PRE.register(server -> {
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

            ClientPacketHandler.register();

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
