# Compose Starter

[![](https://jitpack.io/v/eitanliu/compose_starter.svg)](https://jitpack.io/#eitanliu/compose_starter)

### How to

To get a Git project into your build:  
*Step 1.* Add the JitPack repository to your build file  
Add it in your root `settings.gradle.kts` at the end of repositories:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            setUrl("https://jitpack.io")
            val jitpackToken = System.getProperties().getProperty("jitpackToken")
            if (jitpackToken.isNullOrEmpty().not()) {
                credentials { username = jitpackToken }
            }
        }
    }
}
```

*Step 2.* Add the dependency

```kotlin
dependencies {
    // implementation("com.github.eitanliu.compose_starter:utils:1.0.0")
    implementation("com.github.eitanliu.compose_starter:starter:1.0.0")
}
```

### Doc

[Kotlin Multiplatform Development](https://www.jetbrains.com/help/kotlin-multiplatform-dev)  
[Kotlin Multiplatform Overview | Android](https://developer.android.com/kotlin/multiplatform)  

### Dev Repositories

[Maven](https://maven.pkg.jetbrains.space/public/p/space/maven)  
[Compose](https://maven.pkg.jetbrains.space/public/p/compose/dev/)  
[KtorEAP](https://maven.pkg.jetbrains.space/public/p/ktor/eap/)  
[KotlinIDE](https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-ide/)  
[coroutines](https://maven.pkg.jetbrains.space/public/p/kotlinx-coroutines/maven)  
[grpc](https://maven.pkg.jetbrains.space/public/p/krpc/grpc)  
