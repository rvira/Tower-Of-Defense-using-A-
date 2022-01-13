
## Implementation of a Tower Defense game 

There are many enemies that are all headed to the same place. You can place towers anywhere, and they act as obstacles that affect the paths taken by enemies.

You can place towers anywhere, and they act as obstacles that affect the paths taken by enemies
Implement the above strategy using **A-star algorithm.**

- Show the status of Frontier  at every stage
- Show the final solution path the enemies have taken to reach to the destination
- Implement heuristic Function for A* and show the Nodes in Frontier at each stage with F() value
- Show analysis of both the algorithms with respect to 

  1. Total No. of nodes getting generated
  2. Optimal solution 
  3. Time Complexity and Space Complexity



# Sample Input/Output:
Enter the size of board: 
4
Enter the starting co-ordinates of board(i j): 
0 
0
Enter the destination co-ordinates of board(i j): 
3
3
Enter the number of towers(Obstacles): 
3
Enter the co-ordinates of towers(i j): 
1 1 
1 2
2 2

Initial Grid:  
S   0   0   0  
0   B   B   0     
0   0   B   0  
0   0   0   D  

FRONTIER: []  
Step: 1  
F(n) values of nodes:  
0   0   0   0  
0   B   B   0     
0   0   B   0  
0   0   0   0  

FRONTIER: [[1, 0]]  
Step: 2  
F(n) values of nodes:  
0   15  0   0  
15  B   B   0   
0   0   B   0  
0   0   0   0  

FRONTIER: [[0, 2]]  
Step: 3  
F(n) values of nodes:  
0   15  29  0  
15  B   B   0   
0   0   B   0  
0   0   0   0  

FRONTIER: [[2, 0], [2, 1]]  
Step: 4  
F(n) values of nodes:  
0   15  29  0  
15  B   B   0    
29  32  B   0  
0   0   0   0  

FRONTIER: [[2, 1], [1, 3], [0, 3]]  
Step: 5  
F(n) values of nodes:  
0   15  29  42  
15  B   B   45  
29  32  B   0  
0   0   0   0  

FRONTIER: [[3, 0], [3, 1], [0, 3], [1, 3]]  
Step: 6  
F(n) values of nodes:  
0   15  29  42  
15  B   B   45  
29  32  B   0  
42  45  0   0     

FRONTIER: [[0, 3], [3, 1], [3, 2], [1, 3]]  
Step: 7  
F(n) values of nodes:  
0   15  29  42    
15  B   B   45  
29  32  B   0  
42  44  47  0  

FRONTIER: [[3, 1], [1, 3], [3, 2]]  
Step: 8  
F(n) values of nodes:  
0   15  29  42  
15  B   B   45  
29  32  B   0  
42  44  47  0  

FRONTIER: [[1, 3], [3, 2]]  
Step: 9  
F(n) values of nodes:   
0   15  29  42  
15  B   B   45  
29  32  B   0  
42  44  47  0  

FRONTIER: [[3, 2]]  
Step: 10  
F(n) values of nodes:  
0   15  29  42  
15  B   B   45  
29  32  B   0  
42  44  47  0  

FRONTIER: [[2, 3]]  
Step: 11  
F(n) values of nodes:  
0   15  29  42  
15  B   B   45  
29  32  B   56  
42  44  47  0  

FRONTIER: [[3, 3]]  
Step: 12  
F(n) values of nodes:  
0   15  29  42  
15  B   B   45  
29  32  B   56  
42  44  47  57  

FRONTIER: []  
Step: 13  
F(n) values of nodes:  
0   15  29  42  
15  B   B   45  
29  32  B   56  
42  44  47  57  

Final F(n) value:  
F(n) values of nodes:  
0   15  29  42  
15  B   B   45  
29  32  B   56  
42  44  47  57  

Path:  
[3, 3]->[3, 2]->[2, 1]->[1, 0]->[0, 0]  

Final path:  
S  0  0  0  
X  B  B  0  
0  X  B  0  
0  0  X  D  
