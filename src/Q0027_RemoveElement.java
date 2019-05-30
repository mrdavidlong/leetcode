/**
 * https://leetcode.com/problems/remove-element/
 */
public class Q0027_RemoveElement {

//    public int removeElement(int[] nums, int val) {
//        int j = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] != val) {
//                nums[j] = nums[i];
//                j++;
//            }
//        }
//        return j;
//    }

    public int removeElement(int[] nums, int val) {
        int j = 0;
        for (int n : nums) {
            if (n != val) {
                nums[j++] = n;
            }
        }
        return j;
    }

    public static void main(String[] args) {
        Q0027_RemoveElement sol = new Q0027_RemoveElement();

        int[] nums = new int[] {0,1,2,2,3,0,4,2};
        int val = 2;
        int len = sol.removeElement(nums, val);

        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
