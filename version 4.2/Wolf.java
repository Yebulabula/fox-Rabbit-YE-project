import java.util.List;
import java.util.Iterator;
import java.util.Random;
/**
 * A simple model of a Wolf.
 * Wolfes age, move, eat Wolfs, and die.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29 (2)
 */
public class Wolf  extends Animal
{
    // Characteristics shared by all Wolfes (class variables).
    // The age at which a Wolf can start to breed.
    private static final int BREEDING_AGE = 20;
    // The age to which a Wolf can live.
    private static final int MAX_AGE = 80;
    // The likelihood of a Wolf breeding.
    private static final double BREEDING_PROBABILITY = 0.25;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 2;
    // The food value of a single Wolf. In effect, this is the
    private static final double HUNT_PROBABILITY=0.9;
    // number of steps a Wolf can go before it has to eat again.
    private static final int Deer_COW_FOOD_VALUE = 40;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    // Individual characteristics (instance fields).]
    // The Wolf's food level, which is increased by eating Wolfs.
    private int foodLevel;

    /**
     * Create a Wolf. A Wolf can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     * 
     * @param randomAge If true, the Wolf will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param yearStage The yearStage of the Wolf.
     * @param sex The gender of a Wolf.
     * @param ill check whether the Wolf eat toxic grass.
     */
    public Wolf(boolean randomAge, Field field, Location location,String yearStage,String sex,boolean ill)
    {
        super(field, location,yearStage,sex,ill);
        if(randomAge) {
            setAge(rand.nextInt(MAX_AGE));
            foodLevel = rand.nextInt(Deer_COW_FOOD_VALUE);
        }
        else {
            setAge(0);
            foodLevel = Deer_COW_FOOD_VALUE;
        }
    }

    /**
     * This is what the Wolf does most of the time: it hunts for
     * Wolfs. In the process, it might breed, die of hunger,
     * or die of old age.
     * @param field The field currently occupied.
     * @param newWolfes A list to return newly born Wolfes.
     */
    public void act(List<Animal> newWolfs)
    {
        normalLives();
        if(isAlive()) {
            giveBirth(newWolfs);            
            // Move towards a source of food if found.
            Location newLocation = findFood();
            if(newLocation == null) { 
                // No food found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(getLocation());
            }
            // See if it was possible to move.
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setDead();
            }
        }
    }

    /**
     * Increase the age. This could result in the Wolf's death.
     * the year stage of the cow will be calculated again ,at the same time.
     */
    protected void incrementAge()
    {
        super.incrementAge();
        if(getAge() > MAX_AGE) {
            setDead();
        }
        set_Yearstage(getAge(),MAX_AGE);
    }

    /**
     * This is what the Wolf does at night. Just Stay 
     * at the orginal loctaion and sleep,but the age will increase
     * normally.
     */
    public void act_night()
    {
        normalLives();
    }

    /**
     * Make this Wolf more hungry. This could result in the Wolf's death.
     */
    private void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    }
    
    /**
     * return the MAX_AGE of the Wolf.
     * @return theMAX_AGE of the Wolf.
     */
    public int get_Max_Age()
    {
        return MAX_AGE;
    }
    
    /**
     * the daily lives of the Wolf
     */
    private void normalLives()
    {
        incrementAge();
        incrementHunger();
    }

    /**
     * Look for Wolfs adjacent to the current location.
     * Only the first live Wolf is eaten.
     * @return Where food was found, or null if it wasn't.
     */
    private Location findFood()
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            if(animal instanceof Deer) {
                Deer  Deer= (Deer) animal;
                if(Deer.isAlive()&&can_hunt()) { 
                    Deer.setDead();
                    //if the deer was sick, as tiger ate them,
                    //the food level of tiger will increase 50% .And tiger will get desease.
                    if(Deer.If_getIll())
                    {
                        foodLevel+=10;
                        getIll();
                    }
                    //the food value of the deer just occupy half of the total 
                    // food value.
                    else
                    {
                        foodLevel=Deer_COW_FOOD_VALUE/2;
                    }
                    return where;
                }
            }
            else if(animal instanceof Cow) {
                // The procedure of hunting cow.
                Cow cow = (Cow) animal;
                if(cow.isAlive()) { 
                    cow.setDead();
                    if(cow.If_getIll())
                    {
                        foodLevel = Deer_COW_FOOD_VALUE/2;
                        getIll();
                        return where;
                    }
                    else
                    {
                        foodLevel=Deer_COW_FOOD_VALUE;
                        return where;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Check whether or not this Wolf is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newWolfes A list to return newly born Wolfes.
     */
    private void giveBirth(List<Animal> newWolfs)
    {
        Field field = getField();
        Object animal2=field.getObjectAt(getLocation());
        for(Location locate : field.adjacentLocations(getLocation()))
        {
            Object animal1=field.getObjectAt(locate);
            if(animal1 instanceof Wolf&&animal2 instanceof Wolf)
            {
                Wolf wolf1=(Wolf)animal1;
                Wolf wolf2=(Wolf)animal2;
                if(!wolf1.getSex().equals(wolf2.getSex()))
                {
                    List<Location> free = field.getFreeAdjacentLocations(getLocation());
                    int births = breed();

                    for(int b = 0; b < births && free.size() > 0; b++) {
                        Location loc = free.remove(0);
                        Wolf young = new Wolf(false, field, loc,"young"," ",false);
                        young.getGender();
                        newWolfs.add(young);
                        if(!wolf1.If_getIll()&&!wolf2.If_getIll())
                        {
                            //born the Wolf commonly;
                        }
                        else{young.getIll();}
                        if(wolf1.getSex().equals("Male"))
                        {
                            young.set_Father(wolf1);
                            young.set_Mother(wolf2);
                        }
                        else
                        {
                            young.set_Mother(wolf1);
                            young.set_Father(wolf2);
                        }
                        wolf1.set_Couple(wolf2);
                        wolf2.set_Couple(wolf1);
                        wolf1.set_Child(young);
                        wolf2.set_Child(young);
                    }
                }
            }
        }   

    }

    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    private int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }
    
    /**
     * not every time, the Wolf could hunt successfully.
     * whether tigers can get foods,it largely depends on the hunt 
     * probability.
     */
    private boolean can_hunt()
    {
        Random rando= new Random();
        if(rando.nextDouble()<=HUNT_PROBABILITY)
        {
            return true; 
        }
        else
        {
            return false;}
    }

    /**
     * A Wolf can breed if it has reached the breeding age.
     */
    protected int getBREEDINGAGE()
    {
        return BREEDING_AGE;
    }
}