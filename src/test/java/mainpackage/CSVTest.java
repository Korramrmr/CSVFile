package mainpackage;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CSVTest {

    @Test
    @DisplayName("Test for addToMap method")
    public void hashMapShouldBeContainsCorrectKey() {
        HashMap<String, Integer> testMap = new HashMap<>();
        CSV csv = new CSV();
        Integer expected = 1;
        String string = "Tiefling";

        csv.addToMap(testMap, string);
        assertEquals(expected, testMap.get("Tiefling"));
    }

    @Test
    @DisplayName("Second test for addToMap method")
    public void hashMapShouldBeContainsCorrectKeyIfKeyAlreadyInMap() {
        HashMap<String, Integer> testMap = new HashMap<>();
        CSV csv = new CSV();
        int expected = 1;
        String string = "Tiefling";
        testMap.put(string, expected);

        csv.addToMap(testMap, "Tiefling");
        assertEquals(expected + 1, testMap.get("Tiefling"));
    }

    @Test
    @DisplayName("Test for sortMap method")
    public void shouldReturnCurrentList() {
        CSV csv = new CSV();
        HashMap<String, Integer> testMap = new HashMap<>();

        testMap.put("Tiefling", 1);
        testMap.put("Spells", 2);
        testMap.put("Baldur", 3);
        testMap.put("Gate", 4);

        List<Map.Entry<String, Integer>> testList = csv.sortMap(testMap);

        assertEquals("Gate", testList.get(0).getKey());
        assertEquals(4, testList.get(0).getValue());
        assertEquals("Baldur", testList.get(1).getKey());
        assertEquals(3, testList.get(1).getValue());
        assertEquals("Spells", testList.get(2).getKey());
        assertEquals(2, testList.get(2).getValue());
        assertEquals("Tiefling", testList.get(3).getKey());
        assertEquals(1, testList.get(3).getValue());

    }

}




