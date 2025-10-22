# Querying / Booking operations

## Setting up microservice

First, make sure current directory is the root level of the tsmr-booking folder.
Then run the following command: `mvn spring-boot:run`

## Interacting with microservice

### Create

#### Customer
Create a customer
```
Invoke-RestMethod -Uri 'http://localhost:8080/customers' -Method Post ` -ContentType 'application/json' ` -Body '{"name":"Alice","email":"alice@example.com"}'
```

#### Flight
Create a flight
```
Invoke-RestMethod -Uri 'http://localhost:8080/flights' -Method Post -ContentType 'application/json' -Body '{"airline":"AcmeAir","flightNumber":"AC123","location":"LAX","destination":"JFK","depart":"2025-12-01T09:00:00","arrival":"2025-12-01T17:00:00"}'
```

#### Ticket
Create a ticket
```
Invoke-RestMethod -Uri 'http://localhost:8080/tickets' -Method Post -ContentType 'application/json' `
  -Body '{"customer":{"id":1},"flight":{"id":1},"seat":"12A","bookingDate":"2025-12-01"}'
```


### Read

#### Customer
Get all customers
```
Invoke-RestMethod -Uri 'http://localhost:8080/customers' -Method Get
```
Get a specific customer by id
```
Invoke-RestMethod -Uri 'http://localhost:8080/customers/1' -Method Get
```

#### Flight
Get all flights
```
Invoke-RestMethod -Uri 'http://localhost:8080/flights' -Method Get
```

Get a specific flight by id
```
Invoke-RestMethod -Uri 'http://localhost:8080/flights/1' -Method Get
```

#### Ticket
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


### Update

#### Customer
Update a specific customer by id
```
Invoke-RestMethod -Uri 'http://localhost:8080/customers/1' -Method Put `
  -ContentType 'application/json' `
  -Body '{"name":"John","email":"john@example.com"}'
```

#### Flight
Update a specific flight by id
```
Invoke-RestMethod -Uri 'http://localhost:8080/flights/1' -Method Put -ContentType 'application/json' -Body '{"airline":"Jetstar","flightNumber":"BOC3","location":"LAX","destination":"JFK","depart":"2025-12-01T10:00:00","arrival":"2025-12-01T18:00:00"}'
```

#### Ticket
Update a specific ticket by id
```
Invoke-RestMethod -Uri 'http://localhost:8080/tickets/1' -Method Put -ContentType 'application/json' -Body '{"seat":"13D", "bookingDate":"2025-04-06"}'
```

Update a specific ticket by customer id and flight id
```
Invoke-RestMethod -Uri 'http://localhost:8080/tickets/customer/1/flight/1' -Method Put -ContentType 'application/json' -Body '{"seat":"26T", "bookingDate":"2025-04-02"}'
```


### Delete

#### Customer
Delete a specific customer by id
```
Invoke-RestMethod -Uri 'http://localhost:8080/customers/1' -Method Delete
```

#### Flight
Delete a specific flight by id
```
Invoke-RestMethod -Uri 'http://localhost:8080/flights/1' -Method Delete
```

#### Ticket
Delete a specific ticket by id
```
Invoke-RestMethod -Uri 'http://localhost:8080/tickets/1' -Method Delete
```
