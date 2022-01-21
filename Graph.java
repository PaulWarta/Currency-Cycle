public class Graph {
    private double[][] graphList;
    private int elementCount;

    private Graph(int elementCount) {
        this.elementCount = elementCount;
        graphList = new double[elementCount][elementCount];
        for (int i= 0; i < elementCount; i++) {
            for (int j = 0; j < elementCount; j++) {
                if (i != j) {
                    graphList[i][j] = Double.POSITIVE_INFINITY;
                } else {
                    graphList[i][j] = 0;
                }
            }
        }
    }

    private void addEdge(int from, int to, double value) {
        graphList[from][to] = -Math.log10(value);
    }

    private boolean hasNegativeLoop(int start) {
        double[] cachingList = new double[elementCount];
        for (int i = 0; i < elementCount; i++) {
            if (i != start) {
                cachingList[i] = Double.POSITIVE_INFINITY;
            } else {
                cachingList[i] = 0;
            }
        }

        for (int v = 0; v < elementCount - 1; v++) {
            for (int i = 0; i < elementCount; i++) {
                for (int j = 0; j < elementCount; j++) {
                    cachingList[j] = Math.min(Math.min(graphList[start][j], cachingList[i] + graphList[i][j]), cachingList[j]);
                }
            }
        }

        for (int v = 0; v < elementCount - 1; v++) {
            for (int i = 0; i < elementCount; i++) {
                for (int j = 0; j < elementCount; j++) {
                    if (cachingList[j] != Math.min(Math.min(graphList[start][j], cachingList[i] + graphList[i][j]), cachingList[j])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static void main(String[]args) {
        Graph graph = new Graph(3);
    
        System.out.println(Math.log10(1));
    
        graph.addEdge(0, 1, 2);
        graph.addEdge(1, 0, 0.5);
        graph.addEdge(1, 2, 0.25);
        graph.addEdge(2, 1, 4);
        graph.addEdge(2, 0, 1);
        graph.addEdge(0, 2, 1);
    
        System.out.println(graph.hasNegativeLoop(1));
    }
}
