import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 */
public class Q0030_SubstringWithConcatenationOfAllWords {
    private static List<String> getPerms(String[] words) {
        int totalLength = 0;
        for (String w : words) {
            totalLength += w.length();
        }

        Set<String> set = new HashSet<>();
        ArrayList<String> wordList = new ArrayList<>(Arrays.asList(words));
        String s = "";
        getPermsCore(wordList, s, totalLength, set);
        return new ArrayList<>(set);
    }

    private static void getPermsCore(List<String> words, String s, int totalLength, Set<String> set) {

        for (int i = 0; i < words.size(); i++) {
            // add word at i
            String word = words.get(i);
            s += word;

            if (s.length() == totalLength) {
                set.add(s);
            } else {
                List<String> otherWords = new ArrayList<>();
                List<String> beforeWords = words.subList(0, i);
                List<String> afterWords = words.subList(i + 1, words.size());
                otherWords.addAll(beforeWords);
                otherWords.addAll(afterWords);

                getPermsCore(otherWords, s, totalLength, set);
            }

            // remove word at i
            s = s.substring(0, s.length() - word.length());
        }
    }


    public List<Integer> findSubstringBruteForce(String s, String[] words) {
        List<Integer> indices = new ArrayList<>();
        List<String> perms = getPerms(words);
        for (String pattern : perms) {
            int searchStartIndex = 0;
            while (searchStartIndex < s.length()) {
                int matchIndex = s.indexOf(pattern, searchStartIndex);
                if (matchIndex >= 0) {
                    indices.add(matchIndex);
                    searchStartIndex = matchIndex + 1;
                }  else {
                    searchStartIndex = s.length();
                }
            }
        }
        Collections.sort(indices);
        return indices;
    }


//    public List<Integer> findSubstring(String s, String[] words) {
//        HashMap<String, List<Integer>> map = new HashMap<>();
//        for (String word : words) {
//            List<Integer> indices = indicesOfAWord(s, word);
//            if (indices.size() == 0) return new ArrayList<>();
//
//            map.put(word, indices);
//        }
//        int firstIndex = s.indexOf(words[0]);
//
//
//        return indices;
//    }



    private List<Integer> indicesOfAWord(String s, String word) {
        List<Integer> indices = new ArrayList<>();
        int searchStartIndex = 0;
        while (searchStartIndex < s.length()) {
            int matchIndex = s.indexOf(word, searchStartIndex);
            if (matchIndex >= 0) {
                indices.add(matchIndex);
                searchStartIndex = matchIndex + 1;
            }  else {
                searchStartIndex = s.length();
            }
        }
        return indices;
    }

    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> indices = new ArrayList<>();
        if (s == null || s.isEmpty() || words == null || words.length == 0) return indices;

        HashMap<Integer, String> map = new HashMap<>();
        for (String word : words) {
            List<Integer> indicesOfWord = indicesOfAWord(s, word);
            if (indicesOfWord.size() == 0) return new ArrayList<>();

            for (Integer index : indicesOfWord) {
                map.put(index, word);
            }
        }

        Set<Integer> indicesSet = map.keySet();
        List<Integer> indicesList = new ArrayList<>(indicesSet);
        Collections.sort(indicesList);
        for (int i = 0; i < indicesList.size(); i++) {
            List<String> wordsClone = new ArrayList(Arrays.asList(words));
            int prevWordIndex = -1;
            for (int j = i; j < indicesList.size() && !wordsClone.isEmpty(); j++) {
                int curWordIndex = indicesList.get(j);
                if (prevWordIndex >= 0) {
                    int gapFromPrev = curWordIndex - prevWordIndex;
                    if (gapFromPrev < words[0].length()) {
                        continue;
                    }
                    if (gapFromPrev > words[0].length()) {
                        break;
                    }
                }

                String foundWord = map.get(curWordIndex);
                if (wordsClone.contains(foundWord)) {
                    wordsClone.remove(foundWord);
                } else {
                    break;
                }

                if (wordsClone.isEmpty()) {
                    indices.add(indicesList.get(i));
                    break;
                }

                prevWordIndex = curWordIndex;
            }
        }

        return indices;
    }

    public List<Integer> findSubstringError(String s, String[] words) {
        final Map<String, Integer> counts = new HashMap<>();
        for (final String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }

        final List<Integer> indexes = new ArrayList<>();
        final int sLength = s.length(), numOfWords = words.length, wordLength = words[0].length();
        for (int i = 0; i < sLength - numOfWords * wordLength + 1; i++) {
            final Map<String, Integer> seen = new HashMap<>();
            int j = 0;
            while (j < numOfWords) {
                final String word = s.substring(i + j * wordLength, i + (j + 1) * wordLength);
                if (counts.containsKey(word)) {
                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    if (seen.get(word) > counts.getOrDefault(word, 0)) {
                        break;
                    }
                } else {
                    break;
                }
                j++;
            }
            if (j == numOfWords) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new LinkedList<>();
        if (s.isEmpty() || s == null || words.length == 0) return res;
        int wl = words[0].length(), n = s.length(), m = words.length;
        HashMap<String, Integer> map = new HashMap<>();

        for (String w : words)
            map.put(w, map.getOrDefault(w, 0) + 1);

        for (int i = 0; i < wl; i++) {
            HashMap<String, Integer> temp = new HashMap<>();
            int count = 0;
            int lo = i;
            for (int hi = i; hi + wl <= n; hi += wl) {
                String sHi = s.substring(hi, hi + wl);
                if (map.containsKey(sHi)) {
                    temp.put(sHi, temp.getOrDefault(sHi, 0) + 1);
                    count++;
                    // remove repeated words (including the beginning words), since the requirement is that each word can only appear once
                    while (temp.get(sHi) > map.get(sHi)) {
                        String sLo = s.substring(lo, lo + wl);
                        temp.put(sLo, temp.get(sLo) - 1);
                        count--;
                        lo += wl;
                    }
                    // check if we found all words, if so, remove the first word
                    if (count == m) {
                        res.add(lo);
                        String sLo = s.substring(lo, lo + wl);
                        temp.put(sLo, temp.get(sLo) - 1);
                        count--;
                        lo += wl;
                    }
                } else {
                    temp.clear();
                    count = 0;
                    lo = hi + wl;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Q0030_SubstringWithConcatenationOfAllWords sol = new Q0030_SubstringWithConcatenationOfAllWords();
        
        String s1 = "barfoothefoobarman";
        String[] words1 = new String[] {"foo","bar","car"};
        List<Integer> positions1 = sol.findSubstring(s1, words1);
        for (Integer p : positions1) {
            System.out.println(p + " ");
        }


        String s2 = "wordgoodgoodgoodbestword";
        String[] words2 = new String[] {"word","good","best","word"};
        List<Integer> positions2 = sol.findSubstring(s2, words2);
        for (Integer p : positions2) {
            System.out.println(p + " ");
        }

        String s3 = "aaaaaa";
        String[] words3 = new String[] {"aaa","aaa"};
        List<Integer> positions3 = sol.findSubstring(s3, words3);
        for (Integer p : positions3) {
            System.out.println(p + " ");
        }
    }
}
