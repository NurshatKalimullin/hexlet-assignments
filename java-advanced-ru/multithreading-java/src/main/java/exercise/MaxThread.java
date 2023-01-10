package exercise;

// BEGIN
public class MaxThread extends Thread {

    private final int[] numbers;
    private int maxNumber;

    public MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    @Override
    public void run() {
        Arrays.sort(numbers);
        this.maxNumber = numbers[numbers.length - 1];
    }
}
// END
