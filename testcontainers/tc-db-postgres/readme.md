# tc-db-postgres

Demo app showing how to use Postgres 12 with Test Containers and Spring Boot. 

## Prerequisites

* [Java 11 JDK](https://adoptopenjdk.net/) 
* [Docker](https://www.docker.com/products/docker-desktop) 
* [Docker Compose](https://docs.docker.com/compose/install/)
* Favourite Java IDE 

## Todo 

### Build the app  

* build the app `mvnw clean package` to produce the fat jar. 
* scroll through the console output and find the output from test containers and read it.
  * What version of postgres did test containers launch?
  
### Explore Test Containers usage in the sample app 
* import the project into your favourite Java IDE 
* inspect the `QuotesRepositoryTest` test class read code 
  * Which profile does the test activate? 
* run the `QuotesRepositoryTest` from your IDE and inspect the console output
* inspect the `src/test/resources/application-test.yml` file 
  * notice the format of the jdbc url
  * when do the settings in this file become active? 
* inspect the `src/main/resources/application.yml` 
  * compare the jdbc url in `application.yml` to `application-test.yml` what's the same and
    what is different.
* inspect the project `pom.xml` and notice the test container dependencies. 
* inspect the `src/main/resources/db/migration` folder and notice the SQL migrations. To get 
  full value out of test containers you will need to use a database migration tool such as 
  flywaydb or liquibase so you can configure the database as it is starting up.
    
### Run the application in the IDE 
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

## Resources 

* [Test Containers](https://www.testcontainers.org/)
* [Database Containers](https://www.testcontainers.org/modules/databases/)
* [FylwayDB](https://flywaydb.org/)
* [Liquibase](https://www.liquibase.org/)
* [Frictionless Local Postgres with Docker Compose](https://adibsaikali.com/2020/08/02/developer-friendly-local-postgresql-with-docker-compose/)
