plugins {
    id("school-core")
}

android {
    namespace = "com.school.core.design_system"
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }
}

dependencies {
    implementation(libs.bundles.compose)
    implementation(libs.coil)
}