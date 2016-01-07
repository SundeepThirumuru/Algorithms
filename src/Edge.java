import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.Properties;

public class Edge
{

	private Vertex a,z;
    private Properties properties = new Properties();
	
	public Edge(Vertex a, Vertex z)
	{
		this.a = a;
		this.z = z;
	}

	public Vertex getA()
	{
		return a;
	}

	public Vertex getZ()
	{
		return z;
	}

    public Properties getKeyValueMap()
    {
        return properties;
    }

    public String toString()
    {
        return a + " <--> " + z;
    }
	
}
