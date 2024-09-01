FROM eclipse-temurin:17-jre-alpine@sha256:68b6af70562c648a9d871d866849eb1b82e13152e09503fc9f62bf8bf8ab2fe7
ENV PROFILE local

WORKDIR /app
COPY /build/libs/api-*.jar api.jar
CMD java -jar -Dspring.profiles.active=${PROFILE} api.jar