package io.soffa.tools.gradle.profiles

import org.gradle.api.Plugin
import org.gradle.api.Project

class LibraryPlugin implements Plugin<Project> {

    void apply(Project project) {

        project.subprojects { it ->
            it.plugins.apply("foundation.kotlin")
            it.plugins.apply("foundation.lombok")
            it.plugins.apply("foundation.test.junit5")
            it.plugins.apply("foundation.maven-publish")
            it.plugins.apply("foundation.qa.coverage")
            // it.plugins.apply("foundation.qa.pmd")
            it.plugins.apply("foundation.default-repositories")
        }

        project.plugins.apply("foundation.default-repositories")
        //project.plugins.apply("foundation.qa.coverage-root")

    }

}
