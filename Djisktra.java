import java.util.PriorityQueue;
import java.util.LinkedList;



public class Djisktra {
    String a;
     String b;
     Graph g;
    
     double[] distTo;
     double[] edgeTo;

    public Djisktra(String a, String b, Graph g){
        this.a = a;
        this.b = b;
        this.g = g;
        distTo = new double[g.getVertexCount()];
        distTo = new double[g.getVertexCount()];


    }




    public LinkedList<Node> findShortestPath(){
        for(String a: g.getKeyList()){ //intiliazise 
            Node Node = g.getNodeID(a);
            Node.setDistance(Double.POSITIVE_INFINITY);
            Node.unVisited();
            Node.clearPath();
        }

        LinkedList<Node> shortestPath;
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

        Node start = g.getNodeID(a);
        Node end = g.getNodeID(b);


        if(start == null){
            System.out.println("The start node does not exist. It is null");
            return null;
        }
        if(end == null) {
            System.out.println("The ending node does not exist. It is null");
            return null;
        }

        start.setDistance(0.0);


        priorityQueue.offer(start);
        double totalDistance = 0;


        while(!priorityQueue.isEmpty()){
            Node head = priorityQueue.poll();
            
            head.visitNode();
            

            if(head.isEqualTo(end)){
                shortestPath = end.getPath();
                return shortestPath;
            }
            for(Edge e : head.getAdjList()){
                Node x = e.getEndVertex();
                if(x.getVisitedStatus() == true){
                    continue;
                }
                double wantedDistanceRange = head.getDistance() + e.getWeight();
                if(wantedDistanceRange < x.getDistance()){
                    totalDistance += wantedDistanceRange;
                    x.setDistance(wantedDistanceRange);
                    x.setPredecessor(head);
                    priorityQueue.offer(x);
                }

               
        }
                
                

               
            
           
        }

        System.out.println("Shortest path from " + start.getIntersectionName()+ " to " + end.getIntersectionName() + " does not exist." );
        return null;



    }

    public LinkedList<Node> theShortestPath(String a, String b){
        this.a = a;
        this.b = b;
        long startTime = System.nanoTime();
        System.out.println("Calculating shortest path from " + a + " to " +  b);
        
        LinkedList<Node> shortestPath = findShortestPath();
        
       
        long endTime = System.nanoTime();
        long elapsedTime = (long) ((endTime - startTime) / (Math.pow(10, 6)));
        System.out.println("Time taken to find shortest path: " + elapsedTime + "ms");
        return shortestPath;


    }


    public double[] getDistTo() {
        return distTo;
    }

    public double[] getEdgeTo() {
        return edgeTo;
    }

    public Graph getG() {
        return g;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }


}
