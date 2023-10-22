plugins {
    id("school-core")
}

android {
    namespace = "com.school.core.data"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(libs.paging)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.okhttp)
}