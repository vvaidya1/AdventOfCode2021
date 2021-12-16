import Common.Point;
import Common.WeightedPoint;
import Common.WeightedPointComparator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day15b {
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

            int[][] riskPositionsMatrix = new int[riskPositions.size() * 5][riskPositions.get(0).size() * 5];
            populateMatrix(riskPositions, riskPositionsMatrix);

            PriorityQueue<WeightedPoint> riskQueue = new PriorityQueue<>(new WeightedPointComparator());
            riskQueue.offer(new WeightedPoint(new Point(0, 0), 0));
            int minimumRisk = calculateMinRisk(riskPositionsMatrix, 0, 0, riskQueue, new HashMap<>(), new HashSet<>());
            System.out.println(minimumRisk);
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    static void populateMatrix(List<List<Integer>> riskPositionsList, int[][] riskPositionsMatrix) {
        for (int i = 0; i < riskPositionsMatrix.length; i++) {
            for (int j = 0; j < riskPositionsMatrix[0].length; j++) {
                int oldI = i % riskPositionsList.size();
                int oldJ = j % riskPositionsList.get(0).size();

                int delta, cellValue;
                delta = i / riskPositionsList.size() + j / riskPositionsList.get(0).size();
                cellValue = riskPositionsList.get(oldI).get(oldJ) + delta;
                if (cellValue > 9) cellValue -= 9;
                riskPositionsMatrix[i][j] = cellValue;
            }
        }
    }

    static int calculateMinRisk(int[][] riskPositionsMatrix, int i, int j, PriorityQueue<WeightedPoint> riskQueue, Map<Point, Integer> cost, Set<WeightedPoint> visited) {
        while (!riskQueue.isEmpty()) {
            WeightedPoint node = riskQueue.poll();
            if (visited.contains(node))
                continue;
            visited.add(node);
            cost.putIfAbsent(node.point, node.value);
            if (i == riskPositionsMatrix.length - 1 && j == riskPositionsMatrix[0].length - 1) {
                break;
            }

            List<Point> neighbours = new ArrayList<>();
            neighbours.add(new Point(node.point.x - 1, node.point.y));
            neighbours.add(new Point(node.point.x + 1, node.point.y));
            neighbours.add(new Point(node.point.x, node.point.y - 1));
            neighbours.add(new Point(node.point.x, node.point.y + 1));
            for (Point point : neighbours) {
                if (point.x < 0 || point.x >= riskPositionsMatrix.length || point.y < 0 || point.y >= riskPositionsMatrix.length)
                    continue;

                riskQueue.offer(new WeightedPoint(point, node.value + riskPositionsMatrix[point.x][point.y]));
            }
        }
        return cost.get(new Point(riskPositionsMatrix.length - 1, riskPositionsMatrix[0].length - 1));
    }
}
