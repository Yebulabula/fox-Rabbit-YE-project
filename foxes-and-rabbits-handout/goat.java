import java.util.List;
/**
 * 在这里给出对类 goat 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class goat extends herbivore
{
    // 实例变量 - 用你自己的变量替换下面的例子
     // The age at which a fox can start to breed.
    private static final int BREEDING_AGE = 9;
    // The age to which a fox can live.
    private static final int MAX_AGE = 16;
    // The likelihood of a fox breeding.
    private static final double BREEDING_PROBABILITY = 0.19;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 5;
    // The food value of a single rabbit. In effect, this is the
    // number of steps a fox can go before it has to eat again.
    private int age;
    // A shared random number generator to control breeding.
    //private static final Random rand = Randomizer.getRandom();

    /**
     * 类 goat 的对象的构造函数
     */
    public goat(Field field , Location location, boolean Randomage)
    {
        // 初始化实例变量
        super(field,location);
    }
    
    public void incrementAge()
    {
      age++;
      if(age>MAX_AGE)
      {
        setDead();
    }
    }
    
    public void act( List<Animal> newgoats)
    {
      System.out.println("mistake");
        
        
    }
    
    

    /**
     * 一个方法的例子 - 使用你自己的说明替代它
     * 
     * @参数 y，方法的一个样本参数
     * @返回 x，y的和 
     */
    public int sampleMethod(int y,int x)
    {
        // 在这里加入你的代码
      
        return x+ y;
    }
}
