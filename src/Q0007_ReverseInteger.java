/**
 * Created by davidlong on 6/17/18.
 */
public class Q0007_ReverseInteger {

//    public static int reverse(int input) {
//        //if (input == 0 || input == Integer.MIN_VALUE) return 0;
//
//        long result = 0;
//        while (input  != 0) {
//            int digit = input % 10;
//            input /= 10;
//
//            result = result * 10 + digit;
//        }
//
//        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
//            return 0;
//        } else {
//            return (int)result;
//        }
//    }

    // https://leetcode.com/problems/reverse-integer/solution/
    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static void main(String[] args) {
        int input = -123;
        int output = reverse(input);
        System.out.println("output = " + output);

        int input1 = 123;
        int output1 = reverse(input1);
        System.out.println("output1 = " + output1);

        int input2 = 0;
        int output2 = reverse(input2);
        System.out.println("output2 = " + output2);

        int input3 = Integer.MIN_VALUE;
        int output3 = reverse(input3);
        System.out.println("output3 = " + output3);

        int input4 = 1534236469;//Integer.MAX_VALUE;
        int output4 = reverse(input4);
        System.out.println("output4 = " + output4);
    }
}
