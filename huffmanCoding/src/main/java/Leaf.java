/**
 * Created by jakub on 29/12/2017.
 */
public class Leaf extends Node {
    char value;

    Leaf(char value, double frequency) {
        this.value = value;
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return String.format("Leaf(%f) { %c }", frequency, value);
    }
}
