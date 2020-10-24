package selfLearning.search;

public class Search {

	public static void main(String[] args) {
		Node tree = sample_tree();
		//traversal breadth first tree
		//Bfs.traversal(tree);
		
		//traversal depth first tree
		Dfs.traversal(tree);
	}
	
	private static Node sample_tree()
	{
		Node root = new Node("A", 
					new Node("B", 
							new Node("C"), new Node("D")), 
					new Node("E", 
							new Node("F"), new Node("G",
												new Node("H"),null)));
		return root;
	}

}
