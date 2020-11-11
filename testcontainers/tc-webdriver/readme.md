# tc-db-postgres

Demo app showing how to use Test containers with Web Driver browser automation tests. This sample
runs Chrome in a docker container and uses it to make HTTP requests to a Spring Boot launched 
inside a Junit5 test. 

## Prerequisites

* [Java 11 JDK](https://adoptopenjdk.net/) 
* [Docker](https://www.docker.com/products/docker-desktop) 
* [Docker Compose](https://docs.docker.com/compose/install/)
* Favourite Java IDE 
* A video player that can playback `.flv` video files

## Todo 

### Build the app  

* build the app `mvnw clean package` to produce the fat jar. *The tests pull multiple large 
  container images. The first time you run this step might take a while. Subsequent runs
  should be faster. Docker Hub introduced rate limits in Nov 2020 it might impact your ability
  to pull all these images.*
* scroll through the console output and find the output from test containers and look for the whale
  emoji noting which containers were launched by test containers. 
* navigate into the `target` folder you will find a video recording of the web driver test it's the 
  file with the `.flv` extension, play this video file and you will se a video of test executing. 
  Download an flv player if you don't have one. 
 
### Run the application in the IDE 

 * import the project into your favourite Java IDE 
 * Run the `DemoApplication` from the IDE, you will get an error message saying it can't connect
   to a postgres database it expects to be running on port 15432. Let's fix it.
 * inspect the files `laptop` folder in this project. The `laptop` holds files that are only needed
   on a developer laptop. 
 * open a command prompt then navigate to `laptop` then type `docker-compose up` two containers will
   be launched. 
    * postgres 12 on port 15432
    * pgAdmin 4 on port 15433 
 * use your browser to navigate to 15433. then click around to find the `quotes` database 
   * are there any tables in the `quotes` database?
 * Run the `DemoApplication` from the IDE it should work with no errors.
 * use your browser to visit the app at `http://localhost:8080` 
  
### Explore Test Containers usage in the sample app
 
* inspect the `BrowserTest` test class read the code 
  * Which profile does the test activate? 
* run the `BrowserTest` from your IDE and inspect the console output
* how many docker containers ran during the test execution?
* inspect the project `pom.xml`. Notice the test container and selenium dependencies.
* navigate into the `target` folder you will find a video recording of the web driver test it's the 
  file with the `.flv` extension, play this video file and you will se a video of test executing. 
  Download an flv player if you don't have one. 
  
### Shutdown the app 

* execute `docker-compose down` in the laptop folder

## Resources 

* [Selenium](https://www.selenium.dev/documentation/en/)
* [Test Containers](https://www.testcontainers.org/)
* [Frictionless Local Postgres with Docker Compose](https://adibsaikali.com/2020/08/02/developer-friendly-local-postgresql-with-docker-compose/)
