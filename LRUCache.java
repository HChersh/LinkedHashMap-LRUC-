import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/*
 * Ϊ�������ʹ�ã�LRU������������һ�����ݽṹ��
 * ��Ӧ��֧�����²�������ȡ���ݣ�get����д�����ݣ�set����
 * ��ȡ����get(key)����������д���key�����ȡ������ֵ��ͨ���������������򷵻�-1��
 * д������set(key, value)�����key��û���ڻ����У���д��������ֵ��
 * ������ﵽ���ޣ���Ӧ����д��������֮ǰɾ���������ʹ�õ����������ڳ�����λ�á� 
 *
 */
public class LRUCache<K, V>{
    LinkedHashMap<K,V> cache = null;
    private int cacheSize;
    public LRUCache(int cacheSize){
    	this.cacheSize = (int) Math.ceil (cacheSize / 0.75f) + 1;  // ceil����������ȡ����
    	cache = new LinkedHashMap<K,V>(this.cacheSize,0.75f,true){  //boolean accessOrder�������Ʒ���˳��ģ�Ĭ������Ϊfalse���ڷ���֮�󣬲��Ὣ��ǰ���ʵ�Ԫ�ز��뵽����β��
    	  //�ڲ�������дremoveEldestEntry()����
    	@Override
		  protected boolean removeEldestEntry (Map.Entry<K, V> eldest){
              System.out.println("size="+size());
              return size() > cacheSize;           //��ǰsize()������cacheSize��ɾ��ͷ����Ԫ��
          }
    	};
    }
    
	public V get(K key){           //���ʹ�ü̳еĻ�����getE������get����ֹ�����˸���ĸ÷���
	   return (V) cache.get(key);
	}
	public V set(K key,V value){
       return cache.put(key, value);
	}
	
	public int getCacheSize() {
		return cacheSize;
	}

	public void setCacheSize(int cacheSize) {
		this.cacheSize = cacheSize;
	}
	
	public void printCache(){
		for(Iterator it = cache.entrySet().iterator();it.hasNext();){
			Entry<K,V> entry = (Entry<K, V>)it.next();
			if(!"".equals(entry.getValue())){
				System.out.println(entry.getKey() + "\t" + entry.getValue()); 
			}
		}
		System.out.println("------");
	}
	
	public void PrintlnCache(){
		Set<Map.Entry<K,V>> set = cache.entrySet();
		for(Entry<K,V> entry : set){
			K key = entry.getKey();
			V value = entry.getValue();
			System.out.println("key:"+key+"value:"+value);
		}
		
	}
	
	public static void main(String[] args) {
		LRUCache<String,Integer> lrucache = new LRUCache<String,Integer>(3);
        lrucache.set("aaa", 1);
        lrucache.printCache();
        lrucache.set("bbb", 2);
        lrucache.printCache();
        lrucache.set("ccc", 3);
        lrucache.printCache();
        lrucache.set("ddd", 4);
        lrucache.printCache();
        lrucache.set("eee", 5);
        lrucache.printCache();
        System.out.println("���Ƿ�����ddd��Ľ��");
        lrucache.get("ddd");
        lrucache.printCache();
        lrucache.set("fff", 6);
        lrucache.printCache();
        lrucache.set("aaa", 7);
        lrucache.printCache();
	}

}
