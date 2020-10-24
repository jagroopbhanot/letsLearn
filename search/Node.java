package selfLearning.search;

public class Node {
	
	Node left,right;
	String data;
	
	Node(String data)
	{
		this.data = data;
	}
	public Node(String data, Node left, Node right) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
	}
}
