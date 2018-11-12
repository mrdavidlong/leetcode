package misc;

public class Palindrome {
	public static void main(String[] args) {
		int i = 0;
		int x = reverse(1234321);
		System.out.println(x);
		boolean isP = isPalindrome2(1234321);
	}


	public static int reverse(int num) {
	  assert(num >= 0);   // for non-negative integers only.
	  int rev = 0;
	  while (num != 0) {
	    rev = rev * 10 + num % 10;
	    num /= 10;
	  }
	  return rev;
	}

	public static boolean isPalindrome2(int x) {
		return x == reverse(x);
	}
	
	public static boolean isPalindrome(int x) {
	  if (x < 0) return false;
	  int div = 1;
	  while (x / div >= 10) {
	    div *= 10;
	  }        
	  while (x != 0) {
	    int l = x / div;
	    int r = x % 10;
	    if (l != r) return false;
	    x = (x % div) / 10;
	    div /= 100;
	  }
	  return true;
	}
}
