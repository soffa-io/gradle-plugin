package dev.soffa.foundation.gradle.profiles

import org.gradle.api.Plugin
import org.gradle.api.Project

class FoundationProjectKotlinPlugin implements Plugin<Project> {

    @Override
    void apply(Project p) {
        p.subprojects { prj ->
            if (prj.name.endsWith("-api")) {
                prj.plugins.apply("foundation.api.kotlin")
            }else if (prj.name.endsWith("-service")) {
                prj.plugins.apply("foundation.service.kotlin")
                prj.dependencies {
                    it.add("implementation", project(":${prj.name.replace("-service", "-api")}"))
                }
            }

            prj.plugins.apply("foundation.default-repositories")
        }
    }
}
