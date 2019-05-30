import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by davidlong on 6/29/18.
 */
public class Q0012_IntegerToRoman {

    static Map<Integer, String> map = new HashMap<Integer, String>() {
        {
            put(1, "I");
            put(4, "IV");
            put(5, "V");
            put(9, "IX");
            put(10, "X");
            put(40, "XL");
            put(50, "L");
            put(90, "XC");
            put(100, "C");
            put(400, "CD");
            put(500, "D");
            put(900, "CM");
            put(1000, "M");
        }
    };

    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> integers = new ArrayList(map.keySet());
        Collections.sort(integers, Collections.reverseOrder());
        for (Integer i : integers) {
            int quotient = num / i;
            if (quotient > 0) {
                for (int j = 0; j < quotient; j++) {
                    sb.append(map.get(i));
                }
                num = num - quotient * i;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Q0012_IntegerToRoman q = new Q0012_IntegerToRoman();
        int input = 1993;
        String output = q.intToRoman(input);
        System.out.println("output = " + output);
    }
}
