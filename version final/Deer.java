import java.util.*;
import java.util.Random;
/**
 * A simple model of a Deer.
 * Deers age, move, breed, and die.
 * 
 * @author ye Mao, yiCheng Zhan.
 * @version 2019.02.20 (2)
 */
public class Deer extends Animal 
{
    // Characteristics shared by all Deers (class variables).
    // The age at which a Deer can start to breed.
    private static final int BREEDING_AGE = 2;
    // The age to which a Deer can live.
    private static final int MAX_AGE = 50;
    // The likelihood of a Deer breeding.
    private static final double BREEDING_PROBABILITY = 0.3;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 5;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    // number of steps a Deer can go before it has to eat again.
    private static final int GRASS_FOOD_VALUE = 4;
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
    public Deer(boolean randomAge, Field field, Location location,String yearStage,String sex,boolean ill)
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
     * This is what the Deer does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.
     * @param newDeers A list to return newly born Deers.
     */
    public void act(List<Animal> newDeers)
    {
        dayLives();
        if(isAlive()) {
            giveBirth(newDeers);            
            // Try to move into a free location.
            Location newLocation = null;
            if(getAge()<=0.1*BREEDING_AGE)
            {
                //to stay at the original location and wait their parents to breed them.
            }
            else{
                if(foodLevel<=GRASS_FOOD_VALUE*0.7)
                {
                    newLocation = findFood();
                }
                else
                {
                    //deer is not very hungry, so no need to hunt.
                }
            }

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
                //
            }
        }

    }
  /**
     * This is what the Deer does during the night - it just
     * stay at its location and sleep.
     */
    public void act_night(List<Animal> newDeers)
    {
        dayLives();
    }
    /**
     * look for grasses adjacent to the current location.
     * Only the first alive grass will be esten by Deer.
     * @return Where the food is found,or if it wasn't return null.
     */
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
                    if(!grass.If_cantEat())
                    {
                        grass.setDead();
                        if(has_Children())
                        {
                            int FOODLIVE=GRASS_FOOD_VALUE/3;
                            foodLevel   = GRASS_FOOD_VALUE-FOODLIVE;
                            //foodLevel=GRAS_FOOD_VALUE-FOODLIVE
                            if(get_lowest_Age() instanceof Deer){
                                Deer deer= (Deer)get_lowest_Age();
                                deer.set_Foodlevel(FOODLIVE);
                            }
                        }
                        else{
                            foodLevel=GRASS_FOOD_VALUE;
                        }
                        return where;
                    }
                    else{
                        grass.setDead();
                        getIll();
                        foodLevel+=3;
                        return where;
                    }
                }
            }
        }
        return null;
    } 
    /**
     * return the MAX_AGE of the Deer.
     * @return the MAX_AGE ofthe Deer.
     */
    public int get_Max_Age()
    {
        return MAX_AGE;
    }
     /**
     *set foodvalue of a animal.
     */
    private void giveFood(Deer deer)
    {
        deer.set_Foodlevel(GRASS_FOOD_VALUE); 
    }

    /**
     * Increase the age.
     * This could result in the deer's death.
     */
    private void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    }
    /**
     * as the steps increases, the age to which a deer will increases.
     * At the same time, the Deer's year stage should be updated.
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
     * Check whether or not this deer is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newdeers A list to return newly born deers.
     */
    private void giveBirth(List<Animal> newDeers)
    {
        //
        Field field = getField();
        Object animal2=field.getObjectAt(getLocation());
        for(Location locate : field.adjacentLocations(getLocation()))
        {
            Object animal1=field.getObjectAt(locate);
            if(animal1 instanceof Deer&&animal2 instanceof Deer)
            {
                Deer Deer1=(Deer)animal1;
                Deer Deer2=(Deer)animal2; 
                if(!Deer1.getSex().equals(Deer2.getSex()))
                {
                    List<Location> free = field.getFreeAdjacentLocations(getLocation());
                    int births = breed();

                    for(int b = 0; b < births && free.size() > 0; b++) {
                        Location loc = free.remove(0);
                        Deer young = new Deer(false, field, loc,"young"," ",false);
                        young.setSex();
                        newDeers.add(young);//if newly born deer's parents get ill, young deers will also 
                        //be sick.
                        if(!Deer1.If_getIll()&&!Deer2.If_getIll())
                        {
                            //born the Deer commonly;
                        }
                        else{young.getIll();}
                        //Confirm each deer's role.
                        if(Deer1.getSex().equals("Male"))
                        {
                            young.set_Father(Deer1);
                            young.set_Mother(Deer2);
                        }
                        else
                        {
                            young.set_Mother(Deer1);
                            young.set_Father(Deer2);
                        }
                         //Confirm the couple of borning new deers.
                        Deer1.set_Couple(Deer2);
                        Deer2.set_Couple(Deer1);
                        Deer1.set_Child(young);
                        Deer2.set_Child(young);
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
    private int get_Foodlevel()
    {
        return foodLevel;
    }
    /**
     * after eating food, to change deer's food level again.
     * it is used to give foods to young deer.
     */
    private void set_Foodlevel(int food)
    {
        foodLevel = food;
    }
    
    /**
     * /**
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
     * increase age and hunger
     */
    private void dayLives()
    {
        incrementAge();
        incrementHunger();
    }

    /**
     * A Deer can breed if it has reached the breeding age.
     * @return true if the Deer can breed, false otherwise.
     */
    protected int getBREEDINGAGE()
    {
        return BREEDING_AGE;
    }
}