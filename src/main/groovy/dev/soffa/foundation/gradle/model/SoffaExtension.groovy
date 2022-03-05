package dev.soffa.foundation.gradle.model

import org.gradle.api.provider.Property

interface SoffaExtension {

    Property<String> getPlatform()
    Property<String> getFeatures()
    Property<String> getTestCoverage()


}
