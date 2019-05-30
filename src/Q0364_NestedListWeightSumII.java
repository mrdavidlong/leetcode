import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/nested-list-weight-sum-ii/
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

Example 1:

Input: [[1,1],2,[1,1]]
Output: 8
Explanation: Four 1's at depth 1, one 2 at depth 2.
Example 2:

Input: [1,[4,[6]]]
Output: 17
Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17.
 */
public class Q0364_NestedListWeightSumII {
     // This is the interface that allows for creating nested lists.
     // You should not implement it, or speculate about its implementation
     public interface NestedInteger {
         // Constructor initializes an empty nested list.
         //public NestedInteger();

         // Constructor initializes a single integer.
         //public NestedInteger(int value);

         // @return true if this NestedInteger holds a single integer, rather than a nested list.
         public boolean isInteger();

         // @return the single integer that this NestedInteger holds, if it holds a single integer
         // Return null if this NestedInteger holds a nested list
         public Integer getInteger();

         // Set this NestedInteger to hold a single integer.
         public void setInteger(int value);

         // Set this NestedInteger to hold a nested list and adds a nested integer to it.
         public void add(NestedInteger ni);

         // @return the nested list that this NestedInteger holds, if it holds a nested list
         // Return null if this NestedInteger holds a single integer
         public List<NestedInteger> getList();
     }

     static class MyNestedInteger implements NestedInteger {
         public int value;
         public List<NestedInteger> list = new ArrayList<>();

         // Constructor initializes an empty nested list.
         public MyNestedInteger() {

         }

         // Constructor initializes a single integer.
         public MyNestedInteger(int value) {
             this.value = value;
         }

         // @return true if this NestedInteger holds a single integer, rather than a nested list.
         public boolean isInteger() {
             return (value != 0);
         }

         // @return the single integer that this NestedInteger holds, if it holds a single integer
         // Return null if this NestedInteger holds a nested list
         public Integer getInteger() {
             return value;
         }

         // Set this NestedInteger to hold a single integer.
         public void setInteger(int value) {
             this.value = value;
         }

         // Set this NestedInteger to hold a nested list and adds a nested integer to it.
         public void add(NestedInteger ni) {
             list.add(ni);
         }

         // @return the nested list that this NestedInteger holds, if it holds a nested list
         // Return null if this NestedInteger holds a single integer
         public List<NestedInteger> getList() {
             return list;
         }
     }

    /*
    https://leetcode.com/problems/nested-list-weight-sum-ii/discuss/83641/No-depth-variable-no-multiplication
    Inspired by lzb700m's solution and one of mine. Instead of multiplying by depth, add integers multiple times (by going level by level and adding the unweighted sum to the weighted sum after each level).
     */
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int unweighted = 0, weighted = 0;
        while (!nestedList.isEmpty()) {
            List<NestedInteger> nextLevel = new ArrayList<>();
            for (NestedInteger ni : nestedList) {
                if (ni.isInteger())
                    unweighted += ni.getInteger();
                else
                    nextLevel.addAll(ni.getList());
            }
            weighted += unweighted;
            nestedList = nextLevel;
        }
        return weighted;
    }

    public static void main(String[] args) {
        Q0364_NestedListWeightSumII sol = new Q0364_NestedListWeightSumII();
        List<NestedInteger> nestedList = new ArrayList<>();
        NestedInteger n1 = new MyNestedInteger();
        n1.add(new MyNestedInteger(1));
        n1.add(new MyNestedInteger(1));

        NestedInteger n2 = new MyNestedInteger(2);

        NestedInteger n3 = new MyNestedInteger();
        n3.add(new MyNestedInteger(1));
        n3.add(new MyNestedInteger(1));
        nestedList.add(n1);
        nestedList.add(n2);
        nestedList.add(n3);

        int result = sol.depthSumInverse(nestedList);


        List<NestedInteger> nestedList2 = new ArrayList<>();

        //NestedInteger list2n3 = new MyNestedInteger(6);
        NestedInteger list2n3 = new MyNestedInteger();
        list2n3.add(new MyNestedInteger(6));

        NestedInteger list2n2 = new MyNestedInteger();
        list2n2.add(new MyNestedInteger(4));
        list2n2.add(list2n3);

        NestedInteger list2n1 = new MyNestedInteger(1);

        nestedList2.add(list2n1);
        nestedList2.add(list2n2);

        int result2 = sol.depthSumInverse(nestedList2);

    }
}
