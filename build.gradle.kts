import net.minecrell.pluginyml.paper.PaperPluginDescription

plugins {
    id("java")
    alias(libs.plugins.shadow)
    alias(libs.plugins.run.paper)
    alias(libs.plugins.plugin.yml.paper)
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation(libs.exp4j)

    // Paper
    compileOnly(libs.paper.api)

    // Plugin
    compileOnly(libs.carbonchat)
    compileOnly(libs.miniplaceholders.api)

    // Config
    paperLibrary(libs.configurate.hocon) // sometimes error occur.
    paperLibrary(libs.adventure.serializer.configurate4)

    // Others
    paperLibrary(libs.guice)
}

version = "0.0.1-SNAPSHOT"

paper {
    authors = listOf("tyonakaisan")
    website = "https://github.com/tyonakaisan"
    apiVersion = "1.21"
    generateLibrariesJson = true
    foliaSupported = false

    val mainPackage = "github.tyonakaisan.pixelicon"
    main = "$mainPackage.PixelIcon"
    bootstrapper = "$mainPackage.PixelIconBootstrap"
    loader = "$mainPackage.PixelIconLoader"

    serverDependencies {
        register("MiniPlaceholders") {
            load = PaperPluginDescription.RelativeLoadOrder.BEFORE
            required = false
        }
    }
}

tasks {
    val paperPlugins = runPaper.downloadPluginsSpec {
        // MiniPlaceholders
        // github("MiniPlaceholders", "MiniPlaceholders", "2.2.4", "MiniPlaceholders-Paper-2.2.4.jar")
        // Carbon
        // github("Hexaoxide", "Carbon", "v${libs.versions.carbonchat.get()}", "carbonchat-paper-${libs.versions.carbonchat.get()}.jar")
        // LuckPerms
        url("https://download.luckperms.net/1552/bukkit/loader/LuckPerms-Bukkit-5.4.137.jar")
    }

    compileJava {
        this.options.encoding = Charsets.UTF_8.name()
        options.release.set(21)
    }

    shadowJar {
        this.archiveClassifier.set(null as String?)
    }

    runServer {
        minecraftVersion("1.21.1")
        downloadPlugins {
            downloadPlugins.from(paperPlugins)
        }
    }

    test {
        useJUnitPlatform()
    }
}