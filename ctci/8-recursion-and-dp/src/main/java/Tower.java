import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by jakub on 27/01/2018.
 */
public class Tower {
    Deque<Integer> disks;
    String name;

    public Tower() {
        disks = new ArrayDeque<>();
        name = "";
    }

    public Tower(int diskMin, int diskMax) {
        this();
        addDisks(diskMin, diskMax);

    }

    public Tower(String s, int diskMin, int diskMax) {
        this();
        name = s;
        addDisks(diskMin, diskMax);
    }

    public Tower(String s) {
        this();
        name = s;
    }

    public void addDisks(int diskMin, int diskMax) {
        for (int i = diskMax; i >= diskMin; --i) {
            disks.push(i);
        }
    }

    private void addDisk(int disk) {
        if (!disks.isEmpty() && disk > disks.peek()) {
            throw new IllegalArgumentException("Cannot push larger disk onto the tower");
        }
        disks.push(disk);
    }

    int removeDisk() {
        return disks.pop();
    }

    void moveTopTo(Tower destination) {
        destination.addDisk(removeDisk());
    }

    int numberOfDisks() {
        return disks.size();
    }


    void moveDisks(Tower destination, Tower buffer) {
        moveDisks(destination, buffer, numberOfDisks());
    }

    private void moveDisks(Tower destination, Tower buffer, int n) {
        if (n > 0) {
            moveDisks(buffer, destination, n - 1);
            moveTopTo(destination);
            buffer.moveDisks(destination, this, n - 1);
        }
    }



    @Override
    public String toString() {
        return String.format("%s %s", name, disks.toString());
    }

    public static void main(String[] args) {
        Tower tower1 = new Tower("Tower 1", 1, 30);
        Tower tower2 = new Tower("Tower 2");
        Tower tower3 = new Tower("Tower 3");
        System.out.println(tower1);
        System.out.println(tower2);
        System.out.println(tower3);
        tower1.moveDisks(tower3, tower2);
        System.out.println("RESULT:");
        System.out.println(tower1);
        System.out.println(tower2);
        System.out.println(tower3);
    }
}
