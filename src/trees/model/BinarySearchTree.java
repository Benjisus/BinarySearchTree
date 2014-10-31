package trees.model;

import stack.model.ListQueue;
import stack.model.ListStack;

/**
 * @author Taylor Hogge
 * @param <T> a comparable type
 * 
 * This class represents a binary search tree. You should insert and remove data
 * by using the given methods, messing with the nodes may mess up the structure.
 * 
 * This tree is not balanced. No rotations are performed. 
 */
public class BinarySearchTree<T extends Comparable<T>>
{
	private TreeNode root; //root node of the tree
	
	/**
	 * Creates a new Binary Search Tree
	 * Root is set to null be default. The root will be defined
	 * once the user calls insert(data) for the first time.
	 */
	public BinarySearchTree()
	{
		root = null;
	}
	
	/**
	 * Inserts the given data into the appropriate position of the tree.
	 * @param data data to be inserted
	 */
	public void insert(T data)
	{
		if(root == null)
		{
			root = new TreeNode(data); //create root if null
		}
		else
		{
			insert(data, root); //else, recursively insert
		}
	}
	
	/**
	 * Finds the node that matches the given data and removes it from the tree.
	 * This does nothing if the given data does not exist in the tree. 
	 * @param data data to be searched for and removed.
	 */
	public void remove(T data)
	{
		remove(data, root);
	}
	
	/**
	 * Finds the node which holds the given data.
	 * @param data data to be searched for
	 * @return TreeNode holding the corresponding data, null if data is not found in tree.
	 */
	public T find(T data)
	{
		return find(data, root).data;
	}
	
	/**
	 * Iteratively prints out a level order traversal of the tree to the console.
	 */
	public void levelorderIterative()
	{
		ListQueue<TreeNode> q = new ListQueue<TreeNode>(); //queue to hold calls
		
		if(root != null) //don't perform if the root is null
		{
			q.enqueue(root);
		}
		
		while(!q.isEmpty()) //continue until all operations are completed.
		{
			TreeNode current = q.dequeue(); //hold the node held at the front of the queue
			System.out.print(current.data + " "); //print the current node
			if(current.left != null) 
				q.enqueue(current.left); //add the left node if not null
			if(current.right != null)
				q.enqueue(current.right); //add the right node if not null
		}
	}
	
	/**
	 * Iteratively prints an in-order traversal of this tree
	 */
	public void inorderIterative()
	{
		ListStack<TreeNode> stack = new ListStack<TreeNode>(); //stack to guide traversal
		
		TreeNode current = root; //pointer to the active node.
		
		//continue until stack is empty, or current is null
		while(!stack.isEmpty() || current != null)
		{
			//if not null, push to stack, descend to left
			if(current != null)
			{
				stack.push(current);
				current = current.left;
			}
			else //can't go further left, print active node, descend to the right.
			{
				TreeNode node = stack.pop();
				System.out.print(node.data + ", ");
				current = node.right;
			}
		}
	}
	
	//-----------------Inner Methods-----------------------------------------------------
	
	/**
	 * Recursively inserts the given data into the list.
	 * @param data data to be inserted
	 * @param current current node in our traversal
	 */
	private TreeNode insert(T data, TreeNode current)
	{
		if(current == null) //we have reached the leaves, backtrack one step to insert.
		{
			return new TreeNode(data);
		}
		
		if(data.compareTo(current.data) < 0) //decide whether to move left or right.
		{
			current.left = insert(data, current.left);
		}
		else
		{
			current.right = insert(data, current.right);
		}
		
		return current;
	}
	
	/**
	 * Recursively removes the node with the given data from the tree.
	 * @param data data held in the node to be removed
	 * @param current current node in our traversal
	 */
	private TreeNode remove(T data, TreeNode current)
	{
		if(current == null) //node does not exist in this list.
		{
			return null;
		}
		
		if(current.data == data) //base case, we have found the node we are looking for
		{
			if(current.left == null) //make sure we don't return null
			{
				return current.right;
			}
			if(current.right == null)
			{
				return current.left;
			}
			current.data = findMin(current.right); //remove the node
			current.right = remove(current.data, current.right);
			return current;
		}
		else //haven't found data yet
		{
			if(current.data.compareTo(data) < 0) //move to the side which will contain our data
			{
				current.right = remove(data, current.right);
				return current;
			}
			else
			{
				current.left = remove(data, current.left);
				return current;
			}
		}
	}
	
