package io.soffa.tools.gradle.test

import org.gradle.api.Project

class CucumberPlugin extends JUnit5Plugin {

    @Override
    void apply(Project project) {

        Object.apply(project);

        project.dependencies {
            testImplementation "io.cucumber:cucumber-java8:6.10.0"
            testImplementation "io.cucumber:cucumber-java:6.10.0"
            testImplementation "io.cucumber:cucumber-junit:6.10.0"
            testImplementation("io.cucumber:cucumber-junit-platform-engine:6.10.0") {
                because("we want to use Cucumber with JUnit 5")
            }
            testImplementation("io.cucumber:cucumber-picocontainer:6.10.0") {
                because("we want to use dependency injection in our Cucumber tests")
            }
            /*
            testImplementation("io.cucumber:cucumber-spring:6.10.0") {
                because("we want to use dependency injection in our Cucumber tests")
            }*/
            testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.7.1") {
                exclude group: "org.hamcrest", module: "hamcrest-core"
            }
        }

    }
}
