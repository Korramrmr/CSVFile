package csv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PercentEvaluatorTest {

    @Test
    public void shouldReturnCurrentPercent() {
        PercentEvaluator percentEvaluator = new PercentEvaluator(10);
        assertEquals(200.0, percentEvaluator.getPercent(20));
    }

    @Test
    public void sTest() {
        PercentEvaluator percentEvaluator = new PercentEvaluator(0);
        assertEquals(Double.NaN, percentEvaluator.getPercent(0));
    }

}