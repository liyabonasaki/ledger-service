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

## 🚀 Getting Started

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/yourusername/ledger-service.git
cd ledger-service
