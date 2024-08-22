
# Filter API

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-000000?style=for-the-badge&logo=lombok&logoColor=white)
![Jakarta](https://img.shields.io/badge/Jakarta-007396?style=for-the-badge&logo=jakarta&logoColor=white)

## 📚 Sobre

**Filter API** é uma API RESTful desenvolvida em Java utilizando o Spring Boot, que permite a filtragem dinâmica de dados para entidades de Orders e Employees através de parâmetros de consulta (`RequestParams`).

## 🚀 Tecnologias

Este projeto foi desenvolvido com as seguintes tecnologias:

- **Java**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Web**
- **Spring Boot DevTools**
- **Validation**
- **PostgreSQL**
- **Lombok**
- **Jakarta EE**

## 📂 Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── filterapi/
│   │               ├── controller/
│   │               │   ├── EmployeeController.java
│   │               │   └── OrderController.java
│   │               ├── model/
│   │               │   ├── EmployeeModel.java
│   │               │   └── OrderModel.java
│   │               ├── repository/
│   │               │   ├── EmployeeRepository.java
│   │               │   └── OrderRepository.java
│   │               ├── service/
│   │               │   ├── EmployeeService.java
│   │               │   └── OrderService.java
│   │               ├── specification/
│   │               │   ├── EmployeeSpecification.java
│   │               │   └── OrderSpecification.java
│   │               └── FilterApiApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
```

## 📑 Endpoints

### Orders

- **GET** `/orders` - Lista todos os pedidos com filtros opcionais:
  - `orderNumber`: Número do pedido.
  - `customerName`: Nome do cliente.
  - `status`: Status do pedido.
  - `minAmount`: Valor mínimo do pedido.
  - `maxAmount`: Valor máximo do pedido.
  - `startDate`: Data de início (formato: `YYYY-MM-DD`).
  - `endDate`: Data de término (formato: `YYYY-MM-DD`).

**Exemplo de Requisição:**
```sh
GET http://localhost:8080/orders?customerName=John&status=completed&minAmount=100&maxAmount=500
```

### Employees

- **GET** `/employees` - Lista todos os funcionários com filtros opcionais:
  - `firstName`: Primeiro nome do funcionário.
  - `department`: Departamento do funcionário.
  - `minSalary`: Salário mínimo.
  - `hiredAfter`: Data de contratação após (formato: `YYYY-MM-DD`).
  - `hiredBefore`: Data de contratação antes (formato: `YYYY-MM-DD`).
  - `active`: Status de atividade (`true` ou `false`).

**Exemplo de Requisição:**
```sh
GET http://localhost:8080/employees?firstName=Jane&department=Sales&minSalary=50000&active=true
```

## 🛠️ Como Executar

1. **Clone o repositório:**
   ```sh
   git clone https://github.com/Leo-Raposo/filter_API.git
   ```

2. **Navegue até o diretório do projeto:**
   ```sh
   cd filter_API
   ```

3. **Configure o PostgreSQL:**
  - Crie um banco de dados no PostgreSQL.
  - Configure as credenciais no arquivo `application.properties`.

4. **Execute a aplicação:**
   ```sh
   ./mvnw spring-boot:run
   ```

5. **Teste a API no Postman ou outra ferramenta de sua escolha.**

## 🧑‍💻 Autor

Desenvolvido por [Leonardo Raposo](https://github.com/Leo-Raposo) - Desenvolvedor Java e entusiasta de tecnologia.

---

**[Acesse o repositório](https://github.com/Leo-Raposo/filter_API) para mais detalhes.**

![GitHub last commit](https://img.shields.io/github/last-commit/Leo-Raposo/filter_API)
