plugins {
    id("java")
    id("org.pastalab.fray.gradle") version "0.5.1-SNAPSHOT"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
    jvmArgs("-Dfray.recordSchedule=true")
    jvmArgs("-Dfray.verifySchedule=true")
}
tasks.withType<Test> {
    jvmArgs("-Dfray.recordSchedule=true")
    jvmArgs("-Dfray.verifySchedule=true")
}
