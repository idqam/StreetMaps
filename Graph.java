import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;


public class Graph {

     private int count = 0;
     private int vertexCount = 0;
    private int edgeCount = 0;
     private LinkedList<Node> nodeList;
     private HashMap<String, Node> stringNodeHashMap;
     private double maxLat, minLat, maxLong, minLong;



    public Graph(){
        maxLat = -Double.MAX_VALUE;
        maxLong = -Double.MAX_VALUE;
        minLat = Double.MAX_VALUE;
        minLong = Double.MAX_VALUE;

        stringNodeHashMap = new HashMap<String, Node>();

        nodeList = new LinkedList<Node>();
       
    }

    public Graph(String fileName){

         maxLat = -Double.MAX_VALUE;
         maxLong = -Double.MAX_VALUE;
         minLat = Double.MAX_VALUE;
        minLong = Double.MAX_VALUE;
        String intersectionID;
        double latitude;
        double longitude;
        String roadID;
        String intersectionX;
        String intersectionY;
        Node nodeIntersec;
        Edge roadEdge;
        stringNodeHashMap = new HashMap<String, Node>(); 

        nodeList = new LinkedList<Node>();
       

        Scanner fileScanner;
        
       try {
           fileScanner = new Scanner(new File(fileName));
       }catch (FileNotFoundException e){
           System.out.println(fileName + " was not found.");
           return;
       }

       
        
        while (fileScanner.hasNext()){
            String i = fileScanner.next();
            if(i.equals("i")){
                intersectionID = fileScanner.next();
                latitude = fileScanner.nextDouble();
                longitude = fileScanner.nextDouble();

                if(maxLat <= latitude){
                    maxLat = latitude;
                }
                if(maxLong <= longitude){
                    maxLong = longitude;
                }
                if(minLat >= latitude){
                          minLat = latitude;
                }
                if (minLong >= longitude){
                    minLong = longitude;
                }

                nodeIntersec = new Node(intersectionID, latitude, longitude);
                vertexCount++;
                stringNodeHashMap.put(intersectionID, nodeIntersec);
                nodeList.add(nodeIntersec);
                
            }else if(i.equals("r")){
                roadID = fileScanner.next();
                intersectionX = fileScanner.next();
                intersectionY = fileScanner.next();
                Node source = stringNodeHashMap.get(intersectionX);
                Node terminal = stringNodeHashMap.get(intersectionY);
                if(source == null || terminal ==  null){
                    System.out.println("Either the source is null or the terminal is null");
                    continue;
                }
                roadEdge = new Edge(roadID, source, terminal);
                source.getAdjList().add(roadEdge);
                roadEdge = new Edge(roadID, terminal, source);
                terminal.getAdjList().add(roadEdge);
                edgeCount ++;
                


            }else{
                System.out.println("Error");
            }
            count += 1;
           
            
        }



        
}







   public void addEdge(Edge e){
        Node node1 = e.getStartVertex();
        Node node2 = e.getEndVertex();
        String intersectionID1 = node1.getIntersectionName();
        String intersectionID2 = node2.getIntersectionName();

        Node node = stringNodeHashMap.get(intersectionID1);
       Node n = stringNodeHashMap.get(intersectionID2);
        if(node == null){
            node1 = new Node(intersectionID1, node1.getLatitude(), node1.getLongitude());
            stringNodeHashMap.put(intersectionID1, node1);

        }else{
            node1 = node;
        }


        if(n == null) {
            node2 = new Node(intersectionID2, node2.getLatitude(), node2.getLongitude());
            stringNodeHashMap.put(intersectionID2, node2);
        }else{
            node2 = n;
        }
        e = new Edge(e.getRoadName(), node1, node2);
       assert node1 != null;
       node1.getAdjList().add(e);
       assert node2 != null;
       node2.getAdjList().add(e);
       


        
   }


   public Set<String> getKeyList(){
        return stringNodeHashMap.keySet();

   }

   public Node getNodeID(String ID)     {
        if(stringNodeHashMap.get(ID) != null){
            return stringNodeHashMap.get(ID);
        }else{
            System.out.println("Node ID does not exist");
            return null;
        }
   }

   public LinkedList<Edge> getGraphEdges(){
        LinkedList<Edge> edges = new LinkedList<>();
        int x = 0;
        while(x < vertexCount){
            for(Edge currEdge : this.nodeList.get(x).getAdjList())   {
                if (edges.contains(currEdge))    {
                    continue;
                } else{
                    edges.add(currEdge);
                }
                
            }
            x++;
        }
        return edges;
   }



    public int getEdgeCount() {
        return edgeCount;
    }

    public int getCount() {
        return count;
    }

    public boolean Connected(Node node1, Node node2)  {
        Djisktra djisktra = new Djisktra( node1.getIntersectionName(), node2.getIntersectionName(), this);
        if(node2.getDistance() < Double.MAX_VALUE){
            return true;
        } else{
            return false;
        }


    }

    public int getVertexCount() {
        return vertexCount;
    }

    public double getMaxLat() {
        return maxLat;
    }

    public double getMinLat() {
        return minLat;
    }

    public double getMaxLong() {
        return maxLong;
    }

    public double getMinLong() {
        return minLong;
    }

    public HashMap<String, Node> getStringNodeHashMap() {
        return stringNodeHashMap;
    }

    public LinkedList<Node> getNodeList() {
        return nodeList;
    }















}
