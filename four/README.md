## 最后一轮了

|  | No. | Name | Desc. |
|:--|:----|------|-------|
| 1 | 509 | **Fibonacci Number** | 没什么说的，用一下memo就可以了 | 
| 2 | 322 | **Coin Change** | 这个需要用到背包问题，还是我薄弱的地方 |
| 3 | 46 | **Permutations** | 递归问题，传入一个计数器即可，用数组比用map快 |
| 4 | 51 | **N-Queens** | 虽然有点慢，但是思路很对，就不纠结了 |
| 5 | 111 | **Minimum Depth of Binary Tree** | 广度优先，判定节点空的情况 |
| 6 | 752 | **Open the Lock** | 双向奔赴，就快了；从起始点和结束点同时开始广度优先 |
| 7 | 104 | **Maximum Depth of Binary Tree** | 这个不说了 |
| 8 | 144 | **Binary Tree Preorder Traversal** | 这个不说了 |
| 9 | 543 | **Diameter of Binary Tree** | 直接使用maxDepth进行递归 |
| 10 | 704 | **Binary Search** | 不说了 |
| 11* | 34 | **Find First and Last Position of Element in Sorted Array** | 这道题目，还是要注意一下，用例子好！ |
| 12 | 76 | **Minimum Window Substring** | 思路一样，还是数组比map速度快，同 46 |
| 13 | 567 | **Permutation is String** | 这个不就是一样的最小窗口，然后用数组而不是map比较么，我证明了，对的 |
| 14 | 438 | **Find All Anagrams in a String** | 和567一模一样 |
| 15 | 3 | **Longest Substring Without Repeating Characters** | 这个也是左右窗口，加统计啦 |
| 16 | 21 | **Merge Two Sorted Lists** | 简单 |
| 17 | 23 | **Merge K Sorted Lists** | 明明是高难度，为啥我觉得很简单 |
| 18 | 19 | **Remove Nth Node From End of List** | 就是快慢指针 |
| 19 | 876 | **Middle of the Linked List** | 就是快慢指针 |
| 20 | 141 | **Linked List Cycle** | 就是快慢指针 |
| 21 | 142 | **Linked List Cycle II** | 就是快慢指针，然后把一个指针移动到开始，再走到一起 |
| 22 | 160 | **Intersection of Two Linked Lists** | 走一遍，碰到就碰到，不碰到就是没有 |
| 23 | 26 | **Remove Duplicates from Sorted Array** | 左右指针，重复不动，不重复右移 |
| 24 | 83 | **Remove Duplicates from Sorted List** | 同26，也是指针 |
| 25 | 27 | **Remove Element** | 和26几乎一模一样，最多就是要检查后面的内容 |
| 26 | 283 | **Move Zeroes** | 就是把非0的往前移动，最后都设置成0 |
| 27 | 167 | **Two Sum II - Input Array Is Sorted** | 又是一道左右指针的题目 |
| 28 | 344 | **Reverse String** | 不会做是傻子 |
| 29 | 5 | **Longest Palindromic Substring** | 判定两种情况，i和i，i+1 |
| 30 | 303 | **Range Sum Query - Immutable** | 需要创建一个presum数组，一维的 |
| 31 | 304 | **Range Sum Query 2D - Immutable** | 需要创建一个presum数组，二维的 |
| 32 | 370 plus | **Range Addition** | 就是用差数列，然后再计算，是一道plus |
| 33 | 1109 | **Corporate Flight Bookings** | 就是一个差分数组，添加第一项和第N + 1 项 |
| 34 | 1094 | **Car Pooling** | 和1109类似，但是需要结束的条件不同，因为第N项就下车了 |
| 35 | 146 | **LRU Cache** | DoulbeLink很好，解决很方便 |
| 36 | 460 | **LFU Cache** | DoulbeLink很好，解决很方便 |
| 37 | 380 | **Insert Delete GetRandom O(1)** | 思路是一样的，不过一个用了List，一个是array |
| 38 | 710 | **Random Pick with Blacklist** | 原来是Random的不同，好吧 |
| 39 | 496 | **Next Greater Element I** | 用堆记录最近一个大的数值，如果小了，就抛出 |
| 40 | 739 | **Daily Temperatures** | 用一个堆来计算，不过记录的是index，不是value |
| 41 | 503 | **Next Greater Element II** | 这个其实不难，就是要计算2n - 1 而不是n - 1，并且加入的是index会快 |
| 42 | 239 | **Sliding Window Maximum** | 也是加入index好算很多 |
| 43 | 226 | **Invert Binary Tree** | 把左右子树换一下 |
| 44 | 116 | **Populating Next Right Pointers in Each Node** | 就是需要考虑三种情况，左左右，左右右左，右左右，用广度遍历更好做 |
| 45 | 114 | **Flatten binary Tree to Linked List** | 就是递归，把左右拉平，然后左先放入右，再添加右 |
| 46 | 654 | **Maximum Binary Tree** | 找到最大节点，然后左边的构建左子树，右边构建右子树 |
| 47 | 105 | **Construct Binary Tree from Preorder and Inorder** | 方式很简单，加一个查找map就可以了 |
| 48 | 106 | **Construct Binary Tree from Inorder and Postorder** | 和上面那个一样 |
| 49 | 889 | **Construct Binary Tree from Preorder and Postorder** | 这个有两种可能性 |
| 50 | 297 | **Serialize and Deserialize Binary Tree** | 前序遍历，很好的解决了问题 |
| 51 | 912 | **Sort an Array** | 记住归并排序，我还希望写一下快速排序 |
| 52 | 315 | **Count of Smaller Numbers After Self** | 用归并排序中，左边数组的交换情况记录smaller数据的情况 |
| 53 | 230 | **Kth Smallest Element in a BST** | 计算左子树，如果大了，就不计算；如果刚好，就返回；如果小了，就计算右子树 |
| 54 | 538 | **Convert BST to Greater Tree** | 从最右边的节点开始计算，然后累加；类似中序，但是先算右子树 |
| 55 | 98 | **Validate Binary Search Tree** | 从根节点开始，左边需要根节点作为最大值；右边需要根节点作为最小值 |
| 56 | 700 | **Search in a Binary Search Tree** | 这个太简单了 |
| 57 | 701 | **Insert into a Binary Search Tree** | 如果小于根节点，看看能不能创建左节点；反之右节点 |
| 58 | 450 | **Delete Node in a BST** | 关键是找到右边的最小节点或者左边的最大节点 |
| 59 | 215 | **Kth Largest Element in an Array** | 需要优化一下，通过快速排序，找到第K个 |
| 60 | 797 | **All Paths From Source to Target** | 加节点，然后看一下节点的路径，深度优先 |
| 61 | 323 plus | **Number of Connected Components in an Undirected Graph** | 用Union Form做，加入size快很多 |
| 62 | 130 | **Surrounded Regions** | 深度优先，改坐标；当然了，用uf也行 |
| 63 | 990 | **Satisfiability of Equality Equations** | 这个用uf是很好的 |
| 64 | 261 plus | **Graph Valid Tree** | 同323 |
| 65 | 1135 plus | **Connecting Cities With Minimum Cost** | 同323 |
| 66 | 1584 | **Min Cost to Connect All Points** | 也是323啊 |
| 67 | 78 | **Subsets** | 深度优先，不过有顺序 |
| 68 | 77 | **Combinations** | 判定一下有没有到确认大小，深度遍历，有顺序 |
| 69 | 46 | **Permutations** | 用了数组，就快很多 |
| 70 | 90 | **Subsets II** | 先排序，然后判定在同层循环时，i + 1和之前那个不重复 |
| 71 | 40 | **Combination Sum II** | 这个没法用背包问题解决的原因是，前n个可能有重复情况，比如i和i+1都是1，那么会被计算2次 |
| 72 | 47 | **Permutations II** | 看来还是差别在数组和map了 |
| 73 | 39 | **Combination Sum** | 这个可以用背包问题，但是它要的是具体结果，不是统计数量 |
| 74 | 698 | **Partition to K Equal Sum Subsets** | 需要通过memo防止重复计算 |
| 75 | 200 | **Number of Islands** | 典型的深度优先，回溯算法 |
| 76 | 1254 | **Number of Closed Islands** | 在计算边界的时候可以少算两次 |
| 77 | 1020 | **Number of Enclaves** | 最后一次是累加，不用再深度了 |
| 78 | 695 | **Max Area of Island** | 设定一个成员变量，算一下最大值 |
| 79 | 1905 | **Count Sub Islands** | 去除不可能是sub island的部分，然后计算个数 |
| 80 | 694 plus | **Number of Distinct Islands** | 需要记录每次前进和后退的方向 |
| 81 | 773 | **Sliding Puzzle** | 这个其实简单的，需要把二维变成一维；然后算一下可以走的方式 |
| 82 | 931 | **Minimum Falling Path Sum** | 这就是经典的回溯啊 |
| 83 | 300 | **Longest Increasing Subsequence** | 用二分牌堆算法，最快了 |
| 84 | 354 | **Russian Doll Envelopes** | 同300 |
| 85 | 53 | **Maximum Subarray** | 如果是正值那么统计下一个，否则下一个单独统计 |
| 86 | 72 | **Edit Distance** | Normal | 11'11" | 97.86% |
| 87 | 1143 | **Longest Common Subsequence** | Normal | 4'50" | 5.78% |
| 88 | 583 | **Delete Operation for Two Strings** | Normal | 3'01" | 9.62% |
| 89 | 712 | **Minimum ASCII Delete Sum for Two Strings** | Normal | 3'28" | 12.03% |
| 90 | 10 | **Regular Expression Matching** | Hard | 17'37" | 100.00% |
| 91 | 516 | **Longest Palindromic Subsequence** | Normal | 31'26" | 5.96% |
| 92 | 1312 | **Minimum Insertion Steps to Make a String Palindrome** | Hard | 1'20" | 9.46% |
| 93 | 416 | **Partition Equal Subset Sum** | Normal | 25'59" | 55.43% |
| 94 | 518 | **Coin Change II** | Normal | 25'59" | 84.70% |
| 95 | 64 | **Minimum Path Sum** | Normal | 6'42" | 99.79% |
| 96 | 174 | **Dungeon Game** | Hard | 11'44" | 100.00% |
| 97 | 887 | **Super Egg Drop** | Hard | 24'14" | 52.35% |
| 98 | 312 | **Burst Balloons** | Hard | 25'12" | 92.75% |
| 99 | 206 | **Reverse Linked List** | Easy | 2'49" | 100.00% |
| 100 | 92 | **Reverse Linked List II** | Normal | 9'38" | 100.00% |
| 101 | 870 | **Advantage Shuffle** | Normal | 10'43" | 24.90% |
| 102 | 316 | **Remove Duplicate Letters** | Normal | 18'12" | 70.83% |
| 103 | 528 | **Random Pick with Weights** | Normal | 9'52" | 57.10% |
| 104 | 875 | **Koko Eating Bananas** | Normal | 6'27" | 53.74% |
| 105 | 1011 | **Capacity To Ship Packages Within D Days** | Normal | 6'47" | 86.26% |
| 106 | 410 | **Split Array Largest Sum** | Hard | 1'51" | 100.00% |
| 107 | 42 | **Trapping Rain Water** | Hard | 6'30" | 24.96% |
| 108 | 11 | **Container With Most Water** | Normal | 3'20" | 94.18% |
| 109 | 15 | **3Sum** | Normal | 14'54" | 32.53% |
| 110 | 18 | **4Sum** | Normal | 22'33" | 31.68% |
| 111 | 236 | **Lowest Common Ancestor of a Binary Tree** | Normal | 2'22" | 99.73% |
| 112 | 1644 plus | **Lowest Common Ancestor of a Binary Tree II** | 需要记录那个找到的点，否则靠递归会晕的 |
| 113 | 1650 plus | **Lowest Common Ancestor of a Binary Tree III** | 这个只需要不断的找parent，两个点的parent重复就行了 |
| 114 | 1676 plus | **Lowest Common Ancestor of a Binary Tree IV** | 这个其实和两个点是一样的，只不过要都浏览一下 |
| 115 | 235 | **Lowest Common Ancestor of a Binary Search Tree** | Normal | 2'11" | 99.91% |