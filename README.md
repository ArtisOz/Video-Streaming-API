# Video Management API

## Overview
This project is a Spring Boot application that provides RESTful APIs for managing video content and statistics.

## Features
- Add, list, update, and soft-delete videos.
- Retrieve video statistics by video ID.
- Serve video content.
- Search videos by title, director, genre, and year of release.

## Technologies Used
- Java
- Spring Boot
- Hibernate (JPA)
- Maven
- H2 Database

## Prerequisites
- JDK 17 or later
- Maven 3.8 or later
- IntelliJ IDEA (optional but recommended)

## Setup Instructions
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd demo
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. Access the application:
    - Open your browser and go to: `http://localhost:8080`.

## Project Structure
```
demo/
├── src/
│   ├── main/
│   │   ├── java/       # Java source code
│   │   └── resources/  # Configuration files
│   └── test/           # Test cases
├── .gitignore          # Git ignore rules
├── pom.xml             # Maven configuration file
├── README.md           # Project documentation
└── mvnw, mvnw.cmd      # Maven wrapper scripts
```

## API Endpoints
### Video Management

#### Add Video
- **Endpoint**: `POST /videos`
- **Request Body**:
  ```json
  {
    "title": "Inception",
    "synopsis": "A mind-bending thriller",
    "director": "Christopher Nolan",
    "castMembers": "Leonardo DiCaprio, Ellen Page",
    "yearOfRelease": 2010,
    "genre": "Sci-Fi",
    "runningTime": 148
  }
  ```
- **Expected Response**:
  ```json
  {
    "id": 1,
    "title": "Inception",
    "synopsis": "A mind-bending thriller",
    "director": "Christopher Nolan",
    "castMembers": "Leonardo DiCaprio, Ellen Page",
    "yearOfRelease": 2010,
    "genre": "Sci-Fi",
    "runningTime": 148,
    "listed": true
  }
  ```

#### List All Videos
- **Endpoint**: `GET /videos`
- **Expected Response**:
  ```json
  [
    {
      "id": 1,
      "title": "Inception",
      "director": "Christopher Nolan",
      "genre": "Sci-Fi",
      "runningTime": 148
    }
  ]
  ```

#### Get a Video by ID
- **Endpoint**: `GET /videos/{id}`
- **Example**:
  ```bash
  GET /videos/1
  ```
- **Expected Response**:
  ```json
  {
    "id": 1,
    "title": "Inception",
    "synopsis": "A mind-bending thriller",
    "director": "Christopher Nolan",
    "castMembers": "Leonardo DiCaprio, Ellen Page",
    "yearOfRelease": 2010,
    "genre": "Sci-Fi",
    "runningTime": 148,
    "listed": true
  }
  ```

#### Play a Video
- **Endpoint**: `GET /videos/{id}/play`
- **Example**:
  ```bash
  GET /videos/1/play
  ```
- **Expected Response**:
  ```
  Playing video: Inception - [Content Mocked]
  ```

#### Serve Video Content
- **Endpoint**: `GET /videos/{id}/content`
- **Example**:
  ```bash
  GET /videos/1/content
  ```
- **Expected Response**:
  ```
  Streaming content for video ID: 1
  ```

#### Soft Delete a Video
- **Endpoint**: `PUT /videos/{id}/delist`
- **Example**:
  ```bash
  PUT /videos/1/delist
  ```
- **Expected Response**:
  ```json
  "Video delisted successfully."
  ```

#### Delete a Video
- **Endpoint**: `DELETE /videos/{id}`
- **Example**:
  ```bash
  DELETE /videos/1
  ```
- **Expected Response**: HTTP Status `200 OK` and no additional content.

### Statistics

#### Get Statistics
- **Endpoint**: `GET /videos/{id}/stats`
- **Example**:
  ```bash
  GET /videos/1/stats
  ```
- **Expected Response**:
  ```json
  {
    "videoId": 1,
    "impressions": 0,
    "views": 0
  }
  ```

### Search

#### Search Videos
- **Endpoint**: `GET /videos/search`
- **Example**:
  ```bash
  GET /videos/search?director=Christopher%20Nolan&genre=Sci-Fi
  ```
- **Expected Response**:
  ```json
  [
    {
      "id": 1,
      "title": "Inception",
      "director": "Christopher Nolan",
      "genre": "Sci-Fi",
      "runningTime": 148
    }
  ]
  ```

## Running Tests
Run tests with Maven:
```bash
mvn test
```

## Authors
- [Artis Ozols](mailto:artisozolss@gmail.com)

## License
This project is licensed under the MIT License.
