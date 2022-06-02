package csv;

import java.io.*;
import java.util.*;


public final class CSV {
    private final HashMap<String, Integer> counterWords = new HashMap<>();
    private int countWords = 0;
    private List<Map.Entry<String, Integer>> listWords;

    public CSV() {
    }

    public void writeToFile(final String inFileName, final String outFileName) {
        if (inFileName == null) {
            System.out.println("Файл не найден.");
            return;
        } else {
            readFileWords(inFileName);
        }
        if (outFileName != null) {
            final File file = new File(outFileName);
            try (final PrintWriter writer = new PrintWriter(file.getAbsoluteFile())) {
                writeCSV(listWords, countWords, writer);
                System.out.println("Файл записан успешно.");
            } catch (final FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    void readFileWords(final String inFileName) {
        try (final Reader reader = new InputStreamReader(new BufferedInputStream(new FileInputStream(inFileName)))) {
            StringBuilder builder = new StringBuilder();
            int currentSymbol;
            while ((currentSymbol = reader.read()) != -1) {
                final char charSymbol = (char) currentSymbol;
                if (Character.isLetterOrDigit(charSymbol)) {
                    builder.append(charSymbol);
                } else if (builder.length() > 0) {
                    final String word = builder.toString();
                    addToMap(counterWords, word);
                    countWords++;
                    builder = new StringBuilder();
                }
            }
        } catch (final IOException e) {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        } catch (final IllegalArgumentException e) {
            System.out.println("Некорректный входной файл");
        }
        listWords = sortMap(counterWords);
    }

    void addToMap(final HashMap<String, Integer> counterWords, final String word) {
        int counter = 1;
        Integer currentCount = counterWords.get(word);
        if (currentCount != null) {
            counter += currentCount;
        }
        counterWords.put(word, counter);
    }

    List<Map.Entry<String, Integer>> sortMap(final HashMap<String, Integer> map) {
        final List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> {
            final int comparableValue = o2.getValue().compareTo(o1.getValue());
            if (comparableValue != 0) {
                return comparableValue;
            }
            return o1.getKey().compareTo(o2.getKey());
        });
        return list;
    }

    void writeCSV(final List<Map.Entry<String, Integer>> list, final int countWords, final PrintWriter writer) {
        writer.printf("%-15s%-15s%s%n", "Слово", "Частота,", "Частота (в %)");
        for (Map.Entry<String, Integer> wordsList : list) {
            writer.printf("%-15s%-15s%.3f%n", wordsList.getKey() + ",", wordsList.getValue() + ",", (100.0f * wordsList.getValue() / countWords));
        }
    }
}


