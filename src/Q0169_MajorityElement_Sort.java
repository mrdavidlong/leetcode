import java.util.Arrays;

/**
 * Created by davidlong on 9/15/18.
 */
public class Q0169_MajorityElement_Sort {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public static void main(String[] args) {
        Q0169_MajorityElement_Sort sol = new Q0169_MajorityElement_Sort();
        int majority = sol.majorityElement(new int[]{2, 2, 1, 3, 2, 1, 2});
    }
}
