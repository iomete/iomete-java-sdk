plugins {
    id("java")
    id("maven-publish")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.iomete"
version = "3.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-core:2.15.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.15.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    // Logging
    implementation("org.apache.logging.log4j:log4j-api:2.20.0")
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.20.0")

    // HTTP Client
    implementation("org.apache.httpcomponents:httpclient:4.5.14")

    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation("org.mockito:mockito-core:5.11.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.11.0")

    testImplementation("org.assertj:assertj-core:3.21.0")
}

tasks.jar {}

tasks.test {
    useJUnitPlatform()

    exclude("**/**IntegrationTest")
}

tasks.withType<Jar> {
    exclude("log4j2.properties") // for src/main/resources/application-*.yml, this is already from root directory.
}

publishing {

    publications {
        create<MavenPublication>("maven") {
            groupId = group.toString()
            artifactId = rootProject.name
            version = version

            from(components["java"])
        }
    }
}

fun AbstractArchiveTask.configureArchiveTask() {
    destinationDirectory.set(layout.buildDirectory.dir("dist"))

    from(projectDir) {
        include("build.gradle.kts")
        include("settings.gradle.kts")
        include("gradlew")
        include("gradlew.bat")
        include("LICENSE")
        include("readme.md")
        include("development.md")
        include("gradle/**")
        include("src/**")
    }
}

tasks.register<Zip>("packageDistributionZip") {
    configureArchiveTask()
}

tasks.register<Tar>("packageDistributionTar") {
    configureArchiveTask()
    compression = Compression.GZIP
    archiveExtension.set("tar.gz")
}

tasks.register("packageAll") {
    dependsOn("packageDistributionZip", "packageDistributionTar")
}
