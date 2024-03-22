pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://storage.googleapis.com/download.flutter.io")
    }
}

rootProject.name = "PikaRoad"
include(":app")
include(":qrapp")

apply { from("fluttersettings.gradle") }

include(":core")
project(":core").projectDir = File(rootDir, "features/common/core")

include(":ui")
project(":ui").projectDir = File(rootDir, "features/common/ui")

include ("vgc-data")
project(":vgc-data").projectDir = File(rootDir, "features/vgc/vgc-data")

include ("vgc-domain")
project(":vgc-domain").projectDir = File(rootDir, "features/vgc/vgc-domain")

include ("vgc-ui")
project(":vgc-ui").projectDir = File(rootDir, "features/vgc/vgc-ui")

include ("tcg-data")
project(":tcg-data").projectDir = File(rootDir, "features/tcg/tcg-data")

include ("tcg-domain")
project(":tcg-domain").projectDir = File(rootDir, "features/tcg/tcg-domain")

include ("tcg-ui")
project(":tcg-ui").projectDir = File(rootDir, "features/tcg/tcg-ui")

include("qr-scanner-ui")
project(":qr-scanner-ui").projectDir = File(rootDir, "features/qr-scanner/qr-scanner-ui")

include(":ads-service")
project(":ads-service").projectDir = File(rootDir, "features/ads/ads-service")

include(":podcast-data")
project(":podcast-data").projectDir = File(rootDir, "features/podcast/podcast-data")

include(":podcast-domain")
project(":podcast-domain").projectDir = File(rootDir, "features/podcast/podcast-domain")

include(":podcast-ui")
project(":podcast-ui").projectDir = File(rootDir, "features/podcast/podcast-ui")
