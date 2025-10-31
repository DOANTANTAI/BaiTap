plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
<<<<<<< HEAD
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.smarttasks"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.smarttasks"
=======
}

android {
    namespace = "com.example.librarymanagement"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.librarymanagementsystem"
>>>>>>> 9f11f3fd95039a0f5b2e2f1cee9ed6fddae02ae0
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
<<<<<<< HEAD
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"

=======
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
>>>>>>> 9f11f3fd95039a0f5b2e2f1cee9ed6fddae02ae0
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
<<<<<<< HEAD

=======
>>>>>>> 9f11f3fd95039a0f5b2e2f1cee9ed6fddae02ae0
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
<<<<<<< HEAD

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.7.5")

    // ViewModel Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.compose.material:material-icons-extended")

    // Firebase
    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:34.4.0"))

    // SỬA Ở ĐÂY: Thay "firebase-auth-ktx" bằng "firebase-auth"
    implementation("com.google.firebase:firebase-auth")

    implementation("com.google.firebase:firebase-database")
    implementation("com.google.android.gms:play-services-auth:20.7.0")
    implementation("androidx.activity:activity-ktx:1.8.1")
    implementation("com.google.firebase:firebase-analytics")
=======
    val nav_version = "2.7.5"
    implementation("androidx.navigation:navigation-compose:$nav_version")
    val lifecycle_version = "2.7.0"
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycle_version")
>>>>>>> 9f11f3fd95039a0f5b2e2f1cee9ed6fddae02ae0
}