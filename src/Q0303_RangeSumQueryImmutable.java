/**
 * https://leetcode.com/problems/range-sum-query-immutable/solution/
 *
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

 Example:
 Given nums = [-2, 0, 3, -5, 2, -1]

 sumRange(0, 2) -> 1
 sumRange(2, 5) -> -1
 sumRange(0, 5) -> -3
 */
public class Q0303_RangeSumQueryImmutable {
//    Brute Force
//    private int[] data;
//
//    public NumArray(int[] nums) {
//        data = nums;
//    }
//
//    public int sumRange(int i, int j) {
//        int sum = 0;
//        for (int k = i; k <= j; k++) {
//            sum += data[k];
//        }
//        return sum;
//    }

//    Caching
//    private Map<Pair<Integer, Integer>, Integer> map = new HashMap<>();
//
//    public NumArray(int[] nums) {
//        for (int i = 0; i < nums.length; i++) {
//            int sum = 0;
//            for (int j = i; j < nums.length; j++) {
//                sum += nums[j];
//                map.put(Pair.create(i, j), sum);
//            }
//        }
//    }
//
//    public int sumRange(int i, int j) {
//        return map.get(Pair.create(i, j));
//    }

    private int[] sum;

    public Q0303_RangeSumQueryImmutable(int[] nums) {
        sum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }

    public static void main(String[] args) {
        Q0303_RangeSumQueryImmutable sol = new Q0303_RangeSumQueryImmutable(new int[] {-2, 0, 3, -5, 2, -1});
        int sum1 = sol.sumRange(0,2);
        int sum2 = sol.sumRange(2,5);
        int sum3 = sol.sumRange(0,5);

    }
}
