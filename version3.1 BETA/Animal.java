import java.util.*;
/**
 * A class representing shared characteristics of animals.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29 (2)
 */
public abstract class Animal extends Organism
{
    // Whether the animal is alive or not.
    private boolean ill;
    private Object Father;
    private Object Mother;
    private Object Couple;
    private List<Object>children = new ArrayList<>();
    // The animal's field.
    
    // The animal's position in the field.
   
    protected String sex;
    private String yearstage;
    private Gender gender;
    /**
     * Create a new animal at location in field.
     * 
     * @param yearstage The year stage of an animal.
     * @param sex The gender of the animal.
     * @param ill The animal is ill or not currently.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Animal(Field field, Location location,String yearStage,String sex,boolean ill)
    {
        super(field,location);
        ill = false;
        setLocation(location);
    }
    
    /**
     * to set there year stages which are determined by the age of animals.
     */
    public void set_Yearstage(int age,int MAX_AGE)
    {
        if(age<=MAX_AGE*0.3)
        {
            yearstage= "Young";
        }
        else if(age>MAX_AGE*0.3&&age<=MAX_AGE*0.7)
        {
            yearstage= "Maturity";
        }
        else if(age>MAX_AGE*0.7)
        {
            yearstage= "Old";
        }
    }
    
    /**
     * to set the father of an animal.
     */
    public void set_Father(Object father)
    {
        this.Father = father;
    }
    
    /**
     * to set the mother of the animal.
     */
    public void set_Mother(Object mother)
    {
        this.Mother = mother;
    }
    
    /**
     * to set the animal's parter (wife or husband) of the animal.
     */
     public void set_Couple(Object couple)
    {
        this.Couple = couple;
    }
    
    /**
     * every times a new child is born
     * then add child to the children list.
     *
     */
    public void set_Child(Object child)
    {
        children.add(child);
    }
    
    /**
     * get the father of a single child.
     * @return object Father.
     */
    public Object get_Father()
    {
        return Father;
    }
    /**
     * get the mother of a single child.
     * @return object mother.
     */
    public Object get_Mother()
    {
        return Mother;
    }
    
    /**
     * get the couple of the animal.
     * @return object couple.
     */
     public Object get_Couple()
    {
        return Couple;
    }
    
    /**
     * Return the collection of childeren.
     * @return children list.
     */
    public List get_children()
    {
        return children;
    }
    
    /**
     * Make this animal act - that is: make it do
     * whatever it wants/needs to do.
     * @param newAnimals A list to receive newly born animals.
     */
    abstract public void act(List<Animal> newAnimals);
    
    /**
     * Make this animal to do something during the night.
     * diferent species might have different action at night.
     * @param newAnimals A list to receive newly born animals.
     */
    abstract public void act_night(List<Animal> newAnimals);
    
    /**
     * check whether the animal can breed or not.
     * @return true if　the animal can breed.
     */
    protected boolean canBreed()
    {
        return getAge() >= getBREEDINGAGE();
    }
    
    /**
     * to increase the age of an animal.
     * make the animal to grow up.
     * if the animal was ill, the animal's age will increase
     * rapidly, that means the animal's life expectation will
     * be reduced.
     */
    protected void incrementAge()
    {
        int age=getAge();
        age++;
        if(If_getIll())
        {   
            age+=5;
        }
    }
    
    /**
     * return breeding age of each speices animal.
     * @return the breeding age
     */
    abstract protected int getBREEDINGAGE();
    
    /**
     *  return the age group  of the animal.
     *  @return the year stage of the animal.
     * 
     */
    protected String getYearStage()
    {
        return yearstage;
    }  
    
    /**
     * to set the gender of the animal.
     */
    public void setSex()
    {
        sex=getGender();
    }
    
    /**
     * make the animal to be sick.
     * @return the boolean ill is true.
     */
    protected boolean getIll()
    {
        return ill = true;
    }
    
    /**
     * check whether a single animal is ill or not.
     * @return true. if an animal is ill
     */
    protected boolean If_getIll()
    {
        return ill;
    }
    
    /**
     * as the new animal is born, 
     * to give a gender randomly.
     * @return the sex of an animal.
     */
    protected String getGender()
    {
       gender= new Gender();
       sex=gender.get_gender();
       return sex;
    }
    
    /**
     * return the gender of the animal.
     * @return the sex of the animal.
     */
    protected String getSex()
    {
        return sex;  
    }   
}
