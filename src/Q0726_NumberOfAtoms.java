import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
https://leetcode.com/problems/number-of-atoms/
Given a chemical formula (given as a string), return the count of each atom.

An atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.

1 or more digits representing the count of that element may follow if the count is greater than 1. If the count is 1, no digits will follow. For example, H2O and H2O2 are possible, but H1O2 is impossible.

Two formulas concatenated together produce another formula. For example, H2O2He3Mg4 is also a formula.

A formula placed in parentheses, and a count (optionally added) is also a formula. For example, (H2O2) and (H2O2)3 are formulas.

Given a formula, output the count of all elements as a string in the following form: the first name (in sorted order), followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.

Example 1:

Input:
formula = "H2O"
Output: "H2O"
Explanation:
The count of elements are {'H': 2, 'O': 1}.
Example 2:

Input:
formula = "Mg(OH)2"
Output: "H2MgO2"
Explanation:
The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.
Example 3:

Input:
formula = "K4(ON(SO3)2)2"
Output: "K4N2O14S4"
Explanation:
The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.
Note:

All atom names consist of lowercase letters, except for the first character which is uppercase.
The length of formula will be in the range [1, 1000].
formula will only consist of letters, digits, and round parentheses, and is a valid formula as defined in the problem.

https://leetcode.com/problems/number-of-atoms/solution/
 */
public class Q0726_NumberOfAtoms {
    int i;
    public String countOfAtoms(String formula) {
        StringBuilder ans = new StringBuilder();
        i = 0;
        Map<String, Integer> count = parse(formula);
        for (String name: count.keySet()) {
            ans.append(name);
            int multiplicity = count.get(name);
            if (multiplicity > 1) ans.append("" + multiplicity);
        }
        return new String(ans);
    }

    public Map<String, Integer> parse(String formula) {
        int N = formula.length();
        Map<String, Integer> count = new TreeMap();
        while (i < N && formula.charAt(i) != ')') {
            if (formula.charAt(i) == '(') {
                i++;
                for (Map.Entry<String, Integer> entry: parse(formula).entrySet()) {
                    count.put(entry.getKey(), count.getOrDefault(entry.getKey(), 0) + entry.getValue());
                }
            } else {
                int iStart = i++;
                while (i < N && Character.isLowerCase(formula.charAt(i))) i++;
                String name = formula.substring(iStart, i);
                iStart = i;
                while (i < N && Character.isDigit(formula.charAt(i))) i++;
                int multiplicity = iStart < i ? Integer.parseInt(formula.substring(iStart, i)) : 1;
                count.put(name, count.getOrDefault(name, 0) + multiplicity);
            }
        }
        int iStart = ++i;
        while (i < N && Character.isDigit(formula.charAt(i))) i++;
        if (iStart < i) {
            int multiplicity = Integer.parseInt(formula.substring(iStart, i));
            for (String key: count.keySet()) {
                count.put(key, count.get(key) * multiplicity);
            }
        }
        return count;
    }

    // BEST
    public String countOfAtomsWithStack(String formula) {
        int N = formula.length();
        Stack<Map<String, Integer>> stack = new Stack();
        stack.push(new TreeMap());

        for (int i = 0; i < N;) {
            if (formula.charAt(i) == '(') {
                stack.push(new TreeMap());
                i++;
            } else if (formula.charAt(i) == ')') {
                Map<String, Integer> top = stack.pop();
                int iStart = ++i, multiplicity = 1;
                while (i < N && Character.isDigit(formula.charAt(i))) i++;
                if (i > iStart) multiplicity = Integer.parseInt(formula.substring(iStart, i));
                for (String c: top.keySet()) {
                    int v = top.get(c);
                    stack.peek().put(c, stack.peek().getOrDefault(c, 0) + v * multiplicity);
                }
            } else {
                int iStart = i++;
                while (i < N && Character.isLowerCase(formula.charAt(i))) i++;
                String name = formula.substring(iStart, i);
                iStart = i;
                while (i < N && Character.isDigit(formula.charAt(i))) i++;
                int multiplicity = i > iStart ? Integer.parseInt(formula.substring(iStart, i)) : 1;
                stack.peek().put(name, stack.peek().getOrDefault(name, 0) + multiplicity);
            }
        }

        StringBuilder ans = new StringBuilder();
        for (String name: stack.peek().keySet()) {
            ans.append(name);
            int multiplicity = stack.peek().get(name);
            if (multiplicity > 1) ans.append("" + multiplicity);
        }
        return new String(ans);
    }

    public String countOfAtomsUsingRegex(String formula) {
        Matcher matcher = Pattern.compile("([A-Z][a-z]*)(\\d*)|(\\()|(\\))(\\d*)").matcher(formula);
        Stack<Map<String, Integer>> stack = new Stack();
        stack.push(new TreeMap());

        while (matcher.find()) {
            String match = matcher.group();
            if (match.equals("(")) {
                stack.push(new TreeMap());
            } else if (match.startsWith(")")) {
                Map<String, Integer> top = stack.pop();
                int multiplicity = match.length() > 1 ? Integer.parseInt(match.substring(1, match.length())) : 1;
                for (String name: top.keySet()) {
                    stack.peek().put(name, stack.peek().getOrDefault(name, 0) + top.get(name) * multiplicity);
                }
            } else {
                int i = 1;
                while (i < match.length() && Character.isLowerCase(match.charAt(i))) {
                    i++;
                }
                String name = match.substring(0, i);
                int multiplicity = i < match.length() ? Integer.parseInt(match.substring(i, match.length())) : 1;
                stack.peek().put(name, stack.peek().getOrDefault(name, 0) + multiplicity);
            }
        }

        StringBuilder ans = new StringBuilder();
        for (String name: stack.peek().keySet()) {
            ans.append(name);
            final int count = stack.peek().get(name);
            if (count > 1) ans.append(String.valueOf(count));
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        Q0726_NumberOfAtoms sol = new Q0726_NumberOfAtoms();
//        String atomCount = sol.countOfAtoms("H2O");
//        String atomCount2 = sol.countOfAtoms("Mg(OH)2");
//        String atomCount3 = sol.countOfAtoms("K4(ON(SO3)2)2");

        String atomCount4 = sol.countOfAtomsWithStack("H2O");
        String atomCount5 = sol.countOfAtomsWithStack("Mg(OH)2");
        String atomCount6 = sol.countOfAtomsWithStack("K4(ON(SO3)2)2");

//        String atomCount7 = sol.countOfAtomsUsingRegex("H2O");
//        String atomCount8 = sol.countOfAtomsUsingRegex("Mg(OH)2");
//        String atomCount9 = sol.countOfAtomsUsingRegex("K4(ON(SO3)2)2");
    }
}
