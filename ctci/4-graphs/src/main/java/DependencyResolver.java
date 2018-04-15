import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO work in progress
 */
public class DependencyResolver {
    private enum Status {
        RESOLVED,
        RESOLVING,
    }
    public static List<Package> resolve(List<Package> packages) {
        Map<Package, Status> status = new HashMap<>();
        return new ArrayList<>();
    }

    public static List<Package> resolve(List<String> packageNames, List<String[]> dependencies) {
        // build the dependency graph
        return new ArrayList<>();
    }
}
