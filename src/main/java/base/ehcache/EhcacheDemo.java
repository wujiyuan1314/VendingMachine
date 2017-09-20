package base.ehcache;

import base.util.CacheUtils;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhcacheDemo {
	public static void main(String[] args) throws Exception {
        // Create a cache manager
        final CacheManager cacheManager = new CacheManager();

        // create the cache called "helloworld"
        final Cache cache = cacheManager.getCache("goodsCache");

        // create a key to map the data to
        final String key = "greeting";

        // Create a data element
        final Element putGreeting = new Element(key, "Hello, World!");

        // Put the element into the data store
        cache.put(putGreeting);
        System.out.println(cache.getSize());
        // Retrieve the data element
        final Element getGreeting = cache.get(key);
        System.out.println(cache.getSize());
        // Print the value
        System.out.println(getGreeting.getObjectValue());
    }
}
