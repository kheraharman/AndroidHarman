plugins {
    id(Plugin.androidLibrary)
    id(Plugin.kotlinAndroid)
    id(Plugin.kotlinKapt)
    id(Plugin.hiltAndroid)
    id(Detekt.plugin) version Detekt.version
}

android {
    namespace = "com.android.data"
    compileSdk = Android.compileSdk

    defaultConfig {
        minSdk = Android.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    //Modules
    implementation(project(Module.FruitsDomain))
    implementation(project(Module.Common))


    //Retrofit
    implementation(RetrofitLibs.retrofit)
    implementation(RetrofitLibs.converterGson)
    implementation(RetrofitLibs.okhttp)
    implementation(RetrofitLibs.loggingInterceptor)

    //Hilt
    implementation(HiltAndroid.hiltAndroid)
    kapt(HiltAndroid.hiltAndroidCompiler)
    implementation(HiltAndroid.hiltAndroidTesting)
    implementation(HiltAndroid.hiltNavigationCompose)

    //Testing
    testImplementation(TestingLibs.junit)
    testImplementation(TestingLibs.kotlinCoroutineTest)
    testImplementation(TestingLibs.mockk)
    testImplementation(TestingLibs.archCore)
}