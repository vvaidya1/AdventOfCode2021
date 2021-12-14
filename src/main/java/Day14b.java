import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day14b {
    static Map<String, String> polymerTemplate = new HashMap<>();

    void process() {
        try {
            File myObj = new File("src/main/resources/input_day14.txt");
            Scanner reader = new Scanner(myObj);
            StringBuilder input = null;
            while (reader.hasNextLine()) {
                var line = reader.nextLine();

                if (input == null) input = new StringBuilder(line);
                else if (!line.equals("")) {
                    String[] mapEntry = line.split(" -> ");
                    String key = mapEntry[0];
                    String value = mapEntry[1];

                    polymerTemplate.put(key, value);
                }
            }

            Map<String, Long> pairsCount = new HashMap<>();
            for (int i = 0; i < Objects.requireNonNull(input).length() - 1; i++) {
                String pair = input.substring(i, i + 2);
                pairsCount.put(pair, pairsCount.getOrDefault(pair, 0L) + 1);
            }

            pairsCount = calcPairCounts(input.toString(), 40);
            System.out.println(countLetters(input.toString(), pairsCount));
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static Map<String, Long> calcPairCounts(String polymer, int steps) {
        Map<String, Long> pairCounts = new HashMap<>();
        for (int i = 0; i < polymer.length() - 1; i++) {
            String pair = polymer.substring(i, i + 2);
            pairCounts.put(pair, pairCounts.getOrDefault(pair, 0L) + 1);
        }

        for (int i = 0; i < steps; i++) {
            Map<String, Long> newPairCounts = new HashMap<>();
            for (String pair : pairCounts.keySet()) {
                long pairCount = pairCounts.get(pair);
                String insert = polymerTemplate.get(pair);
                String newPair1 = pair.charAt(0) + insert;
                String newPair2 = insert + pair.charAt(1);
                newPairCounts.put(newPair1, newPairCounts.getOrDefault(newPair1, 0L) + pairCount);
                newPairCounts.put(newPair2, newPairCounts.getOrDefault(newPair2, 0L) + pairCount);
            }
            pairCounts = newPairCounts;
        }
        return pairCounts;
    }

    public static long countLetters(String polymer, Map<String, Long> pairCounts) {
        Map<Character, Long> counts = new HashMap<>();
        for (String s : pairCounts.keySet()) {
            long sCount = pairCounts.get(s);
            char c0 = s.charAt(0);
            counts.put(c0, counts.getOrDefault(c0, 0L) + sCount);
        }
        Character lastChar = polymer.charAt(polymer.length() - 1);
        counts.put(lastChar, counts.getOrDefault(lastChar, 0L) + 1);

        long max = Objects.requireNonNull(counts.values().stream().max(Comparator.comparingLong(i -> i)).orElse(null));
        long min = Objects.requireNonNull(counts.values().stream().min(Comparator.comparingLong(i -> i)).orElse(null));

        return (max - min);
    }
}
