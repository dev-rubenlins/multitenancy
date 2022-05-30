FROM maven:3.8.1-openjdk-11-slim AS builder
ENV HOME=/project
RUN mkdir $HOME
WORKDIR $HOME
ADD pom.xml $HOME
RUN mvn verify clean --fail-never
COPY . $HOME
RUN mvn package -DskipTests


FROM eclipse-temurin:11-alpine
RUN apk add --no-cache bash
RUN mkdir /app
RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser
COPY --from=builder /project/target/multi-tenant-management-0-SNAPSHOT.jar /app/multi-tenant-management-0-SNAPSHOT.jar
WORKDIR /app
RUN chown -R javauser:javauser /app
USER javauser
CMD "java" "-jar" "multi-tenant-management-0-SNAPSHOT.jar"