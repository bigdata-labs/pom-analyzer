package codes.showme.pomAnalyzer.utils;

import codes.showme.pomAnalyzer.entity.simple.Dependency;
import codes.showme.pomAnalyzer.entity.simple.Exclusion;
import codes.showme.pomAnalyzer.entity.simple.Pom;
import com.thoughtworks.xstream.XStream;

import java.io.InputStream;

/**
 * @author guanhong 2017/1/3 下午5:02.
 */
public class SimpleConverter {


    public Pom buildPom(InputStream xml) {
        XStream xs = new XStream();
        xs.setMode(XStream.NO_REFERENCES);
        xs.alias("project", Pom.class);
        xs.alias("dependency", Dependency.class);
        xs.alias("extension", Dependency.class);
        xs.alias("exclusion", Exclusion.class);
        xs.alias("module", String.class);
        xs.ignoreUnknownElements();
        xs.registerConverter(new MapEntryConverter());
        return (Pom) xs.fromXML(xml);
    }

    public Pom buildPom(String xml) {
        XStream xs = new XStream();
        xs.setMode(XStream.NO_REFERENCES);
        xs.alias("project", Pom.class);
        xs.alias("dependency", Dependency.class);
        xs.alias("extension", Dependency.class);
        xs.alias("exclusion", Exclusion.class);
        xs.alias("module", String.class);
        xs.ignoreUnknownElements();
        xs.registerConverter(new MapEntryConverter());
        return (Pom) xs.fromXML(xml);
    }

}
