plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.myaxa.hotels_list"
    compileSdk = 34

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.jakarta.inject.api)
    implementation(libs.kotlinx.coroutines.core)

    implementation(project(":domain"))
    implementation(project(":network"))
    implementation(project(":database"))
}