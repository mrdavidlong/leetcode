package misc;

public class LongestPalindromeSubstring {
	public static void main(String[] args) {
		String result = longestPalindromeDP("fabag");
		System.out.println(result);
		String result2 = longestPalindromeSimple("fabag");
		System.out.println(result2);
	}
	
	public static String longestPalindromeDP(String s) {
	  int n = s.length();
	  int longestBegin = 0;
	  int maxLen = 1;
	  boolean table[][] = new boolean[n][n];
	  for (int i = 0; i < n; i++) {
	    table[i][i] = true;
	  }
	  for (int i = 0; i < n-1; i++) {
	    if (s.charAt(i) == s.charAt(i+1)) {
	      table[i][i+1] = true;
	      longestBegin = i;
	      maxLen = 2;
	    }
	  }
	  for (int len = 3; len <= n; len++) {
	    for (int i = 0; i < n-len+1; i++) {
	      int j = i+len-1;
	      if (s.charAt(i) == s.charAt(j) && table[i+1][j-1]) {
	        table[i][j] = true;
	        longestBegin = i;
	        maxLen = len;
	      }
	    }
	  }
	  return s.substring(longestBegin, longestBegin+maxLen);
	}
	
	public static String expandAroundCenter(String s, int c1, int c2) {
		  int l = c1, r = c2;
		  int n = s.length();
		  while (l >= 0 && r <= n-1 && s.charAt(l) == s.charAt(r)) {
		    l--;
		    r++;
		  }
		  return s.substring(l+1, r);
		}
		 
	public static String longestPalindromeSimple(String s) {
	  int n = s.length();
	  if (n == 0) return "";
	  String longest = s.substring(0, 1);  // a single char itself is a palindrome
	  for (int i = 0; i < n-1; i++) {
		  String p1 = expandAroundCenter(s, i, i);
	    if (p1.length() > longest.length())
	      longest = p1;
	 
	    String p2 = expandAroundCenter(s, i, i+1);
	    if (p2.length() > longest.length())
	      longest = p2;
	  }
	  return longest;
	}
}
