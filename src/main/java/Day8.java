import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day8 {
    void process() {
        try {
            File myObj = new File("src/main/resources/input_day8.txt");
            Scanner reader = new Scanner(myObj);
            List<List<Integer>> input = new ArrayList<>();
            int result = 1, max1 = 1, max2 = 1, max3 = 1;
            while (reader.hasNextLine()) {
                var charRow = reader.nextLine().toCharArray();
                List<Integer> row = new ArrayList<>();

                for (int i = 0; i < charRow.length; i++) {
                    row.add(Character.digit(charRow[i], 10));
                }

                input.add(row);
            }

            /*
            for (int i = 0; i < input.size(); i++) {
                for (int j = 0; j < input.get(0).size(); j++) {
                    int item = input.get(i).get(j);
                    if (i - 1 >= 0 && i + 1 < input.size() && j - 1 >= 0 && j + 1 < input.get(0).size()) {
                        if (item < input.get(i - 1).get(j) && item < input.get(i + 1).get(j) && item < input.get(i).get(j - 1) && item < input.get(i).get(j + 1)) {
                            risk += item + 1;
                        }
                    } else if (i + 1 < input.size() && j - 1 >= 0 && j + 1 < input.get(0).size()) {
                        if (item < input.get(i + 1).get(j) && item < input.get(i).get(j - 1) && item < input.get(i).get(j + 1)) {
                            risk += item + 1;
                        }
                    } else if (i - 1 >= 0 && i + 1 < input.size() && j + 1 < input.get(0).size()) {
                        if (item < input.get(i - 1).get(j) && item < input.get(i + 1).get(j) && item < input.get(i).get(j + 1)) {
                            risk += item + 1;
                        }
                    } else if (i - 1 >= 0 && j - 1 >= 0 && j + 1 < input.get(0).size()) {
                        if (item < input.get(i - 1).get(j) && item < input.get(i).get(j - 1) && item < input.get(i).get(j + 1)) {
                            risk += item + 1;
                        }
                    } else if (i - 1 >= 0 && i + 1 < input.size() && j - 1 >= 0) {
                        if (item < input.get(i - 1).get(j) && item < input.get(i + 1).get(j) && item < input.get(i).get(j - 1)) {
                            risk += item + 1;
                        }
                    } else if (i + 1 < input.size() && j + 1 < input.get(0).size()) {
                        if (item < input.get(i + 1).get(j) && item < input.get(i).get(j + 1)) {
                            risk += item + 1;
                        }
                    } else if (i - 1 < input.size() && j + 1 < input.get(0).size()) {
                        if (item < input.get(i - 1).get(j) && item < input.get(i).get(j + 1)) {
                            risk += item + 1;
                        }
                    } else if (i + 1 < input.size() && j - 1 < input.get(0).size()) {
                        if (item < input.get(i + 1).get(j) && item < input.get(i).get(j - 1)) {
                            risk += item + 1;
                        }
                    } else if (i - 1 < input.size() && j - 1 < input.get(0).size()) {
                        if (item < input.get(i - 1).get(j) && item < input.get(i).get(j - 1)) {
                            risk += item + 1;
                        }
                    }

                }
            }
            */
            boolean[][] visited = new boolean[input.size()][input.get(0).size()];

            for (int i = 0; i < input.size(); i++) {
                for (int j = 0; j < input.get(0).size(); j++) {
                    var key = input.get(i).get(j);
                    if (key == 9 || visited[i][j] == true)
                        continue;

                    int size = dfs(input, i, j, visited);

                    if (size >= max1) {
                        max3 = max2;
                        max2 = max1;
                        max1 = size;
                    } else if (size >= max2) {
                        max3 = max2;
                        max2 = size;
                    } else if (size > max3) {
                        max3 = size;
                    }

                    result = max1 * max2 * max3;
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

    static int dfs(List<List<Integer>> input, int i, int j, boolean[][] visited) {
        if (input.get(i).get(j) == 9 || visited[i][j] == true)
            return 0;

        visited[i][j] = true;
        int size = 1;
        if (i + 1 < input.size())
            size += dfs(input, i + 1, j, visited);

        if (j + 1 < input.get(0).size())
            size += dfs(input, i, j + 1, visited);

        if (i - 1 >= 0)
            size += dfs(input, i - 1, j, visited);

        if (j - 1 >= 0)
            size += dfs(input, i, j - 1, visited);

        return size;
    }
}