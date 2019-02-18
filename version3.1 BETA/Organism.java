import java.util.*;
/**
 * 在这里给出对类 Organism 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public abstract class Organism
{
    
    private int age;
    protected Field field;
    private boolean alive;
    protected Location location;
    /**
     * 类 Organism 的对象的构造函数
     */
    public Organism(Field field,Location location)
    {
        // 初始化实例变量
        this.field = field;
        this.location = location;
        alive =true;
    }
    
    /**
     * Check whether the animal is alive or not.
     * @return true if the animal is still alive.
     */
    protected boolean isAlive()
    {
        return alive;
    }
    
    /**
     * check the age of a single animal.
     * @return the age of the animal.
     */
    protected int getAge()
    {
        return age;
    } 
    
     
    protected void setAge(int Age)
    {
        this.age= age; 
    }   
   
    /**
     * to help the animal to set a new location. 
     */
    protected void setLocation(Location newLocation)
    {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.placePla(this, newLocation);
    }
    
    /**
     * if the animal is not alive, the animal will die.
     * set the alive to false.
     */
    protected void setDead()
    {
        alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }
    
    /**
     * get the current field of the animal.
     */
    protected Field getField()
    {
        return field;
    }
    
    /**
     * Return the animal's location.
     * @return The animal's location.
     */
    protected Location getLocation()
    {
        return location;
    }
}
