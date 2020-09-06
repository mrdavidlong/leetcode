import java.util.*;

/*
https://leetcode.com/problems/top-k-frequent-elements/description/

 */
public class Q0347_TopKFrequentElements {
    public List<Integer> topKFrequentUseMoreMemory(int[] nums, int k) {
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
    public int[] topKFrequent(int[] nums, int k) {
        // O(1) time
        if (k == nums.length) {
            return nums;
        }

        // 1. build hash map : character and how often it appears
        // O(N) time
        Map<Integer, Integer> count = new HashMap();
        for (int n: nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // init heap 'the less frequent element first'
        Queue<Integer> heap = new PriorityQueue<>(
                (n1, n2) -> count.get(n1) - count.get(n2));

        // 2. keep k top frequent elements in the heap
        // O(N log k) < O(N log N) time
        for (int n: count.keySet()) {
            heap.add(n);
            if (heap.size() > k) heap.poll();
        }

        // 3. build an output array
        // O(k log k) time
        int[] top = new int[k];
        for(int i = k - 1; i >= 0; --i) {
            top[i] = heap.poll();
        }
        return top;
    }

    public static void main(String[] args) {
        /*
        Given [1,1,1,2,2,3] and k = 2, return [1,2].
         */

        Q0347_TopKFrequentElements sol = new Q0347_TopKFrequentElements();

        int[] nums = {1,1,1,2,2,3,3,4};
        int k = 2;

        int[] topKFrequentNumbers = sol.topKFrequent(nums, k);
    }
}
