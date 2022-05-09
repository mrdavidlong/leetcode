import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/lru-cache/description/
 *
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

 get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

 Follow up:
 Could you do both operations in O(1) time complexity?

 Example:

 LRUCache cache = new LRUCache( 2 );  // 2 is the capacity

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
 */
public class Q0146_LruCache {

    // https://leetcode.com/problems/lru-cache/solution/
    class LRUCache extends LinkedHashMap<Integer, Integer> {
        private int capacity;

        public LRUCache(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }

    /*
    The problem can be solved with a hashtable that keeps track of the keys and its values in the double linked list. One interesting property about double linked list is that the node can remove itself without other reference. In addition, it takes constant time to add and remove nodes from the head or tail.

One particularity about the double linked list that I implemented is that I create a pseudo head and tail to mark the boundary, so that we don't need to check the NULL node during the update. This makes the code more concise and clean, and also it is good for the performance as well.
     */

    class DLinkedNode {
        int key;
        int value;
        DLinkedNode pre;
        DLinkedNode post;
    }

    /**
     * Always add the new node right after head;
     */
    private void addNode(DLinkedNode node) {
        node.pre = head;
        node.post = head.post;

        head.post.pre = node;
        head.post = node;
    }

    /**
     * Remove an existing node from the linked list.
     */
    private void removeNode(DLinkedNode node) {
        DLinkedNode pre = node.pre;
        DLinkedNode post = node.post;

        pre.post = post;
        post.pre = pre;
    }

    /**
     * Move certain node in between to the head.
     */
    private void moveToHead(DLinkedNode node) {
        this.removeNode(node);
        this.addNode(node);
    }

    // pop the current tail.
    private DLinkedNode popTail() {
        DLinkedNode res = tail.pre;
        this.removeNode(res);
        return res;
    }

    private Hashtable<Integer, DLinkedNode> cache = new Hashtable<Integer, DLinkedNode>();
    private int count;
    private int capacity;
    private DLinkedNode head, tail;

    public Q0146_LruCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;

        head = new DLinkedNode();
        head.pre = null;

        tail = new DLinkedNode();
        tail.post = null;

        head.post = tail;
        tail.pre = head;
    }

    public int get(int key) {

        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1; // should raise exception here.
        }

        // move the accessed node to the head;
        this.moveToHead(node);

        return node.value;
    }


    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);

        if (node == null) {

            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.value = value;

            this.cache.put(key, newNode);
            this.addNode(newNode);

            ++count;

            if (count > capacity) {
                // pop the tail
                DLinkedNode tail = this.popTail();
                this.cache.remove(tail.key);
                --count;
            }
        } else {
            // update the value.
            node.value = value;
            this.moveToHead(node);
        }

    }

//    // same as CtCI Q16_25_LRU_Cache
//    private int maxCacheSize;
//    private HashMap<Integer, LinkedListNode> map = new HashMap<Integer, LinkedListNode>();
//    private LinkedListNode listHead = null;
//    public LinkedListNode listTail = null;
//
//    public Q0146_LruCache(int maxSize) {
//        maxCacheSize = maxSize;
//    }
//
//    /* Get value for key and mark as most recently used. */
//    public int get(int key) {
//        LinkedListNode item = map.get(key);
//        if (item == null) {
//            return 0;
//        }
//
//        /* Move to front of list to mark as most recently used. */
//        if (item != listHead) {
//            removeFromLinkedList(item);
//            insertAtFrontOfLinkedList(item);
//        }
//        return item.value;
//    }
//
//    /* Remove node from linked list. */
//    private void removeFromLinkedList(LinkedListNode node) {
//        if (node == null) {
//            return;
//        }
//        if (node.prev != null) {
//            node.prev.next = node.next;
//        }
//        if (node.next != null) {
//            node.next.prev = node.prev;
//        }
//        if (node == listTail) {
//            listTail = node.prev;
//        }
//        if (node == listHead) {
//            listHead = node.next;
//        }
//    }
//
//    /* Insert node at front of linked list. */
//    private void insertAtFrontOfLinkedList(LinkedListNode node) {
//        if (listHead == null) {
//            listHead = node;
//            listTail = node;
//        } else {
//            listHead.prev = node;
//            node.next = listHead;
//            listHead = node;
//        }
//    }
//
//    /* Remove key, value pair from cache, deleting from hash table
//     * and linked list. */
//    private boolean removeKey(int key) {
//        LinkedListNode node = map.get(key);
//        removeFromLinkedList(node);
//        map.remove(key);
//        return true;
//    }
//
//    /* Put key, value pair in cache. Removes old value for key if
//     * necessary. Inserts pair into linked list and hash table.*/
//    public void put(int key, int value) {
//        /* Remove if already there. */
//        removeKey(key);
//
//        /* If full, remove least recently used item from cache. */
//        if (map.size() >= maxCacheSize && listTail != null) {
//            removeKey(listTail.key);
//        }
//
//        /* Insert new node. */
//        LinkedListNode node = new LinkedListNode(key, value);
//        insertAtFrontOfLinkedList(node);
//        map.put(key, node);
//    }
//
//    private class LinkedListNode {
//        private LinkedListNode next;
//        private LinkedListNode prev;
//        public int key;
//        public int value;
//        public LinkedListNode(int k, int v) {
//            key = k;
//            value = v;
//        }
//    }

    public static void main(String[] args) {
        Q0146_LruCache cache = new Q0146_LruCache( 2 );  // 2 is the capacity

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
    }

}