import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;

public class LruCache {

	public LinkedList<CacheEntry> list = new LinkedList<CacheEntry>();
	public Map<String,LruCache.CacheEntry> map =  new HashMap<String,LruCache.CacheEntry>();
	public CacheEntry topEntry;
	public int maxEntries = 10;

	public class CacheEntry {
		public String key;
		public String value;
		public CacheEntry next;
		public CacheEntry prev;

		CacheEntry(String key, String value, CacheEntry next, CacheEntry prev) {
			this.key = key;
			this.value = value;
			this.next = next;
			this.prev = prev;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String toString() {
			return "Key: " + key + "  Value: " + value;
		}

	}

	// get
	public CacheEntry get(String key) {
		if(map.containsKey(key)) {
			CacheEntry obj = map.get(key);
			//System.out.println(obj);
			bringToTop(obj);
			return obj;
		}
		return null;
	}
	// set
	public CacheEntry set(String key, String value) {
		if(map.containsKey(key)) {
			CacheEntry obj = map.get(key);			
			obj.setValue(value);
			bringToTop(obj);
			return obj;	
		} else {

			CacheEntry obj = new CacheEntry(key, value, null, topEntry);
			if(map.size() == 0) {
				topEntry = obj;	
			} else {
				topEntry.next = obj;
				topEntry = obj;
			}
			list.add(obj);
			map.put(obj.key, obj);
			if(list.size() > 10) {
				CacheEntry delEntry = list.get(list.size()-1);
				list.remove(list.size()-1);
				map.remove(delEntry.key);
			}
			return obj;
		}		
	}

	// delete
	public CacheEntry delete(String key) {
		if(map.containsKey(key)) {	
			CacheEntry obj = map.get(key);
			if(obj.next != null)obj.next.prev = obj.prev;
			if(obj.prev != null)obj.prev.next = obj.next;
			map.remove(obj.key);
			list.remove(obj);
		}
		return null;
	}

	private void bringToTop(CacheEntry obj) {
		if(obj.prev != null) obj.prev.next = obj.next;
		if(obj.next != null) obj.next.prev = obj.prev;
		obj.next = null;
		if(topEntry != null) obj.prev = topEntry;
		topEntry = obj;
		list.remove(obj);
		list.addFirst(obj);
	}

	public void print() {
		System.out.println(list.toString());
		System.out.println(map.toString());
	}
	
	
	public static void main(String[] args) {
		LruCache cache = new LruCache();
		//LruCache.CacheEntry entry = cache.new CacheEntry("Test", "Test", null, null);
		cache.set("1", "1");
		cache.set("2", "2");
		cache.print();
		cache.get("2");
		cache.print();
		cache.delete("1");
		cache.print();
		// Doubly linkedlist
		// HashMap
	}
}