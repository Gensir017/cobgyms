package net.gensir.cobgyms.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.gensir.cobgyms.CobGyms;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class JSONHandler {
    public static Map<String, Object> readJSON(String filePath){
        Gson gson = new Gson();
        folderHandler(filePath);

        try (FileReader reader = new FileReader(filePath)) {
            Type dataType = new TypeToken<Map<String, Object>>() {}.getType();

            return gson.fromJson(reader, dataType);
        } catch (IOException e) {
            return new HashMap<>();
        }
    }

    public static void writeJSON(Map<String, Object> jsonContent, String filePath){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        folderHandler(filePath);

        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(jsonContent, writer);
        } catch (IOException e) {
            CobGyms.LOGGER.info(String.valueOf(e));
        }
    }

    private static void folderHandler(String filePath){
        Path folderPath = Paths.get(filePath).getParent();
        if (!Files.exists(folderPath)) {
            try {
                Files.createDirectories(folderPath);
            } catch (IOException e) {
                CobGyms.LOGGER.info(String.valueOf(e));
            }
        }
    }

    public static double getAdjustX(Path worldSavePath, String playerUUID){
        String playerJSONpath = worldSavePath.resolve("cobgyms/"+playerUUID+".json").toString();

        Map<String, Object> jsonContent = readJSON(playerJSONpath);
        double adjustX = 0.0;
        boolean hasAdjustX = false;

        if(!jsonContent.isEmpty()){
            hasAdjustX = jsonContent.containsKey("adjustX");
            if(hasAdjustX){
                adjustX = (double) jsonContent.get("adjustX");
            }
        }

        if(!hasAdjustX){
            String allPlayersPath = worldSavePath.resolve("cobgyms/all_players.json").toString();

            Map<String, Object> allPlayers = readJSON(allPlayersPath);
            if(allPlayers.isEmpty()){
                allPlayers.put(playerUUID, 0.0);
                adjustX = 0.0;
                JSONHandler.writeJSON(allPlayers, allPlayersPath);
            } else {
                if(!allPlayers.containsKey(playerUUID)){
                    adjustX = allPlayers.size() * 128.0;

                    allPlayers.put(playerUUID, adjustX);
                    JSONHandler.writeJSON(allPlayers, allPlayersPath);
                } else {
                    adjustX = (double) allPlayers.get(playerUUID);
                }
            }

        }
        return  adjustX;
    }
}
