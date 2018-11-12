import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/find-all-duplicates-in-an-array/description/
 *
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

 Find all the elements that appear twice in this array.

 Could you do it without extra space and in O(n) runtime?

 Example:

 Input:
 [4,3,2,7,8,2,3,1]

 Output:
 [2,3]
 */
public class Q0442_FindAllDuplicatesInAnArray {

    //https://leetcode.com/problems/find-all-duplicates-in-an-array/discuss/92387/Java-Simple-Solution
    // when find a number i, flip the number at position i-1 to negative.
    // if the number at position i-1 is already negative, i is the number that occurs twice.

    public List<Integer> findDuplicates(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            int index = Math.abs(nums[i])-1;
            if (nums[index] < 0)
                set.add(Math.abs(index+1));
            else
                nums[index] = -nums[index];
        }
        List<Integer> list = new ArrayList<>(set);
        return list;
    }

    public static void main(String[] args) {
        Q0442_FindAllDuplicatesInAnArray sol = new Q0442_FindAllDuplicatesInAnArray();

        List<Integer> dups = sol.findDuplicates(new int[] {4,3,2,7,8,2,3,1});
        List<Integer> dups2 = sol.findDuplicates(new int[] {2,3,2,2,3,2,3,1});
        List<Integer> dups3 = sol.findDuplicates(new int[] {1, 2, 9, 1, 3, 6, 6,9, 2});
    }
}
