pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "school"
include(":app")
include(":core:domain")
include(":core:data")
include(":core:local")
include(":core:remote")
include(":core:ui")
include(":core:design-system")
include(":core:navigation")
