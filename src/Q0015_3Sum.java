import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/3sum/
 Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

 Note:

 The solution set must not contain duplicate triplets.

 Example:

 Given array nums = [-1, 0, 1, 2, -1, -4],

 A solution set is:
 [
 [-1, 0, 1],
 [-1, -1, 2]
 ]
 */
public class Q0015_3Sum {
    public List<List<Integer>> threeSumBruteForce(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                for (int k = j+1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> list = new LinkedList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        result.add(list);
                    }
                }
            }
        }
        return result;
    }

    private boolean exist(List<Integer> compList, List<List<Integer>> lists) {
        if (lists.size() == 0) return false;

        for (List<Integer> list : lists) {
            if (list.size() != compList.size()) continue;

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == compList.get(i)) {
                    if (i == list.size() - 1) {
                        return true;
                    }
                } else {
                    break;
                }
            }
        }
        return false;
    }

    public List<List<Integer>> threeSumBruteForceCheckOrder(int[] nums) {
        List<Integer> numList = new ArrayList<>();
        for (int n : nums)
        {
            numList.add(n);
        }
        Collections.sort(numList);

        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < numList.size(); i++) {
            for (int j = i+1; j < numList.size(); j++) {
                for (int k = j+1; k < numList.size(); k++) {
                    if (numList.get(i) + numList.get(j) + numList.get(k) == 0) {
                        List<Integer> list = new LinkedList<>();
                        list.add(numList.get(i));
                        list.add(numList.get(j));
                        list.add(numList.get(k));
                        if (!exist(list, result)) {
                            result.add(list);
                        }
                    }
                }
            }
        }
        return result;
    }

    // https://leetcode.com/problems/3sum/discuss/7380/Concise-O(N2)-Java-solution
    /*
The idea is to sort an input array and then run through all indices of a possible first element of a triplet. For each possible first element we make a standard bi-directional 2Sum sweep of the remaining part of the array. Also we want to skip equal elements to avoid duplicates in the answer without making a set or smth like that.
     */
    public List<List<Integer>> threeSum(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < num.length-2; i++) {
            if (i == 0 || (i > 0 && num[i] != num[i-1])) {  // avoid duplicates
                int lo = i+1, hi = num.length-1, sum = 0 - num[i];
                while (lo < hi) {
                    if (num[lo] + num[hi] == sum) {
                        res.add(Arrays.asList(num[i], num[lo], num[hi]));

                        // avoid duplicates
                        while (lo < hi && num[lo] == num[lo+1]) {
                            lo++;
                        }
                        while (lo < hi && num[hi] == num[hi-1]) {
                            hi--;
                        }

                        // next
                        lo++; hi--;
                    } else if (num[lo] + num[hi] < sum) { // sum is too small, try bigger numbers
                        lo++;
                    }  else { // sum is too big, try smaller numbers
                        hi--;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Q0015_3Sum q = new Q0015_3Sum();
        int[] input = new int[] {-1, 0, 1, 2, -1, -4};
        // sorted: {-4, -1, -1, 0, 1, 2};
        //int[] input = new int[] {-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
        List<List<Integer>> lists = q.threeSum(input);
        for (List<Integer> list : lists) {
            System.out.print("list=");
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

    }
}
