/**
 * Counter counts thing.
 */
public class Counter {
    private int value;

    /**
     * constructor.
     * @param value - the initial value.
     */
    public Counter(int value) {
        this.value = value;
    }

    /**
     * Add number to current count.
     * @param number - the added number.
     */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * Subtract number from current count.
     * @param number - the number to subtract.
     */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * Get current count.
     * @return the count.
     */
    public int getValue() {
        return this.value;
    }
}