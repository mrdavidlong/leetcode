/**
 * Created by davidlong on 7/1/18.
 */
public class Q0027_RemoveElement {

    public int removeElement(int[] nums, int val) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {0,1,2,2,3,0,4,2};
        int val = 2;
        Q0027_RemoveElement sol = new Q0027_RemoveElement();
        int len = sol.removeElement(nums, val);

        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
