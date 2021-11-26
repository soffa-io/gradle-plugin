package io.soffa.tools.gradle.spring

import org.gradle.api.Project

class SpringBootVaultPlugin extends SpringBootDependencyPlugin {

    void apply(Project project) {
        Object.apply(project)
        project.dependencies {
            implementation('org.springframework.cloud:spring-cloud-starter-vault-config')
        }

    }

}
