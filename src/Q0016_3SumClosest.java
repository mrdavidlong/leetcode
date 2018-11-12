import java.util.Arrays;

/**
 * Created by davidlong on 6/30/18.
 */
public class Q0016_3SumClosest {

    public int threeSumClosest(int[] num, int target) {
        int result = num[0] + num[1] + num[num.length - 1];
        Arrays.sort(num);
        for (int i = 0; i < num.length - 2; i++) {
            int start = i + 1, end = num.length - 1;
            while (start < end) {
                int sum = num[i] + num[start] + num[end];
                if (sum > target) {
                    end--;
                } else {
                    start++;
                }
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Q0016_3SumClosest q = new Q0016_3SumClosest();
        int[] input = new int[] {-1, 2, 1, -4};
        int output = q.threeSumClosest(input, 1);
        System.out.println("output = " + output);
    }
}
