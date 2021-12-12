import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day9a {
    void process() {
        try {
            File myObj = new File("src/main/resources/input_day9.txt");
            Scanner reader = new Scanner(myObj);
            List<String> input = new ArrayList<>();
            int result = 0;
            while (reader.hasNextLine()) {
                var line = reader.nextLine().toCharArray();
                List<Character> charStack = new ArrayList<>();

                loop:
                for (char ch : line) {
                    if (ch == '(' || ch == '<' || ch == '{' || ch == '[') {
                        charStack.add(ch);
                    } else {
                        var item = charStack.get(charStack.size() - 1);
                        switch (ch) {
                            case ')':
                                if (item == '(')
                                    charStack.remove(charStack.size() - 1);
                                else {
                                    result += 3;
                                    break loop;
                                }
                                break;
                            case '>':
                                if (item == '<')
                                    charStack.remove(charStack.size() - 1);
                                else {
                                    result += 25137;
                                    break loop;
                                }
                                break;
                            case '}':
                                if (item == '{')
                                    charStack.remove(charStack.size() - 1);
                                else {
                                    result += 1197;
                                    break loop;
                                }
                                break;
                            case ']':
                                if (item == '[')
                                    charStack.remove(charStack.size() - 1);
                                else {
                                    result += 57;
                                    break loop;
                                }
                                break;
                        }
                    }
                }

            }
            System.out.println(result);
            reader.close();
        } catch (
                FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
