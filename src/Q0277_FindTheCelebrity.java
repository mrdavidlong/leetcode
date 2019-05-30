/*
https://leetcode.com/problems/find-the-celebrity/
Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.

Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.
 */
// BAD QUESTION
public class Q0277_FindTheCelebrity {
    /*
    https://leetcode.com/problems/find-the-celebrity/discuss/71227/Java-Solution.-Two-Pass
    The first pass is to pick out the candidate. If candidate knows i, then switch candidate. The second pass is to check whether the candidate is real.

    public class Solution extends Relation {
        public int findCelebrity(int n) {
            int candidate = 0;
            for(int i = 1; i < n; i++){
                if(knows(candidate, i))
                    candidate = i;
            }
            for(int i = 0; i < n; i++){
                if(i != candidate && (knows(candidate, i) || !knows(i, candidate))) return -1;
            }
            return candidate;
        }
    }
     */
    public static class Relation {

        private int[][] graph;

        Relation(int[][] graphInput) {
            graph = graphInput;
        }
        
        boolean knows(int i, int j) {
            return ( graph[i][j]  == 1);
        }
    }

    public static int findCelebrity(int n, Relation relation) {
        int candidate = 0;
        for (int i = 1; i < n; i++){
            if (relation.knows(candidate, i))
                candidate = i;
        }
        for (int i = 0; i < n; i++){
            if (i != candidate && (relation.knows(candidate, i) || !relation.knows(i, candidate))) return -1;
        }
        return candidate;
    }

    public static void main(String[] args) {
        Relation relation = new Relation(new int[][] {
            {1,1,0},
            {0,1,0},
            {1,1,1}
        });

        int celeb = findCelebrity(3, relation);
    }
}
