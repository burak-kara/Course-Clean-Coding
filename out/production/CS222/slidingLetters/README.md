# EXAM 1 RULES
* You may NOT have any program other than IntelliJ IDEA open during the exam.
* You may NOT leave the classroom. If you finish early, close your laptop and sleep.


# Sliding Letters

You will implement the sliding letters game. Here, we have an NxN board that contains 
tiles with letters written on them.
There is one tile missing from the board.
We can use this empty slot to shift tiles left/right/up/down.

Here is the initial condition for a 5x5 board:

```
A B C D E
F G H I J
K L M N O
P Q R S T
U V W X  
```

If I do a shift-right by 2, I would obtain:

```
A B C D E
F G H I J
K L M N O
P Q R S T
U V   W X  
```
 
A shift-down by 3 gives me

```
A B C D E
F G   I J
K L H N O
P Q M S T
U V R W X  
```

A board whose tiles are in the initial configuration is said to be "solved".

Implement the missing methods in the `Board` class. 
For testing purposes, we will typically use 3, 4, and 5 as the board size value N,
but your implementation should be generalized to any dimension value N.

You've been provided with a `Main` class and `BoardTest` for your convenience.
You do **NOT** need to refactor `BoardTest` or `Main`. 
Just focus on `Board.java`.

## Grading

* Cleanness: 50%
  - Names: 20%
  - Functions: 20%
  - Format and comments: 10%
* Correctness (auto-graded using the test cases): 50%
  - See the test cases for individual scores. 
  
**NOTE:** Submitting digitCode that does not compile will result in huge penalty.

