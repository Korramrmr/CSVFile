package mainpackage;

import java.io.*;
import java.util.*;


public class CSV {
    private final HashMap<String, Integer> counterWords = new HashMap<>();
    private int countWords = 0;
    private List<Map.Entry<String, Integer>> listWords;

    public CSV() {
    }

    public void writeToFile(String inFileName, String outFileName) {
        if (inFileName == null) {
            System.out.println("Файл не найден.");
            return;
        } else {
            readFileWords(inFileName);
        }
        if (outFileName != null) {
            File file = new File(outFileName);
            try (PrintWriter writer = new PrintWriter(file.getAbsoluteFile())) {
                writeCSV(listWords, countWords, writer);
                System.out.println("Файл записан успешно.");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void readFileWords(String inFileName) {
        try (Reader reader = new InputStreamReader(new BufferedInputStream(new FileInputStream(inFileName)))) {
            StringBuilder builder = new StringBuilder();
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
        listWords = sortMap(counterWords);
    }

    public void addToMap(HashMap<String, Integer> counterWords, String word) {
        int counter = 1;
        Integer currentCount = counterWords.get(word);
        if (currentCount != null) {
            counter += currentCount;
        }
        counterWords.put(word, counter);
    }

    public List<Map.Entry<String, Integer>> sortMap(HashMap<String, Integer> map) {
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

    public void writeCSV(List<Map.Entry<String, Integer>> list, int countWords, PrintWriter writer) {
        writer.printf("%-15s%-15s%s%n", "Слово", "Частота,", "Частота (в %)");
        for (Map.Entry<String, Integer> wordsList : list) {
            writer.printf("%-15s%-15s%.3f%n", wordsList.getKey() + ",", wordsList.getValue() + ",", (100.0f * wordsList.getValue() / countWords));
        }
    }
}


