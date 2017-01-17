package codes.showme.pomAnalyzer.utils;

import codes.showme.pomAnalyzer.entity.*;
import com.thoughtworks.xstream.XStream;

import java.io.InputStream;

/**
 * @author guanhong 2017/1/3.
 */
public class Converter {

    public Pom buildPom(InputStream xml) {
        XStream xs = new XStream();
        xs.setMode(XStream.NO_REFERENCES);
        xs.alias("project", Pom.class);
        xs.alias("dependency", Dependency.class);
        xs.alias("extension", Dependency.class);
        xs.alias("plugin", Plugin.class);
        xs.alias("profile", Profile.class);
        xs.alias("exclusion", Exclusion.class);
        xs.alias("execution", Execution.class);
        xs.alias("pluginRepository", Profile.Repository.class);
        xs.alias("repository", Profile.Repository.class);
        xs.alias("license", Pom.License.class);
        xs.alias("module", String.class);
        xs.alias("filter", String.class);
        xs.alias("role", String.class);
        xs.alias("developer", Pom.Developer.class);
        xs.ignoreUnknownElements();
        xs.registerConverter(new MapEntryConverter());
        return (Pom) xs.fromXML(xml);
    }

}
