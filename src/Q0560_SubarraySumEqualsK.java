import java.util.HashMap;

/*
https://leetcode.com/problems/subarray-sum-equals-k/
Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2

 */
public class Q0560_SubarraySumEqualsK {
    // https://leetcode.com/problems/subarray-sum-equals-k/solution/
    public int subarraySumBF(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            for (int end = start + 1; end <= nums.length; end++) {
                int sum = 0;
                for (int i = start; i < end; i++)
                    sum += nums[i];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }

    /*
    Instead of determining the sum of elements every time for every new subarray considered, we can make use of a cumulative sum array ,
s
u
m
sum. Then, in order to calculate the sum of elements lying between two indices, we can subtract the cumulative sum corresponding to the two indices to obtain the sum directly, instead of iterating over the subarray to obtain the sum.

In this implementation, we make use of a cumulative sum array,
s
u
m
sum, such that
s
u
m
[
i
]
sum[i] is used to store the cumulative sum of
n
u
m
s
nums array up to the element corresponding to the
(
i
−
1
)
(i−1)
th
 index. Thus, to determine the sum of elements for the subarray
n
u
m
s
[
i
:
j
]
nums[i:j], we can directly use
s
u
m
[
j
+
1
]
−
s
u
m
[
i
]
sum[j+1]−sum[i].
     */
    public int subarraySumCumSum(int[] nums, int k) {
        int count = 0;
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++)
            sum[i] = sum[i - 1] + nums[i - 1];
        for (int start = 0; start < nums.length; start++) {
            for (int end = start + 1; end <= nums.length; end++) {
                if (sum[end] - sum[start] == k)
                    count++;
            }
        }
        return count;
    }

    public int subarraySumCumSumNoSpace(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum=0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }


    /*
    Complexity Analysis
    Time complexity : O(n). The entire nums array is traversed only once.
    Space complexity : O(n). Hashmap map can contain up to n distinct entries in the worst case.

    key, value

     */
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        Q0560_SubarraySumEqualsK sol = new Q0560_SubarraySumEqualsK();
        int countBF = sol.subarraySumBF(new int[] {3,4,7,2,-3,1,4,2}, 7);
        int countCumSum = sol.subarraySumCumSum(new int[] {3,4,7,2,-3,1,4,2}, 7);
        int countCumSumNoSpace = sol.subarraySumCumSumNoSpace(new int[] {3,4,7,2,-3,1,4,2}, 7);
        int count = sol.subarraySum(new int[] {3,4,7,2,-3,1,4,2}, 7);
        int count1 = sol.subarraySumCumSum(new int[] {1,1,1}, 2);
        int count2 = sol.subarraySumCumSum(new int[] {1,2,3}, 3);
    }
}
