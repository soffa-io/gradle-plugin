package io.soffa.tools.gradle.qa

import org.gradle.api.Plugin
import org.gradle.api.Project

class CheckstylePlugin implements Plugin<Project> {

    void apply(Project project) {

        project.plugins.apply("checkstyle")

        def configDir = "${project.rootDir}/config"

        project.checkstyle {
            toolVersion '8.40'
            configFile project.file("$configDir/qa/checkstyle.xml")
            // configProperties.checkstyleSuppressionsPath = project.file("$configDir/checkstyle/suppressions.xml").absolutePath
        }

        project.checkstyleMain {
            source = 'src/main/java'
        }

        project.checkstyleTest {
            source = 'src/test/java'
        }
    }
}
