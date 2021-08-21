import java.util.LinkedList;
import java.util.PriorityQueue;

public class minTreeCalc {
    Graph tree;

    public minTreeCalc(Graph graphData){
        tree = new Graph();
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();

        priorityQueue.addAll(graphData.getGraphEdges());

        while(!priorityQueue.isEmpty()){
            Edge e = priorityQueue.poll();
            String firstID = e.getStartVertex().getIntersectionName();
            String secondID = e.getEndVertex().getIntersectionName();

            Node i1Tree = tree.getStringNodeHashMap().get(firstID);
            Node i2Tree = tree.getStringNodeHashMap().get(secondID);

            if(i1Tree == null || i2Tree == null){
                tree.addEdge(e);
                continue;
            }
            if(tree.Connected(i1Tree,i2Tree)){
                continue;
            }else{
                tree.addEdge(e);
            }
        }
        printTree();
    }

    public LinkedList<Edge> getTree() {

        LinkedList<Edge> treelist = new LinkedList<>();
        for (Node v : tree.getStringNodeHashMap().values()) {
            for (Edge e : v.getAdjList()) {
                if (!treelist.contains(e)) {
                    treelist.add(e);

                }
            }
        }

        return treelist;

    }




    public void printTree(){
        System.out.println(tree.getStringNodeHashMap().keySet());
    }
}
