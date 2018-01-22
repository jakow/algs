import java.util.List;

class Utils {
    static <T extends Comparable<T>> int find(List<T> l1, List<T> l2) {
        if (l1.size() < l2.size()) {
            return -1;
        }
        for (int start = 0; start <= l1.size() - l2.size(); ++start) {
            int i = 0;
            boolean isSubstr = true;
            while (i < l2.size()) {
                T t1 = l1.get(start + i);
                T t2 = l2.get(i);
                if ( (t1 == null ^ t2 == null) || t1 != null && !t2.equals(t1) ) {
                    isSubstr = false;
                    break;
                }
                ++i;
            }
            if (isSubstr) {
                return start;
            }
        }
        return -1;
    }
}
