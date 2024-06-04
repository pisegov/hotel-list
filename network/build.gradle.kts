plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.kotlinx.serialization)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.kotlinx.serialization)
    implementation(libs.kotlinx.serialization.converter)
    implementation(libs.retrofit.adapters.result)
    implementation(libs.okhttp.logging.interceptor)

    implementation(project(":domain"))

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.jakarta.inject.api)

}