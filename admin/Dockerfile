FROM gradle:jdk17-alpine@sha256:3a4387447cb837375e0533b1c02501e03b8266608d9f61369aceb61aa93ec412 as BUILD
WORKDIR /app

COPY library/src/main library/src/main
COPY library/build.gradle library/build.gradle
COPY admin/src/main admin/src/main
COPY admin/build.gradle admin/build.gradle
COPY build.gradle .
COPY settings.gradle .

RUN cd admin && gradle build

FROM eclipse-temurin:17-jre-alpine@sha256:68b6af70562c648a9d871d866849eb1b82e13152e09503fc9f62bf8bf8ab2fe7
ENV PROFILE local
WORKDIR /app
COPY --from=BUILD app/admin/build/libs/admin-*.jar admin.jar
CMD java -jar -Dspring.profiles.active=${PROFILE} admin.jar