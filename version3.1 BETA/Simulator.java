import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;

/**
 * A simple predator-prey simulator, based on a rectangular field
 * containing rabbits and foxes.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29 (2)
 */
public class Simulator
{
    // Constants representing configuration information for the simulation.
    
    
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 200;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 80;
    // The probability that a fox will be created in any given grid position.
    private static final double FOX_CREATION_PROBABILITY = 0.0;//2;
    // The probability that a rabbit will be created in any given grid position.
    private static final double RABBIT_CREATION_PROBABILITY = 0.02;   
    // The probability that a cow will be created in any given grid position.
    private static final double COW_CREATION_PROBABILITY= 0.0;
    //The probability that a grass will be created in any given grid position.
    private static final double GRASS_CREATION_PROBABILITY = 0.1;    
    //The probability that a tiger will be created in any given grid position.
    private static final double TIGER_CREATION_PROBABILITY = 0.0;
    //The probability that a wolf will be created in any given grid position.
    private static final double WOLF_CREATION_PROBABILITY=0.0;
    // List of animals in the field.
    private List<Animal> animals;
    //The current weather of that day.
    private String currentWeather;
    private Weather weather;
    //The timezone of a single dy
    private String timezone;
    private List<Plant> plants;
    // The current state of the field.
    private Field field;
    // The current step of the simulation.
    private int step;
    // A graphical view of the simulation.
    private SimulatorView view;

    /**
     * Construct a simulation field with default size.
     */
    public Simulator()
    {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }

    /**
     * Create a simulation field with the given size.
     * Create a color to respresent different creatures;
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width)
    {
        if(width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }

        animals = new ArrayList<>();
        plants = new ArrayList<>();
        field = new Field(depth, width);
        currentWeather = getWeather();

        // Create a view of the state of each location in the field.
        view = new SimulatorView(depth, width);

        view.setColor(Rabbit.class, Color.ORANGE);
        view.setColor(Fox.class, Color.BLUE);
        view.setColor(Tiger.class, Color.RED);
        view.setColor(Grass.class, Color.GREEN);
        view.setColor(Cow.class,Color.lightGray);
        view.setColor(Wolf.class,Color.darkGray);
        // Setup a valid starting point
        reset();
    }
    
    /**
     * to change the current weather randomly.
     * @return current weather.
     */
    protected String getWeather()
    {
        weather= new Weather();       
        currentWeather=weather.get_Weather();
        return currentWeather;
    }

    /**
     * Run the simulation from its current state for a reasonably long period,
     * (4000 steps).
     */
    public void runLongSimulation()
    {
        simulate(4000);
    }
    //
    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     * @param numSteps The number of steps to run for.
     */
    public void simulate(int numSteps)
    {
        for(int step = 1; step <= numSteps && view.isViable(field); step++) {
            simulateOneStep();
            // delay(60);   // uncomment this to run more slowly
        }
    }

