import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Q0350_IntersectionOfTwoArraysII {
    public int[] intersectSort(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        List<Integer> resultList = new LinkedList<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                resultList.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]){
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            }
        }

        int[] result = new int[resultList.size()];
        int index = 0;
        for (Integer x : resultList) {
            result[index++] = x;
        }

        return result;
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int x : nums1) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        List<Integer> resultList = new LinkedList<>();
        for (int y : nums2) {
            if (map.getOrDefault(y, 0) > 0) {
                map.put(y, map.getOrDefault(y, 0) - 1);
                if (map.getOrDefault(y, 0) == 0) {
                    map.remove(y);
                }
                resultList.add(y);
            }
        }

        int[] result = new int[resultList.size()];
        int index = 0;
        for (Integer x : resultList) {
            result[index++] = x;
        }

        return result;
    }

    public static void main(String[] args) {
        Q0350_IntersectionOfTwoArraysII sol = new Q0350_IntersectionOfTwoArraysII();
        //int[] intersection = sol.intersect(new int[] {1,2,2,1}, new int[] {2,2});
        int[] nums1 = new int[] {4,9,5,4,4,};
        int[] nums2 = new int[] {9,4,9,8,4};
        int[] intersection2 = sol.intersect(nums1, nums2);
        int[] intersection3 = sol.intersectSort(new int[] {4,9,5,4,4,}, new int[] {9,4,9,8,4});
    }

}
