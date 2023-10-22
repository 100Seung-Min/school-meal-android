plugins {
    id("school-core")
}

android {
    namespace = "com.school.core.domain"
}

dependencies {
    implementation(libs.coroutines.core)
    implementation(libs.paging)
}