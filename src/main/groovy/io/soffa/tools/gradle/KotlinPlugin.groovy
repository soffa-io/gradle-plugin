package io.soffa.tools.gradle


import org.gradle.api.Project

class KotlinPlugin extends Java8Plugin {

    @Override
    void apply(Project project) {
        super.apply(project)
        project.plugins.apply("kotlin")
        project.plugins.apply("org.jetbrains.kotlin.plugin.spring")

        project.dependencies {
            implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.32"
        }
        project.compileKotlin {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
        project.compileTestKotlin {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }


    }
}
