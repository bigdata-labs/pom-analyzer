package codes.showme.pomAnalyzer.entity;

import java.util.List;

/**
 * @author guanhong 2017/1/3.
 */
public class Build {
    private PluginManagement pluginManagement;
    private List<Plugin> plugins;
    private List<Dependency> extensions;
    private List<String> filters;
    private String defaultGoal;

    private String outputDirectory;
    private String testOutputDirectory;

    public PluginManagement getPluginManagement() {
        return this.pluginManagement;
    }

    public void setPluginManagement(PluginManagement pluginManagement) {
        this.pluginManagement = pluginManagement;
    }

    public List<Plugin> getPlugins() {
        return this.plugins;
    }

    public void setPlugins(List<Plugin> plugins) {
        this.plugins = plugins;
    }

    public List<Dependency> getExtensions() {
        return this.extensions;
    }

    public void setExtensions(List<Dependency> extensions) {
        this.extensions = extensions;
    }

    public String getDefaultGoal() {
        return this.defaultGoal;
    }

    public void setDefaultGoal(String defaultGoal) {
        this.defaultGoal = defaultGoal;
    }

    public String getOutputDirectory() {
        return this.outputDirectory;
    }

    public void setOutputDirectory(String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public String getTestOutputDirectory() {
        return this.testOutputDirectory;
    }

    public void setTestOutputDirectory(String testOutputDirectory) {
        this.testOutputDirectory = testOutputDirectory;
    }

    public List<String> getFilters() {
        return this.filters;
    }

    public void setFilters(List<String> filters) {
        this.filters = filters;
    }
}
