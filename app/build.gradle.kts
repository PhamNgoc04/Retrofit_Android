plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.learnretrofit"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.learnretrofit"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    // Jetpack Compose BOM (Bill of Materials)
    implementation(platform("androidx.compose:compose-bom:2023.06.01"))

    // Jetpack Compose Core
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3") // Material 3
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Activity & Lifecycle for Compose
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

    // Navigation for Compose
    implementation("androidx.navigation:navigation-compose:2.7.2")

    // Image Loading (Glide -> Coil for Compose)
    implementation("io.coil-kt:coil-compose:2.4.0")

    // Coroutine for async operations
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // Retrofit (for API requests)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // JSON Parsing (Gson)
    implementation("com.google.code.gson:gson:2.10.1")

    // Swipe to Refresh (Compose version)
    implementation("com.google.accompanist:accompanist-swiperefresh:0.30.1")

    // Constraint Layout for Compose
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    // Core KTX
    implementation("androidx.core:core-ktx:1.10.1")

    // AppCompat (Only if still needed)
    implementation("androidx.appcompat:appcompat:1.6.1")

    implementation("androidx.compose.material:material-icons-extended:1.5.4")

    implementation( "com.google.dagger:hilt-android:2.44")

    // Hilt hỗ trợ ViewModel
    implementation( "androidx.hilt:hilt-navigation-compose:1.0.0")

    implementation ("androidx.compose.runtime:runtime-livedata:1.5.0")



    // Unit Testing & UI Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
