#### Requirements

- JDK 10
- Gradle
- Postgresql

#### Build

```bash
./gradlew build
```

#### Run

```bash
# Run through spring-boot plugin
./gradlew bootRun
```

#### Database

You should run application with database settings.

```bash
./gradlew bootRun -Dspring.datasource.username=<...> -Dspring.datasource.password=<...> -Dspring.datasource.url=<jdbc:...>
```

Also you can set your own datasource settings to `application-dev.yml` in resources path

#### Build executable jar

```bash
./gradlew clean
```

It's optional command, after

```bash
./gradlew bootJar
```

It will make executable jar