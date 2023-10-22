plugins {
    id("school-core")
}

android {
    namespace = "com.school.core.remote"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.okhttp)
    testImplementation(libs.okhttp.sse)
    implementation(libs.paging)
}