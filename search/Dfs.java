package selfLearning.search;

public class Dfs {
	static void traversal(Node node)
	{
		if(null == node)
			return;

		traversal(node.left);

		traversal(node.right);
		System.out.println(node.data);


	}
}
