package io.soffa.tools.gradle.springboot

import org.gradle.api.Plugin
import org.gradle.api.Project

class SpringBootConfigPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        //project.plugins.apply("io.spring.dependency-management")
        project.plugins.apply("org.jetbrains.kotlin.plugin.spring")

        project.dependencies {
            implementation platform('org.springframework.boot:spring-boot-dependencies:2.5.2')
            implementation platform('org.springframework.cloud:spring-cloud-dependencies:2020.0.3')
            annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:2.5.2") {
                exclude group: 'com.vaadin.external.google', module: 'android-json'
            }
            testImplementation("org.springframework.boot:spring-boot-starter-test") {
                exclude group: "org.junit.vintage", module: "junit-vintage-engine"
                exclude group: "com.vaadin.external.google"
            }
            if (project.plugins.hasPlugin("kotlin")) {
                kapt("org.springframework.boot:spring-boot-configuration-processor:2.5.2")
            }
        }


        project.compileJava.inputs.files(project.processResources)
        project.configurations {
            all {
                //exclude group: 'org.apache.logging.log4j', module: 'log4j-to-slf4j'
                //exclude group: 'org.springframework.boot', module: 'spring-boot-starter-logging'
                exclude group: 'org.slf4j', module: 'slf4j-log4j12'
            }
        }

    }

}
