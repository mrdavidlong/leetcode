import java.util.HashMap;
import java.util.Map;

public class Q0523_Continuous_Subarray_Sum {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(){{put(0,-1);}};;
        int runningSum = 0;
        for (int i=0;i<nums.length;i++) {
            runningSum += nums[i];
            if (k != 0) runningSum %= k;
            Integer prev = map.get(runningSum);
            if (prev != null) {
                if (i - prev > 1) return true;
            }
            else map.put(runningSum, i);
        }
        return false;
    }

    public static void main(String[] args) {
        Q0523_Continuous_Subarray_Sum sol = new Q0523_Continuous_Subarray_Sum();
        boolean hasSubarraySum = sol.checkSubarraySum(new int[] {23,2,4,6,7}, 6);
        boolean hasSubarraySum2 = sol.checkSubarraySum(new int[] {23,2,6,4,7}, 6);
        boolean hasSubarraySum3 = sol.checkSubarraySum(new int[] {23,2,6,4,7}, 13);

    }
}
