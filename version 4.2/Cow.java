import java.util.Random;
import java.util.*;
/**
 * 在这里给出对类 cow 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Cow extends Animal
{
    // The age at which a rabbit can start to breed.
    private static final int BREEDING_AGE = 10;
    // The age to which a rabbit can live.
    private static final int MAX_AGE = 100;
    // The likelihood of a Deer breeding.
    private static final double BREEDING_PROBABILITY = 0.2;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 2;
    // number of steps a Cow can go before it has to eat again.
    private static final int GRASS_FOOD_VALUE = 50;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    // The food level of a Deer,which is increased by eating grass.
    private int foodLevel;
    /**
     * Create a new Deer. A Deer may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the Deer will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param yearStage The yearStage of the Deer.
     * @param sex The gender of a Deer.
     * @param ill check whether the Deer eat toxic grass.
     */
    public Cow(boolean randomAge,Field field,Location location,String yearStage,String sex,boolean ill)
    {
        super(field, location,yearStage,sex,ill);
        setAge(0);
        if(randomAge) {
            setAge(rand.nextInt(MAX_AGE));
            foodLevel = rand.nextInt(GRASS_FOOD_VALUE);
        }
        else {
            foodLevel = GRASS_FOOD_VALUE;
        }
    }
    
    /**
     * return the MAX_AGE of the tiger.
     * @return the MAX_AGE of the tiger.
     */
    public int get_Max_Age()
    {
        return MAX_AGE;
    }
    
    /**
     * This is what the rabbit does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.
     * @param newRabbits A list to return newly born rabbits.
     */
    public void act(List<Animal> newCows)
    {
        normalLives();
        if(isAlive()) {
            giveBirth(newCows);            
            // Try to move into a free location.
            Location newLocation = findFood();
            if(newLocation == null) { 
                // No food found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(getLocation());
            }
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
     * Make this Cow more hungry. This could result in the Tiger's death.
     * 
     */
    private void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    }
    
    /**
     * the cow's act is just to stay at the orginal location,and
     * increase the age as normal.
     */
    public void act_night()
    {
        normalLives();
    }

    private Location findFood()
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object plant = field.getObjectAt(where);
            if(plant instanceof Grass) {
                Grass grass = (Grass) plant;
               if(grass.isAlive()) { 
                    if(!grass.If_cantEat()){
                    grass.setDead();
                    foodLevel = GRASS_FOOD_VALUE;
                    return where;
                }
                 else{
                    grass.setDead();
                    getIll();
                    foodLevel+=5;
                    return where;
                }
            }
        }
    }
        return null;
    }  
    

    /**
     * Increase the age.
     * This could result in the rabbit's death.
     * At the same time, the year stage of the cow will be calculated again.
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
     * Check whether or not this rabbit is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newRabbits A list to return newly born rabbits.
     */
    private void giveBirth(List<Animal> newCows)
    {
        // New rabbits are born into adjacent locations.
        // Get a list of adjacent free locations.
       Field field = getField();
       Object animal2=field.getObjectAt(getLocation());
       for(Location locate : field.adjacentLocations(getLocation()))
        {
            Object animal1=field.getObjectAt(locate);
            if(animal1 instanceof Cow&&animal2 instanceof Cow)
            {
                Cow cow1=(Cow)animal1;
                Cow cow2=(Cow)animal2;
                if(!cow1.getSex().equals(cow2.getSex()))
                {
                    List<Location> free = field.getFreeAdjacentLocations(getLocation());
                    int births = breed();
                    for(int b = 0; b < births && free.size() > 0; b++) {
                        Location loc = free.remove(0);
                        Cow young = new Cow(false, field, loc,"young"," ",false);
                        young.getGender();
                        newCows.add(young);
                        //if newly born deer's parents get ill, young deers will also 
                        //be sick.
                        if(!cow1.If_getIll()&&!cow2.If_getIll())
                        {
                            //born the rabbit commonly;
                        }
                        else{young.getIll();}
                        //Confirm the role of each cow.
                        if(cow1.getSex().equals("Male"))
                        {
                            young.set_Father(cow1);
                            young.set_Mother(cow2);
                        }
                        else
                        {
                            young.set_Mother(cow1);
                            young.set_Father(cow2);
                        }
                        //parents can set their couple after broning newly small tigers.
                        cow1.set_Couple(cow2);
                        cow2.set_Couple(cow1);
                        cow1.set_Child(young);
                        cow2.set_Child(young);
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
     * the daily life of a Cow.
     */
    private void normalLives()
    {
        incrementAge();
        incrementHunger();
    }
    
    /**
     * A rabbit can breed if it has reached the breeding age.
     * @return true if the rabbit can breed, false otherwise.
     */
    protected int getBREEDINGAGE()
    {
        return BREEDING_AGE;
    }
}

