# Weather Project
 This is a bare-bones example of a Spring Boot project which returns weather information to the users about the current weather and the forecasted weather.
 Currently it only supports three cities Mumbai, Banglore, TamilNadu. Random values for weather data is generated and inserted by the DataLoader class at the start of the application.
 As mentioned exceptions and edge cases are handled. 
 The weather data is generally public so the security is not added.
 The data is stored in an internal H2 database.
 Spring profiles are not created but can be added.

Other additions like Docker support and external database can be done but were avoided in the interest of time.


The entire application is contained within the `target/demo-0.0.1-SNAPSHOT.jar` file.


# Requirements
    ● Edge case handling and proper error &amp; exception handling is expected.
    ● APIs should return proper response codes.
    ● A detailed README on how to run the project and see the output.
    
    Technology Stack
    ● Java
    ● Spring Boot 2+



## Install

    mvn install

## Run the app

    `java -jar demo-0.0.1-SNAPSHOT.jar`


# REST API

The API details are mentioned below.


### Request

`GET http://localhost:8081/weather?city=Mumbai`

    curl -i -H 'Accept: application/json' http://localhost:8081/weather?city=Mumbai

### Response

    HTTP/1.1 200 OK
    Date: Thu, 11 Nov 2021 12:36:30 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    
    {
    "id": 4,
    "city": "Mumbai",
    "temperature": 22,
    "weatherType": "STORMY",
    "date": "2021-11-18"
    }


### Request

`GET http://localhost:8081/weather/forecast?city=Mumbai`

    curl -i -H 'Accept: application/json' http://localhost:8081/weather/forecast?city=Mumbai

### Response

    HTTP/1.1 200 OK
    Date: Thu, 11 Nov 2021 12:36:30 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    
    [
      {
      "id": 4,
      "city": "Mumbai",
      "temperature": 22,
      "weatherType": "STORMY",
      "date": "2021-11-18"
      },
      {
      "id": 5,
      "city": "Mumbai",
      "temperature": 46,
      "weatherType": "RAINY",
      "date": "2021-11-19"
      },
      {
      "id": 6,
      "city": "Mumbai",
      "temperature": 15,
      "weatherType": "SUNNY",
      "date": "2021-11-20"
      }
    ]

