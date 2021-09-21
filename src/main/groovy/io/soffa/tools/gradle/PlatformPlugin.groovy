package io.soffa.tools.gradle

import io.soffa.tools.gradle.model.SoffaExtension
import io.soffa.tools.gradle.qa.JacocoSupport
import io.soffa.tools.gradle.service.SpringBootServicePlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

class PlatformPlugin implements Plugin<Project> {

    void apply(Project project) {
        doApply(project)
        project.subprojects { Project it ->
            doApply(it)
        }
    }

    private static void doApply(Project project) {
        project.plugins.apply("java-library")
        def extension  = project.extensions.create('soffa', SoffaExtension)
        new Java8Plugin().apply(project)
        project.afterEvaluate {

            String platform = extension.getPlatform().getOrNull()
            String features = extension.getFeatures().getOrNull()
            String coverage = extension.getTestCoverage().getOrNull()

            if (coverage != null) {
                JacocoSupport.applyTestCoverage(project,  coverage)
            }

            if ("springboot".equalsIgnoreCase(platform)) {
                if (features != null) {
                    new SpringBootServicePlugin().apply(project);
                    project.plugins.apply("org.springframework.boot")
                    //project.plugins.apply("soffa.platform.springboot")
                    //if (features.contains("web")) {
                    project.dependencies.add("implementation", "org.springframework.boot:spring-boot-starter-web")
                    project.dependencies.add("implementation", 'org.springframework.boot:spring-boot-starter-actuator')
                    //}
                    if (features.contains("data")) {
                        project.dependencies.add("implementation", "org.springframework.boot:spring-boot-starter-data-jpa")
                    }
                    if (features.contains("vault")) {
                        project.dependencies.add("implementation", "org.springframework.cloud:spring-cloud-starter-vault-config")
                    }
                    if (features.contains("kafka")) {
                        project.dependencies.add("implementation", "io.soffa.platform:soffa-platform-springboot-kafka")
                        project.dependencies.add("testImplementation", "org.springframework.kafka:spring-kafka-test")
                    }
                }
                // project.dependencies.add("testImplementation", "io.soffa.platform:soffa-platform-springboot-test")
            }

        }
    }

}
