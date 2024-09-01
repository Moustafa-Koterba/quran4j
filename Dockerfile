FROM eclipse-temurin:17-jre-alpine@sha256:68b6af70562c648a9d871d866849eb1b82e13152e09503fc9f62bf8bf8ab2fe7
WORKDIR /app
COPY /admin/build/libs/admin-*.jar admin.jar
CMD ["java", "-jar", "admin.jar"]