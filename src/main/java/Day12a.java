import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day12a {
    public static Map<String, List<String>> graph = new HashMap<>();

    void process() {
        try {
            File myObj = new File("src/main/resources/input_day12.txt");
            Scanner reader = new Scanner(myObj);
            ArrayList<List<String>> results = new ArrayList<>();

            long result = 0;
            while (reader.hasNextLine()) {
                var nodes = reader.nextLine().split("-");

                if (nodes[0].toUpperCase().equals(nodes[0]) || (!nodes[0].equals("start") && !nodes[1].equals("end"))) {
                    graph.computeIfAbsent(nodes[0], k -> new ArrayList<>()).add(nodes[1]);
                    graph.computeIfAbsent(nodes[1], k -> new ArrayList<>()).add(nodes[0]);
                } else
                    graph.computeIfAbsent(nodes[0], k -> new ArrayList<>()).add(nodes[1]);

            }

            Set<String> visited = new HashSet<>();
            dfs("start", "end", new ArrayList<String>(), results, visited);

            System.out.println(results);
            System.out.println(results.size());
            reader.close();
        } catch (
                FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    static void dfs(String s, String d, List<String> currentPath, ArrayList<List<String>> results, Set<String> visited) {
        if (s.equals(d)) {
            visited.add(s);
            currentPath.add(s);
            results.add(new ArrayList<>(currentPath));
            currentPath.remove(currentPath.size() - 1);
            visited.remove(s);
            return;
        }

        visited.add(s);
        currentPath.add(s);
        var neighbours = graph.containsKey(s) ? graph.get(s) : new ArrayList<String>();
        for (String node : neighbours) {
            if (node.toUpperCase().equals(node) || !visited.contains(node)) {
                dfs(node, d, currentPath, results, visited);
            }
        }
        currentPath.remove(currentPath.size() - 1);
        visited.remove(s);
        return;
    }
}
