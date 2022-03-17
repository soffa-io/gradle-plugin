package dev.soffa.foundation.gradle.profiles

import org.gradle.api.Plugin
import org.gradle.api.Project

class FoundationProjectPlugin implements Plugin<Project> {

    @Override
    void apply(Project p) {
        p.subprojects { prj ->
            if (prj.name.endsWith("-api")) {
                prj.plugins.apply("foundation.api")
            }else if (prj.name.endsWith("-service")) {
                prj.plugins.apply("foundation.service")
                prj.dependencies {
                    it.add("implementation", project(":${replaceLast(prj.name, "-service", "-api")}"))
                }
            }
            prj.plugins.apply("foundation.default-repositories")
        }
    }


    private static String replaceLast(String string, String substring, String replacement) {
        int index = string.lastIndexOf(substring);
        if (index == -1)
            return string;
        return string.substring(0, index) + replacement + string.substring(index+substring.length());
    }
}
