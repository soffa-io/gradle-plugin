package io.soffa.tools.gradle

import io.soffa.tools.gradle.java.JavaPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinPlugin implements Plugin<Project> {

    public static final String kotlinVersion = "1.6.10"
    
    void apply(Project project) {
        JavaPlugin.apply(project, 8, false)
        project.dependencies {
            implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
            implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
            testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")

        }
        project.plugins.apply("kotlin")
        project.plugins.apply("kotlin-allopen")

        project.allOpen {
            annotation("javax.persistence.Entity")
            annotation("jakarta.inject.Named")
        }
    }


}
