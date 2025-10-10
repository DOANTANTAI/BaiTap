plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.th2_2"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.th2_2"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    }
}

dependencies {
    // Thư viện cơ bản cho Views và Kotlin
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.7.1")
    // Thư viện Layout
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
    // Thư viện Material Design (BẮT BUỘC cho TextInputLayout và Button hiện đại)
    implementation("com.google.android.material:material:1.13.0")
    // Thư viện Lifecycle KTX (thường có sẵn trong các dự án Kotlin)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    // Dependencies kiểm thử (Giữ nguyên nếu cần)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}