package io.soffa.tools.gradle.maven

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.maven.MavenPublication

class InternalSonatypePublishPlugin implements Plugin<Project> {

    static final String  NEXUS_PUBLISH_PLUGIN = "io.github.gradle-nexus.publish-plugin"

    void apply(Project project) {

        Project root = project.getRootProject();
        if (!root.plugins.hasPlugin(NEXUS_PUBLISH_PLUGIN)) {
            root.plugins.apply(NEXUS_PUBLISH_PLUGIN)

            root.nexusPublishing {
                repositories {
                    sonatype {
                        // stagingProfileId.set(System.getenv("SONATYPE_STAGING_PROFILE_ID"))
                        username = root.property("ossrhUsername")
                        password = root.property("ossrhPassword")
                    }
                }
            }
        }

        project.plugins.apply('maven-publish')
        project.plugins.apply('signing')

        project.publishing {
            publications {

                maven(MavenPublication) {
                    from project.components.java
                    groupId = project.property("group")
                    artifactId = project.name


                    pom {
                        name.set(project.displayName)
                        description.set(project.description)
                        url.set(project.property("url"))
                        licenses {
                            license {
                                name.set("Apache License 2.0")
                                url.set("https://www.apache.org/licenses/LICENSE-2.0")
                            }
                        }
                        developers {
                            developer {
                                id.set("soffa.io")
                                name.set("SOFFA")
                                email.set("studio@soffa.io")
                            }
                        }
                        scm {
                            connection = "scm:git:git://soffa-io/${project.property("github.project")}.git"
                            developerConnection = "scm:git:ssh://soffa-io/${project.property("github.project")}.git"
                            url = "https://github.com/soffa-io/${project.property("github.project")}"
                        }
                    }
                }
            }
        }

        project.java {
            withJavadocJar()
            withSourcesJar()
        }

        project.ext["ossrhUsername"] = project.property("ossrhUsername")
        project.ext["ossrhPassword"] = project.property("ossrhPassword")
        project.ext["sonatypeStagingProfileId"] = ""
        project.ext["signing.keyId"] = project.property("signing.keyId")
        project.ext["signing.password"] = project.property("signing.password")
        project.ext["signing.secretKeyRingFile"] = project.property("signing.secretKeyRingFile")

        project.signing {
            sign(project.publishing.publications["maven"])
        }

    }

}
