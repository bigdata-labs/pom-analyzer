package codes.showme.pomAnalyzer.entity;

import java.util.List;

/**
 * @author guanhong 2017/1/3.
 */
public class DependencyManagement {
    private List<Dependency> dependencies;

    public List<Dependency> getDependencies() {
        return this.dependencies;
    }

    public void setDependencies(List<Dependency> dependencies) {
        this.dependencies = dependencies;
    }
}
