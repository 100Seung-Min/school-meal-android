buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath (Dependency.GradlePlugin.GRADLE_ANDROID)
        classpath (Dependency.GradlePlugin.GRADLE_KOTLIN)
        classpath (Dependency.GradlePlugin.GRADLE_HILT)
        classpath (Dependency.GradlePlugin.GRADLE_GOOGLE)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}