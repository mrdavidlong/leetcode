import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Q0658_Find_K_Closest_Elements {
    //unfinished
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<>();
        Arrays.sort(arr);
        int index = Arrays.binarySearch(arr, x);
        if (index < 0) {
            index = - (index + 1);
        }
        int p1 = index;
        int p2 = index;
        while (k > 0 && (p1 >= 0 || p2 < arr.length) ) {
            if (p1 == p2) {
                if (index > arr.length - 1) {
                    p2--;
                    p1--;
                } else if (arr[index] == x) {
                    result.add(arr[p1]);
                    p1--;
                    p2++;
                    k--;
                } else {
                    p1--;
                }
            } else if (p2 > arr.length - 1 || (p1 >= 0 && Math.abs(x - arr[p1]) <= Math.abs(x - arr[p2]))) {
                result.add(arr[p1]);
                p1--;
                k--;
            } else if (p1 < 0 || (p2 <= arr.length - 1 && Math.abs(x - arr[p1]) > Math.abs(x - arr[p2]))) {
                result.add(arr[p2]);
                p2++;
                k--;
            }
        }
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        Q0658_Find_K_Closest_Elements sol = new Q0658_Find_K_Closest_Elements();
        List<Integer> kClosest1 = sol.findClosestElements(new int[] {1,2,3,4,5}, 4, 3);
        System.out.println(kClosest1);
        List<Integer> kClosest2 = sol.findClosestElements(new int[] {1,2,3,4,5}, 4, -1);
        System.out.println(kClosest2);
        List<Integer> kClosest3 = sol.findClosestElements(new int[] {1,1,1,10,10,10}, 1, 9);
        System.out.println(kClosest3);
        List<Integer> kClosest4 = sol.findClosestElements(new int[] {0,0,1,2,3,3,4,7,7,8}, 3, 5);
        System.out.println(kClosest4);
        List<Integer> kClosest5 = sol.findClosestElements(new int[] {3,5,8,10}, 2, 15);
        System.out.println(kClosest5);
    }
}
