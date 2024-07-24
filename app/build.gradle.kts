plugins {
    id(Plugin.androidApplication)
    id(Plugin.kotlinAndroid)
    id(Plugin.kotlinKapt)
    id(Plugin.hiltAndroid)
}

android {
    namespace = "com.android.harman"
    compileSdk = Android.compileSdk

    defaultConfig {
        applicationId = "com.android.harman"
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
        versionCode = Android.versionCode
        versionName = Android.versionName

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()

        // Enable Coroutines and Flow APIs
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlinx.coroutines.FlowPreview"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/gradle/incremental.annotation.processors"
        }
    }
    lint {
        // Enable lint checks
        checkReleaseBuilds = true
        // Abort the build if errors are found
        abortOnError = true
        // Treat all warnings as errors
        warningsAsErrors = true
    }
}

dependencies {

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.lifecycleRuntimeKtx)
    implementation(AndroidX.lifecycleRuntimeCompose)
    implementation(AndroidX.lifecycleLivedataKtx)
    implementation(AndroidX.lifecycleViewModelCompose)
    implementation(AndroidX.lifecycleViewModelKtx)

    implementation(project(Module.CoreUi))
    implementation(project(Module.Data))
    implementation(project(Module.Features))

    implementation(AndroidX.activityCompose)
    implementation(platform(AndroidX.composeBom))
    implementation(AndroidX.composeUi)
    implementation(AndroidX.composeUiGraphics)
    implementation(AndroidX.composeUiToolingPreview)
    implementation(AndroidX.composeMaterial3)
    implementation(AndroidX.navigationCompose)

    implementation(HiltAndroid.hiltAndroid)
    kapt(HiltAndroid.hiltAndroidCompiler)
    implementation(HiltAndroid.hiltAndroidTesting)
    implementation(HiltAndroid.hiltNavigationCompose)

    implementation(RetrofitLibs.retrofit)
    implementation(RetrofitLibs.converterGson)
    implementation(RetrofitLibs.okhttp)
    implementation(RetrofitLibs.loggingInterceptor)

    testImplementation(TestingLibs.junit)
    androidTestImplementation(TestingLibs.junitExt)
    androidTestImplementation(TestingLibs.espressoCore)
    androidTestImplementation(platform(AndroidX.composeBom))
    androidTestImplementation(TestingLibs.composeUiTestJunit4)
    debugImplementation(TestingLibs.composeUiTooling)
    debugImplementation(TestingLibs.composeUiTestManifest)
}


tasks.withType<JavaCompile> {
    options.compilerArgs.add("-Xlint:unchecked")
    options.compilerArgs.add("-Xlint:deprecation")
}

tasks.register("lintCheck") {
    dependsOn("lint")
}