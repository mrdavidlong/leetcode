import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by davidlong on 12/28/17.
 */
public class Q0001_TwoNumbersInAnArrayAddToTarget {

    // output the indices
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    // output the values
//    public static int[] twoSum(int[] nums, int target) {
//        Set<Integer> set = new HashSet<>();
//        for (int i = 0; i < nums.length; i++) {
//            int complement = target - nums[i];
//            if (set.contains(complement)) {
//                return new int[] { complement, nums[i] };
//            }
//            set.add(nums[i]);
//        }
//        throw new IllegalArgumentException("No two sum solution");
//    }

    public static int[] twoSumNoExtraSpace(int[] nums, int target) {
        Arrays.sort(nums);
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int sum = nums[i] + nums[j];
            if (sum == target) {
                return new int[] { nums[i], nums[j] };
            } else if (sum < target) {
                i++;
            } else if (sum > target) {
                j--;
            }
        }

        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        //int[] a =  {2,-1,7,2,6};
        //int[] answer = twoSum(a, 4);
        int[] a =  {3,-1,7,2,6};
        int[] answer = twoSum(a, 5);
        System.out.println("answer index: " + answer[0] + ", " +  answer[1]);

        int[] answer2 = twoSumNoExtraSpace(a, 5);
        System.out.println("answer2 values: " + answer2[0] + ", " +  answer[1]);
    }
}
