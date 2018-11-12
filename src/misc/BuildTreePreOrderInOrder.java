package misc;
import common.TreeNode;

import java.util.*;

//_______7______
///              \
//__10__          ___2
///      \        /
//4       3      _8
//    \    /
//     1  11
public class BuildTreePreOrderInOrder {
	public static void main(String[] args) {
		int[] preorder = {7,10,4,3,1,2,8,11};
		int[] inorder = {4,10,3,1,7,11,8,2};
		
		TreeNode result = buildTree(preorder, inorder);
	}
	
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null
        	|| inorder == null
        	|| preorder.length == 0
        	|| inorder.length == 0
        	|| preorder.length != inorder.length) {
        	return null;
        }
        
        Hashtable<Integer, Integer> mapIndex = new Hashtable<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) {
        	mapIndex.put(inorder[i], i);
        }
        
        return buildTree(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
        
    }
    
    public static TreeNode buildTree(int[] preorder,int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
            int preLength = preEnd - preStart + 1;
            int inLength = inEnd - inStart + 1;
            if (preLength <= 0 || inLength <= 0 || preLength != inLength) {
            	return null;
            }
            
            int rootVal = preorder[preStart];
            TreeNode node = new TreeNode(rootVal);
            int numLeftCh = findIndex(inorder, inStart, inEnd, rootVal);
            int numRightCh = inLength - numLeftCh -1;
            
            if (numLeftCh > 0) {
                node.left = buildTree(preorder,preStart+1,preStart+numLeftCh,inorder,inStart,inStart+numLeftCh-1);
                 
            }
            
            if (numRightCh > 0) {                                
            node.right = buildTree(preorder,preStart+numLeftCh+1,preEnd,inorder,inStart + numLeftCh+1,inEnd);
            }
            return node;
        
    }
    
    public static int findIndex(int[] arr, int arrStart, int arrEnd, int val) {
        for (int i = arrStart; i <= arrEnd; i++) {
            if (arr[i] == val) return (i-arrStart);
        }
        return -1;
    }
}