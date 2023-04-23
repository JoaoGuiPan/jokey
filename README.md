# Jokey API

This is the Random Joke Generator! It uses the https://v2.jokeapi.dev/ API.

# Building

You will need Java 17 installed on your machine.
To build the code, simply run in the root folder `./gradlew clean build` on Linux/MacOS or `gradlew.bat clean build` on Windows.

# Running

To run the app, execute in the root folder `./gradlew bootRun` on Linux/MacOS or `gradlew.bat bootRun` on Windows.
If you have Docker installed, you can instead run `docker-compose up` in the root folder.
The API will be available at `http://localhost:8080/`.

# Documentation

The API documentation is available at `http://localhost:8080/swagger-ui.html`.
