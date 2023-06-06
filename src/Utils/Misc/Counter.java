package Utils.Misc;

/**
 * The type Counter.
 */
public class Counter {
    private int number = 0;

    /**
     * Increase.
     * add number to current count.
     *
     * @param number the number
     */
    public void increase(int number) {
        this.number += number;
    }

    /**
     * Decrease.
     * subtract number from current count.
     *
     * @param number the number
     */
    public void decrease(int number) {
        this.number -= number;
    }

    /**
     * Gets value.
     * Gets current count.
     *
     * @return the value
     */
    public int getValue() {
        return this.number;
    }
}
