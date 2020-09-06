import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/restore-ip-addresses/description/
 */
public class Q0093_RestoreIPAddresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        restoreIpAddressesCore(result, new StringBuilder(), s, 0);
        return result;
    }

    private void restoreIpAddressesCore(List<String> result, StringBuilder stringBuilder, String s, int section) {
        // found a valid ip address, add it to the result list
        if (section == 4 && s.length() == 0) {
            result.add(stringBuilder.toString());
            return;
        }

        // wrong path, return
        if (section == 4 || s.length() == 0) {
            return;
        }

        // i from 0 to 2, since there are only 3 digits per section
        for (int i = 0; i < s.length() && i <= 2; i++) {
            // the beginning of the section cannot be 0, unless the section value is 0, i.e. i = 0
            if (i > 0 && s.charAt(0) == '0') {
                break;
            }

            String sectionValue = s.substring(0, i + 1);
            if (Integer.valueOf(sectionValue) <= 255) {
                if (stringBuilder.length() != 0) {
                    sectionValue = "." + sectionValue;
                }
                stringBuilder.append(sectionValue);
                restoreIpAddressesCore(result, stringBuilder, s.substring(i + 1), section + 1);
                stringBuilder.delete(stringBuilder.length() - sectionValue.length(), stringBuilder.length()); // remove the sectionValue from stringBuilder
            }
        }
    }

    public static void main(String[] args) {
        Q0093_RestoreIPAddresses sol = new Q0093_RestoreIPAddresses();
//        Input: "25525511135"
//        Output: ["255.255.11.135", "255.255.111.35"]
        String ipString = "25525511135";
        //String ipString = "20525511135";
        List<String> ipList = sol.restoreIpAddresses(ipString);
        for (String ip : ipList) {
            System.out.println(ip);
        }
        System.out.println();
    }

}
