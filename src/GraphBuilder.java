import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class GraphBuilder
{

	public GraphBuilder()
	{
		
	}
	
	
	public static void main(String[] args)
	{
		Graph g = new GraphBuilder().buildGraph();
		System.out.println("Graph has "+ g.getEdges().size()+ " edges");
	}
	
	public Graph buildGraph()
	{
		Graph g = new Graph();
		for(int i=1; i<=200; i++)
		{
			g.addVertex(new Vertex(Integer.toString(i)));
		}
		addEdgesByReadingTheFile(g);
		return g;
	}
	
	private void addEdgesByReadingTheFile(Graph g)
	{
		try
		{
			BufferedReader fileReader = new BufferedReader(new FileReader("/home/sundeep/Desktop/kargerMinCut.txt"));
			String line;
			while(fileReader.ready())
			{
				line = fileReader.readLine();
				String[] splits = line.split("\t");
				Vertex a = g.getVertices().get(Integer.parseInt(splits[0])-1); 
				for(int i=1; i<splits.length; i++)
				{
					if(Integer.parseInt(splits[i]) > Integer.parseInt(splits[0]))
					{
						Vertex z = g.getVertices().get(Integer.parseInt(splits[i])-1);
						Edge edge = new Edge(a, z);
						a.addEdge(edge);
						z.addEdge(edge);
						g.addEdge(edge);
					}
				}
				
			}
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
