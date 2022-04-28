# helmes-assignment (server)

## Prerequisites

You need to setup PostgreSQL and set up it with environment variables defined in ```.env```
(or define those values by yourself). You can run postgres directly from docker:

```bash
docker run --env-file ./.env --name postgres_latest -p 5432:5432 -d postgres
```

You also have to set ```.env``` variables to run server.

**MacOS/Linux**

```
source ./.env
```

**Windows**

```
env.bat
```

## Server startup

Open a command line (or terminal) and navigate to the folder where you have the project files. One can build and run the
application by issuing the following command:

**MacOS/Linux**

```
./mvnw spring-boot:run
```

**Windows**

```
mvnw spring-boot:run
```

## Application

Application on default runs on **http://localhost:8081/**.
