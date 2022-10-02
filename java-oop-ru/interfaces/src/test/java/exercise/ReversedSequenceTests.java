package exercise;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReversedSequenceTests {

    @Test
    void testReversedSequence() {
        CharSequence text = new ReversedSequence("abcdef");
        assertThat(text.toString()).isEqualTo("fedcba");
        assertThat(text.charAt(1)).isEqualTo('e');
        assertThat(text.length()).isEqualTo(6);
        assertThat(text.subSequence(1, 4).toString()).isEqualTo("edc");
    }

    @Test
    void testEmptyString() {
        CharSequence text = new ReversedSequence("");
        assertThat(text.toString()).isEqualTo("");
        assertThat(text.length()).isEqualTo(0);
        try {
            text.charAt(1);
        } catch (Exception e) {
            assertThat(e.equals(new StringIndexOutOfBoundsException()));
        }
        try {
            text.subSequence(1, 4).toString();
        } catch (Exception e) {
            assertThat(e.equals(new StringIndexOutOfBoundsException()));
        }
    }
}
