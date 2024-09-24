package net.gensir.cobgyms.command;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
import com.cobblemon.mod.common.command.argument.PokemonPropertiesArgumentType;
import com.cobblemon.mod.common.pokemon.Species;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import dev.architectury.event.events.common.CommandRegistrationEvent;
import net.gensir.cobgyms.cache.Cache;
import net.gensir.cobgyms.gym.lootTable.GymLootTable;
import net.gensir.cobgyms.item.custom.ModFireworkRocketItem;
import net.gensir.cobgyms.network.LeaveGymPacket;
import net.gensir.cobgyms.registry.ModBlockRegistry;
import net.gensir.cobgyms.util.JSONHandler;
import net.gensir.cobgyms.util.LangUtils;
import net.gensir.cobgyms.world.dimension.ModDimensions;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.argument.Vec3ArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.WorldSavePath;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class ModCommands {

    private static int forceLeave(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity serverPlayer = EntityArgumentType.getPlayer(context, "player");
        World world = serverPlayer.getWorld();

        if (world instanceof ServerWorld serverWorld) {
            LeaveGymPacket.handleLeaveGymPacket(serverPlayer, serverWorld);
            return 1;
        } else{
            serverPlayer.sendMessage(Text.translatable("cobgyms.lang.message.no_response"));
            return -1;
        }
    }

    private static int giveLeaderLoot(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerWorld serverWorld = context.getSource().getWorld();
        ServerWorld cobGymDimension = serverWorld.getServer().getWorld(ModDimensions.COBGYMS_LEVEL_KEY);

        int level = IntegerArgumentType.getInteger(context, "level");
        if(level > 100){
            level = 100;
        }
        ServerPlayerEntity player = EntityArgumentType.getPlayer(context, "player");

        Vec3d pos = Vec3ArgumentType.getVec3(context, "position");
        cobGymDimension.setBlockState(BlockPos.ofFloored(pos), ModBlockRegistry.GYM_EXIT.get().getDefaultState());

        GymLootTable.generateLoot(level, player);

        Path worldSavePath = player.getServer().getSavePath(WorldSavePath.PLAYERDATA).getParent();
        String playerJSONpath = worldSavePath.resolve("cobgyms/" + player.getUuidAsString() + ".json").toString();
        Map<String, Object> JSONcontent = JSONHandler.readJSON(playerJSONpath);

        if(JSONcontent.isEmpty() || !JSONcontent.containsKey("highestLevelBeaten")) {
            JSONcontent.put("highestLevelBeaten", level);
        } else {
            int oldHighestLevel = ((Double) JSONcontent.get("highestLevelBeaten")).intValue();
            if (level > oldHighestLevel){
                JSONcontent.put("highestLevelBeaten",level);
            }
        }
        JSONHandler.writeJSON(JSONcontent, playerJSONpath);

        cobGymDimension.spawnEntity(new FireworkRocketEntity(cobGymDimension, pos.getX(), pos.getY()+1, pos.getZ(), ModFireworkRocketItem.createFireworkStack(DyeColor.RED.getFireworkColor(),DyeColor.ORANGE.getFireworkColor(),1)));

        cobGymDimension.spawnEntity(new FireworkRocketEntity(cobGymDimension, pos.getX()+1, pos.getY()+1, pos.getZ()+1, ModFireworkRocketItem.createFireworkStack(DyeColor.MAGENTA.getFireworkColor(),DyeColor.PURPLE.getFireworkColor(),0.25)));

        cobGymDimension.spawnEntity(new FireworkRocketEntity(cobGymDimension, pos.getX()-1, pos.getY()+1, pos.getZ()-1, ModFireworkRocketItem.createFireworkStack(DyeColor.BLUE.getFireworkColor(),DyeColor.CYAN.getFireworkColor(),0.6)));

        return 1;
    }

    private static int whichCache(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        PlayerEntity player = Objects.requireNonNull(context.getSource().getPlayer());
        PokemonProperties pokemonProperties = PokemonPropertiesArgumentType.Companion.getPokemonProperties(context, "pokemon");

        if (pokemonProperties.getSpecies() != null){
            Species species = PokemonSpecies.INSTANCE.getByIdentifier(new Identifier("cobblemon", pokemonProperties.getSpecies().toLowerCase()));
            if (species != null){
                boolean hasBaseEvolution = false;

                while (species.getPreEvolution() != null){
                    hasBaseEvolution = true;
                    species = species.getPreEvolution().getSpecies();
                }

                String pokemonRegistryKey = species.toString().toLowerCase();
                MutableText translatedPokemonName = species.getTranslatedName();

                ArrayList<MutableText> foundList = Cache.whichCache(pokemonRegistryKey);
                if(foundList.isEmpty()){
                    player.sendMessage(Text.translatable("cobgyms.lang.command.which_cache.not_found", translatedPokemonName));
                    return -1;
                } else {
                    if (hasBaseEvolution){
                        player.sendMessage(Text.translatable("cobgyms.lang.command.which_cache.base_evolution_found", translatedPokemonName));
                    } else {
                        player.sendMessage(Text.translatable("cobgyms.lang.command.which_cache.found", translatedPokemonName));
                    }
                    for(MutableText cacheInstance : foundList){
                        player.sendMessage(cacheInstance);
                    }
                    return 1;
                }
            } else {
                player.sendMessage(Text.translatable("cobgyms.lang.command.which_cache.invalid_pokemon"));
                return -1;
            }
        } else {
            player.sendMessage(Text.translatable("cobgyms.lang.command.which_cache.invalid_pokemon"));
            return -1;
        }

    }

    private static int listCache(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        PlayerEntity player = Objects.requireNonNull(context.getSource().getPlayer());
        String cacheName = StringArgumentType.getString(context, "cacheName");
        String [] cachePokemon = Cache.getCacheList(cacheName);
        if (cachePokemon != null){
            double chance = ((double) 1 /cachePokemon.length)*100;

            String chanceString = String.format("%.2f", chance)+"%";
            MutableText itemName = LangUtils.getCacheName(cacheName);

            player.sendMessage(Text.translatable("cobgyms.lang.command.cache_chance", itemName, chanceString));

            String cachePokemonString = "";
            for (int i = 0; i < cachePokemon.length; i++) {
                cachePokemonString += Text.translatable(String.format("cobblemon.species.%s.name",cachePokemon[i])).getString();
                if (i < cachePokemon.length-1){
                    cachePokemonString += " : ";
                }
            }
            player.sendMessage(Text.literal(cachePokemonString));

            return 1;
        } else {
            player.sendMessage(Text.translatable("cobgyms.lang.command.list_cache.invalid_cache"));
            return -1;
        }
    }


    public static void registerCommands(){
        CommandRegistrationEvent.EVENT.register((dispatcher, access, environment) -> {
            dispatcher.register(CommandManager.literal("cobgyms")
                    .then(CommandManager.literal("complete_helper_command")
                                    .requires(source -> source.hasPermissionLevel(2))
                                    .then(CommandManager.argument("player", EntityArgumentType.player())
                                            .then(CommandManager.argument("level", IntegerArgumentType.integer(5))
                                                    .then(CommandManager.argument("position", Vec3ArgumentType.vec3())
                                                            .executes(context -> {
                                                                if(context.getSource().getPlayer() != null){
                                                                    context.getSource().getPlayer().sendMessage(Text.translatable("cobgyms.lang.command.non_player_command"));
                                                                    return -1;
                                                                }
                                                                return giveLeaderLoot(context);
                                                            }))))));
        });

        CommandRegistrationEvent.EVENT.register((dispatcher, access, environment) -> {
            dispatcher.register(CommandManager.literal("cobgyms")
                    .then(CommandManager.literal("whiteout_helper_command")
                            .requires(source -> source.hasPermissionLevel(2))
                            .then(CommandManager.argument("player", EntityArgumentType.player())
                                    .executes(context -> {
                                        if(context.getSource().getPlayer() != null){
                                            context.getSource().getPlayer().sendMessage(Text.translatable("cobgyms.lang.command.non_player_command"));
                                            return -1;
                                        }
                                        EntityArgumentType.getPlayer(context, "player").sendMessage(Text.translatable("cobgyms.lang.command.whiteout"));
                                        return forceLeave(context);
                                    }))));
        });

        CommandRegistrationEvent.EVENT.register((dispatcher, access, environment) -> {
            dispatcher.register(CommandManager.literal("cobgyms")
                    .then(CommandManager.literal("forceLeave")
                            .requires(source -> source.hasPermissionLevel(2))
                            .then(CommandManager.argument("player", EntityArgumentType.player())
                                            .executes(ModCommands::forceLeave))));
        });

        CommandRegistrationEvent.EVENT.register((dispatcher, access, environment) -> {
            dispatcher.register(CommandManager.literal("cobgyms")
                    .then(CommandManager.literal("whichCache")
                            .then(CommandManager.argument("pokemon", PokemonPropertiesArgumentType.Companion.properties())
                                    .executes(ModCommands::whichCache))));
        });

        CommandRegistrationEvent.EVENT.register((dispatcher, access, environment) -> {
            dispatcher.register(CommandManager.literal("cobgyms")
                    .then(CommandManager.literal("listCache")
                            .then(CommandManager.argument("cacheName", StringArgumentType.string()).suggests(getSuggestionsFromArray(Cache.getCacheKeys()))
                                    .executes(ModCommands::listCache))));
        });

    }

    public static SuggestionProvider<ServerCommandSource> getSuggestionsFromArray(String[] options) {
        return (CommandContext<ServerCommandSource> context, SuggestionsBuilder builder) -> {
            for (String option : options) {
                if (option.startsWith(builder.getRemaining())) {
                    builder.suggest(option);
                }
            }
            return builder.buildFuture();
        };
    }

}
