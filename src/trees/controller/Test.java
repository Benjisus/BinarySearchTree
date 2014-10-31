package trees.controller;

import trees.model.BinarySearchTree;

public class Test
{
	public static void main(String[] args)
	{
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		
		tree.insert(50);
		tree.insert(25);
		tree.insert(75);
		tree.insert(0);
		tree.insert(100);
		tree.insert(90);
		tree.insert(10);
		
		tree.inorderIterative();
	}
}
