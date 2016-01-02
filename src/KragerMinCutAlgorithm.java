
public class KragerMinCutAlgorithm
{

	private Graph g;
	
	public KragerMinCutAlgorithm(Graph g)
	{
		this.g = g;
	}
	
	public int doMinCut()
	{
		if(g.getVertices().size() == 2)
		{
			return g.getEdges().size();
		}
		Edge edge = getEdgeToContractGraph();
		Vertex a = edge.getA();
		Vertex z = edge.getZ();
		Vertex superVertex = new SuperVertex(a.getName() + ";" + z.getName());
		g.getVertices().remove(a);
		g.getVertices().remove(z);
		g.getEdges().remove(edge);
		g.addVertex(superVertex);
		handleEdges(a, z, superVertex);
		handleEdges(z, a, superVertex);
		return doMinCut();
	}
	
	private Edge getEdgeToContractGraph()
	{
		assert(g.getEdges().size() >= 1);
		int index = (int)(Math.random() * g.getEdges().size());
		Edge edge = g.getEdges().get(index);
		return edge;
	}
	
	private void handleEdges(Vertex a, Vertex z, Vertex superVertex)
	{
		g.getEdges().removeAll(a.getConnectedEdges());
		for(Edge edge1:a.getConnectedEdges())
		{
			Vertex otherVertex = edge1.getA().equals(a) ? edge1.getZ() : edge1.getA();
			if(!otherVertex.equals(z))
			{
				otherVertex.getConnectedEdges().remove(edge1);
				Edge newEdge = new Edge(superVertex, otherVertex);
				superVertex.addEdge(newEdge);
				otherVertex.addEdge(newEdge);
				g.addEdge(newEdge);
			}
		}		
	}
	
	public Graph getGraph()
	{
		return g;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Graph g = new GraphBuilder().buildGraph();
		System.out.println("Graph has " + g.getEdges().size() + " edges");
		System.out.println("Degree of each Vertex of the Graph");
		int lowestDegree = g.getVertices().size();
		for(Vertex vertex : g.getVertices())
		{
			System.out.println("Vertex " + vertex.getName() + " has " + vertex.getConnectedEdges().size() + " degree");
			if(lowestDegree > vertex.getConnectedEdges().size())
			{
				lowestDegree = vertex.getConnectedEdges().size();
			}
		}
		System.out.println("Lowest degree " + lowestDegree);
		int bestMinCut = lowestDegree;
		long startTime = System.currentTimeMillis();
		for(int i=0; i < 1000; i++)
		{
			System.out.println("Iteration " + i);
			KragerMinCutAlgorithm  kragerMinCutAlgorithm = new KragerMinCutAlgorithm(g.clone());
			int minCut = kragerMinCutAlgorithm.doMinCut();
			if(minCut < bestMinCut)
			{
				bestMinCut = minCut;
			}
			System.out.println("The Minimum edges to be cut is "+ minCut);
			System.out.println("The resultant Graph partitions are ");
			for(Vertex vertex : kragerMinCutAlgorithm.getGraph().getVertices())
			System.out.println(vertex.getName());
		}
		
		System.out.println("Best MinCut is " + bestMinCut);
		System.out.println("Time consumed + " + (System.currentTimeMillis() - startTime));
	}

}
