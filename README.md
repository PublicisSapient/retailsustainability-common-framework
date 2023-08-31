# Common Framework

The Common Framework is a Spring Boot application that provides common functionalities that are shared by the other microservices.


## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Error Messages](#error-messages)
- [Contributing](#contributing)
- [License](#license)



## Features

- Includes cookie generation and validation for the authenticated endpoints in the other services.
- Includes Global Exception handling and Error handling.
- Includes the method for getting the user details.
- Includes the logging feature and its customization.
- Includes the common models that are shared.


## Technologies Used

- Java 17
- Spring Boot 3.0.5
- MongoDB
- JSON Web Tokens (JWT)
- Spring Security


## Getting Started

To get started with the Common Framework, follow these steps:

1. Clone the repository: `git clone https://tools.publicis.sapient.com/bitbucket/scm/pem/common-framework.git`
2. Navigate to the common framework directory: `cd common-framework`
3. Build the common-framework: `mvn clean build`

Now, this service can be integrated with other services by including its dependency in their `pom.xml` file.


## Error Messages

The application defines a set of error messages for common scenarios. These messages are configurable and can be found in the `application.yml` file. You can customize the error messages according to your needs.


## Contributing

Contributions to the Common Framework are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.

1. Fork the repository.
2. Create your feature branch: `git checkout -b feature/my-new-feature`.
3. Commit your changes: `git commit -m 'Add some feature'`.
4. Push to the branch: `git push origin feature/my-new-feature`.
5. Submit a pull request.


## License

The Common Framework is open-source and available under the [MIT License](https://opensource.org/licenses/MIT).

Feel free to modify and adapt the code to suit your needs.

