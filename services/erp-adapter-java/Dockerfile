# =========================
# STAGE 1 - BUILD
# =========================
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

# copia somente o necessário primeiro (cache de dependências)
COPY pom.xml .
RUN mvn -B -q -e -DskipTests dependency:go-offline

# copia o restante do projeto
COPY src ./src

# build
RUN mvn -B -DskipTests clean package

# =========================
# STAGE 2 - RUNTIME
# =========================
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# copiar jar gerado
COPY --from=builder /app/target/*.jar app.jar

# boas práticas JVM
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75"

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]