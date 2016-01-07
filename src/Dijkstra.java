import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: SThirumuru
 * Date: 7/1/16
 * Time: 1:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class Dijkstra {

    public static final String COST = "cost";
    public static final String PREV_EDGE = "prevEdge";

    public List<Edge> findShortestPath(Vertex s, Vertex d, Graph g)
    {
        for(Vertex v : g.getVertices())
        {
            v.getKeyValueMap().put(COST, Double.MAX_VALUE);
        }
        s.getKeyValueMap().put(COST, 0d);
        PriorityQueue<Vertex> pq = new PriorityQueue<>(10, getComparator());

        for(Vertex v : g.getVertices())
        {
            if(!v.equals(s))
            {
                pq.add(v);
            }
        }

        Vertex minNode = s;
        while(!pq.isEmpty())
        {
            double minNodeCost = (Double)minNode.getKeyValueMap().get(COST);
            for(Edge e : minNode.getConnectedEdges())
            {
                Vertex otherEnd = e.getA().equals(minNode) ? e.getZ() : e.getA();
                double otherEndCost = (Double)otherEnd.getKeyValueMap().get(COST);
                double edgeCost = (Double)e.getKeyValueMap().get(COST);
                if(otherEndCost > minNodeCost + edgeCost)
                {
                    pq.remove(otherEnd);
                    otherEnd.getKeyValueMap().put(COST, minNodeCost + edgeCost);
                    otherEnd.getKeyValueMap().put(PREV_EDGE, e);
                    pq.add(otherEnd);
                }
            }
            if(!pq.isEmpty())
            {
                minNode = pq.remove();
                if(minNode.equals(d))
                {
                    break;
                }
            }

        }

        if(minNode.equals(d))
        {
            List<Edge> path = new LinkedList<>();
            Vertex v = d;
            while (!v.equals(s))
            {
                Edge e  = (Edge)v.getKeyValueMap().get(PREV_EDGE);
                path.add(0, e);
                v = e.getA().equals(v) ? e.getZ() : e.getA();
            }
            return path;
        }

        throw new RuntimeException("Path not found");
    }

    private Comparator<Vertex> getComparator()
    {
        return new Comparator<Vertex>(){
            public int compare(Vertex v1, Vertex v2)
            {
                double cost1 = (Double)v1.getKeyValueMap().get(COST);
                double cost2 = (Double)v2.getKeyValueMap().get(COST);
                if(cost1 < cost2)
                {
                    return -1;
                }
                else if(cost1 > cost2)
                {
                    return 1;
                }
                else {
                    return 0;
                }
            };
        };
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        String vertices = "a b c d";
        String[] edges = new String[] {"a b 1", "a c 2", "a d 3", "b c 1", "b d 1", "c d 1"};
        Map<String, Vertex> vertexMap = new LinkedHashMap<>();
        for(String vertexName : vertices.split(" "))
        {
            Vertex vertex = new Vertex(vertexName);
            vertexMap.put(vertexName, vertex);
            g.addVertex(vertex);
        }

        for(String edgeLabel : edges)
        {
            String[] splits = edgeLabel.split(" ");
            Edge edge  = new Edge(vertexMap.get(splits[0]), vertexMap.get(splits[1]));
            edge.getKeyValueMap().put(COST, Double.parseDouble(splits[2]));
            g.addEdge(edge);
            vertexMap.get(splits[0]).getConnectedEdges().add(edge);
            vertexMap.get(splits[1]).getConnectedEdges().add(edge);
        }

        Vertex s = vertexMap.get("a");
        Vertex d = vertexMap.get("d");
        List<Edge> path = new Dijkstra().findShortestPath(s, d, g);
        System.out.println(" Shortest Path between " + s +  " and " + d + " is " + path);
    }
}
