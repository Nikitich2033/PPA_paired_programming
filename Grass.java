import java.util.List;
import java.util.Random;

/**
 * Write a description of class Grass here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Grass extends Organism
{
    //the age at which grass can produce seeds and reproduce
    private static final int POLLINATION_AGE = 5;
    // The age to which a grass patch can live.
    private static final int MAX_AGE = 30;
    // The likelihood of a grass patch to pollinate.
    private static final double POLLINATION_PROBABILITY = 0.40;
    // The maximum number of offsprings.
    private static final int MAX_OFFSPRING_NUM = 12;

    private int age;

    private static final Random rand = Randomizer.getRandom();

    /**
     * Constructor for objects of class Grass
     */
    public Grass(boolean randomAge,Field field,Location location)
    {
        super(field,location);

        age = 0;
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
        }

    }


    /**
     * This is what the Impala does most of the time - it runs
     * around. Sometimes it will breed or die of old age.
     * @param newGrass A list to return newly born Impalas.
     */
    public void act(List<Organism> newGrass, String timeOfDayString, Weather weather)
    {
        incrementAge();

        if(isAlive()) {

            if (timeOfDayString.equals("Morning") || timeOfDayString.equals("Day")){
                reproduce(newGrass);
            }


        }
    }


    /**
     * Increase the age.
     * This could result in the Impala's death.
     */
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }

    private void reproduce(List<Organism> newGrass)
    {
        // New Impalas are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();

        List<Location> free = field.getFreeAdjacentLocations(getLocation());

        int births = breed();

        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Grass sprout = new Grass(false, field, loc);
            newGrass.add(sprout);
        }


    }


    /**
     * Generate a number representing the number of new offsprings,
     * if it can pollinate.
     * @return The number of new offsprings (may be zero).
     */
    private int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= POLLINATION_PROBABILITY) {
            births = rand.nextInt(MAX_OFFSPRING_NUM) + 1;
        }
        return births;
    }

    /**
     * A plant can produce offsprings if it has reached the pollination age.
     * @return true if the plant is old enough to pollinate.
     */
    private boolean canBreed()
    {
        return age >= POLLINATION_AGE;
    }
}
