1.) First you insert 2,5 and 16. 2 being the root, 5 being the left child and 6 being the right child. This adheres to the min heap structure (parent is less than the children) so no swaps occur.
Then we add 4. 4 would be in the left of it's parent (5) BUT it is less than 5 so it must upheap. 4 replaces the place of 5 and vice versa so now, 4 is of index 1 and 5 is of index 3
Then we add 10. 10 > 4 so it can stay a child of 4 and no upheaping needs to happen.
Then we add 23 as the left child of 16. 16 < 23 so it can remain it's child and no upheaping needs to occur.
Then we add 39 the right child of 16. 16 < 39 so it can remain it's child and no upheaping needs to occur.
Then we add 18 the left child of 5. 5 < 18 so it can remain it's child and no upheaping needs to occur.
Then we add 26 the right child of 5. 5 < 26 so it can remain it's child and no upheaping needs to occur.
Then we add 15 the left child of 10. 10 < 15 so it can remain it's child and no upheaping needs to occur.

After all this, your heap should look like this:
[2,4,16,5,10,23,39,18,26,15]

2.) 2,5,4,18,26,10,15,16,23,39 (as preorder goes from root to left to right)

3.) 18,26,4,15,10,5,23,39,16,2 (as post order is bottom to top, left, right node)

4.) Yes you can. You can see from the previous questions that the arrays given didn't end up making the heap sorted after pre and postorder. Another example could be a heap that looks like this: [2,8,10,50,9]
Preorder: 2,8,50,9,10
Postorder: 50,9,8,10,2 
From these results the two traversals do not list them in descending or ascending order.


