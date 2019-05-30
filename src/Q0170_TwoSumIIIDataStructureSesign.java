import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/*
https://leetcode.com/problems/two-sum-iii-data-structure-design/
Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

Example 1:

add(1); add(3); add(5);
find(4) -> true
find(7) -> false
Example 2:

add(3); add(1); add(2);
find(3) -> true
find(6) -> false
 */
public class Q0170_TwoSumIIIDataStructureSesign {

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */

/*
https://leetcode.com/problems/two-sum-iii-data-structure-design/discuss/52005/Trade-off-in-this-problem-should-be-considered
 */

    static class TwoSum {
        Map<Integer,Integer> hm;

        public TwoSum(){
            hm = new HashMap<Integer,Integer>();
        }
        // Add the number to an internal data structure.
        public void add(int number) {
            if (hm.containsKey(number)){
                hm.put(number,hm.get(number) + 1);
            } else {
                hm.put(number,1);
            }
        }

        // Find if there exists any pair of numbers which sum is equal to the value.
        public boolean find(int value) {
            Iterator<Integer> iter = hm.keySet().iterator();
            while(iter.hasNext()){
                int num1 = iter.next();
                int num2 = value - num1;
                if (hm.containsKey(num2)){
                    if (num1 != num2 || hm.get(num2) >= 2){
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        TwoSum obj = new TwoSum();
        obj.add(1);
        obj.add(1);
        obj.add(1);
        obj.add(3);
        obj.add(5);
        boolean found4 = obj.find(4);
        boolean found2 = obj.find(2);
        boolean found7 = obj.find(20);
    }
}
