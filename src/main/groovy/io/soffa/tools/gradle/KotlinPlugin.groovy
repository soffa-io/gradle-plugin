package io.soffa.tools.gradle


import org.gradle.api.Project

class KotlinPlugin extends Java8Plugin {

    @Override
    void apply(Project project) {
        super.apply(project)
        project.plugins.apply("kotlin") //
        project.plugins.apply("kotlin-kapt") //

        project.dependencies.add("implementation", "org.jetbrains.kotlin:kotlin-reflect:1.5.10")
        project.dependencies.add("implementation", "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.10")

        project.compileKotlin {
            kotlinOptions {
                freeCompilerArgs = ["-Xjsr305=strict"]
                jvmTarget = "1.8"
            }
        }

        project.javadoc {
            exclude "**/springframework/**"
        }

        project.compileTestKotlin {
            kotlinOptions {
                freeCompilerArgs = ["-Xjsr305=strict"]
                jvmTarget = "1.8"
            }
        }

    }


}
