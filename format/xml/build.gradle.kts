import org.spongepowered.configurate.build.core

plugins {
    id("org.spongepowered.configurate.build.component")
}

dependencies {
    api(core())
    testImplementation("com.google.guava:guava:30.0-jre")
}
