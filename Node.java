import java.util.LinkedList;

public class Node implements Comparable<Node> {

    private double Latitude, Longitude;
    private Node predecessor;
    private String intersectionName; //iid
    private double distance;
    private LinkedList<Edge> adjList;
    private boolean visited;

    public Node(String idName, double latitude, double longitude){
        this.intersectionName = idName;
        this.Latitude = latitude;
        this.Longitude = longitude;
        adjList = new LinkedList<>();
        this.visited = false;
    }
    public Node(){
        //default constructor
    }


    public LinkedList<Edge> getAdjList() {
        return this.adjList;
    }

    public boolean isEqualTo(Node node){
        if(intersectionName.equals(node.intersectionName)){
            return (Latitude == node.Latitude ) && (Longitude == node.Longitude);
        }
        return false;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    public double getDistance() {
        return distance;
    }


    public void setPredecessor(Node node) {
        this.predecessor = node;
    }
    public Node getPredecessor() {
        return predecessor;
    }

    public Double getLatitude() {
        return Latitude;
    }
    public Double getLongitude() {
        return Longitude;
    }

    public void unVisited(){
         this.visited = false;
    }


    public String getIntersectionName() {
        return intersectionName;
    }

    public void visitNode(){ //sets whether the node has been checked
        this.visited = true;
    }

    public boolean getVisitedStatus(){
        return this.visited;
    }

    public void clearPath(){
        this.predecessor = null;
    }

    @Override
    public int compareTo(Node o) {
        if(this.distance < o.distance){
            return -1;
        }else if(this.distance > o.distance){
            return 1;
        }else{
            return 0;
        }
    }


    public LinkedList<Node> getPath(){
        LinkedList<Node> thePath = new LinkedList<>();
        Node current = this;
        while(current != null){
            thePath.add(current);
            current = current.predecessor;
        }
        return thePath;
    }
}

