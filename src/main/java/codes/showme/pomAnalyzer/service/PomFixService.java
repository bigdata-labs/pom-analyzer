package codes.showme.pomAnalyzer.service;

import codes.showme.pomAnalyzer.entity.simple.Dependency;
import codes.showme.pomAnalyzer.entity.simple.Pom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author guanhong 2017/1/16.
 */
public class PomFixService {

    private static final Logger logger = LoggerFactory.getLogger(PomFixService.class);

    public PomService pomService = new PomServiceDBImpl();

    public Pom fixPom(Pom completePom) {
        Stack<Pom> pomStack = new Stack<>();
        List<Dependency> dependencyList = new ArrayList<>();
        List<Dependency> dependencyManagementList = new ArrayList<>();
        Map<String, String> propertyMap = new HashMap<>();
        Pom subPom = completePom;
        do {
            pomStack.add(subPom);
            subPom = subPom.getParent();
        } while (subPom.getParent() != null);
        while (!pomStack.empty()) {
            Pom pom = pomStack.pop();
            List<Dependency> dependencyListTemp = pom.getDependencies();
            List<Dependency> dependencyManagementListTemp = pom.getDependencyManagement() != null ? pom.getDependencyManagement().getDependencies() : null;
            Map<String, String> propertyMapTemp = pom.getProperties();
            if (dependencyListTemp != null) {
                dependencyListTemp.forEach(dependency -> {
                    for (int i = dependencyList.size() - 1; i >= 0; i--) {
                        if (isDependencyEqual(dependencyList.get(i), dependency)) {
                            dependencyList.remove(i);
                        }
                    }
                    dependencyList.add(dependency);
                });
            }

            if (dependencyManagementListTemp != null) {
                dependencyManagementListTemp.forEach(dependency -> {
                    for (int i = dependencyManagementList.size() - 1; i >= 0; i--) {
                        if (isDependencyEqual(dependencyManagementList.get(i), dependency)) {
                            dependencyManagementList.remove(i);
                        }
                    }
                    dependencyManagementList.add(dependency);
                });
            }

            if (propertyMapTemp != null) {
                propertyMap.putAll(propertyMapTemp);
            }
        }

        //todo replace pattern of version & exclusion, learning how to handle dependencyManagement's exclusion & dependency's exclusion


        return completePom;
    }

    private boolean isDependencyEqual(Dependency dependency1, Dependency dependency2) {
        if (dependency1 == null || dependency2 == null) {
            return false;
        }
        return dependency1.getArtifactId().equals(dependency2.getArtifactId()) && dependency1.getGroupId().equals(dependency2.getGroupId());
    }

    public Pom completePom(Pom basicPom) {
        Pom subPom = basicPom;
        while (subPom.getParent() != null) {
            Pom parentPom = subPom.getParent();
            Pom newParentPom = pomService.getParentPom(parentPom.getArtifactId(), parentPom.getGroupId(), parentPom.getVersion());
            if (newParentPom == null) {
                logger.error("父pom获取失败：" + parentPom.getArtifactId() + " : " + parentPom.getGroupId() + " : " + parentPom.getVersion());
                break;
            }
            subPom.setParent(newParentPom);
            subPom = subPom.getParent();
        }
        return basicPom;
    }

}
