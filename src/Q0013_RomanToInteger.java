import java.util.HashMap;
import java.util.Map;

/**
 * Created by davidlong on 6/29/18.
 */
public class Q0013_RomanToInteger {
//
//    private int getVal(char c){
//        switch (c){
//            case 'M':
//                return 1000;
//            case 'D':
//                return 500;
//            case 'C':
//                return 100;
//            case 'L':
//                return 50;
//            case 'X' :
//                return 10;
//            case 'V':
//                return 5;
//            case 'I':
//                return 1;
//        }
//        throw new IllegalArgumentException("unsupported character");
//    }

private static Map<Character, Integer> map = new HashMap<Character, Integer>() {
    {
        put('M', 1000);
        put('D', 500);
        put('C', 100);
        put('L', 50);
        put('X', 10);
        put('V', 5);
        put('I', 1);
    }
};

public int romanToInt(String s) {
    int result = 0;
    if(s.length() == 0) return result;

    for (int i = 0; i < s.length() - 1; i++) {
        int curInt = map.get(s.charAt(i));
        int nextInt = map.get(s.charAt(i + 1));
        if (curInt < nextInt) {
            result -= curInt;
        } else {
            result += curInt;
        }
    }
    // add the last char
    return result + map.get(s.charAt(s.length()-1));
}

    public static void main(String[] args) {
        Q0013_RomanToInteger q = new Q0013_RomanToInteger();
        String input = "MCMXCIV";
        int result = q.romanToInt(input); // 1994
        System.out.println(result);

    }
}
