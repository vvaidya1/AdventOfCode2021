import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day14a {
    void process() {
        try {
            File myObj = new File("src/main/resources/input_day14.txt");
            Scanner reader = new Scanner(myObj);
            Map<String, String> polymerTemplate = new HashMap<>();

            String input = "";
            while (reader.hasNextLine()) {
                var line = reader.nextLine();

                if (input.equals("")) input = line;
                else if (!line.equals("")) {
                    String[] mapEntry = line.split(" -> ");
                    String key = mapEntry[0];
                    String value = mapEntry[1];

                    polymerTemplate.put(key, value);
                }
            }

            StringBuffer result = new StringBuffer();
            for (int j = 0; j < 10; j++) {
                result = new StringBuffer();
                for (int i = 1; i < input.length(); i++) {
                    result.append(input.charAt(i - 1));
                    String key = input.substring(i - 1, i + 1);
                    result.append(polymerTemplate.get(key));
                }
                result.append(input.charAt(input.length() - 1));
                input = result.toString();
                System.out.println(result);
            }
            System.out.println();
            System.out.println(result);

            Map<Character, Integer> resultMap = new HashMap<>();
            for (int i = 0; i < result.length(); i++) {
                resultMap.put(result.charAt(i), resultMap.getOrDefault(result.charAt(i), 0) + 1);
            }

            long max = Objects.requireNonNull(resultMap.values().stream().max(Comparator.comparingInt(i -> i)).orElse(null));
            long min = Objects.requireNonNull(resultMap.values().stream().min(Comparator.comparingInt(i -> i)).orElse(null));
            System.out.println("max = " + max + "; " + "min = " + min + "; max - min = " + (max - min));
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
