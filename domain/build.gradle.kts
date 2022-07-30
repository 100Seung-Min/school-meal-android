plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Versions.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = Versions.MIN_SDK_VERSION
        targetSdk = Versions.TARGET_SDK_VERSION

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Versions.JAVA_VERSION
        targetCompatibility = Versions.JAVA_VERSION
    }
    kotlinOptions {
        jvmTarget = Versions.JAVA_VERSION.toString()
    }
}

dependencies {
    implementation(Dependency.AndroidX.CORE_KTX)
    implementation(Dependency.AndroidX.APP_COMPAT)

    implementation(Dependency.Google.MATERIAL)

    testImplementation(Dependency.UnitTest.JUNIT)

    androidTestImplementation(Dependency.AndroidTest.ANDROID_JUNIT)
    androidTestImplementation(Dependency.AndroidTest.ESPRESSO_CORE)
}