/*
https://leetcode.com/problems/android-unlock-patterns/

Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.



Rules for a valid pattern:

Each pattern must connect at least m keys and at most n keys.
All the keys must be distinct.
If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
The order of keys used matters.





Explanation:

| 1 | 2 | 3 |
| 4 | 5 | 6 |
| 7 | 8 | 9 |
Invalid move: 4 - 1 - 3 - 6
Line 1 - 3 passes through key 2 which had not been selected in the pattern.

Invalid move: 4 - 1 - 9 - 2
Line 1 - 9 passes through key 5 which had not been selected in the pattern.

Valid move: 2 - 4 - 1 - 3 - 6
Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern

Valid move: 6 - 5 - 4 - 1 - 9 - 2
Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.



Example:

Input: m = 1, n = 1
Output: 9

https://leetcode.com/problems/android-unlock-patterns/solution/

Algorithm

The algorithm uses backtracking technique to enumerate all possible
k
k combinations of numbers
[
1
…
9
]
[1…9] where
m
≤
k
≤
n
m≤k≤n. During the generation of the recursive solution tree, the algorithm cuts all the branches which lead to patterns which doesn't satisfy the rules and counts only the valid patterns. In order to compute a valid pattern, the algorithm performs the following steps:

Select a digit
i
i which is not used in the pattern till this moment. This is done with the help of a
u
s
e
d
used array which stores all available digits.

We need to keep last inserted digit
l
a
s
t
last. The algorithm makes a check whether one of the following conditions is valid.

There is a knight move (as in chess) from
l
a
s
t
last towards
i
i or
l
a
s
t
last and
i
i are adjacent digits in a row, in a column. In this case the sum of both digits should be an odd number.

The middle element
m
i
d
mid in the line which connects
i
i and
l
a
s
t
last was previously selected. In case
i
i and
l
a
s
t
last are positioned at both ends of the diagonal, digit
m
i
d
mid = 5 should be previously selected.

l
a
s
t
last and
i
i are adjacent digits in a diagonal

In case one of the conditions above is satisfied, digit
i
i becomes part of partially generated valid pattern and the algorithm continues with the next candidate digit till the pattern is fully generated. Then it counts it. In case none of the conditions are satisfied, the algorithm rejects the current digit
i
i, backtracks and continues to search for other valid digits among the unused ones.


 */
public class Q0351_AndroidUnlockPatterns {
    private boolean used[] = new boolean[9];

    public int numberOfPatterns(int m, int n) {
        int res = 0;
        for (int len = m; len <= n; len++) {
            res += calcPatterns(-1, len);
            for (int i = 0; i < 9; i++) {
                used[i] = false;
            }
        }
        return res;
    }

    private boolean isValid(int index, int last) {
        if (used[index])
            return false;
        // first digit of the pattern
        if (last == -1)
            return true;
        // knight moves or adjacent cells (in a row or in a column)
        if ((index + last) % 2 == 1)
            return true;
        // indexes are at both end of the diagonals for example 0,0, and 8,8
        int mid = (index + last)/2;
        if (mid == 4)
            return used[mid];
        // adjacent cells on diagonal  - for example 0,0 and 1,0 or 2,0 and //1,1
        if ((index%3 != last%3) && (index/3 != last/3)) {
            return true;
        }
        // all other cells which are not adjacent
        return used[mid];
    }

    private int calcPatterns(int last, int len) {
        if (len == 0)
            return 1;
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            if (isValid(i, last)) {
                used[i] = true;
                sum += calcPatterns(i, len - 1);
                used[i] = false;
            }
        }
        return sum;
    }

}
