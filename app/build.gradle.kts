plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.jsomven.pokedex"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.jsomven.pokedex"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.compileSdk.get().toInt()
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
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //Compose
    implementation(libs.bundles.compose.all)
    implementation(libs.activity.compose)

    //Voyager
    implementation(libs.bundles.voyager)

    // For splash screen
    implementation(libs.core.splash)

    //Coil with Compose Extension
    implementation(libs.bundles.coil.compose)

    //Arrow
    implementation(libs.arrow.core)

    //DI
    implementation(libs.hilt)
    ksp(libs.hilt.ksp)

    //Retrofit
    implementation(libs.bundles.retrofit.all)

    //Room
    implementation(libs.bundles.room)
    ksp(libs.room.ksp)

    //Test
    testImplementation(libs.junit.ktx)
    testImplementation(libs.room.test)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.bundles.compose.testing)
}
