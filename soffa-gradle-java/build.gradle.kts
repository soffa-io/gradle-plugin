

java {
    withJavadocJar()
    withSourcesJar()
}

dependencies {
    compileOnly(gradleApi())
    // implementation("io.github.gradle-nexus:publish-plugin:1.1.0")
    implementation("org.jacoco:org.jacoco.core:0.8.7")
    implementation("org.jacoco:org.jacoco.report:0.8.7")
    // implementation("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:3.3")
    implementation("org.springframework.boot:spring-boot-gradle-plugin:2.5.4")
    implementation("io.spring.gradle:dependency-management-plugin:1.0.11.RELEASE")
}
