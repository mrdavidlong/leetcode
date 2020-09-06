import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by davidlong on 7/8/18.
 */
public class Q0215_KthLargestElementInAnArray {

    public int findKthLargestWithSort(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public int findKthLargestWithPriorityQueue(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 小顶堆

        for (int val : nums) {
            pq.add(val);
            if (pq.size() > k) // 维护堆的大小为 K
                pq.poll();
        }
        return pq.peek();
    }

    public int findKthLargestWithPartition(int[] nums, int k) {
        int indexOfKthLargest = nums.length - k;
        int lo = 0, hi = nums.length - 1;

        while (lo < hi) {
            int partitionPoint = partition(nums, lo, hi);
            if (partitionPoint == indexOfKthLargest) {
                break;
            } else if (partitionPoint < indexOfKthLargest) {
                lo = partitionPoint + 1;
            } else {
                hi = partitionPoint - 1;
            }
        }
        return nums[indexOfKthLargest];
    }

//    private int partition2(int[] arr, int left, int right) {
//        int pivot = arr[(left + right) / 2]; // Pick a pivot point. Can be an element
//
//        while (left <= right) { // Until we've gone through the whole array
//            // Find element on left that should be on right
//            while (arr[left] < pivot) {
//                left++;
//            }
//
//            // Find element on right that should be on left
//            while (arr[right] > pivot) {
//                right--;
//            }
//
//            // Swap elements, and move left and right indices
//            if (left <= right) {
//                swap(arr, left, right);
//                left++;
//                right--;
//            }
//        }
//        return left - 1;
//    }

    private int partition(int[] a, int lo, int hi) {
        int i = lo, j = hi + 1; // j = hi + 1, since we are doing a[--j] below
        int pivot = a[lo];
        while (true) {
            // starts comparing with the number after the first one, thus ++i
            while (a[++i] < pivot && i < hi) ;
            while (a[--j] > pivot && j > lo) ;

            if (i >= j) {
                break;
            }

            swap(a, i, j);
        }
        // put the pivot from the first element to the partition point
        swap(a, lo, j);
        return j;
    }

    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        Q0215_KthLargestElementInAnArray sol = new Q0215_KthLargestElementInAnArray();

//        Example 1:
//
//        Input: [3,2,1,5,6,4] and k = 2
//        Output: 5

        int[] nums1 = {3,2,4,5,6};
        int k1 = 2;
        //int kthLargest1WithSort = sol.findKthLargestWithSort(nums1, k1);
        //int kthLargest1WithPQ = sol.findKthLargestWithPriorityQueue(nums1, k1);
        int kthLargest1 = sol.findKthLargestWithPartition(nums1, k1);

//
//        Example 2:
//
//        Input: [3,2,3,1,2,4,5,5,6] and k = 4
//        Output: 4
        int[] nums2 = {3,2,3,1,2,4,5,5,6};
        int k2 = 4;
        int kthLargest2 = sol.findKthLargestWithPartition(nums2, k2);

//        Example 3:
//
//        Input: [3,2,1,5,6,4] and k = 5
//        Output: 2
        int[] nums3 = {3,2,1,5,6,4};
        int k3 = 2;
        int kthLargest3 = sol.findKthLargestWithPartition(nums3, k3);

    }
}
