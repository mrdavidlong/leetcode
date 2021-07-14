public class Q0724_Find_Pivot_Index {
    public int pivotIndex(int[] nums) {
        int sum = 0, leftsum = 0;
        for (int x: nums) sum += x;
        for (int i = 0; i < nums.length; ++i) {
            if (leftsum == sum - leftsum - nums[i]) return i;
            leftsum += nums[i];
        }
        return -1;
    }

    public static void main(String[] args) {
        Q0724_Find_Pivot_Index sol = new Q0724_Find_Pivot_Index();
        int index = sol.pivotIndex(new int[] {1,7,3,6,5,6});
        int index2 = sol.pivotIndex(new int[] {1,2,3});
        int index3 = sol.pivotIndex(new int[] {2,1,-1});
    }
}
