package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {

    private final String reversedString;

    public ReversedSequence(String text) {
        StringBuilder s  = new StringBuilder(text);
        reversedString = s.reverse().toString();
    }

    @Override
    public int length() {
        return reversedString.length();
    }

    @Override
    public char charAt(int index) {
        return reversedString.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return reversedString.substring(start, end);
    }

    @Override
    public String toString() {
        return reversedString;
    }
}
// END
