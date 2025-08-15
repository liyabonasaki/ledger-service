# Ledger Service

A simple **Spring Boot REST API** for managing account balances and performing ledger transactions.  
Supports **account creation**, **balance retrieval**, and **fund transfers** between accounts.

---

## 📌 Features

- Create accounts with an initial balance.
- Retrieve account balances.
- Perform transfers between accounts.
- Prevent overdrafts with validation.
- Uses in-memory H2 database (data resets on restart).
- Ready-to-run Spring Boot project.

---

## 🛠 Tech Stack

- **Java 17+**
- **Spring Boot 3+**
- **Spring Data JPA**
- **H2 Database**
- **Maven**
- **Lombok** (for boilerplate reduction)

---

## Project Structure

src
├── main
│   ├── java/com/sun/ledger_service
│   │    ├── controller      # REST Controllers
│   │    ├── model           # Entity classes
│   │    ├── repository      # Spring Data JPA Repositories
│   │    ├── service         # Business logic services
│   │    └── dto             # Data Transfer Objects
│   └── resources
│        ├── application.yml
│       
└── test
└── java/...            # Unit and Integration tests

## 🚀 Getting Started

###  Clone the Repository

``
git clone https://github.com/yourusername/ledger-service.git
cd ledger-service
``

### Manual Build & Run

```aidl
mvn clean install
mvn spring-boot:run

```
### Running with docker (Recommended)
Create a Docker network
```aidl
docker network create fintech-network
```

Build the Docker image:
```aidl
docker build -t ledger-service .
```

Run the container inside the network:
```aidl
docker run -d \
  --name ledger-service \
  --network fintech-network \
  -p 8081:8081 \
  ledger-service

```

Verify it’s running:
```aidl
http://localhost:8081/health
```
###  API Endpoints
Below are example curl requests for testing each endpoint.

Create Account

```aidl
curl -X POST http://localhost:8081/accounts \
-H "Content-Type: application/json" \
-d '{"initialBalance":1000}'
```


Get Account Details

```aidl
curl -X GET http://localhost:8081/accounts/1

```

Perform a Transfer
````aidl
curl -X POST http://localhost:8081/ledger/transfer \
  -H "Content-Type: application/json" \
  -d '{"transferId":"tx-123","fromAccountId":1,"toAccountId":2,"amount":200}'

````

Health Check

```aidl
curl -X GET http://localhost:8081/health

```