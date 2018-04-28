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

It's required for run project. Also you can 