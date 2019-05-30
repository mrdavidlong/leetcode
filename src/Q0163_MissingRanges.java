import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/missing-ranges/description/
 * Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

 Example:

 Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
 Output: ["2", "4->49", "51->74", "76->99"]

 */
public class Q0163_MissingRanges {
    // https://leetcode.com/problems/missing-ranges/discuss/50476/Accepted-Java-solution-with-explanation
    // add the code from comment to handle corner case
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();

        if(nums.length == 0) {
            res.add(generateRange(lower, upper));
            return res;
        }

        if(lower == Integer.MAX_VALUE) {
            return res;
        }

        int next = lower;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] < next)
                continue;

            if(nums[i] == next) {
                next = next+1;
                continue;
            }
            res.add(generateRange(next, nums[i]-1));

            //Overflow : no need to update and check, just return
            if(nums[i] == upper) {
                return res;
            }
            next = nums[i]+1;
        }

        //check if upper didn't overlap in the nums array
        if(next <= upper)
            res.add(generateRange(next, upper));

        return res;
    }

    private String generateRange(long start, long end) {
        if(start == end)
            return ""+start;
        else
            return start+"->"+end;
    }

    public static void main(String[] args) {
        Q0163_MissingRanges sol = new Q0163_MissingRanges();
        List<String> missingRanges = sol.findMissingRanges(new int[] {0, 1, 3, 50, 75}, 0, 99);
    }

}
