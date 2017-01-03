package codes.showme.pomAnalyzer.entity;

import java.util.Map;

/**
 * Created by jeremie on 2017/1/3.
 */
public class Execution {
    private Map<String, String> goals;
    private String id;
    private String phase;
    private String inherited;
    private Map<String, String> configuration;

    public Map<String, String> getGoals() {
        return this.goals;
    }

    public void setGoals(Map<String, String> goals) {
        this.goals = goals;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhase() {
        return this.phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public Map<String, String> getConfiguration() {
        return this.configuration;
    }

    public void setConfiguration(Map<String, String> configuration) {
        this.configuration = configuration;
    }

    public String getInherited() {
        return this.inherited;
    }

    public void setInherited(String inherited) {
        this.inherited = inherited;
    }
}
