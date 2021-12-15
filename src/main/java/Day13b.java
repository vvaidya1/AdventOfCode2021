import Common.Point;
import Common.Fold;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day13b {
    void process() {
        try {
            File myObj = new File("src/main/resources/input_day13.txt");
            Scanner reader = new Scanner(myObj);
            Set<Point> results = new LinkedHashSet<>();
            Set<Point> coordinates = new HashSet<>();
            LinkedHashSet<Fold> folds = new LinkedHashSet<>();

            while (reader.hasNextLine()) {
                var line = reader.nextLine();
                String[] input;
                if (line.equals("")) break;
                else input = line.split(",");
                coordinates.add(new Point(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
            }

            while (reader.hasNextLine()) {
                String[] input;
                var line = reader.nextLine();
                if (line.equals("")) continue;
                else input = line.substring(11).split("=");
                folds.add(new Fold(input[0], Integer.parseInt(input[1])));
            }

            for (Fold fold : folds) {
                var temp = ((long) results.size() == 0) ? coordinates : results;
                results = new LinkedHashSet<>();
                for (Point coordinate : temp) {
                    if (fold.axis.equals("y")) {
                        if (coordinate.y <= fold.value) {
                            results.add(new Point(coordinate.x, coordinate.y));
                        } else {
                            results.add(new Point(coordinate.x, fold.value - (coordinate.y - fold.value)));
                        }
                    } else {
                        if (coordinate.x <= fold.value) {
                            results.add(new Point(coordinate.x, coordinate.y));
                        } else {
                            results.add(new Point(fold.value - (coordinate.x - fold.value), coordinate.y));
                        }
                    }
                }
            }

            int maxX = Objects.requireNonNull(results.stream().max(Comparator.comparingInt(i -> i.x)).orElse(null)).x;
            int maxY = Objects.requireNonNull(results.stream().max(Comparator.comparingInt(i -> i.y)).orElse(null)).y;
            for (int i = 0; i <= maxY; i++) {
                for (int j = 0; j <= maxX; j++) {
                    System.out.print(results.contains(new Point(j, i)) ? "█" : " ");
                }
                System.out.println();
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}