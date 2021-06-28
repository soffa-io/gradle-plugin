package io.soffa.tools.gradle.profiles

import org.gradle.api.Plugin
import org.gradle.api.Project

class ServicePlugin implements Plugin<Project> {

    void apply(Project project) {

        if (project.subprojects.isEmpty()) {
            interalApply(project)
        } else {
            project.subprojects { Project it ->
                interalApply(it)
            }
            project.plugins.apply("foundation.default-repositories")
        }
        project.plugins.apply("foundation.qa.coverage-root")

    }

    private static void interalApply(Project project) {
        project.plugins.apply("foundation.kotlin")
        project.plugins.apply("foundation.lombok")
        project.plugins.apply("foundation.test.junit5")
        project.plugins.apply("foundation.qa.coverage")
        // it.plugins.apply("foundation.qa.pmd")
        project.plugins.apply("foundation.default-repositories")
    }

}
