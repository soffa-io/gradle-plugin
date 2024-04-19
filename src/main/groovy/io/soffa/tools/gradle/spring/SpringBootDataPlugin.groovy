package io.soffa.tools.gradle.spring

import org.gradle.api.Project

class SpringBootDataPlugin extends SpringBootDependencyPlugin {

    void apply(Project project) {
        Object.apply(project)
        project.dependencies {
            implementation('org.springframework.boot:spring-boot-starter-data-jpa')
            testImplementation("com.h2database:h2:2.2.224")
        }

    }

}
