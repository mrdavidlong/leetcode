import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by davidlong on 10/5/18.
 */
public class Q0412_FizzBuzz {

    public List<String> fizzBuzz1(int n) {

        // ans list
        List<String> ans = new ArrayList<String>();

        for (int num = 1; num <= n; num++) {

            boolean divisibleBy3 = (num % 3 == 0);
            boolean divisibleBy5 = (num % 5 == 0);

            if (divisibleBy3 && divisibleBy5) {
                // Divides by both 3 and 5, add FizzBuzz
                ans.add("FizzBuzz");
            } else if (divisibleBy3) {
                // Divides by 3, add Fizz
                ans.add("Fizz");
            } else if (divisibleBy5) {
                // Divides by 5, add Buzz
                ans.add("Buzz");
            } else {
                // Not divisible by 3 or 5, add the number
                ans.add(Integer.toString(num));
            }
        }

        return ans;
    }

    public List<String> fizzBuzz2(int n) {
        // ans list
        List<String> ans = new ArrayList<String>();

        for (int num = 1; num <= n; num++) {

            boolean divisibleBy3 = (num % 3 == 0);
            boolean divisibleBy5 = (num % 5 == 0);

            String numAnsStr = "";

            if (divisibleBy3) {
                // Divides by 3, add Fizz
                numAnsStr += "Fizz";
            }

            if (divisibleBy5) {
                // Divides by 5, add Buzz
                numAnsStr += "Buzz";
            }

            if (numAnsStr.equals("")) {
                // Not divisible by 3 or 5, add the number
                numAnsStr += Integer.toString(num);
            }

            // Append the current answer str to the ans list
            ans.add(numAnsStr);
        }

        return ans;
    }

    public List<String> fizzBuzz3(int n) {
        // ans list
        List<String> ans = new ArrayList<String>();

        // Hash map to store all fizzbuzz mappings.
        HashMap<Integer, String> fizzBizzDict =
                new HashMap<Integer, String>() {
                    {
                        put(3, "Fizz");
                        put(5, "Buzz");
                    }
                };

        for (int num = 1; num <= n; num++) {
            String numAnsStr = "";
            for (Integer key : fizzBizzDict.keySet()) {
                // If the num is divisible by key,
                // then add the corresponding string mapping to current numAnsStr
                if (num % key == 0) {
                    numAnsStr += fizzBizzDict.get(key);
                }
            }
            if (numAnsStr.equals("")) {
                // Not divisible by 3 or 5, add the number
                numAnsStr += Integer.toString(num);
            }

            // Append the current answer str to the ans list
            ans.add(numAnsStr);
        }

        return ans;
    }

    public static void main(String[] args) {
        Q0412_FizzBuzz sol = new Q0412_FizzBuzz();
        List<String> fizzBuzz1 = sol.fizzBuzz1(15);
    }
}
