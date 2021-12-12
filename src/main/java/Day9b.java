import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day9b {
    void process() {
        try {
            File myObj = new File("src/main/resources/input_day9.txt");
            Scanner reader = new Scanner(myObj);
            List<Long> results = new ArrayList<>();
            long result = 0;
            while (reader.hasNextLine()) {
                var line = reader.nextLine().toCharArray();
                List<Character> charStack = new ArrayList<>();
                result = 0;
                boolean invalid = false;
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
                                    invalid = true;
                                    break loop;
                                }

                                break;
                            case '>':
                                if (item == '<')
                                    charStack.remove(charStack.size() - 1);
                                else {
                                    invalid = true;
                                    break loop;
                                }
                                break;
                            case '}':
                                if (item == '{')
                                    charStack.remove(charStack.size() - 1);
                                else {
                                    invalid = true;
                                    break loop;
                                }
                                break;
                            case ']':
                                if (item == '[')
                                    charStack.remove(charStack.size() - 1);
                                else {
                                    invalid = true;
                                    break loop;
                                }
                                break;
                        }
                    }
                }

                if (!invalid) {
                    while (charStack.size() > 0) {
                        var item = charStack.remove(charStack.size() - 1);
                        switch (item) {
                            case '(':
                                result *= 5;
                                result += 1;
                                break;
                            case '<':
                                result *= 5;
                                result += 4;
                                break;
                            case '{':
                                result *= 5;
                                result += 3;
                                break;
                            case '[':
                                result *= 5;
                                result += 2;
                                break;
                        }

                    }
                    results.add(result);
                }
            }
            Collections.sort(results);
            System.out.println(results);
            System.out.println(results.size() + " " + results.size() / 2 + " " + results.get(results.size() / 2));
            reader.close();
        } catch (
                FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
