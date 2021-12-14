import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class App {

    public static void main(String[] args) {
        try {
            File myObj = new File("src/main/resources/input_day14.txt");
            Scanner reader = new Scanner(myObj);
            while (reader.hasNextLine()) {
                var line = reader.nextLine();

            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}