/**
 * Created by davidlong on 7/2/18.
 */
public class Q0038_CountAndSay {
//    private String toString(ArrayList<Character> list)
//    {
//        StringBuilder builder = new StringBuilder(list.size());
//        for(Character ch: list)
//        {
//            builder.append(ch);
//        }
//        return builder.toString();
//    }

    public String countAndSay(int n) {
        if (n < 1) throw new IllegalArgumentException("n cannot be less than 1");

        String say = "1";

        // return the base case
        if (n == 1) return say;

        for (int i = 1; i < n; i++) {
            String newSay = "";
            char[] chars = say.toCharArray();
            char prevChar = '\0';
            int count = 0;
            for (int j = 0; j < chars.length; j++) {
                // special case the first char
                if (j == 0) {
                    prevChar = chars[j];
                    count = 1;
                    continue;
                }

                if (chars[j] == prevChar) {
                    count++;
                } else {
                    newSay += count;
                    newSay += prevChar;

                    prevChar = chars[j];
                    count = 1; // reset the count to 1
                }
            }

            // add the remaining
            if (count > 0) {
                newSay += count;
                newSay += prevChar;
            }
            say = newSay;
        }
        return say;
    }

    // https://leetcode.com/problems/count-and-say/discuss/16000/Show-an-Answer-in-Java
    public String countAndSay2(int n) {
        StringBuilder curr=new StringBuilder("1");
        StringBuilder prev;
        int count;
        char say;
        for (int i=1;i<n;i++){
            prev=curr;
            curr=new StringBuilder();
            count=1;
            say=prev.charAt(0);

            for (int j=1,len=prev.length();j<len;j++){
                if (prev.charAt(j)!=say){
                    curr.append(count).append(say);
                    count=1;
                    say=prev.charAt(j);
                }
                else count++;
            }
            curr.append(count).append(say);
        }
        return curr.toString();

    }

    public static void main(String[] args) {
        Q0038_CountAndSay sol = new Q0038_CountAndSay();
        String output1 = sol.countAndSay(1);
        String output2 = sol.countAndSay(2);
        String output3 = sol.countAndSay(3);
        String output4 = sol.countAndSay(4);
        String output5 = sol.countAndSay(5);
        String output6 = sol.countAndSay(6);

        String outputb1 = sol.countAndSay2(1);
        String outputb2 = sol.countAndSay2(2);
        String outputb3 = sol.countAndSay2(3);
        String outputb4 = sol.countAndSay2(4);
        String outputb5 = sol.countAndSay2(5);
        String outputb6 = sol.countAndSay2(6);


    }
}
