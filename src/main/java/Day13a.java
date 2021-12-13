import Common.Coordinate;
import Common.Fold;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day13a {
    void process() {
        try {
            File myObj = new File("src/main/resources/input_day13.txt");
            Scanner reader = new Scanner(myObj);
            Set<Coordinate> results = new LinkedHashSet<>();
            Set<Coordinate> coordinates = new HashSet<>();
            LinkedHashSet<Fold> folds = new LinkedHashSet<>();

            while (reader.hasNextLine()) {
                var line = reader.nextLine();
                String[] input;
                if (line.equals("")) break;
                else input = line.split(",");
                coordinates.add(new Coordinate(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
            }

            while (reader.hasNextLine()) {
                String[] input;
                var line = reader.nextLine();
                if (line.equals("")) continue;
                else input = line.substring(11).split("=");
                folds.add(new Fold(input[0], Integer.parseInt(input[1])));
            }

            //Process first instruction
            Fold fold = (Fold) folds.toArray()[0];
            for (Coordinate coordinate : coordinates) {
                if (fold.axis.equals("y")) {
                    if (coordinate.y <= fold.value) {
                        results.add(new Coordinate(coordinate.x, coordinate.y));
                    } else {
                        results.add(new Coordinate(coordinate.x, fold.value - (coordinate.y - fold.value)));
                    }
                } else {
                    if (coordinate.x <= fold.value) {
                        results.add(new Coordinate(coordinate.x, coordinate.y));
                    } else {
                        results.add(new Coordinate(fold.value - (coordinate.x - fold.value), coordinate.y));
                    }
                }
            }

            System.out.println(results.size());
            reader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}