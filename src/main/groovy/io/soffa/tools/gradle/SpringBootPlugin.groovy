package io.soffa.tools.gradle


import org.gradle.api.Project

class SpringBootPlugin extends SpringBootPlatformPlugin {

    @Override
    void apply(Project project) {
        Object.apply(project);

        /*project.processResources {
            filesMatching('application.yml') {
                expand(project.properties)
            }
        }*/
        //if (!project.hasProperty("unb.service.embedded") && !project.hasProperty("sandbox")) {
        project.plugins.apply("org.springframework.boot")
        project.dependencies {
            testImplementation("org.springframework.boot:spring-boot-starter-test") {
                exclude group: "org.junit.vintage", module: "junit-vintage-engine"
                exclude group: "com.vaadin.external.google"
            }
        }

        project.configurations {
            [it.apiElements, it.runtimeElements].each {
                it.outgoing.artifacts.removeIf { it.buildDependencies.getDependencies(null).contains(project.tasks.jar) }
                it.outgoing.artifact(project.tasks.bootJar)
            }
        }
        //}

    }
}
