FROM amazoncorretto:17 AS build

RUN yum update -y && yum install -y maven
WORKDIR /app

COPY . .

RUN mvn clean install

FROM amazoncorretto:17

EXPOSE 8080

COPY --from=build /target/filter_render-1.0.0.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]