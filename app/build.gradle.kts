import java.lang.Runtime.Version

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.google.gms.google-services")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")

}

@Suppress("UnstableApiUsage")
android {
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.themovie"
        minSdk = 24
        targetSdk = 34
        versionCode = 10
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
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
        buildConfig = true
        viewBinding = true
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //OkHttp
    implementation(platform("com.squareup.okhttp3:okhttp-bom:${Versions.okHttpVersion}"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}")

    //Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

    //Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.6.0"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-database-ktx")
    implementation("com.google.firebase:firebase-storage-ktx")

    //Splash API
    implementation("androidx.core:core-splashscreen:1.0.1")

    //Hilt
    implementation("com.google.dagger:hilt-android:${Versions.hiltVersion}")
    kapt("com.google.dagger:hilt-compiler:${Versions.hiltVersion}")

    //ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycleVersion}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycleVersion}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycleVersion}")

    //Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:${Versions.navControllerVersion}")
    implementation("androidx.navigation:navigation-ui-ktx:${Versions.navControllerVersion}")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:${Versions.navControllerVersion}")
    androidTestImplementation("androidx.navigation:navigation-testing:${Versions.navControllerVersion}")
    implementation("androidx.navigation:navigation-compose:${Versions.navControllerVersion}")


    //Glide
    implementation("com.github.bumptech.glide:glide:${Versions.glideVersion}")
    annotationProcessor("com.github.bumptech.glide:compiler:${Versions.glideVersion}")

    //SimpleSearchView
    implementation("com.github.Ferfalk:SimpleSearchView:0.2.0")

    //Room
    implementation("androidx.room:room-ktx:${Versions.roomVersion}")
    implementation("androidx.room:room-runtime:${Versions.roomVersion}")
    kapt("androidx.room:room-compiler:${Versions.roomVersion}")
}

kapt {
    correctErrorTypes = true
}