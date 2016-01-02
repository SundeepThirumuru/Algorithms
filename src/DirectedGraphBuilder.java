import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class DirectedGraphBuilder
{
	
	public Graph buildGraph()
	{
		Graph g = new Graph();
		Map<String, Vertex> labelVsVertexMap = new HashMap<>();
		for(int i=1; i <=875714; i++)
		{
			Vertex v = new Vertex(Integer.toString(i));
			labelVsVertexMap.put(Integer.toString(i), v);
			g.addVertex(v);
		}
		
		try
		{
			BufferedReader fileReader = new BufferedReader(new FileReader("/home/sundeep/Desktop/SCC.txt"));
			String line = null;
			while(fileReader.ready())
			{
				line = fileReader.readLine();
				String[] splits = line.split(" ");
				Edge edge = new Edge(labelVsVertexMap.get(splits[0]), labelVsVertexMap.get(splits[1]));
				labelVsVertexMap.get(splits[0]).addEdge(edge);
				labelVsVertexMap.get(splits[1]).addEdge(edge);
			}
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		} 
		return g;
	}

}
