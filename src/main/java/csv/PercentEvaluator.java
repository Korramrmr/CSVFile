package csv;

public final class PercentEvaluator {
    private final int countWords;

    public PercentEvaluator(final int countWords) {
        this.countWords = countWords;
    }

    float getPercent(final int value) {
        return 100.0f * value / countWords;
    }
}
