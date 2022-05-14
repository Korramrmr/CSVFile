import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        args = null;

        HashMap<String, Integer> counterWords = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        int countWords = 0;

        try (Reader reader = new InputStreamReader(new BufferedInputStream(new FileInputStream("fileToRead.txt")))) {

            int currentSymbol;
            while ((currentSymbol = reader.read()) != -1) {
                char charSymbol = (char) currentSymbol;
                if (Character.isLetterOrDigit(charSymbol)) {
                    builder.append(charSymbol);
                } else if (builder.length() > 0) {
                    String word = builder.toString();
                    addToMap(counterWords, word);
                    countWords++;
                    builder = new StringBuilder();
                }
            }
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Некорректный входной файл");
        }
        List<Map.Entry<String, Integer>> listWords;
        listWords = sortMap(counterWords);

        writeCSV(listWords, countWords);
    }

    private static void addToMap(HashMap<String, Integer> counterWords, String word) {
        int counter = 1;
        Integer currentCount = counterWords.get(word);
        if (currentCount != null) {
            counter += currentCount;
        }
        counterWords.put(word, counter);
    }

    private static List<Map.Entry<String, Integer>> sortMap(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> {
            int comparableValue = o2.getValue().compareTo(o1.getValue());
            if (comparableValue != 0) {
                return comparableValue;
            }
            return o1.getKey().compareTo(o2.getKey());
        });
        return list;
    }

    private static void writeCSV(List<Map.Entry<String, Integer>> list, int countWords) {

        try (PrintWriter writer = new PrintWriter("frequencyWords.csv")) {
            writer.printf("%-15s%-15s%s%n", "Слово", "Частота,", "Частота (в %)");
            for (Map.Entry<String, Integer> wordsList : list) {
                writer.printf("%-15s%-15s%.3f%n", wordsList.getKey() + ",", wordsList.getValue() + ",", (100.0f * wordsList.getValue() / countWords));
            }
            System.out.println("Файл записан успешно.");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
