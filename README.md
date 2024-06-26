# World Cup Game Backend

This project provides the backend for a World Cup game application, built with Java. The backend handles various aspects of the game logic, data storage, and API services.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

## Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/luanmxz/worldcupgame-backend.git
    cd worldcupgame-backend
    ```

2. Build the project using Maven:

    ```bash
    ./mvnw clean install
    ```

## Usage

1. Run the application:

    ```bash
    ./mvnw spring-boot:run
    ```

2. The backend server will start, and you can access it at `http://localhost:8080`.

## API Endpoints

- **GET /teams**: Retrieve a list of teams.
- **POST /teams**: Add a new team.
- **GET /matches**: Retrieve a list of matches.
- **POST /matches**: Add a new match.

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes.
4. Commit your changes (`git commit -m 'Add some feature'`).
5. Push to the branch (`git push origin feature-branch`).
6. Create a new Pull Request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
