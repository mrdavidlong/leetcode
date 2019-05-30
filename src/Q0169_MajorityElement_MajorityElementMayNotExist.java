/**
 * https://leetcode.com/problems/majority-element/description/
 *
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

 You may assume that the array is non-empty and the majority element always exist in the array.

 Example 1:

 Input: [3,2,3]
 Output: 3
 Example 2:

 Input: [2,2,1,1,1,2,2]
 Output: 2
 */
//https://www.geeksforgeeks.org/majority-element/
class Q0169_MajorityElement_MajorityElementMayNotExist {

    /* Function to print Majority Element */
    void printMajority(int a[], int size) {
        /* Find the candidate for Majority*/
        int cand = findCandidate(a, size);

        /* Print the candidate if it is Majority*/
        if (isMajority(a, size, cand))
            System.out.println(" " + cand + " ");
        else
            System.out.println("No Majority Element");
    }

    /* Function to find the candidate for Majority */
    int findCandidate(int a[], int size) {
        int maj_index = 0, count = 1;
        int i;
        for (i = 1; i < size; i++) {
            if (a[maj_index] == a[i])
                count++;
            else
                count--;

            if (count == 0) {
                maj_index = i;
                count = 1;
            }
        }
        return a[maj_index];
    }

    /* Function to check if the candidate occurs more
       than n/2 times */
    boolean isMajority(int a[], int size, int cand) {
        int i, count = 0;
        for (i = 0; i < size; i++) {
            if (a[i] == cand)
                count++;
        }

        if (count > size / 2)
            return true;
        else
            return false;
    }

    /* Driver program to test the above functions */
    public static void main(String[] args) {
        Q0169_MajorityElement_MajorityElementMayNotExist majorelement = new Q0169_MajorityElement_MajorityElementMayNotExist();
        int a[] = new int[]{1, 3, 3, 1, 2};
        int size = a.length;
        majorelement.printMajority(a, size);
    }
}
