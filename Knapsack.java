package tester;



//Student Name: Satvir Singh 01756353


import java.util.PriorityQueue;
public class Knapsack

{
    private static int knapsack(int n, int W, int w[], int p[], NodeStorageVariable nNodes)
    {

        // Best-First search…. branch-and-bound pruning

        PriorityQueue<Node> queue = new PriorityQueue<>();
        int maxProfit = 0;
        nNodes.value = 0;

        Node u, v;
        v = new Node();
        u = new Node();
        v.bound = bound(v, n, W, w, p);
        queue.add(v);
        nNodes.value++;

        while (queue.size() > 0)

        {

            v = queue.remove();
            if (v.bound > maxProfit)
            {

                u.level = v.level + 1;
                u.weight = v.weight + w[u.level];
                u.profit = v.profit + p[u.level];

                if (u.weight <= W && u.profit > maxProfit)
                {
                    maxProfit = u.profit;

                }
                u.bound = bound(u, n, W, w, p);
                if (u.bound > maxProfit)
                {

                    queue.add(u);
                    nNodes.value++;

                }

                u.weight = v.weight;
                u.profit = v.profit;
                u.bound = bound(u, n, W, w, p);
                if (u.bound > maxProfit)

                {

                    queue.add(u);
                    nNodes.value++;

                }

            }

        }

        return maxProfit;
    }
    private static float bound(Node u, int n, int W, int w[], int p[])
    {

        // get the bound for the node you’re on
        int j, k;

        int totweight;

        float result;
        if (u.weight >= W)

        {
            return 0;
        }
        else

        {

            result = u.profit;

            j = u.level + 1;
            totweight = u.weight;
            while (j < n && totweight + w[j] < W)

            {
                totweight = totweight + w[j];

                result = result + p[j];

                j++;

            }
            k = j;

            if (k < n)

            {
                result += result + (W - totweight) * p[k] / w[k];

            }
        }
        return result;

    }

    private static void printInfo(int n, int W, int p[], int w[], int maxProfit, NodeStorageVariable nNodes)

    {
        // Print info for an instance of the knapsack problem.

        System.out.print("Profits = {");

        // printing stuff, fancy fancy

        for (int i = 0; i < n; i++)

        {
            System.out.print(p[i] + ((i == n - 1) ? "}" : ", "));
        }
        System.out.println();
        System.out.print("Weights = {");
        for (int i = 0; i < n; i++)
        {
            System.out.print(w[i] + ((i == n - 1) ? "}" : ", "));

        }

        System.out.println();



        System.out.println("Item Count = " + n);
        System.out.println("Max Weight = " + W);
        System.out.println("Node Count = " + nNodes.value);
        System.out.println("Maxprofit = " + maxProfit);
        System.out.println();
    }
  public static void main(String[] args)
    {
        // Three instances below:
        int FirstNode = 5;
        int W1 = 13;
        int p1[] =
        {
            20, 30, 35, 12, 3
        };
        int w1[] =
        {
            2, 5, 7, 3, 1
        };

        
        int SecondNode = 6;
        int W2 = 10;
        int p2[] =
        {
            2, 10, 12, 14, 16, 18
        };
        int w2[] =
        {
            2, 3, 4, 5, 6, 7
        };

        
        int ThirdNode = 7;
        int W3 = 35;
        int p3[] =
        {
            25, 10, 12, 28, 30, 42, 50
        };
        int w3[] =
        {
            1, 3, 5, 7, 9, 11, 13, 15 
        };


        NodeStorageVariable nNodes1 = new NodeStorageVariable();

        NodeStorageVariable nNodes2 = new NodeStorageVariable();

        NodeStorageVariable nNodes3 = new NodeStorageVariable();



        // get the max profit

        int maxProfit1 = knapsack(FirstNode, W1, w1, p1, nNodes1);

        int maxProfit2 = knapsack(SecondNode, W2, w2, p2, nNodes2);

        int maxProfit3 = knapsack(ThirdNode, W3, w3, p3, nNodes3);


        printInfo(FirstNode, W1, w1, p1, maxProfit1, nNodes1);

        printInfo(SecondNode, W2, w2, p2, maxProfit2, nNodes2);

        printInfo(ThirdNode, W3, w3, p3, maxProfit3, nNodes3);

    }

}



class NodeStorageVariable

{

    // For passing an integer by reference.

    public int value;



    public NodeStorageVariable()

    {

        this(0);

    }



    public NodeStorageVariable(int value)

    {

        this.value = value;

    }

}



class Node implements Comparable

{

    // A node in the 0-1 knapsack problem.

    public int level;

    public int profit;

    public int weight;

    public float bound;



    public Node()

    {

        this.bound = 0;

        this.profit = 0;

        this.weight = 0;

        this.level = 0;

    }



    @Override

    public int compareTo(Object o)

    {

        Float b1 = this.bound;

        Float b2 = ((Node) o).bound;



        return b1.compareTo(b2);

    }

}
