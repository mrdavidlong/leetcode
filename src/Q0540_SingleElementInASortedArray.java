/**
 * https://leetcode.com/problems/single-element-in-a-sorted-array/description/
 */
class Q0540_SingleElementInASortedArray {
    public int singleNonDuplicate(int[] nums) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (m % 2 == 1) {
                m--;   // 保证 l/h/m 都在偶数位，使得查找区间大小一直都是奇数
            }
            if (nums[m] == nums[m + 1]) {
                l = m + 2;
            } else {
                h = m;
            }
        }
        return nums[l];
    }

    public static void main(String[] args) {

//        Input: [1,1,2,3,3,4,4,8,8]
//        Output: 2
        Q0540_SingleElementInASortedArray sol = new Q0540_SingleElementInASortedArray();
        int[] a = {1,1,2,3,3,4,4,8,8};
        int index = sol.singleNonDuplicate(a);
    }
}
