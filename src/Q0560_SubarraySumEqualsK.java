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

    public int subarraySumNoSpace(int[] nums, int k) {
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
    Approach 4: Using Hashmap
    Algorithm

The idea behind this approach is as follows: If the cumulative sum(repreesnted by
s
u
m
[
i
]
sum[i] for sum upto
i
t
h
i
th
  index) upto two indices is the same, the sum of the elements lying in between those indices is zero. Extending the same thought further, if the cumulative sum upto two indices, say
i
i and
j
j is at a difference of
k
k i.e. if
s
u
m
[
i
]
−
s
u
m
[
j
]
=
k
sum[i]−sum[j]=k, the sum of elements lying between indices
i
i and
j
j is
k
k.

Based on these thoughts, we make use of a hashmap
m
a
p
map which is used to store the cumulative sum upto all the indices possible along with the number of times the same sum occurs. We store the data in the form:
(
s
u
m
i
,
n
o
.
o
f
o
c
c
u
r
e
n
c
e
s
o
f
s
u
m
i
)
(sum
i
​
 ,no.ofoccurencesofsum
i
​
 ). We traverse over the array
n
u
m
s
nums and keep on finding the cumulative sum. Every time we encounter a new sum, we make a new entry in the hashmap corresponding to that sum. If the same sum occurs again, we increment the count corresponding to that sum in the hashmap. Further, for every sum encountered, we also determine the number of times the sum
s
u
m
−
k
sum−k has occured already, since it will determine the number of times a subarray with sum
k
k has occured upto the current index. We increment the
c
o
u
n
t
count by the same amount.

After the complete array has been traversed, the
c
o
u
n
t
count gives the required result.

The animation below depicts the process.

**Complexity Analysis**
Time complexity :
O
(
n
)
O(n). The entire
n
u
m
s
nums array is traversed only once.

Space complexity :
O
(
n
)
O(n). Hashmap
m
a
p
map can contain upto
n
n distinct entries in the worst case.
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap <Integer, Integer> map = new HashMap<>();
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
        int count = sol.subarraySum(new int[] {3,4,7,2,-3,1,4,2}, 7);
    }
}