    /**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each
     * cow,fox , rabbit ,tiger ,wolf and grass .
     */
    public void simulateOneStep()
    {
        step++;
        if(step%2==0)
        {
            timezone= "AM";
        }
        else if(step%2==1)
        {
            timezone ="PM";
        }  

        // Provide space for newborn animals.
        List<Animal> newAnimals = new ArrayList<>();   
        List<Plant> newPlants = new ArrayList<>();
        // Let all animals and grasses act.
        // when step increases to an odd,the animal's act would change which depends
        //on different weather.
        if(step%2==0)
        { 
            for(Iterator<Animal> it = animals.iterator(); it.hasNext(); ) { 
                Animal animal = it.next();
                animal.act_night(newAnimals);
                if(!animal.isAlive()) {
                    it.remove();
                }
            }
            if(currentWeather.equals("Sunny")||currentWeather.equals("Foggy"))
            {
                for(Iterator<Plant> it = plants.iterator(); it.hasNext(); ) {
                    Plant plant = it.next();
                    if(!plant.isAlive()) {
                        plant.act_Sunny(newPlants);
                        it.remove();
                    }
                }
            }
            else if(currentWeather.equals("Rainy"))
            {
                for(Iterator<Plant> it = plants.iterator(); it.hasNext(); ) {
                    Plant plant = it.next();
                    if(!plant.isAlive()) {
                        plant.act_Rainy(newPlants);
                        it.remove();
                    }
                }
            }
        }
        // as the time zone is afternoon.
        else if(step%2==1)
        {
            for(Iterator<Animal> it = animals.iterator(); it.hasNext(); ) {
                Animal animal = it.next();
                animal.act(newAnimals);
                if(!animal.isAlive()) {
                    it.remove();
                }
            }
            if(currentWeather.equals("Rainy")){
                for(Iterator<Plant> it = plants.iterator(); it.hasNext(); ) {
                    Plant plant = it.next();
                    plant.act_Rainy(newPlants);
                    if(!plant.isAlive()) {
                        it.remove();
                    }
                }
            }
            else if(currentWeather.equals("Sunny")||currentWeather.equals("Foggy")){
                for(Iterator<Plant> it = plants.iterator(); it.hasNext(); ) {
                    Plant plant = it.next();
                    plant.act_Sunny(newPlants);
                    if(!plant.isAlive()) {
                        it.remove();
                    }
                }
            }
        }
        // Add the newly born animals and plants to the main lists.
        currentWeather = getWeather();
        animals.addAll(newAnimals);
        plants.addAll(newPlants);
        //show different status at the window.Eg:step,weather and so on.
        view.showStatus(step,currentWeather, field,timezone);
    }

    
    /**
     * Reset the simulation to a starting position.
     */
    public void reset()
    {
        step = 0;
        animals.clear();
        plants.clear();
        populate();
        // Show the starting state in the view.
        view.showStatus(step,currentWeather ,field, timezone);
    }

    /**
     * Randomly populate the field with foxes and rabbits.
     */
    private void populate()
    {
        Random rand = Randomizer.getRandom();
        field.clear();
        //weathe=currentWeather();
        currentWeather=getWeather();
        //the initial timezone is am.
        timezone="am";
        //create random number of animals with their field at the beginning.
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                if(rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
                    Gender gender= new Gender();
                    gender.get_gender();
                    Location location = new Location(row, col);
                    Fox fox = new Fox(true, field, location,"young","",false);
                    fox.setSex();
                    animals.add(fox);     
                }
                else if(rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
                    Gender gender= new Gender();
                    gender.get_gender();
                    Location location = new Location(row, col);
                    Rabbit rabbit = new Rabbit(true, field, location ,"young","",false);
                    rabbit.set_Yearstage(rabbit.getAge(),rabbit.get_Max_Age());
                    rabbit.setSex();
                    animals.add(rabbit);
                }
                else if(rand.nextDouble() <= GRASS_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Grass grass = new Grass(true, field, location,false,false);
                    plants.add(grass);     
                }
                else if(rand.nextDouble() <= COW_CREATION_PROBABILITY) {
                    Gender gender= new Gender();
                    gender.get_gender();
                    Location location = new Location(row, col);
                    Cow cow = new Cow(true, field, location,"young","",false); 
                    cow.setSex();
                    animals.add(cow);
                }
                else if(rand.nextDouble() <= TIGER_CREATION_PROBABILITY) {
                    Gender gender= new Gender();
                    gender.get_gender();
                    Location location = new Location(row, col);
                    Tiger tiger = new Tiger(true, field, location,"young","",false); 
                    tiger.setSex();
                    animals.add(tiger);
                }
                else if(rand.nextDouble() <=  WOLF_CREATION_PROBABILITY) {
                    Gender gender= new Gender();
                    gender.get_gender();
                    Location location = new Location(row, col);
                    Wolf wolf = new Wolf(true, field, location,"young","",false); 
                    wolf.setSex();
                    animals.add(wolf);
                                // else leave the location empty.
            }
        }
        view.showStatus(step,currentWeather, field,timezone);
    }
}
    /**
     * Pause for a given time.
     * @param millisec  The time to pause for, in milliseconds
     */
    private void delay(int millisec)
    {
        try {
            Thread.sleep(millisec);
        }
        catch (InterruptedException ie) {
            // wake up
        }
    }
}
