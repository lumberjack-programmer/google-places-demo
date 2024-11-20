# A simple Spring Boot demo REST API that acts as a proxy server for the Google Places API, forwarding requests and handling responses.

The Google Places API is a service that returns information about places using HTTP requests. Places are defined within this API as establishments, geographic locations, or prominent points of interest.

It's important to note that it's not secure to use the Google Places API key in the frontend application. This is because the API key can be easily extracted from the frontend application and used to make unauthorized requests to the Google Places API. To avoid this, you can create a backend application that acts as a proxy server for the Google Places API. This backend application can be used to forward requests to the Google Places API and handle responses. This way, the Google Places API key is kept secure on the server-side and not exposed to the frontend application.

### Prerequisites to run this application
- JDK 21
- Gradle 8.10.2
- Spring Boot 3.3.5
- Google API Key

### How to run this application
1. Clone this repository to your local machine
```bash
$ git clone https://github.com/lumberjack-programmer/google-places-demo.git
```
2. Open the project in your favorite IDE
3. Create `.env` file in the root of the project and add the following line
```bash
GOOGLE_API_KEY=<YOUR_GOOGLE_API_KEY>
```

4. Run the application from your IDE or using the following command
```bash
$ gradle bootRun
```
5. Open your browser and navigate to `http://localhost:8080/api/v1/address?input=<YOUR_ADDRESS>`
6. You should see the response from the Google Places API
7. You can also test the application using Postman or any other API testing tool such as Curl

### Example of curl command
```bash
$ curl -X GET "http://127.0.0.1:8080/api/v1/address?input=290%20Bremner%20Blvd" -H "accept: application/json"
```