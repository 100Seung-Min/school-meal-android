plugins {
    id("school-core")
}

android {
    namespace = "com.school.core.local"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(libs.androidx.preference)
    implementation(libs.room)
    ksp(libs.room.compiler)
}