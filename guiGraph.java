import javax.swing.*;



import java.awt.*;
import java.util.LinkedList;
import java.util.Random;



public class guiGraph extends JComponent {
     private Graph graphData;
     private LinkedList<Node> path = new LinkedList<>();
     private LinkedList<Edge> mintreePath = new LinkedList<>();
     private double x_Axis, y_Axis;
    int height;

    public guiGraph(Graph g, LinkedList<Node> path, LinkedList<Edge> treePath){
        this.graphData = g;
        System.out.println("GUI enabled");
        this.path = path;
        this.mintreePath = treePath;
        repaint();
    }

    public guiGraph(LinkedList<Node> path, Graph graph) {
        System.out.println("GUI enabled");
        this.graphData = graph;
        this.path = path;
        repaint();

    }

    public guiGraph(Graph g, LinkedList<Edge> treePath){
        System.out.println("GUI enabled");
        this.graphData = g;
        this.mintreePath = treePath;
        repaint();
    }



    @Override
    public void paintComponent(Graphics g){
        int width = getWidth();
         height = getHeight();

        x_Axis = width / (graphData.getMaxLong() - graphData.getMinLong());
        y_Axis = height / (graphData.getMaxLat() - graphData.getMinLat());
        System.out.println("Max latitude: " + graphData.getMaxLat() + "\n" + "Min latitude: " + graphData.getMinLat()
                + "\n" + "Max longitude: " + graphData.getMaxLong() + "\n" + "Min longitude: " + graphData.getMinLong());

        System.out.println("X_Axis: " + x_Axis + ", Y_Axis: " + y_Axis);

        for(Node node : graphData.getStringNodeHashMap().values()){
            int x1 = (int) (x_Axis * (node.getLongitude() -graphData.getMinLong()));
            int y1 = (int) (y_Axis * (node.getLatitude() - graphData.getMinLat()));
            y1 =  height - y1;
            
            for(Edge e: node.getAdjList()){
                Node x = e.getEndVertex();
                int x2 = (int) (x_Axis * (x.getLongitude() - graphData.getMinLong()));
                int y2 = (int) (y_Axis*(x.getLatitude() - graphData.getMinLat()));
               
                y2 = height - y2;

                
                 g.setColor(Color.black);

                g.drawLine(x1,y1,x2,y2);
            }
        }
        drawPath(g, path);

    }
    public void drawEdges(Graphics g, LinkedList<Edge> edges){
        g.setColor(Color.BLUE); //shows meridian 

        for( Edge e: edges){
            Node source = e.getStartVertex();
            Node terminal = e.getEndVertex();
            int x1 = (int) (x_Axis * (source.getLongitude() - graphData.getMinLong()));
            int y1 = (int) (height - (y_Axis * (source.getLatitude() - graphData.getMinLat())));
            //System.out.println("(" + x1 + ", " + y1 + ")");


            int x2 = (int) (x_Axis * (terminal.getLongitude() - graphData.getMinLong()));
            int y2 = (int) (height - (y_Axis * (terminal.getLatitude() - graphData.getMinLat())));
            
        


            g.drawLine(x1,y1,x2,y2);

        }
    }

    public void drawPath(Graphics g, LinkedList<Node> path) throws NullPointerException{
        g.setColor(Color.RED);
        
        
        
        
        if(path != null){
            for(int i = 0; i < path.size() - 1; i++){
                Node currNode = path.get(i);
                int x1 = (int) (x_Axis * (currNode.getLongitude() - graphData.getMinLong()));
                int y1 = (int) (height - (y_Axis * (currNode.getLatitude() - graphData.getMinLat())));
                

                Node succNode = path.get(i + 1);
                int x2 = (int) (x_Axis * (succNode.getLongitude() - graphData.getMinLong()));
                int y2 = (int) (height - (y_Axis * (succNode.getLatitude() - graphData.getMinLat())));
                



                g.drawLine(x1,y1,x2,y2);

            }if(mintreePath != null){
                System.out.println("Drawing minTreePath");
                g.setColor(Color.GREEN);
                drawEdges(g, mintreePath);
            }
        }else{
            System.out.println("No min weight span path exists. Sorry :/");
        }

    }



}
