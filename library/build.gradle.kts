import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.library")
    kotlin("android")
    id("maven-publish")
}

val versionMajor = 1
val versionMinor = 0
val versionPatch = 1

val artifactVersionCode = versionMajor * 10000 + versionMinor * 1000 + versionPatch * 100
val artifactVersionName = "$versionMajor.$versionMinor.$versionPatch"

val releaseArtifactId = "omnitext"
val releaseArtifactGroupId = "com.github.stevenrudenko"
val releaseArtifactPath = "$buildDir/outputs/aar/${project.name}-release.aar"

android {
    compileSdkVersion(30)
    buildToolsVersion = "30.0.2"

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 2
        versionName = "1.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        resourcePrefix = "omni_"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(kotlin("stdlib", KotlinCompilerVersion.VERSION))

    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.2.1")

    implementation("com.googlecode.libphonenumber:libphonenumber:8.12.12")

    testImplementation("junit:junit:4.+")
    testImplementation("org.mockito:mockito-core:3.3.3")

    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}

publishing {
    val gprBaseUrl = "https://maven.pkg.github.com"
    val gprRepoOwner = "StevenRudenko"
    val gprRepoId = "OmniText"

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("$gprBaseUrl/$gprRepoOwner/$gprRepoId")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("GH_USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("GH_API_KEY")
            }
        }
    }

    publications {
        register("gprRelease", MavenPublication::class) {
            groupId = releaseArtifactGroupId
            artifactId = releaseArtifactId
            version = artifactVersionName
            artifact(releaseArtifactPath)

            pom {
                withXml {
                    // add dependencies to pom
                    val dependencies = asNode().appendNode("dependencies")
                    configurations.implementation.get().dependencies.forEach {
                        if (it.group != null && "unspecified" != it.name && it.version != null) {
                            val dependencyNode = dependencies.appendNode("dependency")
                            dependencyNode.appendNode("groupId", it.group)
                            dependencyNode.appendNode("artifactId", it.name)
                            dependencyNode.appendNode("version", it.version)
                        }
                    }
                }
            }
        }
    }
}

