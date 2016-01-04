import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Vertex 
{

	private String name;
	private List<Edge> connectedEdges;
	private Map<Object, Object> keyValueMap;
	
	public Vertex(String name)
	{
		this.name = name;
		connectedEdges = new ArrayList<Edge>();
		keyValueMap = new HashMap<Object, Object>();
	}
	
	public void addEdge(Edge edge)
	{
		connectedEdges.add(edge);
	}

	public String getName()
	{
		return name;
	}

	public List<Edge> getConnectedEdges()
	{
		return connectedEdges;
	}
	
	public Map<Object, Object> getKeyValueMap()
	{
		return keyValueMap;
	}
	
	public Vertex clone()
	{
		Vertex clone = new Vertex(name);
		return clone;
	}
	
}
