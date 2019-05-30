/*

https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/description/
Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.

Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.

Could you do this in O(n) runtime?

Example:

Input: [3, 10, 5, 25, 2, 8]

Output: 28

Explanation: The maximum result is 5 ^ 25 = 28.
 */

import java.util.HashSet;
import java.util.Set;

public class Q0421_MaximumXorOfTwoNumbersInAnArray {
    // https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/discuss/91049/Java-O(n)-solution-using-bit-manipulation-and-HashMap
    /*
    I think most people who find it hard to understand the code is stuck on this line if(set.contains(tmp ^ prefix))
The tricky part here is that we need to be aware of a key property of XOR applying on the above line: if A ^ B = C, then A ^ B ^ B = C ^ B, then A = C ^ B
Before executing that line, max stands for the maximum we can get if we consider only the most significant i - 1 bits, tmp stands for the potential max value we can get when considering the most significant i bits. How can we get this tmp? The only way we can get this value is that we have two values A and B in the set (a set of most significant i bits of each member), such that A ^ B equals to tmp. As mentioned earlier, A ^ B = tmp is equivalent to A = tmp ^ B. Here is where that line comes in: set.contains(tmp ^ B).

BTW, though this is a great solution, it is actually faulty if the input contains negative numbers (though not required by the problem itself) as i starts from 31 instead of 30. It would be a perfect solution if the input is unsigned int instead.
     */
    public int findMaximumXORWithHashMap(int[] nums) {
        int max = 0, mask = 0;
        for(int i = 31; i >= 0; i--){
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();
            for(int num : nums){
                set.add(num & mask);
            }
            int tmp = max | (1 << i);
            for(int prefix : set){
                if(set.contains(tmp ^ prefix)) {
                    max = tmp;
                    break;
                }
            }
        }
        return max;
    }

    // https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/discuss/91059/Java-O(n)-solution-using-Trie?page=1
    class Trie {
        Trie[] children;
        public Trie() {
            children = new Trie[2];
        }
    }

    public int findMaximumXOR(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        // Init Trie.
        Trie root = new Trie();
        for(int num: nums) {
            Trie curNode = root;
            for(int i = 31; i >= 0; i --) {
                int curBit = (num >>> i) & 1;
                if(curNode.children[curBit] == null) {
                    curNode.children[curBit] = new Trie();
                }
                curNode = curNode.children[curBit];
            }
        }
        int max = Integer.MIN_VALUE;
        for(int num: nums) {
            Trie curNode = root;
            int curSum = 0;
            for(int i = 31; i >= 0; i --) {
                int curBit = (num >>> i) & 1;
                if(curNode.children[curBit ^ 1] != null) {
                    curSum += (1 << i);
                    curNode = curNode.children[curBit ^ 1];
                }else {
                    curNode = curNode.children[curBit];
                }
            }
            max = Math.max(curSum, max);
        }
        return max;
    }

    public static void main(String[] args) {
        Q0421_MaximumXorOfTwoNumbersInAnArray sol = new Q0421_MaximumXorOfTwoNumbersInAnArray();
        int maxXor = sol.findMaximumXOR(new int[] {3, 10, 5, 25, 2, 8});
    }
}
