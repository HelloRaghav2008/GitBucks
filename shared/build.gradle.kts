import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.koin.compiler)
}

kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    android {
       namespace = "com.raghav.gitbucks.shared"
       compileSdk = libs.versions.android.compileSdk.get().toInt()
       minSdk = libs.versions.android.minSdk.get().toInt()

       compilerOptions {
           jvmTarget = JvmTarget.JVM_11
       }
       androidResources {
           enable = true
       }
       withHostTest {
           isIncludeAndroidResources = true
       }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(dependencyNotation = libs.ktor.client.okhttp)
        }
        commonMain.dependencies {
            // put your Multiplatform dependencies here
            implementation(dependencyNotation = libs.koin.core)
            implementation(dependencyNotation = libs.bundles.ktor)
            implementation(dependencyNotation = libs.kotlinx.serialization.json)
        }
        commonTest.dependencies {
            // put your testing dependenices here
            implementation(dependencyNotation = libs.ktor.client.mock)
            implementation(dependencyNotation = libs.koin.test)
        }
        iosMain.dependencies {
            implementation(dependencyNotation = libs.ktor.client.darwin)
        }
    }
}
