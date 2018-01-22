import java.util.NoSuchElementException;

public class ThreeStacks<T> {
    private int[] sz;
    private int[] cap;
    T[] arr;

    @SuppressWarnings("unchecked")
    public ThreeStacks(int initialSize) {
        cap = new int[] {initialSize, initialSize, initialSize};
        sz = new int[] {0, 0, 0};
        arr = (T[]) new Object[initialSize * 3];
    }

    public ThreeStacks() {
        this(4);
    }

    public void push(int stackId, T value) {
        if (sz[stackId] == cap[stackId]) {
            resize(stackId, cap[stackId] * 2);
        }
        int idx = getInsertIndex(stackId);
        arr[idx] = value;
        sz[stackId] += 1;

    }

    public T pop(int stackId) {
        int idx = getTopIndex(stackId);
        T elem = arr[idx];
        sz[stackId] -= 1;
        arr[idx] = null;

        if (sz[stackId] < cap[stackId] / 4) {
            resize(stackId, cap[stackId] / 2);
        }
        return elem;
    }

    public T peek(int stackId) {
        return arr[getTopIndex(stackId)];
    }

    public int size(int stackId) {
        return sz[stackId];
    }

    private int getTopIndex(int stackId) {
        if (sz[stackId] == 0) {
            throw new NoSuchElementException(String.format("Stack %d is empty", stackId));
        }
        return getInsertIndex(stackId) - 1;
    }

    private int getInsertIndex(int stackId) {
        int idx = 0;
        // skip the capacities of previous stacks
        for (int i = 0; i < stackId; ++i) {
            idx += cap[i];
        }
        // grab last element of current stack
        idx += sz[stackId];
        return idx;
    }

    @SuppressWarnings("unchecked")
    private void resize(int stackIdx, int newCapacity) {
        int[] oldCap = new int[]{cap[0], cap[1], cap[2]};
        cap[stackIdx] = newCapacity;
        int newTotalSize = 0;
        for (int i = 0; i < 3; ++i) {
            newTotalSize += cap[i];
        }
        T[] resized = (T[]) new Object[newTotalSize];

        int startOld = 0;
        int startNew = 0;
        for (int i = 0; i < 3; ++i) {
            System.arraycopy(arr, startOld, resized, startNew, sz[i]);
            startNew += cap[i];
            startOld += oldCap[i];
        }
        arr = resized;
    }


}
