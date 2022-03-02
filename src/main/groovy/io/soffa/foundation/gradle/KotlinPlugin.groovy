package io.soffa.foundation.gradle


import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinPlugin implements Plugin<Project> {

    public static final String kotlinVersion = "1.6.10"
    
    void apply(Project project) {
        io.soffa.foundation.gradle.java.JavaPlugin.apply(project, 8, false)
        project.dependencies {
            implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
            implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
            testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")

        }
        project.plugins.apply("kotlin")
        project.plugins.apply("kotlin-allopen")

        project.allOpen {
            annotation("javax.persistence.Entity")
            annotation("javax.inject.Named")
        }
    }


}
