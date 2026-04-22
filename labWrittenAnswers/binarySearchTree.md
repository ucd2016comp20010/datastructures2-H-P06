4.) a similar sorting scheme to this would be treesort. 
What treesort does is:

It's average case would be O(n log n) and worst case O(n^2) whereas heap based pqsort would be O(n log n)

This is the outline of the code:
BSTSort(S, T):
while S.isEmpty() is False do
e ← S.removeFirst()
T.put(e, e)
end while

while T.isEmpty() is False do
e ← T.firstEntry()
S.insertLast(e.getKey())
T.remove(e.getKey())
end while

In simple terms:
- that it puts elements into the tree in a standard bst way (elements less than the root on the left, elements greater than the root on the right)
- Perform in order traversal to retrieve the elements in sorted order.
