import java.util.*;

public class Main {
    private class Edge {
        String dest;
        double weight;

        private Edge(String dest, double weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }
    private static Scanner scan;
    private Map<String, List<Edge>> conversionGraph;
    private Map<String, Boolean> traversed;
    public Main(){
        traversed = new HashMap<>();
        conversionGraph = new HashMap<>();
    }
    public void addEdge(String a, String b, double correl){
        // adds an undirected edge between a and b (although directed in nature because weights are inverses of each other).
        if(!conversionGraph.containsKey(a)) conversionGraph.put(a, new ArrayList<>());
        if(!conversionGraph.containsKey(b)) conversionGraph.put(b, new ArrayList<>());
        conversionGraph.get(a).add(new Edge(b, correl));
        conversionGraph.get(b).add(new Edge(a, 1/correl));
    }
    public void conversion(String source, String destination, double value){
        traversed.put(source, true);
        if(source.equals(destination)){
            System.out.println("Answer: " + value);
            return;
        }
        for(Edge v: conversionGraph.get(source)) {
            if (!traversed.containsKey(v.dest)) {
                double new_value = value * v.weight;
                conversion(v.dest, destination, new_value);
            }
        }
    }
    public void run(){
        addEdge("Feet", "Inches", 12);
        addEdge("Inches", "Centimeters", 2.54);
        addEdge("Meters", "Centimeters", 100);
        addEdge("Kilometers", "Meters", 1000);
        addEdge("Miles", "Kilometers", 1.609);
        // add a bit more edges yourself ig lol I'm too lazy
        System.out.println("Convert from: ");
        String a = scan.nextLine();
        System.out.println("Convert to: ");
        String b = scan.nextLine();
        System.out.println("Numerical count: ");
        double amt = scan.nextDouble();
        conversion(a, b, amt);
    }
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}