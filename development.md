# Building the project

```shell
./gradlew clean build

# Build skip tests
./gradlew clean build -x test
```

## Uber Jar

```shell
./gradlew clean build shadowJar -x test
```
