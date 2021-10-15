package matrixInAndOutlab;
/* 
 * Graph Outlab 5
 * @ William S Cook
 */
import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;



public class Graph 
{
	static Node[] nodes;
	static int[][] matrix;
	static Scanner fileIn = null;
	static int size;
	int[] temp;
	boolean[] v;
	
	class Node
	{
		public int data = 0;
		private ArrayList<AddEdge> list = new ArrayList<AddEdge>();
		
		Node(int value) {this.data = value;}
		
		private void add(AddEdge input)
		{
			if(!list.contains(input))
				list.add(input);	
		}
	}
	
	private class AddEdge
	{
		Node temp1;
		Node temp2;
		int weight;
		
		AddEdge(Node one, Node two, int localWeight)
		{
			temp1 = one;
			temp2 = two;
			weight = localWeight;
		}
		
		private void printEdge()
		{
			System.out.println("From " + temp1.data + " to " + temp2.data + " with a weight" + weight);
		}
	}

	private Graph(String input) 
	{
		try 
		{
			fileIn = new Scanner(new File(input));
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		
		size = fileIn.nextInt();
		nodes = new Node[size];
		matrix = new int[size][size];
		AddEdge tempEdge;
		int digit = 0;
		
		
		for(int i = 0; i < size; i++) 
		{
			for(int j = 0; j < size; j++)
			{
				matrix[i][j] = fileIn.nextInt();
			}
		}
		
		try 
		{
			fileIn = new Scanner(new File(input));
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		for (int i = 0; i < size; i++) 
		{
		    nodes[i] = addNode(i);
		    for (int j = 0; j < size ; j++) 
		    {
		        digit = fileIn.nextInt();
		        if (digit > 0) 
		        	{
		        		tempEdge = new AddEdge(nodes[i], nodes[j], matrix[i][j]);
		        		nodes[i].add(tempEdge);
		        	}
		    }
		}
		
		fileIn.close();
	}
	
	public Node addNode(int value) {return new Node(value);}
	
	private void findNeighbor(int number)
	{
		temp = new int[size];
		System.out.print("\nNode " + (number-1) + " is connected to:" );
		for(int i = 0; i<size; i++)
		{
			if(matrix[number-1][i] > 0)
			{
				temp[i] = i;
				System.out.print(" " + temp[i]);
			}
		}
	}
	private void bfs(int n)
	{
		System.out.print("\nUsing BFS and starting from index " + n + " we can reach indexes = ");
        PriorityQueue q = new PriorityQueue();
        boolean[] v = new boolean[size];
        
        v[n] = true;
        q.add(n);
        
        while(!q.isEmpty())
        {
        	n = (int)q.poll();
        	
        	
        	for(int i = 0; i < v.length; i++)
        	{
        		if(!v[i])
        		{
        			v[i] = true;
        			q.add(i);
        		}
        	}

			System.out.print(n + " ");
        }
	}
	
	private void dfs(int n)
	{
		v = new boolean[size];
		int one = n;
		
		System.out.print("\nUsing DFS and starting from index " + n + " we can reach indexes = ");
		this.dfs(n, 0, one);
	}
	private void dfs(int n, int k, int one)
	{
		for(int j = k; j < size; j++)
		{
			if((matrix[n][j] != 0) && v[j] == false)
			{
				if(j != one)
				{
					System.out.print(" " + j);
				}
				v[j] = true;
				dfs(j, j, one);
			}
		}
	}
	
	
	private void dijkstra(int start) 
	{
		@SuppressWarnings("rawtypes")
		PriorityQueue<Integer> q = new PriorityQueue();
		PriorityQueue<Integer> q2 = new PriorityQueue();
		int[] number = new int[size];
		v = new boolean[size];	
		int c = 0;
		

		for(int j = start; j < size; j++) 
		{	
			if(size != c)
			{
				if(v[j] == true)
				{
					c--;
				}
				else
				{
					c++;
				}
				for(int k = 0; k < size; k++)
				{
					if(matrix[j][k] != 0 && v[k] == false)
					{
						if((matrix[j][k] + number[j]) < number[k] || number[k] == 0)
						{
							
							number[k] = matrix[j][k] + number[j];
							q.add(number[k]);
							
						}
					}
				}
				v[j] = true;
				if(q.peek() == null)
				{
					break;
				}
				else
				{
					int count = 0;
					int temp = ((int)q.poll());
					for(int h = 0; h < number.length; h++)
					{
						if(number[h] == temp)
						{
							count = h;
							q2.add(h);
						}
					}
					j = count;
					j--;
				}
			}
			else
			{
				break;
			}
		}

		for(int i = 0; i < size; i++) 
		{
			if(i != start)
			{
				if(number[i] != 0)		
				{
					System.out.print("\nUsing Dijkstra, the shortest path from node # " + start + " to node # " + i + " has a weight of " + number[i] + " with a path: " + q2);
				}
				else 					{System.out.print("\nUsing Dijkstra, the shortest path from node # " + start + " to node # " + i + " has a weight of " + number[i] + " with a path: no path");}
			}
		} 
	}
	
	private void warshall(int input)
	{
		boolean[][] w = new boolean [size][size];
		
		for(int j = 0; j < size; j++)
		{
			for(int k = 0; k < size; k++)
			{
				if(matrix[j][k] == 0)
				{
					w[j][k] = false;
				}
				else
				{
					w[j][k] = true;
				}
			}
		}
		
		for(int j = 0; j < size; j++)
		{
			for(int k = 0; k < size; k++)
			{
				for(int i = 0; i < size; i++)
				{
					if(!w[k][i])
					{
						w[k][i] = w[k][j] & w[j][i];
					}
				}
			}
		}
		System.out.print("\nUsing Warshall's, the nodes we can reach from " + input + " are:");
		for(int j = 0; j< size; j++)
		{
			if(input != j)
			{
				if(w[input][j])
				{
					System.out.print(" " + j);
				}
			}
		}
	}

	private void prim(int start)
	{
		PriorityQueue q = new PriorityQueue();
		int[] weight = new int[size];
		int[] travel = new int[size];
		v = new boolean[size];
		
		System.out.println("\n\nUsing Prim, the MST starting from node # " + start + " is:");
		for(int j = start; j < size; j++)
		{
			v[j] = true;
			for(int k = 0; k < size; k++)
			{
				if((matrix[j][k] != 0 && v[k] == false))
				{
					if(matrix[j][k]<weight[k] || weight[k] == 0)
					{
						travel[k] = j;
						weight[k] = matrix[j][k];
						q.add(weight[k]);
					}
				}
			}
			
			if(q.peek() == null)
			{
				break;
			}
			else
			{
				int count = 0;
				int temp = ((int)q.poll());
				for(int h = 0; h < weight.length; h++)
				{
					if(weight[h] == temp)
					{
						count = h;
					}
				}
				j = count;
				j--;
			}
		}
		
		for(int j = 0; j < size; j++)
		{
			if(j != start && weight[j] != 0)
			{
				System.out.println("From " + travel[j] + " the weight will be " + weight[j]);
			}
		}
	}
	
	private void printGraph()
	{
		System.out.println("Graph: (Adjacency Matrix)");
		for(int i = 0; i<size; i++)
		{
			for(int j = 0; j<size; j++) 
			{
				System.out.print(matrix[i][j] + " ");
				if(j+1==size)
				{
					System.out.println();
				}
			}
		}
	}
	
	//Main
	public static void main(String[] args) 
	{
		
		
		Graph g = new Graph("input.txt");
 
		g.printGraph();
		
		for(int i = 1; i <= g.size; i++)
		{
			g.findNeighbor(i);
		}
		System.out.println("\n");
		
		g.bfs(2);
		g.bfs(5);
		System.out.println("\n");
		
		g.dfs(2);
		g.dfs(5);
		System.out.println("\n");
		
		g.warshall(2);
		g.warshall(5);
		System.out.println("\n");
		
		Graph g2 = new Graph("inputw.txt");
		

		g2.printGraph();
		
		for(int i = 1; i <= g.size; i++)
		{
			g.findNeighbor(i);
		}
		
		g2.dijkstra(2);
		
		g2.prim(2);
		
		
		
	}


	
}
