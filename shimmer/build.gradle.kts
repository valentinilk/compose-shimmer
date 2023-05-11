plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
    signing
}

android {
    compileSdk = Config.targetSdk
    namespace = "com.valentinilk.shimmer"

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Lib.Compose.compiler
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
    arrayOf(
        platform(Lib.Compose.composeBom),
        Lib.Compose.runtime,
        Lib.Compose.foundation,
        Lib.Compose.ui,
    ).forEach { dependency ->
        implementation(dependency)
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("maven") {
                from(components["release"])

                groupId = "com.valentinilk.shimmer"
                artifactId = "compose-shimmer"
                version = "1.0.5"

                pom {
                    name.set("Compose Shimmer")
                    description.set("A shimmer library for Android's Jetpack Compose")
                    url.set("https://github.com/valentinilk/compose-shimmer/")
                    inceptionYear.set("2021")

                    scm {
                        connection.set("scm:git:git://github.com/valentinilk/compose-shimmer.git")
                        developerConnection.set("scm:git:git://github.com/valentinilk/compose-shimmer.git")
                        url.set("https://github.com/valentinilk/compose-shimmer/")
                    }

                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                            distribution.set("repo")
                        }
                    }

                    developers {
                        developer {
                            id.set("valentinilk")
                            name.set("Valentin Ilk")
                            url.set("https://github.com/valentinilk/")
                        }
                    }
                }
            }
        }
        repositories {
            maven {
                url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                credentials {
                    username = project.property("ossrhUsername") as String
                    password = project.property("ossrhPassword") as String
                }
            }
        }
    }

    signing {
        sign(publishing.publications["maven"])
    }
}
