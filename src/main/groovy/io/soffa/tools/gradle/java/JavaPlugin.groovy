package io.soffa.tools.gradle.java

import io.soffa.tools.gradle.LombokPlugin
import io.soffa.tools.gradle.qa.PmdPlugin
import org.gradle.api.Project
import org.gradle.api.attributes.Usage

public class JavaPlugin {

    public static void apply(Project project, String version) {
        project.plugins.apply('java')
        project.plugins.apply('java-library')
        project.setProperty("sourceCompatibility", version)
        LombokPlugin.applyPlugin(project)
        // project.setProperty("targetCompatibility", "1.8")
        if (project.findProperty("soffa.pmd.disabled") != true) {
            new PmdPlugin().apply(project)
        }

        project.compileJava.options.encoding = 'UTF-8'
        project.compileJava.options.compilerArgs << '-parameters'

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

        project.test {
            testLogging {
                events "PASSED", "SKIPPED", "FAILED"
            }
        }

    }

}
