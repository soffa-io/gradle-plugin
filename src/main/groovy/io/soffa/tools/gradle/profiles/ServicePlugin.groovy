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
            // project.plugins.apply("soffa.default-repositories")
        }
        // project.plugins.apply("soffa.qa.coverage-root")

    }

    private static void interalApply(Project project) {
        project.plugins.apply("soffa.kotlin")
        project.plugins.apply("soffa.lombok")
        project.plugins.apply("soffa.test.junit5")
        project.plugins.apply("soffa.qa.coverage")
        // it.plugins.apply("soffa.qa.pmd")
        // project.plugins.apply("soffa.default-repositories")
    }

}
