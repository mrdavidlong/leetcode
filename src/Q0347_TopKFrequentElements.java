import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
https://leetcode.com/problems/top-k-frequent-elements/description/

 */
public class Q0347_TopKFrequentElements {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        List<Integer>[] bucket = new List[nums.length + 1]; // This is an array of ArrayList instead of an array of int, because there can be more than one integer with the same frequency

        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        List<Integer> result = new ArrayList<>();
        // iterate from the most frequent number to less frequent, i.e. bottom of the bucket
        for (int pos = bucket.length-1; pos >= 0; pos--) {
            if (bucket[pos] != null) {
                for (int i = 0; i < bucket[pos].size(); i++) {
                    result.add(bucket[pos].get(i));

                    // found the top K frequent numbers, just return the results
                    if (result.size() == k) {
                        return result;
                    }
                }
            }
        }
        return result;
    }


    //https://leetcode.com/problems/top-k-frequent-elements/solution/#_=_
    public List<Integer> topKFrequentOfficial(int[] nums, int k) {
        // build hash map : character and how often it appears
        HashMap<Integer, Integer> count = new HashMap();
        for (int n: nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // init heap 'the less frequent element first'
        PriorityQueue<Integer> heap =
                new PriorityQueue<Integer>((n1, n2) -> count.get(n1) - count.get(n2));

        // keep k top frequent elements in the heap
        for (int n: count.keySet()) {
            heap.add(n);
            if (heap.size() > k)
                heap.poll();
        }

        // build output list
        List<Integer> top_k = new LinkedList<>();
        while (!heap.isEmpty())
            top_k.add(heap.poll());
        Collections.reverse(top_k);
        return top_k;
    }

    public static void main(String[] args) {
        /*
        Given [1,1,1,2,2,3] and k = 2, return [1,2].
         */

        Q0347_TopKFrequentElements sol = new Q0347_TopKFrequentElements();

        int[] nums = {1,1,1,2,2,3,3,4};
        int k = 2;

        List<Integer> topKFrequentNumbers = sol.topKFrequent(nums, k);
    }
}
