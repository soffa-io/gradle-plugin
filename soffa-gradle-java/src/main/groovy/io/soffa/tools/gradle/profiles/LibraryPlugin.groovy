package io.soffa.tools.gradle.profiles

import org.gradle.api.Plugin
import org.gradle.api.Project

class LibraryPlugin implements Plugin<Project> {

    void apply(Project project) {

        project.subprojects { it ->
            it.plugins.apply("soffa.kotlin")
            it.plugins.apply("soffa.lombok")
            it.plugins.apply("soffa.test.junit5")
            it.plugins.apply("soffa.maven-publish")
            it.plugins.apply("soffa.qa.coverage")
            // it.plugins.apply("soffa.qa.pmd")
            // it.plugins.apply("soffa.default-repositories")
        }

        // project.plugins.apply("soffa.default-repositories")
        //project.plugins.apply("soffa.qa.coverage-root")

    }

}
