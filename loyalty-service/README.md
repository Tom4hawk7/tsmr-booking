# tsmr-loyalty Microservice

This microservice manages the accumulation and redemption of loyalty points for members. It allows users to track their points and redeem them for rewards. Below are the available endpoints for managing members, redemptions, points transactions, and rewards.

## Members

The `Members` controller tracks users within the loyalty microservice by their `memberId`, ensuring no issues with passing data.

### Endpoints:

- **Get All Members:**
    - **URL:** `http://localhost:8081/api/members`
    - **Method:** `GET`

- **Get a Specific Member (e.g., Member 2):**
    - **URL:** `http://localhost:8081/api/members/2`
    - **Method:** `GET`

- **Search for a Member (e.g., Charlie Brown):**
    - **URL:** `http://localhost:8081/api/members/search?name=Charlie+Brown`
    - **Method:** `GET`
    - **Query Params:** `name` (e.g., "Charlie Brown")

- **Create a New Member (e.g., John Doe):**
    - **URL:** `http://localhost:8081/api/members/create`
    - **Method:** `POST`
    - **Body (JSON):**
      ```json
      {
        "memberName": "John Doe",
        "joinDate": "2025-10-21",
        "status": "ACTIVE"
      }
      ```

- **Delete a Member (e.g., Member 7):**
    - **URL:** `http://localhost:8081/api/members/7`
    - **Method:** `DELETE`

## Redemptions

Manage and track point redemptions for members.

### Endpoints:

- **Create a New Redemption:**
    - **URL:** `http://localhost:8081/api/redemptions`
    - **Method:** `POST`
    - **Body (JSON):**
      ```json
      {
        "member": { "memberID": 1 },
        "reward": { "rewardId": 1 }
      }
      ```

- **Get a Specific Redemption (e.g., Redemption 1):**
    - **URL:** `http://localhost:8081/api/redemptions/1`
    - **Method:** `GET`

- **Get All Redemptions:**
    - **URL:** `http://localhost:8081/api/redemptions`
    - **Method:** `GET`

- **Get All Redemptions from a Specific Member (e.g., Member 1):**
    - **URL:** `http://localhost:8081/api/redemptions/member/1`
    - **Method:** `GET`

## Points Transactions

Track and manage points transactions.

### Endpoints:

- **Get All Points Transactions:**
    - **URL:** `http://localhost:8081/api/points-transactions`
    - **Method:** `GET`

- **Create a Points Transaction:**
    - **URL:** `http://localhost:8081/api/points-transactions`
    - **Method:** `POST`
    - **Body (JSON):**
      ```json
      {
        "memberId": 1,
        "points": 200
      }
      ```

## Rewards

Manage rewards that members can redeem with their points.

### Endpoints:

- **Get All Rewards:**
    - **URL:** `http://localhost:8081/api/rewards`
    - **Method:** `GET`

- **Create a New Reward:**
    - **URL:** 
  '''
  http://localhost:8081/api/rewards
  '''
    - **Method:** `POST`
    - **Body (JSON):**
      ```json
      {
        "name": "Free Coffee",
        "description": "Redeem 100 points for a free coffee",
        "availability": 10,
        "rewardCost": 100,
        "rewardType": "BEVERAGE",
        "validFrom": "2025-10-21T00:00:00",
        "validTo": "2025-12-31T23:59:59"
      }
      ```

---

## Notes

- **Base URL:** `http://localhost:8081`
- Ensure the appropriate service is running on the provided port before making requests.
- The JSON payloads for member creation, redemption, transaction creation, and reward creation should match the provided examples.
- Use `GET` methods to retrieve lists and individual records. `POST` is used for creating records, while `DELETE` is used for removing members.

