plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

// Versions
val kotlinVersion = "1.7.3"
val hiltVersion = "2.51.1"
val okhttpVersion = "4.10.0"
val retrofitVersion = "2.9.0"
val mockkVersion = "1.12.5"

android {
    namespace = "com.velosobr.kaizengaming"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.velosobr.kaizengaming"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String",
            "BASE_URL",
            "\"https://618d3aa7fe09aa001744060a.mockapi.io/api/\""
        )
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
        viewBinding = true
        buildConfig = true
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    // AndroidX
    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.activity:activity-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")

    // Shimmer
    implementation("com.facebook.shimmer:shimmer:0.5.0")

    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttpVersion")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion") {
        exclude(group = "com.squareup.okhttp3", module = "okhttp")
    }
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinVersion")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation ("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("com.google.dagger:hilt-android-testing:$hiltVersion")
    kaptAndroidTest("com.google.dagger:hilt-compiler:$hiltVersion")
    testImplementation("com.google.dagger:hilt-android-testing:$hiltVersion")
    kaptTest("com.google.dagger:hilt-compiler:$hiltVersion")
    testImplementation("io.mockk:mockk:$mockkVersion")
}