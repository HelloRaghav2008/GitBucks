import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
    }
}
dependencies {
    implementation(project(":shared"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(dependencyNotation = libs.androidx.activity.compose)
    implementation(dependencyNotation = libs.androidx.compose.material3)
    implementation(dependencyNotation = libs.androidx.compose.ui)
    implementation(dependencyNotation = libs.androidx.compose.ui.graphics)
    implementation(dependencyNotation = libs.androidx.compose.ui.tooling.preview)
    implementation(dependencyNotation = libs.androidx.core.ktx)
    implementation(dependencyNotation = libs.androidx.compose.ui.text)
    implementation(dependencyNotation = libs.androidx.lifecycle.runtime.ktx)


    testImplementation(dependencyNotation = libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(dependencyNotation = libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(dependencyNotation = libs.androidx.espresso.core)
    androidTestImplementation(dependencyNotation = libs.androidx.junit)
    debugImplementation(dependencyNotation = libs.androidx.compose.ui.test.manifest)
    debugImplementation(dependencyNotation = libs.androidx.compose.ui.tooling)
}

android {
    namespace = "com.raghav.gitbucks"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.raghav.gitbucks"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}
