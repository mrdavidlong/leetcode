/**
 * https://www.geeksforgeeks.org/majority-element/
 */
public class Q0169_MajorityElement_TwoLoops {
    public int findMajority(int[] a) {
        int n = a.length;
        int maxCount = 0;
        int index; // sentinels
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if(a[i] == a[j])
                    count++;
            }

            // update maxCount if count of current element is greater
            if(count > maxCount) {
                maxCount = count;
                index = i;

                // if maxCount is greater than n/2 return the corresponding element
                if (maxCount > n/2)
                    return a[index];
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1, 1, 2, 1, 3, 5, 1};
        Q0169_MajorityElement_TwoLoops sol = new Q0169_MajorityElement_TwoLoops();
        int majority = sol.findMajority(a);
    }
}
