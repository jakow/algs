import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class EdgeInput {
    int tail;
    int head;
    int weight;

    EdgeInput(int head, int tail, int weight) {
        this.tail = tail;
        this.head = head;
        this.weight = weight;
    }

    static List<EdgeInput> fromScanner(Scanner s) {
        List<EdgeInput> ret = new ArrayList<>();
        s.nextInt();
        s.nextInt();
        while(s.hasNextInt()) {
            int head = s.nextInt();
            int tail = s.nextInt();
            int weight = s.nextInt();
            EdgeInput e = new EdgeInput(head, tail, weight);
            ret.add(e);
        }
        return ret;
    }
}
