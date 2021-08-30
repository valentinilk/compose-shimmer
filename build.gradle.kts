buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Lib.androidGradlePlugin)
        classpath(Lib.kotlinGradlePlugin)
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}
