import java.util.ArrayList;
import java.util.List;

/**
 *
 * https://leetcode.com/problems/palindrome-partitioning/description/
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#backtracking
 Given a string s, partition s such that every substring of the partition is a palindrome.

 Return all possible palindrome partitioning of s.

 Example:

 Input: "aab"
 Output:
 [
 ["aa","b"],
 ["a","a","b"]
 ]
 */
public class Q0131_PalindromePartitioning {
//    public List<List<String>> partition(String s) {
//        List<List<String>> result = new ArrayList<>();
//        partitionCore(result, new ArrayList<>(), s, 0);
//        return result;
//    }
//
//    public void partitionCore(List<List<String>> result, List<String> tempList, String s, int start){
//        if (start == s.length()) {
//            result.add(new ArrayList<>(tempList));
//            return;
//        }
//
//        for (int i = start; i < s.length(); i++) {
//            if (isPalindrome(s, start, i)) {
//                tempList.add(s.substring(start, i + 1));
//                partitionCore(result, tempList, s, i + 1);
//                tempList.remove(tempList.size() - 1);
//            }
//        }
//    }
//
//    public boolean isPalindrome(String s, int start, int end){
//        while (start < end) {
//            if (s.charAt(start) != s.charAt(end)) {
//                return false;
//            }
//            start++;
//            end--;
//        }
//        return true;
//    }

    // https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)#_=_
    public List<List<String>> partition(String s) {
        List<List<String>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), s, 0);
        return list;
    }

    public void backtrack(List<List<String>> list, List<String> tempList, String s, int start){
        if (start == s.length())
            list.add(new ArrayList<>(tempList));
        else {
            for (int i = start; i < s.length(); i++){
                if (isPalindrome(s, start, i)){
                    tempList.add(s.substring(start, i + 1));
                    backtrack(list, tempList, s, i + 1);
                    tempList.remove(tempList.size() - 1);
                }
            }
        }
    }

    public boolean isPalindrome(String s, int low, int high){
        while (low < high)
            if (s.charAt(low++) != s.charAt(high--)) return false;
        return true;
    }

    public static void main(String[] args) {
        Q0131_PalindromePartitioning sol = new Q0131_PalindromePartitioning();

        String s = "aab";
        List<List<String>> partitions = sol.partition(s);
        for (List<String> list : partitions) {
            for (String str : list) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
    }
}
