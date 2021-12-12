import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class App {

    public static void main(String[] args) {
        try {
            File myObj = new File("src/main/resources/input_day12.txt");
            Scanner reader = new Scanner(myObj);
            ArrayList<List<String>> results = new ArrayList<>();

            while (reader.hasNextLine()) {
            }

            System.out.println(results);
            System.out.println(results.stream().distinct().count());
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}


