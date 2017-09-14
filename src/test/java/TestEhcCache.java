
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class TestEhcCache {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Create a cache manager
        final CacheManager cacheManager = new CacheManager();
        System.out.println(System.getProperty("java.io.tmpdir"));
        // create the cache called "helloworld"
        final Cache cache = cacheManager.getCache("goodsCache");

        // create a key to map the data to
        final String key = "greeting";

        // Create a data element
        final Element putGreeting = new Element(key, "Hell, World!");
        System.out.println(cache.getSize());
        // Put the element into the data store
        cache.put(putGreeting);
         
        System.out.println(cache.getSize());
        // Retrieve the data element
        //final Element getGreeting = cache.get("vendGoods");
        //cache.flush();

        // Print the value
        //System.out.println(getGreeting.getObjectValue());
	}

}
