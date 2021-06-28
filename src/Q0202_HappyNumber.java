import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
https://leetcode.com/problems/happy-number/

Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example:

Input: 19
Output: true
Explanation:
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
 */
public class Q0202_HappyNumber {
    // https://leetcode.com/problems/happy-number/solution/
    private int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

//    public List<Integer> getDigits(int n) {
//        List<Integer> digits = new ArrayList<>();
//        while (n > 0) {
//            int digit = n % 10;
//            digits.add(digit);
//            n /= 10;
//        }
//        return digits;
//    }
//
//    public boolean isHappy(int n) {
//        return isHappyCore(n, new HashSet<>());
//    }
//
//    public boolean isHappyCore(int n, Set<Integer> visited) {
//        if (n == 0) return false;
//        if (!visited.add(n)) return false;
//
//        List<Integer> digits = getDigits(n);
//        int sum = 0;
//        for (Integer digit: digits) {
//            sum += digit * digit;
//        }
//        if (sum == 1) return true;
//        else return isHappyCore(sum, visited);
//    }

    public static void main(String[] args) {
        Q0202_HappyNumber sol = new Q0202_HappyNumber();
        boolean isHappy1 = sol.isHappy(19);
    }
}
