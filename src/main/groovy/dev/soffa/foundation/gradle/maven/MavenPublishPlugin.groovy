package dev.soffa.foundation.gradle.maven

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.gradle.api.tasks.javadoc.Javadoc
import org.gradle.external.javadoc.JavadocMemberLevel

class MavenPublishPlugin implements Plugin<Project> {

    void apply(Project project) {
        boolean skipPublish = Boolean.parseBoolean(System.getenv("NO_PUBLISH")) ||
            Boolean.parseBoolean(System.getenv("SKIP_PUBLISH")) ||
            Boolean.parseBoolean(System.getenv("SKIP_MAVEN_PUBLISH")) ||
            project.findProperty("skipMavenPublish") != null;

        if (skipPublish) {
            return
        }
        project.plugins.apply('maven-publish')

        String projectVersion = project.version

        project.javadoc {
            source = project.sourceSets.main.allJava
        }

        if (project.hasProperty("snapshot") && !projectVersion.endsWith("-SNAPSHOT")) {
            projectVersion += "-SNAPSHOT"
        } else if (project.hasProperty("release") && projectVersion.endsWith("-SNAPSHOT")) {
            projectVersion = projectVersion.replace("-SNAPSHOT", "")
        }

        if (project.rootProject.plugins.hasPlugin(SonatypePublishPlugin.NEXUS_PUBLISH_PLUGIN)) {
            configureSonatypePublishing(project, projectVersion)
        } else {
            configureInternalPublishing(project, projectVersion)
        }

        project.java {
            // withJavadocJar()
            // withSourcesJar()
        }
    }

    private static String lookupProperty(Project project, String... candidates) {
        for (String c : candidates) {
            String value = project.findProperty(c)
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    private static String lookupEnv(String... candidates) {
        for (String c : candidates) {
            String value = System.getenv(c)
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    private void configureInternalPublishing(Project project, String projectVersion) {

        project.publishing.publications {
            maven(MavenPublication) {
                from project.components.java
                groupId = project.property("group")
                artifactId = project.name
                version = projectVersion
            }
        }

        String mvnPublishingUrl = lookupProperty(
            project,
            "mvnRepo",
            "mvnReleaseRepo",
            "mavenUrl",
            "mavenReleaseUrl",
            "mavenRepo",
            "mavenReleaseRepo",
            "mavenPublishingUrl") ?:
            lookupEnv(
                "MVN_REPO",
                "MVN_RELEASE_REPO",
                "MAVEN_URL",
                "MAVEN_RELEASE_URL",
                "MAVEN_REPO",
                "MAVEN_RELEASE_REPO",
                "MVN_PUBLISHING_URL",
                "MAVEN_PUBLISHING_URL"
            )


        String mvnSnapshotPublishingUrl = lookupProperty(
            project,
            "mvnSnaphostRepo",
            "mavenSnapshotUrl",
            "mavenSnapshotRepo"
        ) ?: lookupEnv(
            "mavenSnapshotPublishingUrl",
            "MVN_SNAPSHOT_REPO",
            "MAVEN_SNAPSHOT_URL",
            "MAVEN_SNAPSHOT_REPO",
            "MVN_SNAPSHOT_PUBLISHING_URL",
            "MAVEN_SNAPSHOT_PUBLISHING_URL"
        )

        String mvnPublishingUser = lookupProperty(
            project, "mvnUser", "mavenUser", "mavenPublishingUser"
        ) ?: lookupEnv(
            "MVN_USER", "MAVEN_USER", "MAVEN_USR", "MAVEN_PUBLISHING_USER"
        )

        String mvnPublishingPwd = lookupProperty(
            project, "mvnPassword", "mavenPassword", "mavenPublishingPassword"
        ) ?: lookupEnv(
            "MVN_PASSWORD", "MAVEN_PASSWORD", "MAVEN_PSW", "MAVEN_PUBLISHING_PASSWORD"
        )

        if (mvnPublishingUrl != null) {
            project.publishing.repositories {
                maven {
                    if (projectVersion.endsWith("-SNAPSHOT")) {
                        url = mvnSnapshotPublishingUrl
                    } else {
                        url = mvnPublishingUrl
                    }
                    credentials {
                        username = mvnPublishingUser
                        password = mvnPublishingPwd
                    }
                }
            }
        }
    }

    private void configureSonatypePublishing(Project project, String projectVersion) {
        project.plugins.apply('signing')



        project.publishing {
            publications {
                maven(MavenPublication) {
                    from project.components.java

                    groupId = project.property("group")
                    artifactId = project.name
                    version = projectVersion
                    pom {
                        name.set(project.displayName ?: project.name)
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
                                id.set("soffalabs")
                                name.set("SOFFA")
                                email.set("studio@soffalabs.com")
                            }
                        }
                        scm {
                            connection = "scm:git:git://soffalabs/${project.property("github.project")}.git"
                            developerConnection = "scm:git:ssh://soffalabs/${project.property("github.project")}.git"
                            url = "https://github.com/soffalabs/${project.property("github.project")}"
                        }
                    }
                }
            }
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
