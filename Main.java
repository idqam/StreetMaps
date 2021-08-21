import javax.swing.*;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args){
        String filename = args[0];
        Graph graph = new Graph(filename);

        if(args[1].equals("-show")){
            String firstCommand = args[2];
            String secondCommand = args[3];

            
            Djisktra djisktra = new Djisktra(firstCommand, secondCommand, graph);
            LinkedList<Node> path = djisktra.theShortestPath(firstCommand, secondCommand);


           System.out.print("Djisktra path: ");
            if(path != null){
                for(Node x : path){
                    if(x != null){
                        System.out.print(x.getIntersectionName());
                        System.out.print(" --> ");
                    }else{
                        return;
                    }
                }
            }
            System.out.println();
            if(args.length == 5){
                System.out.println("Creating minTree");
                long startTime = System.nanoTime();
                System.out.println("Finding min weight span tree:  ");
                minTreeCalc minTree = new minTreeCalc(graph);
              
                long stopTime = System.nanoTime();
                long timeElapsed = stopTime - startTime;
                timeElapsed = (long) (timeElapsed / Math.pow(10, 6));
                System.out.println("time taken to compute the min weight span tree: " + timeElapsed + "ms");

                LinkedList<Edge> kruskaList;
                kruskaList = minTree.getTree();

                guiGraph guiGraph1 = new guiGraph(graph, path, kruskaList);
                guiGraph guiGraph2 = new guiGraph(graph, kruskaList);
    
                JFrame frame = new JFrame("View");
                frame.add(guiGraph1);
                frame.add(guiGraph2);
                frame.setSize(1000, 1000);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



            }else if(args.length == 4){
                guiGraph guiGraph = new guiGraph(path, graph);
                JFrame jFrame = new JFrame("Map");
                jFrame.add(guiGraph);
                jFrame.setSize(1000, 1000);
                jFrame.setVisible(true);

                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }
        } else{
            String firstCommand = args[1];
            String secondCommand = args[2];
            Djisktra djisktra = new Djisktra(firstCommand, secondCommand , graph);
            LinkedList<Node> path = djisktra.theShortestPath(firstCommand, secondCommand);
            minTreeCalc minTree = new minTreeCalc(graph);
            LinkedList<Edge> kruskaList;
            kruskaList = minTree.getTree();
            System.out.println("The shortest path is: ");
            if( path != null){
                for(Node Node : path){
                    if(Node != null){
                        System.out.print(Node.getIntersectionName() + " --> ");
                    }else{
                        return;
                    }
                }
            }
        }

    }
}
