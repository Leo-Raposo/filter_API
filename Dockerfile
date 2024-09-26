FROM amazoncorretto:17 AS build

RUN yum update -y && \
    yum install -y wget tar gzip && \
    wget https://downloads.apache.org/maven/maven-3/3.9.5/binaries/apache-maven-3.9.5-bin.tar.gz && \
    tar xzvf apache-maven-3.9.5-bin.tar.gz -C /opt && \
    ln -s /opt/apache-maven-3.9.5/bin/mvn /usr/bin/mvn

WORKDIR /app

COPY . .

RUN mvn clean install

FROM amazoncorretto:17

EXPOSE 8080

COPY --from=build /app/target/filter_render-1.0.0.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
