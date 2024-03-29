// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.1")
        classpath(kotlin("gradle-plugin", version = "1.4.10"))

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

fun getGrpUser(): String {
    return if (project.hasProperty("gpr.user")) {
        project.properties["gpr.user"] as String
    } else {
        System.getenv("GPR_USER") ?: ""
    }
}

fun getGprKey(): String {
    return if (project.hasProperty("gpr.key")) {
        project.properties["gpr.key"] as String
    } else {
        System.getenv("GPR_API_KEY") ?: ""
    }
}

print(getGrpUser())
print(getGprKey())