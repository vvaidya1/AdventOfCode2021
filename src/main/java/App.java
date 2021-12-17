import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        try {
            File myObj = new File("src/main/resources/input_day17.txt");
            Scanner reader = new Scanner(myObj);
            List<String> input = new ArrayList<>();

            while (reader.hasNextLine()) {
                input.add(reader.nextLine());
            }

            (new Day17b()).process(input);
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}