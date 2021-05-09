import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by davidlong on 6/30/18.
 * https://leetcode.com/problems/4sum/discuss/8575/Clean-accepted-java-O(n3)-solution-based-on-3sum
 */
public class Q0018_4Sum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }
    public List<List<Integer>> kSum(int[] nums, int target, int start, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (start == nums.length || nums[start] * k > target || target > nums[nums.length - 1] * k)
            return res;
        if (k == 2)
            return twoSum(nums, target, start);
        for (int i = start; i < nums.length; ++i)
            if (i == start || nums[i - 1] != nums[i])
                for (List<Integer> set : kSum(nums, target - nums[i], i + 1, k - 1)) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i])));
                    res.get(res.size() - 1).addAll(set);
                }
        return res;
    }
    public List<List<Integer>> twoSum(int[] nums, int target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        int lo = start, hi = nums.length - 1;
        while (lo < hi) {
            int sum = nums[lo] + nums[hi];
            if (sum < target || (lo > start && nums[lo] == nums[lo - 1]))
                ++lo;
            else if (sum > target || (hi < nums.length - 1 && nums[hi] == nums[hi + 1]))
                --hi;
            else
                res.add(Arrays.asList(nums[lo++], nums[hi--]));
        }
        return res;
    }

    public List<List<Integer>> fourSumFixed(int[] num, int target) {
        ArrayList<List<Integer>> ans = new ArrayList<>();
        if (num.length < 4) return ans;
        Arrays.sort(num);
        for (int i=0; i < num.length-3; i++){
            if (num[i] + num[i+1] + num[i+2] + num[i+3] > target) break; //first candidate too large, search finished
            if (num[i] + num[num.length-1] + num[num.length-2] + num[num.length-3] < target) continue; //first candidate too small
            if (i > 0 && num[i] == num[i-1]) continue; //prevents duplicate result in ans list
            for (int j = i+1; j < num.length-2; j++){
                if (num[i] + num[j] + num[j+1] + num[j+2] > target) break; //second candidate too large
                if (num[i] + num[j] + num[num.length-1] + num[num.length-2] < target) continue; //second candidate too small
                if (j > i + 1 && num[j] == num[j-1]) continue; //prevents duplicate results in ans list
                int low = j + 1, high = num.length - 1;
                while (low < high) {
                    int sum = num[i] + num[j] + num[low] + num[high];
                    if (sum == target){
                        ans.add(Arrays.asList(num[i], num[j], num[low], num[high]));
                        while(low < high && num[low] == num[low+1]) low++; //skipping over duplicate on low
                        while(low < high && num[high] == num[high-1]) high--; //skipping over duplicate on high
                        low++;
                        high--;
                    }
                    //move window
                    else if (sum < target) low++;
                    else high--;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Q0018_4Sum q = new Q0018_4Sum();
        int[] input = new int[] {1, 0, -1, 0, -2, 2};
        List<List<Integer>> output = q.fourSum(input, 0);
        output.forEach(list -> {
            list.forEach(i -> System.out.print(i + " "));
            System.out.println();
        });
    }

}
