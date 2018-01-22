import java.util.List;
import java.util.Map;

/**
 * Created by jakub on 20/01/2018.
 */
public class DependencyResolver {
    private enum Status {
        RESOLVED,
        RESOLVING,
    }
    public static List<Package> resolve(List<Package> packages) {
        Map<Package, Status>
        for (Package p : packages) {

        }
    }

    public static List<Package> resolve(List<String> packageNames, List<String[]> dependencies) {
        // build the dependency graph
    }
}
