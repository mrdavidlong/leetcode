import java.util.HashMap;

/**
 * https://leetcode.com/problems/copy-list-with-random-pointer/description/
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

 Return a deep copy of the list.

 *
 * https://leetcode.com/problems/copy-list-with-random-pointer/solution/
 */

class RandomListNode {
    int label;
    RandomListNode next, random;
    RandomListNode(int x) { this.label = x; }
}

public class Q0138_CopyListWithRandomPointer {
    // HashMap which holds old nodes as keys and new nodes as its values.
    HashMap<RandomListNode, RandomListNode> visitedHash = new HashMap<>();

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }

        // If we have already processed the current node, then we simply return the cloned version of it.
        if (this.visitedHash.containsKey(head)) {
            return this.visitedHash.get(head);
        }

        // Create a new node with the label same as old node. (i.e. copy the node)
        RandomListNode node = new RandomListNode(head.label);

        // Save this value in the hash map. This is needed since there might be
        // loops during traversal due to randomness of random pointers and this would help us avoid them.
        this.visitedHash.put(head, node);

        // Recursively copy the remaining linked list starting once from the next pointer and then from the random pointer.
        // Thus we have two independent recursive calls.
        // Finally we update the next and random pointers for the new node created.
        node.next = this.copyRandomList(head.next);
        node.random = this.copyRandomList(head.random);

        return node;
    }
}
