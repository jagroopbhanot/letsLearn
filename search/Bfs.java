package selfLearning.search;

import java.util.LinkedList;
import java.util.Queue;

public class Bfs {

	static void traversal(Node node)
	{
		Queue<Node> q = new LinkedList<Node>();
		
		q.add(node);
		
		while(!q.isEmpty())
		{
			node = q.remove();
			
			System.out.println(node.data + " ");
			
			if(null !=node.left)
			{
				q.add(node.left);
			}
			
			if(null !=node.right)
			{
				q.add(node.right);
			}
		}
	}
}
