package net.gensir.cobgyms.util;

import net.gensir.cobgyms.CobGyms;
import net.gensir.cobgyms.gym.Gym;

import java.util.*;

public class ThemeUtils {
    private static final Random random = new Random();

    public static int getFixedThemeGymIndex(String theme){
        List<Gym> foundGyms = new ArrayList<>();
        for(Gym gym : CobGyms.GymRegistry){
            if(Objects.equals(gym.theme, theme)){
                foundGyms.add(gym);
            }
        }
        int randomIndex = random.nextInt(foundGyms.size());
        return CobGyms.GymRegistry.indexOf(foundGyms.get(randomIndex));
    }

    public static int getRandomGymIndex(){
        List<String> foundThemes = new ArrayList<>();
        for(Gym gym : CobGyms.GymRegistry){
            if (!foundThemes.contains(gym.theme)){
                foundThemes.add(gym.theme);
            }
        }
        int randomThemeIndex = random.nextInt(foundThemes.size());
        return getFixedThemeGymIndex(foundThemes.get(randomThemeIndex));
    }
}
