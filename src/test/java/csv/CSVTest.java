package csv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CSVTest {

    @Test
    @DisplayName("Test for addToMap method")
    public void hashMapShouldContainsCorrectKey() {
        HashMap<String, Integer> counterWords = new HashMap<>();
        CSV csv = new CSV();
        Integer expectedCount = 1;
        String stringToCount = "Tiefling";

        csv.addToMap(counterWords, stringToCount);
        assertEquals(expectedCount, counterWords.get("Tiefling"));
    }

    @Test
    @DisplayName("Second test for addToMap method")
    public void hashMapShouldContainsCorrectKeyIfKeyAlreadyInMap() {
        HashMap<String, Integer> testMap = new HashMap<>();
        CSV csv = new CSV();
        int initialCount = 1;
        int expectedCount = 2;
        String stringToCount = "Tiefling";
        testMap.put(stringToCount, initialCount);

        csv.addToMap(testMap, "Tiefling");
        assertEquals(expectedCount, testMap.get("Tiefling"));
    }

    @Test
    @DisplayName("Test for sortMap method")
    public void shouldReturnCurrentList() {
        CSV csv = new CSV();
        HashMap<String, Integer> counterWords = new HashMap<>();

        counterWords.put("Tiefling", 1);
        counterWords.put("Spells", 2);
        counterWords.put("Baldur", 3);
        counterWords.put("Gate", 4);

        List<Map.Entry<String, Integer>> listWords = csv.sortMap(counterWords);

        assertEquals("Gate", listWords.get(0).getKey());
        assertEquals(4, listWords.get(0).getValue());
        assertEquals("Baldur", listWords.get(1).getKey());
        assertEquals(3, listWords.get(1).getValue());
        assertEquals("Spells", listWords.get(2).getKey());
        assertEquals(2, listWords.get(2).getValue());
        assertEquals("Tiefling", listWords.get(3).getKey());
        assertEquals(1, listWords.get(3).getValue());

    }

}




