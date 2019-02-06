
/**
 * 在这里给出对类 herbivore 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
abstract public class herbivore extends Animal
{
    // 实例变量 - 用你自己的变量替换下面的例子
    private int x;

    /**
     * 类 herbivore 的对象的构造函数
     */
    public herbivore()
    {
        // 初始化实例变量
        x = 0;
    }
    
    abstract protected void findFood();

    /**
     * 一个方法的例子 - 使用你自己的说明替代它
     * 
     * @参数 y，方法的一个样本参数
     * @返回 x，y的和 
     */
    public int sampleMethod(int y)
    {
        // 在这里加入你的代码
        return x + y;
    }
}
