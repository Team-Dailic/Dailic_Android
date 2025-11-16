import java.util.Properties

val properties = Properties().apply {
    load(project.rootProject.file("local.properties").inputStream())
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.ktlint)
}

android {
    namespace = "com.syaainn.dailic"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.syaainn.dailic"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BASE_URL", properties.getProperty("base.url").toString())
    }

    lint {
        disable.add("CoroutineCreationDuringComposition")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
}

dependencies {
    // Core
    implementation(libs.bundles.core)

    // Kotlin Serialization
    implementation(libs.kotlinx.serialization.json)

    // Jetpack Compose
    implementation(platform(libs.androidx.compose.bom)) // Jetpack Compose BOM 설정
    implementation(libs.bundles.compose) // Jetpack Compose 관련 라이브러리들
    androidTestImplementation(platform(libs.androidx.compose.bom)) // Compose UI 테스트를 위한 BOM

    // DataStore
    implementation(libs.bundles.datastore)

    // Dependency Injection (Hilt)
    implementation(libs.bundles.di) // Hilt 관련 라이브러리들
    ksp(libs.hilt.compiler) // Hilt 컴파일러를 위한 KSP

    // Networking
    implementation(platform(libs.okhttp.bom)) // OkHttp BOM 설정
    implementation(libs.bundles.networking) // Retrofit과 OkHttp 관련 라이브러리들

    // Logging
    implementation(libs.timber)

    // Debug Dependencies (UI Tooling for Debugging)
    debugImplementation(libs.bundles.debug) // 디버깅을 위한 UI 툴링 관련 라이브러리들

    // Testing
    testImplementation(libs.junit) // JUnit 라이브러리
    androidTestImplementation(libs.bundles.testing) // Android UI 테스트 관련 라이브러리들
}

ktlint {
    android = true
    debug = true
    coloredOutput = true
    verbose = true
    outputToConsole = true
}
