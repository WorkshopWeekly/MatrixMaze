package matrixInAndOutlab;
/* 
 * Graph Outlab 5
 * @ William S Cook
 */
public class Queue 
{
	private int size;
	private int beginning;
	private int behind;
	public int iterator;
	public int list[];
	
	Queue(int s)
	{
		size = s;
		beginning = 0;
		behind = -1;
		iterator = 0;
		list = new int[s];
	}

	public void add(int input)
	{
		if(size == iterator)
		{
			System.exit(1);
		}  
		else  //if the queue isn't full
		{
			behind = (behind+1)%size; 	//so it doesn't go past the matrix size
			list[behind] = input;  		//adds to queue
			iterator++; 				//updates size of queue
		}
	}
	
	public void dequeue()
	{
		if(iterator == 0) //the queue is empty
		{
			System.exit(1);
		}
		else
		{
			beginning = (beginning+1)%size;
			iterator--;
		}
	}
	public int remove()
	{
		int[] temp = list;
		list = new int[size];
		for(int i = 1; i < list.length; i++)
		{
			if(temp.length <= i)
			{
				list[i] = temp[i-1];
			}
		}
		iterator--;
		return temp[0];
	}
	public boolean isEmpty()
	{
		boolean temp = false;
		if(iterator == 0)
		{
			temp = true;
		}
		return temp;
	}
	
	public boolean doesHave(int input)
	{
		boolean temp = false;
		for(int count : list)  {if(count == input) {temp = true;}}
		return temp;
	}
}
