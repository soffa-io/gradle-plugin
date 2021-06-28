package io.soffa.tools.gradle

import io.soffa.tools.gradle.qa.PmdPlugin
import org.gradle.api.Project
import org.gradle.api.attributes.Usage

import java.util.concurrent.TimeUnit

class Java8Plugin extends PmdPlugin {

    void apply(Project project) {
        project.plugins.apply('java')
        //project.plugins.apply('checkstyle')
        project.plugins.apply('java-library')
        project.setProperty("sourceCompatibility", "1.8")
        project.setProperty("targetCompatibility", "1.8")

        project.afterEvaluate {
            project.compileJava.options.encoding = 'UTF-8'
            project.compileJava.options.compilerArgs << '-parameters'
        }

        project.dependencies {
            testImplementation("com.openpojo:openpojo:0.8.13")
        }

        // Workaround the Gradle bug resolving multiplatform dependencies.
        project.configurations.all { c ->
            // https://github.com/spring-projects/spring-boot/issues/21549
            if (c.name.contains("productionRuntimeClasspath") || c.name.contains('kapt') || c.name.contains("wire") || c.name.contains("proto")) {
                c.attributes.attribute(Usage.USAGE_ATTRIBUTE, project.objects.named(Usage.class, Usage.JAVA_RUNTIME))
            }
        }

        project.compileJava {
            options.warnings = false
            options.deprecation = false
            options.compilerArgs << "-Xlint:unchecked" << "-Xlint:deprecation"
        }


        project.configurations.all({
            resolutionStrategy.cacheChangingModulesFor(30, TimeUnit.SECONDS)
        })


        //super.apply(project)
    }

}
