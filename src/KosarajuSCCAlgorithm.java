import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class KosarajuSCCAlgorithm
{

	private List<Vertex> vertexList = new LinkedList<>();
	private int sccSize = 0;
	private List<Integer> sccList = new ArrayList<>();
	public static void main(String[] args)
	{
		List<Integer> sccList = new KosarajuSCCAlgorithm().findSCC(new DirectedGraphBuilder().buildGraph());
		Collections.sort(sccList);
		System.out.println("SCCs sizes are ");
		System.out.println(sccList);
	}
	
	public List<Integer> findSCC(Graph g)
	{
		markGraphUnExplored(g);
		DFS(g.getVertices(), true);
		sccList.clear();
		sccSize = 0;
		markGraphUnExplored(g);
		DFS(new ArrayList<>(vertexList), false);
		return sccList;
		
	}
	
	public void DFS(List<Vertex> vertices, boolean reverseDFS)
	{
		for(Vertex v : vertices)
		{
			if(!isExplored(v))
			{
				DFS(v, reverseDFS);
				sccList.add(sccSize);
				sccSize = 0;
			}
		}
	}
	
	public void DFS(Vertex v, boolean reverseDFS)
	{
		markExplored(v, true);
		sccSize ++;
		for(Edge e :  v.getConnectedEdges())
		{
			boolean considerEdge = e.getA().equals(v);
			Vertex otherVertex = e.getZ();
			if(reverseDFS)
			{
				considerEdge = e.getZ().equals(v);
				otherVertex = e.getA();
			}
			if(considerEdge)
			{
				if(!isExplored(otherVertex))
			     {
					DFS(otherVertex, reverseDFS);
			     }
			}
		}
		vertexList.add(0, v);
	}
	
	
	private void markExplored(Vertex v, boolean explored)
	{
		v.getKeyValueMap().put("Explored", explored);		
	}
	
	private boolean isExplored(Vertex v)
	{
		return v.getKeyValueMap().get("Explored").equals(Boolean.TRUE);
	}
	
	private void markGraphUnExplored(Graph g)
	{
		for(Vertex v : g.getVertices())
		{
			markExplored(v, false);
		}
	}
}
