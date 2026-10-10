# BST vs Hash Table Performance Analysis

## 1. Introduction

The purpose of this project is to implement a Binary Search Tree (BST) and a Hash Table in Java and compare their performance.

The experiment evaluates three operations: insertion, searching, and deletion. Both data structures are tested with 100, 1,000, and 10,000 elements.

## 2. Implementation

The BST stores values in nodes. Each node contains a key and references to its left and right children. Smaller values are placed on the left, and larger values are placed on the right.

The Hash Table uses a hash function to calculate the index for each key. Collisions are handled using separate chaining, where elements with the same index are stored in a linked list. The table doubles its capacity when its load factor exceeds 0.75.

Both implementations support insertion, searching, and deletion.

## 3. Time Complexity Analysis

| Operation | BST Average | BST Worst | Hash Table Average | Hash Table Worst |
| --------- | ----------- | --------- | ------------------ | ---------------- |
| Insertion | O(log n)    | O(n)      | O(1) amortized     | O(n)             |
| Searching | O(log n)    | O(n)      | O(1)               | O(n)             |
| Deletion  | O(log n)    | O(n)      | O(1)               | O(n)             |

The average BST complexity assumes that the tree remains reasonably balanced. A standard BST does not guarantee this.

The average Hash Table complexity assumes a suitable distribution of hash values. Collisions can increase the execution time.

## 4. Experimental Results

Both data structures were tested with 100, 1,000, and 10,000 elements. The program measured insertion, searching, and deletion times in milliseconds. Each experiment was repeated five times, and the average execution time was recorded.

| Elements | BST Insert | Hash Insert | BST Search | Hash Search | BST Delete | Hash Delete |
| -------: | ---------: | ----------: | ---------: | ----------: | ---------: | ----------: |
|      100 |   2.359660 |    0.475480 |   0.044960 |    0.048640 |   0.053660 |    0.064180 |
|    1,000 |   0.252240 |    1.037360 |   0.225600 |    0.219000 |   0.293820 |    0.248300 |
|   10,000 |   2.312020 |    3.541180 |   1.989000 |    1.657540 |   2.363540 |    1.281380 |

## 5. Discussion

The results show that neither data structure was faster in every operation and dataset.

For insertion, the BST was faster with 1,000 and 10,000 elements, while the Hash Table was faster with 100 elements. These results may be affected by implementation overhead and JVM optimization.

For searching, the Hash Table performed slightly better with 1,000 elements and more clearly with 10,000 elements. This is consistent with its expected average-case O(1) search complexity when collisions are limited.

For deletion, the Hash Table was faster with 1,000 and 10,000 elements. Its average-case performance is usually O(1), while a standard BST has O(log n) average-case complexity when reasonably balanced.

The measurements for 100 elements were not fully consistent with the larger tests. Very short execution times can be affected by JVM warm-up, system activity, and measurement overhead. Therefore, additional benchmark runs would provide more reliable conclusions.

## 6. Conclusion

The experiment compared a Binary Search Tree and a Hash Table implemented in Java.

In these measurements, the BST performed better in insertion for the two larger datasets. The Hash Table performed better in searching and deletion for the larger datasets.

The results support the idea that Hash Tables are useful for fast lookup by key, while BSTs are useful when maintaining ordered data is important. However, actual performance depends on the implementation, data distribution, and testing environment. A standard BST can also become unbalanced, which increases its operation times to O(n).

