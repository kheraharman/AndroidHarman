plugins {
    id(Plugin.androidApplication)
    id(Plugin.kotlinAndroid)
    id(Plugin.kotlinKapt)
    id(Plugin.hiltAndroid)
    id(Detekt.plugin) version Detekt.version
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
        kotlinCompilerExtensionVersion = Versions.kotlinCompile
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/gradle/incremental.annotation.processors"
        }
    }
    /**
     * Configures Detekt for static code analysis.
     * - `toolVersion`: Specifies the version of Detekt to use.
     * - `config`: Sets the path to the Detekt configuration file.
     * - `buildUponDefaultConfig`: Indicates whether to build upon the default Detekt configuration.
     */
    detekt {
        toolVersion = Detekt.version
        config.setFrom(file("../config/detekt/detekt.yml"))
        buildUponDefaultConfig = true
    }
}

dependencies {

    //Lifecycle
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.lifecycleRuntimeKtx)
    implementation(AndroidX.lifecycleRuntimeCompose)
    implementation(AndroidX.lifecycleLivedataKtx)
    implementation(AndroidX.lifecycleViewModelCompose)
    implementation(AndroidX.lifecycleViewModelKtx)

    //Modules
    implementation(project(Module.CoreUi))
    implementation(project(Module.Data))
    implementation(project(Module.Features))

    //Compose
    implementation(AndroidX.activityCompose)
    implementation(platform(AndroidX.composeBom))
    implementation(AndroidX.composeUi)
    implementation(AndroidX.composeUiGraphics)
    implementation(AndroidX.composeUiToolingPreview)
    implementation(AndroidX.composeMaterial3)
    implementation(AndroidX.navigationCompose)

    //Hilt
    implementation(HiltAndroid.hiltAndroid)
    kapt(HiltAndroid.hiltAndroidCompiler)
    implementation(HiltAndroid.hiltAndroidTesting)
    implementation(HiltAndroid.hiltNavigationCompose)

    //Retrofit
    implementation(RetrofitLibs.retrofit)
    implementation(RetrofitLibs.converterGson)
    implementation(RetrofitLibs.okhttp)
    implementation(RetrofitLibs.loggingInterceptor)

    //Testing
    testImplementation(TestingLibs.junit)
    androidTestImplementation(TestingLibs.junitExt)
    androidTestImplementation(TestingLibs.espressoCore)
    androidTestImplementation(platform(AndroidX.composeBom))
    androidTestImplementation(TestingLibs.composeUiTestJunit4)
    debugImplementation(TestingLibs.composeUiTooling)
    debugImplementation(TestingLibs.composeUiTestManifest)
}