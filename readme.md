```shell
./gradlew clean build
./gradlew publishToMavenLocal

# Build skip tests
./gradlew clean build -x test

./gradlew clean build -x test && cp build/libs/iomete-spark-extensions-2.4.jar /Users/vusaldadalov/Documents/iomete/github.com/spark/assembly/target/scala-2.12/jars 
```
