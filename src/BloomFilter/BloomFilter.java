package BloomFilter;

import java.util.BitSet;

/**
 * BloomFilter算法去重策略<br>
 * Bloom-Filter，布隆过滤器。可以用于检索一个元素是否在一个集合中的快速的概率算法。<br>
 * 是一种空间效率很高的随机数据结构，利用位数组很简洁地表示一个集合，并能判断一个元素是否属于这个集合。<br>
 * 优点:是空间效率和查询时间都远远超过一般的算法。<br>
 * 缺点:是有一定的误识别率和删除困难。<br>
 * ---------------------------------------
 * <p>
 * 可应用场景： 1、垃圾邮件地址过滤<br>
 * 2、广播消息时，可以检测某个IP是否已发包。 <br>
 * 3、检测广播消息包的环路，将Bloom Filter保存在包里，每个节点将自己添加入Bloom Filter <br>
 * 4、信息队列管理，使用Counter Bloom Filter管理信息流量。<br>
 * <p>
 * 给你A,B两个文件，各存放50亿条URL，每条URL占用64字节，内存限制是4G，让你找出A,B文件共同的URL。如果是三个乃至n个文件呢？
 */
public class BloomFilter {

    /* BitSet初始分配2^24个bit */
    private static final int DEFAULT_SIZE = 1 << 25;

    /* 不同哈希函数的种子，一般应取质数 */
    private static final int[] seeds = new int[]{5, 7, 11, 13, 31, 37, 61};

    private BitSet bits = new BitSet(DEFAULT_SIZE);

    /* 哈希函数对象 */
    private SimpleHash[] func = new SimpleHash[seeds.length];

    public BloomFilter() {
        for (int i = 0; i < seeds.length; i++) {
            func[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);
        }
    }

    // 将字符串标记到bits中  
    public synchronized void add(String value) {
        for (SimpleHash f : func) {
            bits.set(f.hash(value), true);
        }
    }

    // 判断字符串是否已经被bits标记  
    public boolean contains(String value) {
        if (value == null) {
            return false;
        }

        boolean ret = true;
        for (SimpleHash f : func) {
            ret = ret && bits.get(f.hash(value));
        }

        return ret;
    }

    /* 哈希函数类 */
    public static class SimpleHash {
        private int cap;
        private int seed;

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        // hash函数，采用简单的加权和hash  
        public int hash(String value) {
            int result = 0;
            int len = value.length();
            for (int i = 0; i < len; i++) {
                result = seed * result + value.charAt(i);
            }
            return (cap - 1) & result;
        }
    }


}  