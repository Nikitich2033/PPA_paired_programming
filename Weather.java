import java.util.ArrayList;
import java.util.Random;

/**
 * Write a description of class Weather here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Weather
{
    // instance variables - replace the example below with your own
    private String[] statesOfWeather = {"Clear","Heatwave","Fog","Cloudy", "Rain","Thunderstorm"};
    private String currentWeather;
    private Boolean IsDrought;
    private int daysSinceRain;

    private static final Random rand = Randomizer.getRandom();

    /**
     * Constructor for objects of class Weather
     */
    public Weather()
    {
        setRandomWeather();
        IsDrought = false;
    }

    /**
     * Set weather to a random state
     */
    public void setRandomWeather()
    {
        currentWeather = statesOfWeather[rand.nextInt(statesOfWeather.length - 1)];
        if (currentWeather != "Rain" && currentWeather != "Thunderstorm" ){
            daysSinceRain++;
        }

        else{
            daysSinceRain = 0;
            removeDrought();
        }
        setIsDrought();
    }

    /**
     * Return the current state of weather.
     */
    public String getCurrentWeather() {
        return currentWeather;
    }

    private void setIsDrought(){

        if (daysSinceRain > 7){
            currentWeather = currentWeather + " (DROUGHT)";
            IsDrought = true;
        }
        else if(daysSinceRain <= 7){
            IsDrought = false;
            removeDrought();

        }

    }

    private void removeDrought(){

        String[] words = currentWeather.split(" ");
        currentWeather = "";
        for (String word:
             words) {
            if (word != "(DROUGHT)"){currentWeather = currentWeather + word;}
        }

    }

    public Boolean getIsDrought() {
        return IsDrought;
    }

    public int getDaysSinceRain() {
        return daysSinceRain;
    }

    public void reset(){
        IsDrought = false;
        daysSinceRain = 0;
        currentWeather = statesOfWeather[rand.nextInt(statesOfWeather.length - 1)];
    }
}
