package io.soffa.tools.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class SpringBootPlatformPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.dependencies {
            implementation platform('org.springframework.boot:spring-boot-dependencies:2.4.4')
            implementation platform('org.springframework.cloud:spring-cloud-dependencies:2020.0.2')
        }

        project.dependencies {
            annotationProcessor "org.springframework.boot:spring-boot-configuration-processor:2.4.4"
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
