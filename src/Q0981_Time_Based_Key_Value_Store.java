import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Q0981_Time_Based_Key_Value_Store {
    private Map<String,TreeMap<Integer,String>> map;

    /** Initialize your data structure here. */
    public Q0981_Time_Based_Key_Value_Store() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if(!map.containsKey(key)) {
            map.put(key,new TreeMap<>());
        }
        map.get(key).put(timestamp,value);
    }

    public String get(String key, int timestamp) {
        TreeMap<Integer,String> treeMap = map.get(key);
        if (treeMap == null) {
            return "";
        }
        Integer floor = treeMap.floorKey(timestamp);
        if (floor == null) {
            return "";
        }
        return treeMap.get(floor);
    }

}
