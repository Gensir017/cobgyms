package net.gensir.cobgyms.util;

import net.gensir.cobgyms.CobGyms;
import net.gensir.cobgyms.gym.Gym;

import java.util.*;
import java.util.concurrent.Callable;

public class GymUtils {
    private static final Random random = new Random();

    public static Callable<Gym> getFixedThemeGymCallable(String theme){
        List<Callable<Gym>> availableGyms = CobGyms.GymRegistry.get(theme);
        int randomIndex = random.nextInt(availableGyms.size());
        return availableGyms.get(randomIndex);
    }

    public static Callable<Gym> getRandomGymCallable(){
        List<String> themes = new ArrayList<>(CobGyms.GymRegistry.keySet());
        int randomIndex = random.nextInt(themes.size());
        return getFixedThemeGymCallable(themes.get(randomIndex));
    }
}