	/**
	 * Finds the smallest node contained in the tree below the given node.
	 * @param node node to start searching from
	 */
	private T findMin(TreeNode node)
	{
		if(node.left == null) //we have reached the minimum value held in the tree
		{
			return node.data;
		}
		
		return findMin(node.left); //we need to keep moving
	}
	
	/**
	 * Recursively searches for the node which holds the given data.
	 * @param data data to be searched for
	 * @param current current node in our traversal
	 */
	private TreeNode find(T data, TreeNode current)
	{
		if(current == null) //make sure we are not running off the tree
		{
			return null;
		}
		
		if(current.data == data) //we have found the node
		{
			return current;
		}
		else
		{
			if(current.data.compareTo(data) < 0) //decide whether to move left or right
			{
				return find(data, current.right);
			}
			else
			{
				return find(data, current.left);
			}
		}
	}
	//-----------------------------------Given Methods-----------------------------------
	
	/**
	 * Determines the size of this tree
	 */
	public int size() 
	{
        return size(root);
    }
	/**
	 * Recursively counts every node held in this tree.
	 */
    private int size(TreeNode n) {
        if (n == null) return 0;
        return 1 + size(n.left) + size(n.right);
    }
    
    /**
     * Determines the height of this tree.
     * @return
     */
    public int height() {
        return height(root);
    }
    /**
     * Recursively measures the length of the deepest path length.
     */
    private int height(TreeNode n) {
        if (n == null) return 0;
        return 1 + Math.max(height(n.left), height(n.right));
    }
    
    /**
     * prints a preorder traversal of this tree to the console.
     * Uses the recursive version
     */
    public void preorder() {
        System.out.println("preorder: ");
        preorder(root);
        System.out.println(" ");
    }
    /**
     * Recursively prints a preorder traversal of this tree.
     */
    private void preorder(TreeNode n) {
        if (n == null) return;
        System.out.print(n.data + ", ");
        preorder(n.left);
        preorder(n.right);
    }
    
    /**
     * Prints an inorder traversal of this tree to the console.
     * Uses the recursive version of this method
     */
    public void inorder() {
        System.out.println("inorder: ");
        inorder(root);
        System.out.println(" ");
    }
    /**
     * Recursively prints an inorder traversal of this tree
     * @param n
     */
    private void inorder(TreeNode n) {
        if (n == null) return;
        inorder(n.left);
        System.out.print(n.data + ", ");
        inorder(n.right);
        
    }

    /**
     * Prints a postorder traversal of this tree to the console.
     * Uses the recursive version of this method.
     */
    public void postorder() {
        System.out.println("postorder: ");
        postorder(root);
        System.out.println(" ");
    }
    /**
     * Recursively prints a postorder traversal of this tree.
     */
    private void postorder(TreeNode n) {
        if (n == null) return;
        postorder(n.left);
        postorder(n.right);
        System.out.print(n.data + ", ");
    }
	
	//------------------------------TreeNode Class----------------------------------------
	/**
	 * This class represents a single node held in the tree.
	 * Tampering with the pointers could mess up the structure of the tree.
	 * @author Taylor Hogge
	 */
	public class TreeNode
	{
		public T data; //data held in this node
		public TreeNode left; //pointer to left child node
		public TreeNode right; //pointer to right child node
		
		/**
		 * Creates a new TreeNode which will hold the given data.
		 * Both left and right pointers are set to null.
		 */
		public TreeNode(T data)
		{
			this.data = data;
			left = null;
			right = null;
		}
		
		/**
		 * Creates a new TreeNode which will hold the given data.
		 * @param data data to be held in this node
		 * @param left left child of this node
		 * @param right right child of this node
		 */
		public TreeNode(T data, TreeNode left, TreeNode right)
		{
			this.data = data;
			this.left = left;
			this.right = right;
		}
		
		/**
		 * Converts the data held in this node into a string.
		 */
		@Override
		public String toString()
		{
			return data + "";
		}
	}
}
