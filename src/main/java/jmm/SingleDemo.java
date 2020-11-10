package jmm;

/**
 * @Author gaoxing
 * @Date 2020-06-19 16:38
 */
public class SingleDemo {

    /**
     * 对象初始化指令：
     *      1. 分配对象需要的内存空间
     *      2. 初始化对象
     *      3. 设置instance指向该内存空间
     * 2/3没有数据依赖关系，可发生指令重排
     *
     * 多线程下，可能出现以下现象
     *      T1.1 -> T1.3 -> T2 -> T1.2
     *
     * 所以需要禁止指令重排序
     */
    private volatile static SingleDemo s;

    private SingleDemo(){}

    public SingleDemo getInstance() {
        if (s == null) {
            synchronized (SingleDemo.class) {
                if (s == null) {
                    s = new SingleDemo();
                }
            }
        }
        return s;
    }

}
