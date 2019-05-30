import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/jewels-and-stones/
You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.

The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so "a" is considered a different type of stone from "A".

Example 1:

Input: J = "aA", S = "aAAbbbb"
Output: 3
Example 2:

Input: J = "z", S = "ZZ"
Output: 0
 */
public class Q0771_JewelAndStones {
    // https://leetcode.com/problems/jewels-and-stones/discuss/113553/C%2B%2BJavaPython-Set-Solution-O(M%2BN)
    public int numJewelsInStones(String J, String S) {
        int num = 0;
        Set<Character> set = new HashSet<>();
        for (char j : J.toCharArray()) set.add(j);
        for (char s : S.toCharArray()) if (set.contains(s)) num++;
        return num;
    }

    public static void main(String[] args) {
        Q0771_JewelAndStones sol = new Q0771_JewelAndStones();
        int numJewels = sol.numJewelsInStones("aA","aAAbbbb");
    }
}
