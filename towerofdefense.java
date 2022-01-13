
package sem5collexpt;

import java.util.*;

class Node {

    // Coordinates
    public int i, j;

    // Parent node
    public Node parent;

    // H(n)
    public int hcost;

    // F(n)=H(n)+G(n)
    public int finalCost;
    
    //If Node is part of solution path
    public boolean solution;

    public Node(int i, int j) {
        this.i=i;
        this.j=j;
    }
    
    @Override
    public String toString() {
        return "["+i+", "+j+"]";
    }
}


public class towerofdefense {
 public static final int DIAGONAL_COST = 14;// Diagonal cost
    public static final int V_H_COST = 10;// Vertical or horizontal cost

    public static int steps = 1;

    private Node[][] grid;

    // priority queue
    private PriorityQueue<Node> openNodes;

    // already evaluated
    private boolean[][] closedNodes;

    private int startI, startJ, endI, endJ;

    public towerofdefense(int width, int height, int si, int sj, int ei, int ej, int[][] blocks) {
        grid = new Node[width][height];
        closedNodes = new boolean[width][height];
        openNodes = new PriorityQueue<Node>((Node n1, Node n2) -> {
            return n1.finalCost < n2.finalCost ? -1 : n1.finalCost > n2.finalCost ? 1 : 0;
        });

        // Initializing the start and end nodes
        startNode(si, sj);
        endNode(ei, ej);

        // H(n)
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Node(i, j);
                // set the h(n) value
                grid[i][j].hcost = this.hOfNode(i, j, endI, endJ);
                grid[i][j].solution = false;
            }
        }

        grid[startI][startJ].finalCost = 0;

        // Set the barriers
        for (int i = 0; i < blocks.length; i++) {
            addBlockOnNode(blocks[i][0], blocks[i][1]);
        }
    }

    public void addBlockOnNode(int i, int j) {
        grid[i][j] = null;
    };

    public void startNode(int i, int j) {
        startI = i;
        startJ = j;
    };

    public void endNode(int i, int j) {
        endI = i;
        endJ = j;
    }

    // Update F(n)
    public void updateCostIfNeeded(Node current, Node t, int cost) {
        if (t == null || closedNodes[t.i][t.j]) {
            return;
        }

        int tFinalCost = t.hcost + cost;
        boolean isOpen = openNodes.contains(t);

        // If not in open queue and the new value is less
        if (!isOpen || tFinalCost < t.finalCost) {
            t.finalCost = tFinalCost;
            t.parent = current;

            if (!isOpen) {
                openNodes.add(t);
            }
        }
    }

    // Calculate H(n)
    public int hOfNode(int i, int j, int endI, int endJ) {
        return Math.abs(i - endI) + Math.abs(j - endJ);
    }

    public void find() {
        openNodes.add(grid[startI][startJ]);
        Node current;

        while (true) {
            current = openNodes.poll();

            // Display F(n) value at each step
            System.out.println("FRONTIER: "+openNodes);
            System.out.println("Step: " + steps);
            this.displayScores();

            // Blocked Node
            if (current == null) {
                break;
            }

            // visited
            closedNodes[current.i][current.j] = true;

            // Destination node
            if (current.equals(grid[endI][endJ]))
                return;

            Node t;

            if (current.i - 1 >= 0) {

                // Left
                t = grid[current.i - 1][current.j];
                updateCostIfNeeded(current, t, current.finalCost + V_H_COST);

                // Left upper diagonal
                if (current.j - 1 >= 0) {
                    t = grid[current.i - 1][current.j - 1];
                    updateCostIfNeeded(current, t, current.finalCost + DIAGONAL_COST);
                }

                // Left lower diagonal
                if (current.j + 1 < grid[0].length) {
                    t = grid[current.i - 1][current.j + 1];
                    updateCostIfNeeded(current, t, current.finalCost + DIAGONAL_COST);
                }
            }

            // down move
            if (current.j - 1 >= 0) {
                t = grid[current.i][current.j - 1];
                updateCostIfNeeded(current, t, current.finalCost + V_H_COST);
            }

            // up move
            if (current.j + 1 < grid[0].length) {
                t = grid[current.i][current.j + 1];
                updateCostIfNeeded(current, t, current.finalCost + V_H_COST);
            }

            if (current.i + 1 < grid.length) {
                // right move
                t = grid[current.i + 1][current.j];
                updateCostIfNeeded(current, t, current.finalCost + V_H_COST);

                // right upper diagonal move
                if (current.j - 1 >= 0) {
                    t = grid[current.i + 1][current.j - 1];
                    updateCostIfNeeded(current, t, current.finalCost + DIAGONAL_COST);
                }

                // Right down diagonal move
                if (current.j + 1 < grid[0].length) {
                    t = grid[current.i + 1][current.j + 1];
                    updateCostIfNeeded(current, t, current.finalCost + DIAGONAL_COST);
                }

            }
            steps++;
        }
    }

    public void display() {
        System.out.println("Initial Grid: ");

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                // source
                if (i == startI && j == startJ) {
                    System.out.print("S   ");
                }
                // Destination
                else if (i == endI && j == endJ) {
                    System.out.print("D   ");
                }
                // Blocked
                else if (grid[i][j] != null) {
                    System.out.printf("%-3d ", 0);
                }

                else {
                    System.out.print("B   ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void displayScores() {
        System.out.println("F(n) values of nodes: ");

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != null) {
                    System.out.printf("%-3d ", grid[i][j].finalCost);
                } else {
                    System.out.print("B   ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void displaySolution() {
        if (closedNodes[endI][endJ]) {
            System.out.println("Path: ");
            Node current = grid[endI][endJ];
            System.out.print(current);
            grid[current.i][current.j].solution = true;

            while (current.parent != null) {
                System.out.print("->" + current.parent);
                grid[current.parent.i][current.parent.j].solution = true;
                current = current.parent;
            }

            System.out.println("\n\nFinal path: ");

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {

                    // source
                    if (i == startI && j == startJ) {
                        System.out.print("S  ");
                    }
                    // Destination
                    else if (i == endI && j == endJ) {
                        System.out.print("D  ");
                    }
                    // Blocked
                    else if (grid[i][j] != null) {
                        System.out.printf("%-3s", grid[i][j].solution ? "X" : "0");
                    } else {
                        System.out.print("B  ");
                    }
                }
                System.out.println();
            }
            System.out.println();

        } else {
            System.out.println("No possible path");
        }
    }

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        System.out.println("Enter the size of board: ");
        int width = scn.nextInt();

        System.out.println("Enter the starting co-ordinates of board(i j): ");
        int startI = scn.nextInt();
        int startJ = scn.nextInt();

        System.out.println("Enter the destination co-ordinates of board(i j): ");
        int endI = scn.nextInt();
        int endJ = scn.nextInt();

        System.out.println("Enter the number of towers(Obstacles): ");
        int n = scn.nextInt();
        int[][] blocked = new int[n][2];

        System.out.println("Enter the co-ordinates of towers(i j): ");
        for (int i = 0; i < n; i++) {
            blocked[i][0] = scn.nextInt();
            blocked[i][1] = scn.nextInt();
        }

//      AStar aStar = new AStar(5, 5, 0, 0, 3, 2,
//              new int[][] { { 0, 4 }, { 2, 2 }, { 3, 1 }, { 3, 3 }, { 2, 1 }, { 2, 3 } });

        towerofdefense aStar = new towerofdefense(width, width, startI, startJ, endI, endJ, blocked);

        System.out.println();
        aStar.display();
        aStar.find();// A*
        System.out.println("Final F(n) value: ");
        aStar.displayScores();// Display F(N)
        aStar.displaySolution();// Display solution
    }

}



