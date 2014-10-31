package trees.controller;

import java.util.ArrayList;

import trees.model.BinarySearchTree;

public class Test
{
	public static void main(String[] args)
	{
		timeSort(100000, 1);
	}
	
	/**
	 * Measures the average amount of time it takes for the list to be sorted using a BinarySearchTree
	 * @param size - Size of the list to be sorted
	 * @param times - Times to repeat
	 */
	private static void timeSort(int size, int times)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();//list to originally hold random vals
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();//tree used to sort list
	    long totalTime = 0; //long to keep track of total time taken in nanoseconds.
	    
	    for(int reps = 0; reps < times; reps++) //repeat for given times.
	    {
	    	for(int count = 0; count < size; count++) //fill list
			{
				list.add((int)(Math.random() * 100)); //random number between [0, 100)
			}
			
	    	long startTime = System.nanoTime();//------------Start Timing-----------------------
			while(!list.isEmpty()) //repeat until list is empty
			{
				tree.insert(list.remove(0));//add number onto the tree
			}
			
			tree.inorderIterative(); //print an inorder traversal of the tree.
			long endTime = System.nanoTime();//--------------End Timing-----------------------
			long timeTaken = endTime - startTime; //determine how long this sort took.
			
			totalTime += timeTaken; //add this frames time to the total time.
			
			System.out.println();
	    }
	    
	    long averageTime = totalTime / times; //determine average time each frame took.
	    
	    System.out.println("Size: " + size);
	    System.out.println("Times: " + times);
	    System.out.println("Average Time NanoSeconds: " + averageTime);
	    System.out.println("Average Time Seconds: " + averageTime/1000000000.0);
	}
}
