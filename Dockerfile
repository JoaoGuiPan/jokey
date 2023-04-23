FROM gradle:7.6.1-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/app
WORKDIR /home/app
RUN gradle build --no-daemon

FROM amazoncorretto:17
RUN mkdir -p /app
WORKDIR /app
COPY --from=build /home/app/build/libs/*.jar ./app.jar
EXPOSE $PORT
CMD [ "java", "-jar", "./app.jar" ]