import Common.Point;
import Common.WeightedPoint;
import Common.WeightedPointComparator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class App {

    public static void main(String[] args) {
        try {
            File myObj = new File("src/main/resources/input_day15.txt");
            Scanner reader = new Scanner(myObj);
            List<List<Integer>> riskPositions = new ArrayList<>();

            while (reader.hasNextLine()) {
                var line = reader.nextLine();
                reader.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}