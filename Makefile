.PHONY: build

build:
	./gradlew clean build -x test

tests:
	./gradlew test

copy_to_s3:
	aws s3 cp build/libs/iomete-spark-extensions-2.4.jar s3://iomete-config/spark/jars/

install:
	./gradlew publishToMavenLocal