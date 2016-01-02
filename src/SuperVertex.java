import java.util.ArrayList;
import java.util.List;


public class SuperVertex extends Vertex
{

	private List<Vertex> containedVertices;
	public SuperVertex(String name)
	{
		super(name);
		containedVertices = new ArrayList<>();
	}
	
	public void addVertex(Vertex gobbledVertex)
	{
		if(gobbledVertex instanceof SuperVertex)
		{
			containedVertices.addAll(((SuperVertex)gobbledVertex).containedVertices);
		}
		else
		{
			containedVertices.add(gobbledVertex);
		}
	}
	
	public List<Vertex> getContainedVertices()
	{
		return containedVertices;
	}
	
	

}
