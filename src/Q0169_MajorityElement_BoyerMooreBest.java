/**
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#多数投票问题
 */
public class Q0169_MajorityElement_BoyerMooreBest {
    public int majorityElement(int[] nums) {
        int cnt = 0, majority = nums[0];
        for (int num : nums) {
            majority = (cnt == 0) ? num : majority;
            cnt = (majority == num) ? cnt + 1 : cnt - 1;
        }
        return majority;
    }

    public static void main(String[] args) {
        Q0169_MajorityElement_BoyerMooreBest sol = new Q0169_MajorityElement_BoyerMooreBest();
        int majority = sol.majorityElement(new int[]{2, 2, 1, 3, 4, 2, 1, 2});
    }
}
