import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {


    public static void main(String[] args) {
        try {
            File myObj = new File("src/main/resources/input_day16.txt");
            Scanner reader = new Scanner(myObj);
            List<String> input = new ArrayList<>();

            while (reader.hasNextLine()) {
                input.add(reader.nextLine());
            }

            Day16a.process(input);
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}