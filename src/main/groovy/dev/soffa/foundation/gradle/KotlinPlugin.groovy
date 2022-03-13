package dev.soffa.foundation.gradle

import dev.soffa.foundation.gradle.java.JavaPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinPlugin implements Plugin<Project> {

    public static final String kotlinVersion = "1.6.10"
    
    void apply(Project project) {
        JavaPlugin.apply(project, 8, true)
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

        project.compileKotlin {
            kotlinOptions {
                jvmTarget = "1.8"
                freeCompilerArgs = ['-Xjvm-default=all']
            }
        }
        project.compileTestKotlin {
            kotlinOptions {
                jvmTarget = "1.8"
                freeCompilerArgs = ['-Xjvm-default=all']
            }
        }
    }


}
