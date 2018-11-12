package misc;


//http://leetcode.com/2011/01/find-k-th-smallest-element-in-union-of.html
public class KthSmallest {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[] A={1, 2, 5, 10, 12, 14, 19, 282, 290};
        int[] B={3, 4, 6, 9, 281, 289};
        int k0 = 6;
        int kthSmallest = findKthSmallest(A, 0, A.length-1, B, 0, B.length-1, k0);
        System.out.println(kthSmallest);
    }
    

	public static int findKthSmallest(int A[], int aStart, int aEnd, int B[], int bStart, int bEnd, int k) {
	  //assert(m >= 0); assert(n >= 0); assert(k > 0); assert(k <= m+n);
	  
	  //int i = (int)((double)m / (m+n) * (k-1));
	  //int j = (k-1) - i;
		int i = aStart + ((aEnd-aStart)*(k-1)/((aEnd-aStart) + (bEnd-bStart)));
		int j = bStart + (k-1) - (i-aStart);
	 
	  //assert(i >= 0); assert(j >= 0); assert(i <= m); assert(j <= n);
	  // invariant: i + j = k-1
	  // Note: A[-1] = -INF and A[m] = +INF to maintain invariant
	  int Ai_1 = ((i == 0) ? Integer.MIN_VALUE : A[i-1]);
	  int Bj_1 = ((j == 0) ? Integer.MIN_VALUE : B[j-1]);
	  int Ai   = ((i == A.length) ? Integer.MAX_VALUE : A[i]);
	  int Bj   = ((j == B.length) ? Integer.MAX_VALUE : B[j]);
	 
	  if (Bj_1 < Ai && Ai < Bj)
	    return Ai;
	  else if (Ai_1 < Bj && Bj < Ai)
	    return Bj;
	 
	  assert((Ai > Bj && Ai_1 > Bj) || 
	         (Ai < Bj && Ai < Bj_1));
	 
	  // if none of the cases above, then it is either:
	  if (Ai < Bj) {
	    // exclude Ai and below portion
	    // exclude Bj and above portion
	    return findKthSmallest(A, i+1, aEnd, B, bStart, j-1, k-i-1);
	  }
	  else /* Bj < Ai */ {
	    // exclude Ai and above portion
	    // exclude Bj and below portion
	    return findKthSmallest(A, aStart, i-1, B, j+1, bEnd, k-j-1);
	  }
	}
 
//    //refine the search range of X, Y for the kth smallest number
//    //xlb: lower bound of X
//    //xub: upper bound of X
//    //ylb: lower bound of Y
//    //yub: upper bound of Y
//    public int Kthsmallest(int[] X, int xlb, int xub, int[] Y, int ylb, int yub, int k){
//        //If the total # of elements in X, Y is less than k, invalid
//        if(xub + yub <k-2) return 0; 
//        //If the total # of elements match with k, return the bigger number of X&#039;s or Y&#039;s last element
//        if(xub + yub == k-2) return Math.max(Y[yub], X[xub]);
//        //Otherwise, if X is empty, return Y[k-1]
//        if(X.length==0){
//            return Y[k-1];
//        }
//        if(Y.length==0){
//            return X[k-1];
//        }
//        
//        //Pick the new pivot in X, Y to refine the search range based on binary search concept
//        int xpiv = (int)Math.ceil((xlb+xub)/2);
//        //Constraints for ypiv calculated based on xpiv       
//        //0=<k-xpiv-2<=Y.length -1
//        //xpiv=k-1-Y.length 
//        //so, in general, k-1-Y.length Y.length>=1 
//        //refine xpiv based on constraints above
//        xpiv = Math.min(xpiv, k-2);
//        xpiv = Math.max(xpiv, k-1-Y.length);
//        int ypiv = k - xpiv - 2;
//        
//        if (X[xpiv] > Y[ypiv]) {
//                return Y[ypiv];
//        	}    
//            else
//                return Kthsmallest(X, xpiv, xub, Y, ylb, ypiv, k);
//        }       
//        //if (X[xpiv]>Y[ypiv])
//        else{
//            if(X[xpiv]<Y[ypiv+1])
//                return X[xpiv];
//            else
//                return Kthsmallest(X, xlb, xpiv, Y, ypiv, yub, k);
//        }
//    }
}
