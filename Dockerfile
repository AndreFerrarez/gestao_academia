# Estágio de build
FROM maven:3.8.5-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
ENV MAVEN_OPTS="-Dfile.encoding=UTF-8"
RUN mvn -f /home/app/pom.xml clean package

# Estágio de execução
FROM openjdk:17-jdk-slim
COPY --from=build /home/app/target/gestao-academica-0.0.1-SNAPSHOT.jar /usr/local/lib/gestao-academica.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/gestao-academica.jar"]