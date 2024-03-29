/*
https://leetcode.com/problems/counting-bits/

Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

Example 1:

Input: 2
Output: [0,1,1]
Example 2:

Input: 5
Output: [0,1,1,2,1,2]
Follow up:

It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.

 */
public class Q0338_CountingBits {
    // https://leetcode.com/problems/counting-bits/discuss/79539/Three-Line-Java-Solution
    // f[i >> 1] is number of 1's but not counting the last digit
    public int[] countBits(int num) {
        int[] f = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            f[i] = f[i >> 1] + (i & 1);
        }
        return f;
    }

    public static void main(String[] args) {
        Q0338_CountingBits sol = new Q0338_CountingBits();
        int[] result = sol.countBits(5);
    }
}
