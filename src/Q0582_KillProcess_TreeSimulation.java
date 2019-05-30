import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
https://leetcode.com/problems/kill-process/
Given n processes, each process has a unique PID (process id) and its PPID (parent process id).

Each process only has one parent process, but may have one or more children processes. This is just like a tree structure. Only one process has PPID that is 0, which means this process has no parent process. All the PIDs will be distinct positive integers.

We use two list of integers to represent a list of processes, where the first list contains PID for each process and the second list contains the corresponding PPID.

Now given the two lists, and a PID representing a process you want to kill, return a list of PIDs of processes that will be killed in the end. You should assume that when a process is killed, all its children processes will be killed. No order is required for the final answer.

Example 1:

Input:
pid =  [1, 3, 10, 5]
ppid = [3, 0, 5, 3]
kill = 5
Output: [5,10]
Explanation:
           3
         /   \
        1     5
             /
            10
Kill 5 will also kill 10.
Note:

The given kill id is guaranteed to be one of the given PIDs.
n>= 1.
 */
public class Q0582_KillProcess_TreeSimulation {
    /*
    https://leetcode.com/problems/kill-process/solution/

    Approach #1 Depth First Search [Time Limit Exceeded]

Algorithm

Since killing a process leads to killing all its children processes, the simplest solution is to traverse over the
p
p
i
d
ppid array and find out all the children of the process to be killed. Further, for every child chosen to be killed we recursively make call to the killProcess function now treating this child as the new parent to be killed. In every such call, we again traverse over the
p
p
i
d
ppid array now considering the id of the child process, and continue in the same fashion. Further, at every step, for every process chosen to be killed, it is added to the list
l
l that needs to be returned at the end.


Complexity Analysis

Time complexity :
O
(
n
n
)
O(n
n
 ).
O
(
n
n
)
O(n
n
 ) function calls will be made in the worst case

Space complexity :
O
(
n
)
O(n). The depth of the recursion tree can go upto
n
n.

Approach #2 Tree Simulation [Accepted]

Algorithm

We can view the given process relationships in the form of a tree. We can construct the tree in such a way that every node stores information about its own value as well as the list of all its direct children nodes. Thus, now, once the tree has been generated, we can simply start off by killing the required node, and recursively killing the children of each node encountered rather than traversing over the whole
p
p
i
d
ppid array for every node as done in the previous approach.

In order to implement this, we've made use of a
N
o
d
e
Node class which represents a node of a tree. Each node represents a process. Thus, every node stores its own value(
N
o
d
e
.
v
a
l
Node.val) and the list of all its direct children(
N
o
d
e
.
c
h
i
l
d
r
e
n
Node.children). We traverse over the whole
p
i
d
pid array and create nodes for all of them. Then, we traverse over the
p
p
i
d
ppid array, and make the parent nodes out of them, and at the same time add all their direct children nodes in their
N
o
d
e
.
c
h
i
l
d
r
e
n
Node.children list. In this way, we convert the given process structure into a tree structure.

Now, that we've obtained the tree structure, we can add the node to be killed to the return list
l
l. Now, we can directly obtain all the direct children of this node from the tree, and add its direct children to the return list. For every node added to the return list, we repeat the same process of obtaining the children recursively.


Complexity Analysis

Time complexity :
O
(
n
)
O(n). We need to traverse over the
p
p
i
d
ppid and
p
i
d
pid array of size
n
n once. The getAllChildren function also takes atmost
n
n time, since no node can be a child of two nodes.

Space complexity :
O
(
n
)
O(n).
m
a
p
map of size
n
n is used.

Approach #3 HashMap + Depth First Search [Accepted]

Algorithm

Instead of making the tree structure, we can directly make use of a data structure which stores a particular process value and the list of its direct children. For this, in the current implementation, we make use of a hashmap
m
a
p
map, which stores the data in the form
p
a
r
e
n
t
:
[
l
i
s
t
o
f
a
l
l
i
t
s
d
i
r
e
c
t
c
h
i
l
d
r
e
n
]
parent:[listofallitsdirectchildren].

Thus, now, by traversing just once over the
p
p
i
d
ppid array, and adding the corresponding
p
i
d
pid values to the children list at the same time, we can obtain a better structure storing the parent-children relationship.

Again, similar to the previous approach, now we can add the process to be killed to the return list, and keep on adding its children to the return list in a recursive manner by obtaining the child information from the structure created previously.

Current
1 / 8
**Complexity Analysis**
Time complexity :
O
(
n
)
O(n). We need to traverse over the
p
p
i
d
ppid array of size
n
n once. The getAllChildren function also takes atmost
n
n time, since no node can be a child of two nodes.

Space complexity :
O
(
n
)
O(n).
m
a
p
map of size
n
n is used.

Approach #4 HashMap + Breadth First Search [Accepted]:

Algorithm

We can also make use of Breadth First Search to obtain all the children(direct+indirect) of a particular node, once the data structure of the form
(
p
r
o
c
e
s
s
:
[
l
i
s
t
o
f
a
l
l
i
t
s
d
i
r
e
c
t
c
h
i
l
d
r
e
n
]
(process:[listofallitsdirectchildren] has been obtained. The process of obtaining the data structure is the same as in the previous approach.

In order to obtain all the child processes to be killed for a particular parent chosen to be killed, we can make use of Breadth First Search. For this, we add the node to be killed to a
q
u
e
u
e
queue. Then, we remove an element from the front of the
q
u
e
u
e
queue and add it to the return list. Further, for every element removed from the front of the queue, we add all its direct children(obtained from the data structure created) to the end of the queue. We keep on doing so till the queue becomes empty.

Current
1 / 9

Complexity Analysis

Time complexity :
O
(
n
)
O(n). We need to traverse over the
p
p
i
d
ppid array of size
n
n once. Also, atmost
n
n additions/removals are done from the
q
u
e
u
e
queue.

Space complexity :
O
(
n
)
O(n).
m
a
p
map of size
n
n is used.
     */

    class Node {
        int val;
        List<Node> children = new ArrayList<>();
    }
    
    public List <Integer> killProcess(List <Integer> pid, List <Integer> ppid, int kill) {
        HashMap<Integer, Node> map = new HashMap <> ();
        for (int id: pid) {
            Node node = new Node();
            node.val = id;
            map.put(id, node);
        }
        for (int i = 0; i <ppid.size(); i++) {
            if (ppid.get(i)> 0) {
                Node par = map.get(ppid.get(i));
                par.children.add(map.get(pid.get(i)));
            }
        }
        List <Integer> l = new ArrayList <> ();
        l.add(kill);
        getAllChildren(map.get(kill), l);
        return l;
    }

    public void getAllChildren(Node pn, List <Integer> l) {
        for (Node n: pn.children) {
            l.add(n.val);
            getAllChildren(n, l);
        }
    }
}
