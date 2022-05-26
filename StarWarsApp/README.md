# **Star Wars Application**

## Keywords
* Java, JUnit, Microservices, Spring Boot, Docker, REST API, Postman, JSON.

## Dev notes
* The goal of this exercise is to develop a microservice that runs on a Docker container. This microservice manages information consumed from a public API (in this case the Star Wars API).
* Developed in [**Java**](https://www.java.com/es/) language,  [**Spring Boot**](https://spring.io/projects/spring-boot) as framework and [**Maven**](https://maven.apache.org/) as building tool.

## Installations
* [**Docker Desktop**](https://hub.docker.com/editions/community/docker-ce-desktop-windows/) for Windows:
    * Needed for the image that will run the Star Wars Application.
    * Wizard installation. May ask to install WSL2 linux kernel (accept).
    * Restart.
* [**Postman**](https://www.postman.com/downloads/?utm_source=postman-home) for Windows:
    * Needed for easy testing of the Star Wars Application endpoints.
    * Wizard installation.

## Setup
* **Code and JAR**:
    * From your git client prompt, go to a folder of your choice and clone the repository:
    `git clone https://github.com/eleagece/Learning.git`. 
    * The project is inside `StarWarsApp`
    * The JAR is already generated in `target/` folder.
    * There are a couple of `.launch` files in `src/main/resources/` to build the project in Eclipse. One passes the tests (`StarWarsApp --- maven build with tests.launch`) and the other ignores them (`StarWarsApp --- maven build without tests.launch`)
* **Docker**:
    * From your `cmd` go to the `StarWarsApp` folder where the `Dockerfile` is.
    * Create container (`-t`) with a name:tag (`starwars:1.0`):
    `docker build -t starwars:1.0 .`
    * Run the created container (`starwars:1.0`) in background (`-d`), listening in the desired port (`-p 6969:6969`) and giving a name to the container (`--name StarWarsApp`):
    `docker run -d -p 6969:6969 --name StarWarsApp starwars:1.0`
    * You can check the container is running by executing `docker ps` or in Docker Desktop.
    * You can stop the container by executing `docker stop StarWarsApp` or in Docker Desktop.

## Usage
* **Endpoints**:
    * GET: retrieves a list of names of the residents of the planet `planetName`: `localhost:6969/planets/residents-names-by-planet-name/{planetName}`
    * GET: retrieves a list of starships (with all their information) used by the person `personName`: `localhost:6969/starships/starships-info-by-person-name/{personName}`
* **Postman**: 
    * A Postman collection is included in `src/main/resources/StarWarsApp.postman_collection.json` for easy testing of the endpoints.
* **Tests**:
    * A Eclipse `.launch` file to run the tests is included in `src/main/resources/StarWarsApp --- JUnit tests.launch`.