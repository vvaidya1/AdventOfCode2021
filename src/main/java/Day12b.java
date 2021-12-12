import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day12b {
    public static Map<String, List<String>> graph = new HashMap<>();

    void process() {
        try {
            File myObj = new File("src/main/resources/input_day12.txt");
            Scanner reader = new Scanner(myObj);
            ArrayList<List<String>> results = new ArrayList<>();

            long result = 0;
            while (reader.hasNextLine()) {
                var nodes = reader.nextLine().split("-");

                graph.computeIfAbsent(nodes[0], k -> new ArrayList<>()).add(nodes[1]);
                graph.computeIfAbsent(nodes[1], k -> new ArrayList<>()).add(nodes[0]);
            }

            Map<String, Integer> visited = new HashMap<>();
            dfs("start", "end", new ArrayList<>(Arrays.asList("start")), results, visited);

            System.out.println(results);
            System.out.println(results.stream().distinct().count());
            reader.close();
        } catch (
                FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    static void dfs(String s, String d, List<String> currentPath, ArrayList<List<String>> results, Map<String, Integer> visited) {
        if (s.equals(d)) {
            results.add(new ArrayList<>(currentPath));
            return;
        }

        if (!s.toUpperCase().equals(s))
            visited.put(s, visited.getOrDefault(s, 0) + 1);

        for (var node : graph.get(s)) {
            if (node.equals("start"))
                continue;
            if (node.toUpperCase().equals(node) || !visited.containsKey(node) ||
                    (visited.get(node) < 2 && visited.values().stream().filter(x -> x == 2).count() == 0)) {
                currentPath.add(node);
                dfs(node, d, currentPath, results, visited);
                currentPath.remove(currentPath.size() - 1);
            }
        }
        if (visited.containsKey(s) && visited.get(s) <= 1)
            visited.remove(s);
        else if (visited.containsKey(s) && visited.get(s) > 1)
            visited.put(s, visited.getOrDefault(s, 0) - 1);
        return;
    }
}
