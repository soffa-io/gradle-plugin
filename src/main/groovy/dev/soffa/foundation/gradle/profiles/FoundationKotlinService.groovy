package dev.soffa.foundation.gradle.profiles


import org.gradle.api.Project

class FoundationKotlinService extends FoundationService {

    @Override
    void apply(Project project) {
        super.configure(project, true)
    }
}

