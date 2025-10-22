how to start it 
docker-compose up -d 
mvn clean install


then run TravelBookingUserServiceApplication.java

then in postman 
to signup
POST http://localhost:8080/api/auth/signup
body:
{
  "email": "alice@example.com",
  "password": "secure123",
  "fullName": "Alice Smith"
}
or something like that

to signin
POST http://localhost:8080/api/auth/signin

{
  "email": "alice@example.com",
  "password": "secure123"
}
