import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/nested-list-weight-sum/
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:

Input: [[1,1],2,[1,1]]
Output: 10
Explanation: Four 1's at depth 2, one 2 at depth 1.
Example 2:

Input: [1,[4,[6]]]
Output: 27
Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27.

 */
public class Q0339_NestedListWeightSum {
    /**
     // This is the interface that allows for creating nested lists.
     // You should not implement it, or speculate about its implementation
     */
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

     // https://leetcode.com/problems/nested-list-weight-sum/discuss/79957/2ms-easy-to-understand-java-solution
    public int depthSum(List<NestedInteger> nestedList) {
        return helper(nestedList, 1);
    }

    private int helper(List<NestedInteger> list, int depth) {
        int ret = 0;
        for (NestedInteger e: list) {
            ret += e.isInteger()? e.getInteger() * depth: helper(e.getList(), depth + 1);
        }
        return ret;
    }

    public static void main(String[] args) {
        Q0339_NestedListWeightSum sol = new Q0339_NestedListWeightSum();

        NestedInteger n1 = new MyNestedInteger();
        n1.add(new MyNestedInteger(1));
        n1.add(new MyNestedInteger(1));

        NestedInteger n2 = new MyNestedInteger(2);

        NestedInteger n3 = new MyNestedInteger();
        n3.add(new MyNestedInteger(1));
        n3.add(new MyNestedInteger(1));

        List<NestedInteger> nestedList = new ArrayList<>();
        nestedList.add(n1);
        nestedList.add(n2);
        nestedList.add(n3);

        int result = sol.depthSum(nestedList);


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

        int result2 = sol.depthSum(nestedList2);

    }
}
