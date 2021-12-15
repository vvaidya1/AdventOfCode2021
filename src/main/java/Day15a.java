import Common.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day15a {
    void process() {
        try {
            File myObj = new File("src/main/resources/input_day15.txt");
            Scanner reader = new Scanner(myObj);
            List<List<Integer>> riskPositions = new ArrayList<>();

            while (reader.hasNextLine()) {
                var line = reader.nextLine();
                List<Integer> row = new ArrayList<>();
                for (int j = 0; j < line.length(); j++) {
                    if (j == line.length() - 1)
                        row.add(Integer.parseInt(line.substring(j)));
                    else
                        row.add(Integer.parseInt(line.substring(j, j + 1)));
                }
                riskPositions.add(row);
            }

            Map<Point, Integer> riskMap = new HashMap<>();
            int minimumRisk = dfs(riskPositions, 0, 0, riskMap);
            System.out.println(minimumRisk - riskPositions.get(0).get(0));
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    static int dfs(List<List<Integer>> riskPositions, int i, int j, Map<Point, Integer> riskMap) {
        if (i == riskPositions.size() - 1 && j == riskPositions.get(0).size() - 1) {
            return riskPositions.get(i).get(j);
        }

        Point key = new Point(i, j);
        if (riskMap.containsKey(key)) {
            return riskMap.get(key);
        } else {
            int riskNextRow = 0;
            if (i + 1 < riskPositions.size())
                riskNextRow += dfs(riskPositions, i + 1, j, riskMap);

            int riskNextCol = 0;
            if (j + 1 < riskPositions.get(0).size())
                riskNextCol += dfs(riskPositions, i, j + 1, riskMap);

            int minRisk = (riskNextCol != 0 && riskNextRow != 0) ? Math.min(riskNextCol, riskNextRow) : (riskNextCol == 0 ? riskNextRow : riskNextCol);
            int currentMinRisk = riskPositions.get(i).get(j) + minRisk;
            riskMap.put(key, currentMinRisk);
            return currentMinRisk;
        }
    }
}
