2.) The largest fibonacci number you can compute in under a minute using my method is about 50 calls. According to my function, it made 40730022162 calls.

4.) The McCarthy 91 function is what we call a nested recursive function (a recursive function in a recursive function)

5a.) What foo does is you give it a number n, it divides the number by 2 each time and prints out the remainder (so it can only be 1 or 0 that it prints out) and when we get to 0/2 it returns 0. So essentially what this is doing is turning decimal to binary.

5b.) 100110100100

6a.) We need the function to take in the head as a parameter so we pass in a node as a parameter. Since this is recursive and we don't want it to run forever, we make a base case. The base case will be that if the current node it's on is null, then to stop. If this isn't met, we call function this time passing the next node down the line. In order to print this in reverse however, we put a print statement followed by a space after the recursive call. This will make it so that when it reaches the base case, the function stops (stop the recursive chain) and since that pass of the function is fully complete, we go backwards, finishing off the previous function calls (which just needed the print statement)


6b.) I had already written a function called reverse. This was iterative and not recursive. I called the function I wrote for this lab "recursiveReverse".

7a.) To copy, we need to have a helper function which takes in a node and first checks if it is null. If it is, we stop the recursion. If it isn't, we make a node, make that current node that new node and have it's next be null. After that, we set the next one of that new node to the helper function but this time referring to the next node. After this we return the new node so that the previous call can refer to that node.

10.) I expected the time complexity to be O(N) as each node is accessed once (because of the structure of the recursion its going as far left as it can and once it can no longer do so, it starts spreading to the right nodes). When you see the diagram however, it seems to dip or jitter a bit and according to some research, it is because of this thing called JIT. What this is doing is it sees that after a bit, we are running the inorder function a lot and it optimises the compilation by compiling it into native machine code which makes it faster even though the size is increasing. You can see that it each line reflects linear time complexity if you look closely
See this link where I learned about this: https://stackoverflow.com/questions/30844146/why-is-java-faster-if-it-repeats-the-same-code?answertab=createdasc#tab-top
