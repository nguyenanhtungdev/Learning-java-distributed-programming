plugins {
    id("java")
}

group = "org.github.tung"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("jakarta.json:jakarta.json-api:2.1.3")
// https://mvnrepository.com/artifact/org.eclipse/yasson
    testImplementation ("org.eclipse:yasson:3.0.4")
// https://mvnrepository.com/artifact/org.eclipse.parsson/parsson
    implementation ("org.eclipse.parsson:parsson:1.1.7")
    // https://mvnrepository.com/artifact/jakarta.json.bind/ .bind-api
    implementation ("jakarta.json.bind:jakarta.json.bind-api:3.0.1")
// https://mvnrepository.com/artifact/net.datafaker/datafaker
    implementation ("net.datafaker:datafaker:2.4.2")
    // https://mvnrepository.com/artifact/org.projectlombok/lombok
    compileOnly ("org.projectlombok:lombok:1.18.36")

}


tasks.test {
    useJUnitPlatform()
}