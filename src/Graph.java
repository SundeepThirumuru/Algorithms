import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Graph
{
	
	private List<Vertex> vertices;
	private List<Edge> edges;
	
	public Graph()
	{
		vertices = new ArrayList<>();
		edges = new ArrayList<>();
	}
	
	public void addVertex(Vertex vertex)
	{
		vertices.add(vertex);
	}
	
	public void addEdge(Edge edge)
	{
		edges.add(edge);
	}

	public List<Vertex> getVertices()
	{
		return vertices;
	}

	public List<Edge> getEdges()
	{
		return edges;
	}
	
	public Graph clone()
	{
		Graph clone = new Graph();
		HashMap<Vertex, Vertex> cloneMap = new HashMap<>();
		for(Vertex v : vertices)
		{
			cloneMap.put(v, v.clone());
			clone.vertices.add(cloneMap.get(v));
		}
		for(Edge e : edges)
		{
			Edge cloneEdge = new Edge(cloneMap.get(e.getA()), cloneMap.get(e.getZ()));
			clone.edges.add(cloneEdge);
			cloneEdge.getA().addEdge(cloneEdge);
			cloneEdge.getZ().addEdge(cloneEdge);
		}
		return clone;
	}
		
}
