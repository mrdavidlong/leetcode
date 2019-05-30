/*
https://leetcode.com/problems/compare-version-numbers/

Compare two version numbers version1 and version2.
If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.

The . character does not represent a decimal point and is used to separate number sequences.

For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

You may assume the default revision number for each level of a version number to be 0. For example, version number 3.4 has a revision number of 3 and 4 for its first and second level revision number. Its third and fourth level revision number are both 0.



Example 1:

Input: version1 = "0.1", version2 = "1.1"
Output: -1
Example 2:

Input: version1 = "1.0.1", version2 = "1"
Output: 1
Example 3:

Input: version1 = "7.5.2.4", version2 = "7.5.3"
Output: -1
Example 4:

Input: version1 = "1.01", version2 = "1.001"
Output: 0
Explanation: Ignoring leading zeroes, both “01” and “001" represent the same number “1”
Example 5:

Input: version1 = "1.0", version2 = "1.0.0"
Output: 0
Explanation: The first version number does not have a third level revision number, which means its third level revision number is default to "0"


Note:

Version strings are composed of numeric strings separated by dots . and this numeric strings may have leading zeroes.
Version strings do not start or end with dots, and they will not be two consecutive dots.
 */
public class Q0165_CompareVersionNumbers {
    public int compareVersionByDavid(String version1, String version2) {
        String[] versionArr1 = version1.split("\\.");
        String[] versionArr2 = version2.split("\\.");
        int i = 0;
        int j = 0;
        while (i < versionArr1.length && j < versionArr2.length) {
            int value1 = Integer.parseInt(versionArr1[i]);
            int value2 = Integer.parseInt(versionArr2[j]);
            if (value1 > value2) {
                return 1;
            } else if (value1 < value2) {
                return -1;
            }
            i++;
            j++;
        }

        if (i == versionArr1.length && j == versionArr2.length) {
            return 0;
        } else if (i < versionArr1.length) {
            while (i < versionArr1.length && Integer.parseInt(versionArr1[i]) == 0) {
                i++;
            }

            if (i == versionArr1.length) {
                return 0;
            } else {
                return 1;
            }
        } else if (j < versionArr2.length) {
            while (j < versionArr2.length && Integer.parseInt(versionArr2[j]) == 0) j++;
            if (j == versionArr2.length) return 0; else return -1;
        }
        return 0;
    }

    // https://leetcode.com/problems/compare-version-numbers/discuss/50774/Accepted-small-Java-solution.
    public int compareVersion(String version1, String version2) {
        String[] levels1 = version1.split("\\.");
        String[] levels2 = version2.split("\\.");

        int length = Math.max(levels1.length, levels2.length);
        for (int i=0; i < length; i++) {
            Integer v1 = i < levels1.length ? Integer.parseInt(levels1[i]) : 0;
            Integer v2 = i < levels2.length ? Integer.parseInt(levels2[i]) : 0;
            int compare = v1.compareTo(v2);
            if (compare != 0) {
                return compare;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        Q0165_CompareVersionNumbers sol = new Q0165_CompareVersionNumbers();
        int compare1 = sol.compareVersion("0.1", "1.1"); // -1
        int compare2 = sol.compareVersion("1.0.1", "1"); // 1
        int compare3 = sol.compareVersion("7.5.2.4", "7.5.3"); // -1
        int compare4 = sol.compareVersion("1.01", "1.001"); // 0
        int compare5 = sol.compareVersion("1.0", "1.0.0"); // 0
    }
}
