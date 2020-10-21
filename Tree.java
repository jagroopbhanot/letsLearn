package selfLearning;

public class Tree 
{

	public static void main(String[] args) 
	{
		Node root = new Node(1);
		root.left = new Node(2);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		
		root.right = new Node(3);
		root.right.left = new Node(6);
		root.right.right = new Node(7);
		
		//printInOrder(root);
		printPostOrder(root);
	}
	
	public static void printInOrder(Node n)
	{
		/*
		https://www.geeksforgeeks.org/dfs-traversal-of-a-tree-using-recursion/
		
		Therefore, the Depth First Traversals of this Tree will be:
			(a) Inorder   (Left, Root, Right) : 4 2 5 1 3
			(b) Preorder  (Root, Left, Right) : 1 2 4 5 3
			(c) Postorder (Left, Right, Root) : 4 5 2 3 1
		*/
		
		 if (n == null) 
	            return; 
		 
			printInOrder(n.left);
		//then print the data of the node
			System.out.println(n.data);
			
			printInOrder(n.right);
	}
	
	public static void printPostOrder(Node n)
	{
		if(n == null)
			return;
		
		printPostOrder(n.left);
		
		printPostOrder(n.right);

		//then print the data of the node
		System.out.println(n.data);

	}

}

	
class Node
{
	int data;
	Node left, right;
	
	public Node(int data)
	{
		this.data = data;
		left = null;
		right = null;
	}
}
