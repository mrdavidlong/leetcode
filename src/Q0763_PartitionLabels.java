import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidlong on 7/5/18.
 */
public class Q0763_PartitionLabels {
    //https://github.com/CyC2018/Interview-Notebook/blob/master/notes/Leetcode%20题解.md#算法思想
    public List<Integer> partitionLabels(String S) {
        int[] lastIndexsOfChar = new int[26];
        for (int i = 0; i < S.length(); i++) {
            lastIndexsOfChar[char2Index(S.charAt(i))] = i;
        }
        List<Integer> partitions = new ArrayList<>();
        int firstIndex = 0;
        while (firstIndex < S.length()) {
            int lastIndex = firstIndex;
            for (int i = firstIndex; i < S.length() && i <= lastIndex; i++) {
                int index = lastIndexsOfChar[char2Index(S.charAt(i))];
                if (index > lastIndex) {
                    lastIndex = index;
                }
            }
            partitions.add(lastIndex - firstIndex + 1);
            firstIndex = lastIndex + 1;
        }
        return partitions;
    }

    private int char2Index(char c) {
        return c - 'a';
    }

    public static void main(String[] args) {
        Q0763_PartitionLabels sol = new Q0763_PartitionLabels();

        String s = "ababcbacadefegdehijhklij";
        List<Integer> partitions = sol.partitionLabels(s);
    }

}
