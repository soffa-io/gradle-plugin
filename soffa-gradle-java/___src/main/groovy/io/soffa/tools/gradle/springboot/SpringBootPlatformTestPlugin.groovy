package io.soffa.tools.gradle.springboot

import org.gradle.api.Plugin
import org.gradle.api.Project

class SpringBootPlatformTestPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.dependencies {
            testImplementation platform('org.springframework.boot:spring-boot-dependencies:2.4.4')
            testImplementation platform('org.springframework.cloud:spring-cloud-dependencies:2020.0.2')
        }
        /*
        project.configurations {
            all {
                exclude group: 'org.apache.logging.log4j', module: 'log4j-to-slf4j'
            }
        }
         */

    }
}
