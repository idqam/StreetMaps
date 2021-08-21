public class Edge implements Comparable<Edge>{ //used for an adjacency matrix


     private Node x,y; // x: starting vertex index; y: terminal vertex index
     private String roadName;
     private double weight;





    public Edge(String roadName, Node x, Node y){
        this.x = x;
        this.y = y;
        this.roadName = roadName;
        setWeight(x, y);

    }

    public String getRoadName() {
        return roadName;
    }

    public void setWeight(Node x, Node y){
        double latitude = Math.pow(x.getLatitude() - y.getLatitude(), 2);
        double longitude = Math.pow(x.getLongitude() - y.getLongitude(), 2);

        weight = Math.sqrt(latitude + longitude);



    }

    public double getWeight(){
        return weight;
    }



    public Node getStartVertex(){return x;}

    public Node getEndVertex(){return y;}



    @Override
    public int compareTo(Edge o) {
        if(this.weight < o.weight){
            return -1;
        }else if(this.weight > o.weight){
            return 1;
        }else{
            return 0;
        }
    }
}
