package io.soffa.tools.gradle.qa

import org.gradle.api.Plugin
import org.gradle.api.Project


class TestCoverageL0 extends AbstractCoveragePlugin {
    void apply(Project project) {
        apply(project, 0.0, 0.0)
    }
}

class TestCoverageL1 extends AbstractCoveragePlugin {
    void apply(Project project) {
        apply(project, 0.0, 0.1)
    }
}

class TestCoverageL2 extends AbstractCoveragePlugin {
    void apply(Project project) {
        apply(project, 0.0, 0.2)
    }
}

class TestCoverageL3 extends AbstractCoveragePlugin {
    void apply(Project project) {
        apply(project, 0.0, 0.3)
    }
}

class TestCoverageL4 extends AbstractCoveragePlugin {
    void apply(Project project) {
        apply(project, 0.0, 0.4)
    }
}

class TestCoverageL5 extends AbstractCoveragePlugin {
    void apply(Project project) {
        apply(project, 0.0, 0.5)
    }
}

class TestCoverageL6 extends AbstractCoveragePlugin {
    void apply(Project project) {
        apply(project, 0.0, 0.6)
    }
}

class TestCoverageL7 extends AbstractCoveragePlugin {
    void apply(Project project) {
        apply(project, 0.0, 0.7)
    }
}

class TestCoverageL8 extends AbstractCoveragePlugin {
    void apply(Project project) {
        apply(project, 0.0, 0.8)
    }
}

class TestCoverageL85 extends AbstractCoveragePlugin {
    void apply(Project project) {
        apply(project, 0.0, 0.85)
    }
}

class TestCoverageL9 extends AbstractCoveragePlugin {
    void apply(Project project) {
        apply(project, 0.0, 0.9)
    }
}

class TestCoverageL95 extends AbstractCoveragePlugin {
    void apply(Project project) {
        apply(project, 0.0, 0.95)
    }
}

class TestCoverageLX extends AbstractCoveragePlugin {
    void apply(Project project) {
        apply(project, 0.0, 0.99)
    }
}

abstract class AbstractCoveragePlugin implements Plugin<Project> {

    void apply(Project project, BigDecimal classesCoverage, BigDecimal linesCoverage) {
        project.plugins.apply("jacoco")
        project.jacocoTestReport {
            reports {
                xml.enabled true
                xml.destination project.file("${project.buildDir}/coverage.xml")
                html.enabled true
                html.destination project.file("${project.buildDir}/jacocoHtml")
            }
        }

        project.jacocoTestCoverageVerification {
            violationRules {
                rule {
                    limit {
                        minimum = linesCoverage
                        counter = "LINE" // BRANCH CLASS COMPLEXITY INSTRUCTION  LINE  METHOD
                        value = "COVEREDRATIO" // COVEREDCOUNT COVEREDRATIO  MISSEDCOUNT  MISSEDRATIO  TOTALCOUNT
                    }
                }
            }
            dependsOn("jacocoTestReport")
        }

        project.jacoco {
            toolVersion = "0.8.5"
        }

        project.check {
            dependsOn("jacocoTestCoverageVerification")
        }
    }
}

