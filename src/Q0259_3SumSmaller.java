import java.util.Arrays;

/*
https://leetcode.com/problems/3sum-smaller/
Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

Example:

Input: nums = [-2,0,1,3], and target = 2
Output: 2
Explanation: Because there are two triplets which sums are less than 2:
             [-2,0,1]
             [-2,0,3]
Follow up: Could you solve it in O(n2) runtime?


*/
public class Q0259_3SumSmaller {

    /*
    https://leetcode.com/problems/3sum-smaller/solution/
    Approach #3 (Two Pointers) [Accepted]

Let us try sorting the array first. For example,
n
u
m
s
=
[
3
,
5
,
2
,
8
,
1
]
nums=[3,5,2,8,1] becomes
[
1
,
2
,
3
,
5
,
8
]
[1,2,3,5,8].

Let us look at an example
n
u
m
s
=
[
1
,
2
,
3
,
5
,
8
]
nums=[1,2,3,5,8], and
t
a
r
g
e
t
=
7
target=7.

[1, 2, 3, 5, 8]
 ↑           ↑
left       right
Let us initialize two indices,
l
e
f
t
left and
r
i
g
h
t
right pointing to the first and last element respectively.

When we look at the sum of first and last element, it is
1
+
8
=
9
1+8=9, which is
≥
t
a
r
g
e
t
≥target. That tells us no index pair will ever contain the index
r
i
g
h
t
right. So the next logical step is to move the right pointer one step to its left.

[1, 2, 3, 5, 8]
 ↑        ↑
left    right
Now the pair sum is
1
+
5
=
6
1+5=6, which is
<
t
a
r
g
e
t
<target. How many pairs with one of the
i
n
d
e
x
=
l
e
f
t
index=left that satisfy the condition? You can tell by the difference between
r
i
g
h
t
right and
l
e
f
t
left which is
3
3, namely
(
1
,
2
)
,
(
1
,
3
)
,
(1,2),(1,3), and
(
1
,
5
)
(1,5). Therefore, we move
l
e
f
t
left one step to its right.
     */
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            sum += twoSumSmaller(nums, i + 1, target - nums[i]);
        }
        return sum;
    }

    private int twoSumSmaller(int[] nums, int startIndex, int target) {
        int sum = 0;
        int left = startIndex;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                sum += right - left;
                left++;
            } else {
                right--;
            }
        }
        return sum;
    }
}
