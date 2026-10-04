package Game;

/**
 * A {@code Counter} is a simple utility class used to keep track of a numerical count.
 * It can be increased or decreased by a specified amount.
 */
public class Counter {
    private int counter;

    /**
     * Constructs a new {@code Counter} initialized to zero.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * Increases the counter by the specified number.
     *
     * @param number the amount to add to the counter.
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * Decreases the counter by the specified number.
     *
     * @param number the amount to subtract from the counter.
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * Returns the current value of the counter.
     *
     * @return the current counter value.
     */
    public int getValue() {
        return this.counter;
    }
}
