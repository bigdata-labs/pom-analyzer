package codes.showme.pomAnalyzer.entity;

import java.util.List;

/**
 * @author guanhong 2017/1/3 下午4:50.
 */
public class Build {
    private PluginManagement pluginManagement;
    private List<Plugin> plugins;

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
}
