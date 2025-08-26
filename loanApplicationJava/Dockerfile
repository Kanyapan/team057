# ---- Build stage ----
FROM maven:3.9-eclipse-temurin-21-alpine AS build
WORKDIR /src
COPY pom.xml .
# cache dependency ก่อน ทำให้ build รอบต่อไปเร็ว
RUN mvn -q -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests clean package

# ---- Run stage ----
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# ถ้าไฟล์ jar ชื่อแน่นอนจะระบุชื่อเต็มก็ได้; ถ้าไม่ชัวร์ใช้ wildcard
COPY --from=build /src/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
