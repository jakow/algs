import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakub on 20/01/2018.
 */
public class Package {
    String name;
    List<String> dependencies;

    Package(String name) {
        this.name = name;
        dependencies = new ArrayList<>();
    }
}
