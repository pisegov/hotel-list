import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kapt)
}

android {
    namespace = "com.myaxa.hotels_list"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.myaxa.hotels_list"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        val properties = Properties().apply {
            load(rootProject.file("local.properties").inputStream())
        }

        buildConfigField("String", "BASE_URL", properties.getProperty("BASE_URL"))

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.dagger)
    implementation(libs.jakarta.inject.api)
    kapt(libs.dagger.compiler)

    implementation(project(":domain"))

    implementation(project(":data"))
    implementation(project(":network"))
    implementation(project(":database"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}