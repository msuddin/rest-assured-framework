# REST Assured Framework

## Purpose
This is a sample project for REST Assured API endpoint testing. This project currently is hitting a test endpoint.
The focus here is to explore all the possible ways to use REST Assured to write tests.

## Instructions
To run all tests, from the root directory of this project run:
```
./gradlew clean test
```

## Features
 - Get and Post tests are seperated into their own tests
 - Example PostObject class for which we have generate a json scheme (including test)

## Useful Info
All the tests in this code hit endpoints at:
```
https://jsonplaceholder.typicode.com/
```

## Structure of a Rest Assured Test
```
Given() lets you:
    pass the request headers
    query and path param
    body
    cookies and headers
When() lets you:
    get
    post
    put
Then() lets you:
    perform assertion conditions
```