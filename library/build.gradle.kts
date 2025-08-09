plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.vanniktech.publish)
}

android {
    namespace = "io.lokile.composeoverlay"
    compileSdk = 36
    buildFeatures { compose = true }

    defaultConfig {
        minSdk = 26

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.runtime)
    implementation(libs.ui)
    implementation(libs.androidx.lifecycle.process)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.startup.runtime)
}

mavenPublishing {
    coordinates(
        groupId = "io.github.lokile",
        artifactId = "compose-overlay",
        version = "1.0.0"
    )

    // Configure POM metadata for the published artifact
    pom {
        // General information
        name.set("compose-overlay")
        description.set(
            """
            A lightweight Android library for displaying Compose UI as overlays on top of any running activity without modifying the activity's layout
            """.trimIndent()
        )
        inceptionYear.set("2025")
        url.set("https://github.com/lokile/compose-overlay")

        // License information
        licenses {
            license {
                name.set("MIT License")
                url.set("https://opensource.org/licenses/MIT")
                distribution.set("https://opensource.org/licenses/MIT")
            }
        }

        // Specify developers information
        developers {
            developer {
                id.set("lokile")
                name.set("Loki Le")
                email.set("lokile208@gmail.com")
                url.set("https://github.com/lokile")
            }
        }

        // Specify SCM information
        scm {
            url.set("https://github.com/lokile/compose-overlay")
            connection.set("scm:git:git://github.com/lokile/compose-overlay.git")
            developerConnection.set("scm:git:ssh://git@github.com/lokile/compose-overlay.git")
        }
    }

    // Configure publishing to Maven Central
    publishToMavenCentral()

    // Enable GPG signing for all publications
    if (
        providers.gradleProperty("signingInMemoryKey").isPresent ||
        providers.gradleProperty("signing.secretKeyRingFile").isPresent
    ) {
        signAllPublications()
    }
}