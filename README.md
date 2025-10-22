# Setting up microservice

First, make sure current directory is the root level of the tsmr-booking folder.
Run all services at once: `mvn spring-boot:run`.
Run specific service: `mvn -pl module-name spring-boot:run`.

Modules:

- auth-service
- booking-service
- loyalty-service
- query-service

# query-service

## Create

### Customer

Create a customer

```
Invoke-RestMethod -Uri 'http://localhost:8080/customers' -Method Post ` -ContentType 'application/json' ` -Body '{"name":"Alice","email":"alice@example.com"}'
```

### Flight

Create a flight

```
Invoke-RestMethod -Uri 'http://localhost:8080/flights' -Method Post -ContentType 'application/json' -Body '{"airline":"AcmeAir","flightNumber":"AC123","location":"LAX","destination":"JFK","depart":"2025-12-01T09:00:00","arrival":"2025-12-01T17:00:00"}'
```

### Ticket

Create a ticket

```
Invoke-RestMethod -Uri 'http://localhost:8080/tickets' -Method Post -ContentType 'application/json' `
  -Body '{"customer":{"id":1},"flight":{"id":1},"seat":"12A","bookingDate":"2025-12-01"}'
```

## Read

### Customer

Get all customers

```
Invoke-RestMethod -Uri 'http://localhost:8080/customers' -Method Get
```

Get a specific customer by id

```
Invoke-RestMethod -Uri 'http://localhost:8080/customers/1' -Method Get
```

### Flight

Get all flights

```
Invoke-RestMethod -Uri 'http://localhost:8080/flights' -Method Get
```

Get a specific flight by id

```
Invoke-RestMethod -Uri 'http://localhost:8080/flights/1' -Method Get
```

### Ticket

Get all tickets

```
Invoke-RestMethod -Uri 'http://localhost:8080/tickets' -Method Get
```

Get a specific ticket by id

```
Invoke-RestMethod -Uri 'http://localhost:8080/tickets/1' -Method Get
```

Get a specific ticket by customer id

```
Invoke-RestMethod -Uri 'http://localhost:8080/tickets/customer/1' -Method Get
```

Get a specific ticket by flight id

```
Invoke-RestMethod -Uri 'http://localhost:8080/tickets/flight/1' -Method Get
```

Get a specific ticket by customer and flight id

```
Invoke-RestMethod -Uri 'http://localhost:8080/tickets/customer/1/flight/1' -Method Get
```

## Update

### Customer

Update a specific customer by id

```
Invoke-RestMethod -Uri 'http://localhost:8080/customers/1' -Method Put `
  -ContentType 'application/json' `
  -Body '{"name":"John","email":"john@example.com"}'
```

### Flight

Update a specific flight by id

```
Invoke-RestMethod -Uri 'http://localhost:8080/flights/1' -Method Put -ContentType 'application/json' -Body '{"airline":"Jetstar","flightNumber":"BOC3","location":"LAX","destination":"JFK","depart":"2025-12-01T10:00:00","arrival":"2025-12-01T18:00:00"}'
```

### Ticket

Update a specific ticket by id

```
Invoke-RestMethod -Uri 'http://localhost:8080/tickets/1' -Method Put -ContentType 'application/json' -Body '{"seat":"13D", "bookingDate":"2025-04-06"}'
```

Update a specific ticket by customer id and flight id

```
Invoke-RestMethod -Uri 'http://localhost:8080/tickets/customer/1/flight/1' -Method Put -ContentType 'application/json' -Body '{"seat":"26T", "bookingDate":"2025-04-02"}'
```

## Delete

### Customer

Delete a specific customer by id

```
Invoke-RestMethod -Uri 'http://localhost:8080/customers/1' -Method Delete
```

### Flight

Delete a specific flight by id

```
Invoke-RestMethod -Uri 'http://localhost:8080/flights/1' -Method Delete
```

### Ticket

Delete a specific ticket by id

```
Invoke-RestMethod -Uri 'http://localhost:8080/tickets/1' -Method Delete
```




# Booking Service 

!!!!Make sure other services are not currently running!!!!
(End existing springboot sessions)

NOTE: This service required openAI api key to be run 

### set openai API key using 

```bash
export OPENAI_API_KEY="<YOUR_KEY>"
```

Start booking service using 

```bash
 mvn -pl booking-service  spring-boot:run #run in terminal 
 ```

## Testing using Postman 

### Create a flight

Copy the URL below into a POST request in Postman

```bash
  http://localhost:8080/api/flights #GET/POST URL for flights
```

In postman, click Body -> raw -> change type to JSON in drop down menu (default might be Text)

copy sample flight object data into request body and send POST request

```json
{
  "airline": "QANTAS",
  "capacity": 170,
  "price": 200,
  "location": "MELBOURNE",
  "destination": "BRISBANE",
  "depart": "2025-03-27T10:30:00",
  "arrival": "2025-04-05T12:05:00"
}
```

The same URL can be used for a GET request to check that the flight was created

### Create a customer

Copy URL below into a new POST request in Postman

```bash
http://localhost:8080/api/customers #GET/POST URL for customers
```

Follow same instructions as above to

Copy the sample customer object data into request body and send POST request

```json
{
  "name":"John Doe",
  "dob":"1995-05-15",
  "phone":"0400123456",
  "email":"Customer@example.com"
}
```
Same URL can be used to GET customers and check creation

### Create Booking using Endpoint

A booking can be made using a POST method on endpoint:
(No request body is required)

```bash
http://localhost:8080/api/bookings?customerId=1&flightId=1 #change customerID/FlightID to create bookings for different customers or flights
```

### Create Booking using Chatbot

The chatbot can be interacted with using a POST method at endpoint URL:

```bash
http://localhost:8080/api/assistant/chat
```
Type prompt to chatbot into Body -> raw (make sure text type is on 'Text' not 'JSON')

### Example Sequence

Make sure to create flight/customer objects before hand

```bash
Can you show me all available flights to brisbane?

-> chatbot retrieves all flights to brisbane

Can you book this flight for customer 1

-> "Flight to brisbane has been booked"

Can you show me all bookings

-> Here is the list of all bookings:
  1. Customer: John Doe, Destination: BRISBANE, Airline: QANTAS, Booking Date: 23rd October 2025

```


