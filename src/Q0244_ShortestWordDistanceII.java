import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
https://leetcode.com/problems/shortest-word-distance-ii/
Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list. Your method will be called repeatedly many times with different parameters.

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “coding”, word2 = “practice”
Output: 3
Input: word1 = "makes", word2 = "coding"
Output: 1
Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.


 */
public class Q0244_ShortestWordDistanceII {
    /**
     * Your WordDistance object will be instantiated and called as such:
     * WordDistance obj = new WordDistance(words);
     * int param_1 = obj.shortest(word1,word2);
     */

    /*
    https://leetcode.com/problems/shortest-word-distance-ii/discuss/67028/Java-Solution-using-HashMap
     */
    public static class WordDistance {

        private Map<String, List<Integer>> map;

        public WordDistance(String[] words) {
            map = new HashMap<>();
            for(int i = 0; i < words.length; i++) {
                String w = words[i];
                if(map.containsKey(w)) {
                    map.get(w).add(i);
                } else {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(i);
                    map.put(w, list);
                }
            }
        }

        public int shortest(String word1, String word2) {
            List<Integer> list1 = map.get(word1);
            List<Integer> list2 = map.get(word2);
            int ret = Integer.MAX_VALUE;
            for(int i = 0, j = 0; i < list1.size() && j < list2.size(); ) {
                int index1 = list1.get(i), index2 = list2.get(j);
                if(index1 < index2) {
                    ret = Math.min(ret, index2 - index1);
                    i++;
                } else {
                    ret = Math.min(ret, index1 - index2);
                    j++;
                }
            }
            return ret;
        }
    }

    public static void main(String[] args) {
        Q0244_ShortestWordDistanceII sol = new Q0244_ShortestWordDistanceII();
        WordDistance wordDistance = new WordDistance(new String[] {"practice", "makes", "perfect", "coding", "makes"});
        int distance1 = wordDistance.shortest("coding", "practice");
        int distance2 = wordDistance.shortest("makes", "coding");
    }
}
