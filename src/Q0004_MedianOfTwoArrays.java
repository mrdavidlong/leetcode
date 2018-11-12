/**
 * Created by davidlong on 6/16/18.
 */
public class Q0004_MedianOfTwoArrays {

    public static void copyRemaining(int[] source, int[] destination, int sourceIndex, int destIndex) {
        if (destIndex < destination.length && sourceIndex < source.length) {
            int remaining = destination.length - destIndex;
            for (int i = 0; i < remaining; i++) {
                destination[destIndex + i] = source[sourceIndex + i];
            }
        }
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double median = 0.0;
        int index1 = 0;
        int index2 = 0;
        int totalLength = nums1.length + nums2.length;

        int[] numsMerged = new int[totalLength];
        int indexMerged = 0;

        while (index1 < nums1.length && index2 < nums2.length && indexMerged < totalLength) {
            if (nums1[index1] <= nums2[index2]) {
                numsMerged[indexMerged] = nums1[index1];
                index1++;
            } else {
                numsMerged[indexMerged] = nums2[index2];
                index2++;
            }
            indexMerged++;
        }

        copyRemaining(nums1, numsMerged, index1, indexMerged);
        copyRemaining(nums2, numsMerged, index2, indexMerged);

        if (totalLength % 2 == 0) {
            median = (numsMerged[totalLength/2] + numsMerged[(totalLength/2) - 1])/2.0;
        } else {
            median = numsMerged[totalLength/2];
        }

        return median;
    }

//
//    public static void copyRemainingUntil(int[] source, int[] destination, int sourceIndex, int destIndex) {
//        if (destIndex < destination.length && sourceIndex < source.length) {
//            //int remaining = source.length - sourceIndex;
//            int remaining = destination.length - destIndex;
//            for (int i = 0; i < remaining; i++) {
//                destination[destIndex + i] = source[sourceIndex + i];
//            }
//        }
//    }

    public static double findMedianSortedArraysBetter(int[] nums1, int[] nums2) {
        double median = 0.0;
        int index1 = 0;
        int index2 = 0;
        int totalLength = nums1.length + nums2.length;
        int mergedLength = (totalLength / 2) + 1;

        int[] numsMerged = new int[mergedLength];
        int indexMerged = 0;

        while (index1 < nums1.length && index2 < nums2.length && indexMerged < mergedLength) {
            if (nums1[index1] <= nums2[index2]) {
                numsMerged[indexMerged] = nums1[index1];
                index1++;
            } else {
                numsMerged[indexMerged] = nums2[index2];
                index2++;
            }
            indexMerged++;
        }

        copyRemaining(nums1, numsMerged, index1, indexMerged);
        copyRemaining(nums2, numsMerged, index2, indexMerged);

        if (totalLength % 2 == 0) {
            median = (numsMerged[mergedLength - 1] + numsMerged[mergedLength - 2])/2.0;
        } else {
            median = numsMerged[mergedLength - 1];
        }

        return median;
    }

    public static double findMedianSortedArraysBest(int[] nums1, int[] nums2) {
        double median = 0.0;
        int index1 = 0;
        int index2 = 0;
        int totalLength = nums1.length + nums2.length;
        int mergedMiddleIndex = totalLength / 2;

        int indexMerged = 0;

        int mid1 = 0;
        int mid2 = 0;
        while (index1 < nums1.length && index2 < nums2.length && indexMerged <= mergedMiddleIndex) {
            if (nums1[index1] <= nums2[index2]) {
                mid1 = mid2;
                mid2 = nums1[index1];
                index1++;
            } else {
                mid1 = mid2;
                mid2 = nums2[index2];
                index2++;
            }
            indexMerged++;
        }

        if (indexMerged <= mergedMiddleIndex) {
            if (index1 >= nums1.length) {
                while (indexMerged <= mergedMiddleIndex && index2 < nums2.length) {
                    mid1 = mid2;
                    mid2 = nums2[index2];
                    index2++;
                    indexMerged++;
                }
            }
            if (index2 >= nums2.length) {
                while (indexMerged <= mergedMiddleIndex && index1 < nums1.length) {
                    mid1 = mid2;
                    mid2 = nums1[index1];
                    index1++;
                    indexMerged++;
                }
            }
        }

        if (totalLength % 2 == 0) {
            median = (mid1 + mid2)/2.0;
        } else {
            median = mid2;
        }

        return median;
    }

    public double findMedianSortedArrays_OfficialSolution(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i]){
                iMin = iMin + 1; // i is too small
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = iMax - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    public static void main(String[] args) {
//        int[] nums1 = new int[] {1,5,8};
//        int[] nums2 = new int[] {3,7};
        // merged {1,3,5,7,8}

//        int[] nums1 = new int[] {1,5,8};
//        int[] nums2 = new int[] {3,7,9};
        // merged {1,3,5,7,8,9}

//        int[] nums1 = new int[] {1,5,8};
//        int[] nums2 = new int[] {3,7,9};
        // merged {1,3,5,7,8,9}

//        int[] nums1 = new int[] {1,3};
//        int[] nums2 = new int[] {2};

//        int[] nums1 = new int[] {1};
//        int[] nums2 = new int[] {3,5,7,9};
//        // merged {1,3,5,7,8,9}

        int[] nums1 = new int[] {1, 2};
        int[] nums2 = new int[] {3, 4};
        // merged {1,2,3,4}

        double median = findMedianSortedArrays(nums1, nums2);
        System.out.println("findMedianSortedArrays median = " + median);

        double medianBetter = findMedianSortedArraysBetter(nums1, nums2);
        System.out.println("findMedianSortedArraysBetter medianBetter = " + medianBetter);


        double medianBest = findMedianSortedArraysBest(nums1, nums2);
        System.out.println("findMedianSortedArraysBest medianBest = " + medianBest);
    }
}
