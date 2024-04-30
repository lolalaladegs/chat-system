# Chat Server System

## Table of Contents

- [Overview](#overview)
- [Technology Used](#technology-used)
- [Installation](#installation)
- [Code Coverage](#code-coverage)
- [Contributing](#contributing)
- [License](#license)

***

## Overview

A Java-based chat server service system that enables users to join chat rooms, send messages and access chat history.  

## Features

- [x] User authentication with basic username/password
- [x] Creation of single chat room upon server startup
- [x] Storage of chat message in database
- [x] Sending and receiving messages in the chat room
- [x] Unit testing

## Technology Used

This project was built using the following technologies:

- [Java 17](https://www.oracle.com/java/technologies/downloads/)
- [Spring Boot](http://projects.spring.io/spring-boot/)
- [Maven](https://maven.apache.org/)
- [PostgreSQL](https://www.postgresql.org/)

## Installation

Make sure you have Java, Maven and PostgresSQL installed on your machine.

1. Clone the repository:

    ```sh
    git clone https://github.com/lolalaladegs/chat-system.git
    ```

2. Navigate into the project directory:

    ```sh
    cd chat_system
    ```

3. Build the project:

    ```sh
    mvn clean install
    ```

## Usage

1. Run the application:

    ```sh
    mvn spring-boot:run
    ```

## Code Coverage

This project has achieved a code coverage of 99% excluding configuration files.

For detailed coverage reports, do the following:

1. Generate code coverage report:

    ```sh
    mvn clean test
    ```

2. View the coverage report located at target/site/jacoco/index.html:

- [Coverage Report](target/site/jacoco/index.html)

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create your feature branch: `git checkout -b feature/YourFeature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin feature/YourFeature`
5. Submit a pull request

## License

This project is licensed under the [MIT License](LICENSE).
