# CHATOP

Chatop is an application for connecting tenants and owners.
The backend of the application is developed in JAVA with Spring Boot and the frontend with Angular.

## USING Chatop
To use Chatop, follow these steps:

## <ins>BACK-END</ins>

### Prerequisites

* Java 18
* Mysql 8.x.x
* IDE like Intellij / Eclipse


Clone the project from: [GitHub - Chatop Backend](https://github.com/jodt/P3-Developpez-le-back-end-en-utilisant-Java-et-Spring-API)

### Database :

* After installing mysql, and setting your username and password, open a terminal and use the command :

```
mysql -u username -p password
```

* Now create the chatop database using the schema.sql file. Copy folder path and use command :

```
SOURCE /path/to/schema.sql
```

* A second file allows you to insert data into the database to be able to test it directly. To do this, use the
  data.sql file, proceeding in the same way as before :

```
SOURCE /path/to/data.sql
```
This creates a few rentals and two users with the following credentials:
* email : userOne@gmail.com
* password : azerty


* email : userTwo@gmail.com
* password : azerty

### Project setup :

* Open the project in your IDE. Open the application.properties file and edit the following lines:

```
#jwt secret key
jwt.secret=Your256BitSecretHere
```
:warning: use a 256-bit key, you can use an online key generator to do this.
```
#database
spring.datasource.url=jdbc:mysql://localhost:3306/chatop?createDatabaseIfNotExist=true
```
:warning: change the mysql port if it's not 3306.

### Run the project

* In your terminal, go to your project folder and follow the instructions :
<br></br>
  - Define two environment variables with these commands :
  ```
  export DB_USERNAME=your_username 
  ```
  ```
  export DB_PASSWORD=your_password 
  ```
  :warning: Replace "your_username" with your database username and "your_password" with your database password
<br></br>
- Launch the application with these commands:
  <br></br>
  :warning: Make sure the location of the jar file and modify it if necessary in the command
  ```
  mvn clean install
  java -jar target/chatop-0.0.1-SNAPSHOT.jar
  ```
  **or**
  ```
  mvn spring-boot:run
  ```

### API documentation

After launching the application, opening your terminal and using the following link
````
http://localhost:3001/swagger-ui/index.html
````
You can now explore all available routes of API, including their descriptions, parameters, and even test endpoints directly.

To test some routes, you will need to provide an authentication token.

Obtain it using the endpoint login with a user's credentials.

Click the "Authorize" button and paste the token value. You can now test secure endpoints.


## <ins>FRONT-END</ins>

### Prerequisites

* Node 20.x.x
* Angular 18.x.x
* IDE VS code / Sublime text

Clone the project from: [GitHub - Chatop Frontend](https://github.com/jodt/P3-Developpez-le-back-end-en-utilisant-Java-et-Spring)


### Run the project
* Open it in your IDE
* install dependencies
    ```
    npm install
    ```
* start the development server
    ```
    ng serve
    ```
- Open your browser and navigate to:
    ```
    http://localhost:4200
    ```