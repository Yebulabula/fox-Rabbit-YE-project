import java.util.*;
/**
 * 在这里给出对类 grass 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Grass extends Plant
{
    // 实例变量 - 用你自己的变量替换下面的例子
    private static final int EAT_AGE = 3;
    private static final int MAX_AGE = 15;

    private static final double BREEDING_PROBABILITY = 0.67;
    private static final int WATER_LEVEL = 50;

    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 3;
    //private List<Integer> toxic_PROBABILITY= new ArrayList();
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();

    private int water;
    /**
     * 类 grass 的对象的构造函数
     */
    public Grass(boolean randomAge, Field field, Location location,boolean Eatable,boolean toxic)
    {
        super(field, location);
        setAge(0);
        water = WATER_LEVEL;
        if(randomAge) {
            setAge(rand.nextInt(MAX_AGE));
            water = rand.nextInt(WATER_LEVEL);
            if(getAge() > EAT_AGE)
            {
                canEat();
            }
        }
    }
    
    /**
     * the plant's act in the sunny days.
     */
    public void act_Sunny(List<Plant> newGrass)
    {
        incrementAge();
        absorbWater();
        if(isAlive()&&water>=0.4*(WATER_LEVEL)) {
            giveBirth(newGrass);            
        }
        else{}    
    }
    
    /**
     * the plant's act in the rainy days.
     */
    public void act_Rainy(List<Plant> newGrass)
    {
        water = WATER_LEVEL;
        incrementAge();
        absorbWater();
        if(isAlive()) {}
    }
    
    /**
     *
     * increase the age of the grass.
     * and if the grass is larger than 
     * the EAT_AGE, the grass could be eaten by the animals.
     * and if the grass is over than 
     * the MAX_AGE, the grass will set dead.
     * 
     */
    private void incrementAge()
    {
        int age= getAge();
        age++;
        if(age > EAT_AGE){
            canEat();
        }
        if(age > MAX_AGE) {
            setDead();
        }
    }
   
    /**
     * the grass will absorb water everyday.
     * when the water level is smaller than 0, the grass 
     * will die.
     */
    private void absorbWater()
    {
        water--;
        if(water <= 0) {
            setDead();
        }
    }

    /**
     * the whole procedure to give birth to the grass. put them
     * into the newGrass list.
     */
    private void giveBirth(List<Plant> newGrass)
    {
        // New foxes are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        Random Rand =new Random();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        if(!If_cantEat()){
            for(int b = 0; b < births && free.size() > 0; b++) {
                Location loc = free.remove(0);
                int nextRand = Rand.nextInt(1000);
                if(nextRand<=999)
                {
                    Grass young = new Grass(false, field, loc,false,false);
                    newGrass.add(young);
                }
                else{
                    Grass young = new Grass(false, field, loc,false,false);
                    cantEat();
                    newGrass.add(young);
                }
            }
        }
        else
        {
            int age=getAge()+1;
        }
    }
    
    /**
     * return the the number of newly born grasses.
     * @ return the number of the newly born grasses.
     */
    private int breed()
    {
        int births = 0;
        if(rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }
}
