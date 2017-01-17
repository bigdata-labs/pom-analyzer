package codes.showme.pomAnalyzer.service;

import codes.showme.pomAnalyzer.entity.simple.Dependency;
import codes.showme.pomAnalyzer.entity.simple.Exclusion;
import codes.showme.pomAnalyzer.entity.simple.Pom;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @author guanhong 2017/1/16.
 */
public class PomFixService {

    private static final Logger logger = LoggerFactory.getLogger(PomFixService.class);

    public PomService pomService = new PomServiceDBImpl();

    /**
     * 完善一个Pom
     *
     * @param completePom Pom
     * @return Pom
     */
    public Pom fixPom(Pom completePom) {
        //合并父母pom
        completePom = mergeDenpendency(completePom);
        //填充version和合并Exclusion
        completePom = fillDenpendency(completePom);
        //根据properties填充version
        return fillVersion(completePom);
    }

    /**
     * 合并父母pom
     *
     * @param completePom pom
     * @return pom
     */
    private Pom mergeDenpendency(Pom completePom) {
        Stack<Pom> pomStack = new Stack<>();
        List<Dependency> dependencyList = new ArrayList<>();
        List<Dependency> dependencyManagementList = new ArrayList<>();
        Map<String, String> propertyMap = new HashMap<>();
        Pom subPom = completePom;
        if (completePom.getParent() != null && StringUtils.isBlank(completePom.getGroupId())) {
            completePom.setGroupId(completePom.getParent().getGroupId());
        }
        do {
            pomStack.add(subPom);
            subPom = subPom.getParent();
        } while (subPom.getParent() != null);
        //整体构建dependency和dependencyManagement列表
        while (!pomStack.empty()) {
            Pom pom = pomStack.pop();
            List<Dependency> dependencyListTemp = pom.getDependencies();
            List<Dependency> dependencyManagementListTemp = pom.getDependencyManagement() != null ? pom.getDependencyManagement().getDependencies() : null;
            Map<String, String> propertyMapTemp = pom.getProperties();
            if (dependencyListTemp != null) {
                dependencyListTemp.forEach(dependency -> {
                    for (int i = dependencyList.size() - 1; i >= 0; i--) {
                        if (dependency.simpleEqual(dependencyList.get(i))) {
                            dependencyList.remove(i);
                        }
                    }
                    dependencyList.add(dependency);
                });
            }

            if (dependencyManagementListTemp != null) {
                dependencyManagementListTemp.forEach(dependency -> {
                    for (int i = dependencyManagementList.size() - 1; i >= 0; i--) {
                        if (dependency.simpleEqual(dependencyManagementList.get(i))) {
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

        completePom.setDependencies(dependencyList);
        completePom.getDependencyManagement().setDependencies(dependencyManagementList);
        completePom.setProperties(propertyMap);
        return completePom;
    }

    /**
     * 填充version和合并Exclusion
     *
     * @param pom pom
     * @return pom
     */
    private Pom fillDenpendency(Pom pom) {
        List<Dependency> dependencyList = pom.getDependencies();
        List<Dependency> dependencyManagementList = pom.getDependencyManagement().getDependencies();
        Map<String, String> propertyMap = pom.getProperties();

        //合并version和Exclusion
        for (Dependency dependency : dependencyList) {
            for (Dependency dependencyManagement : dependencyManagementList) {
                if (dependency.simpleEqual(dependencyManagement)) {
                    if (StringUtils.isBlank(dependency.getVersion())) {
                        dependency.setVersion(dependencyManagement.getVersion());
                    }
                    List<Exclusion> dependencyManagementExclusionList = dependencyManagement.getExclusions();
                    List<Exclusion> dependencyExclusionList = dependency.getExclusions();
                    if (dependencyExclusionList == null) {
                        dependencyExclusionList = new ArrayList<>();
                    }
                    if (dependencyManagementExclusionList == null) {
                        dependencyManagementExclusionList = new ArrayList<>();
                    }
                    for (Exclusion exclusion : dependencyManagementExclusionList) {
                        if (!containExclusion(dependencyExclusionList, exclusion)) {
                            dependencyExclusionList.add(exclusion);
                        }
                    }
                    dependency.setExclusions(dependencyExclusionList);
                }
            }
        }
        pom.setDependencies(dependencyList);
        pom.getDependencyManagement().setDependencies(dependencyManagementList);
        pom.setProperties(propertyMap);
        return pom;
    }

    /**
     * 根据properties填充version
     *
     * @param completePom 完整的pom文件
     * @return 填充version后的pom文件
     */
    private Pom fillVersion(Pom completePom) {
        Map<String, String> propertiesTrans = new HashMap<>();
        for (Map.Entry<String, String> entry : completePom.getProperties().entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
            propertiesTrans.put("${" + entry.getKey() + "}", entry.getValue());
        }
        completePom.setGroupId(getByProperties(propertiesTrans, completePom.getGroupId()));
        completePom.setArtifactId(getByProperties(propertiesTrans, completePom.getArtifactId()));
        completePom.setVersion(getByProperties(propertiesTrans, completePom.getVersion()));
        for (Dependency dependency : completePom.getDependencyManagement().getDependencies()) {
            dependency.setVersion(getByProperties(propertiesTrans, dependency.getVersion()));
        }

        for (Dependency dependency : completePom.getDependencies()) {
            dependency.setVersion(getByProperties(propertiesTrans, dependency.getVersion()));
        }
        return completePom;
    }

    /**
     * 从配置列表中获取信息
     *
     * @param propertiesTrans 配置列表
     * @param temp            要匹配的字符串
     * @return 匹配后的字符串
     */
    private String getByProperties(Map<String, String> propertiesTrans, String temp) {
        Pattern pattern = Pattern.compile("\\$\\{.+\\}");
        if (pattern.matcher(temp).matches()) {
            return propertiesTrans.getOrDefault(temp, temp);
        }
        return temp;
    }

    /**
     * 是否包含Exclusion
     *
     * @param exclusionList Exclusion列表
     * @param exclusion     被判断的Exclusion
     * @return boolean
     */
    private boolean containExclusion(List<Exclusion> exclusionList, Exclusion exclusion) {
        for (Exclusion exclusionTemp : exclusionList) {
            if (exclusionTemp.simpleEqual(exclusion)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 完善一个pom的父母pom信息
     *
     * @param basicPom pom
     * @return 完善后的pom
     */
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
