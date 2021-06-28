import java.util.Arrays;
import java.util.HashSet;

/*
https://leetcode.com/problems/intersection-of-two-arrays/
Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Note:

Each element in the result must be unique.
The result can be in any order.

 */
public class Q0349_IntersectionOfTwoArrays {
    /*
    https://leetcode.com/problems/intersection-of-two-arrays/solution/
     */
    private int[] set_intersection(HashSet<Integer> set1, HashSet<Integer> set2) {
        int [] output = new int[set1.size()];
        int idx = 0;
        for (Integer s : set1)
            if (set2.contains(s)) output[idx++] = s;

        return Arrays.copyOf(output, idx);
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        for (Integer n : nums1) set1.add(n);
        HashSet<Integer> set2 = new HashSet<>();
        for (Integer n : nums2) set2.add(n);

        if (set1.size() < set2.size()) return set_intersection(set1, set2);
        else return set_intersection(set2, set1);
    }

    public int[] intersectionUsingBuiltInMethod(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        for (Integer n : nums1) set1.add(n);
        HashSet<Integer> set2 = new HashSet<>();
        for (Integer n : nums2) set2.add(n);

        set1.retainAll(set2);

        int[] output = new int[set1.size()];
        int idx = 0;
        for (int s : set1) output[idx++] = s;
        return output;
    }

    public static void main(String[] args) {
        Q0349_IntersectionOfTwoArrays sol = new Q0349_IntersectionOfTwoArrays();
        int[] intersact = sol.intersection(new int[] {4,9,5}, new int[] {9,4,9,8,4});
        int[] intersact1 = sol.intersection(new int[] {1,2,2,1}, new int[] {2,2});
        int[] intersact2 = sol.intersectionUsingBuiltInMethod(new int[] {4,9,5}, new int[] {9,4,9,8,4});

    }
}
