import java.util.List;
/**
 * 在这里给出对类 plant 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public abstract class Plant extends Organism
{
    // 实例变量 - 用你自己的变量替换下面的例子
    private boolean eatable;
    private boolean toxic;
    // The plant's field.
    // The plant's position in the field.
    private Location location;
    /**
     * 类 plant 的对象的构造函数
     */

    public Plant(Field field, Location location)
    {
        super(field,location);
        eatable = false;
        toxic = false;
        setLocation(location);
    }

    /**
     * check whether the grass can be eaten,
     * if true, the animals can eat them.
     * @return boolean eatable property.
     */
    protected boolean If_canEat()
    {
        return eatable;
    }

    /**
     * return the booean toxic property.
     * @ return the boolean toxic property.
     */
    protected boolean If_cantEat()
    {
        return toxic;
    }

    /**
     * return the field of a plant.
     * @return the field of a plant.
     */
    protected Field getField()
    {
        return field;
    }

    /**
     * let the plant be toxic.
     * @return true,the toxic property.
     */
    protected boolean cantEat()
    {
        return toxic =true;
    }

    /**
     * return the boolean eatable true.
     * @return the boolean eatable true.
     */
    protected boolean canEat()
    {
        return eatable =true ;
    }    

    /**
     * as the weather is sunny, plants will show different behaviours.
     * implement this method in its subclass.
     * @param list<plant>newPlants. a list to receive the newly born plants.
     */
    abstract public void act_Sunny(List<Plant> newPlants);

    /**
     * Makes the grasses act in the rainy days,it means make it do.
     * whatever it wants/ need to do.
     * @param a list to receive the newly born plants.
     */
    abstract public void act_Rainy(List<Plant> newPlants);

}
