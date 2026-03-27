# 🚀 Retail Integration Platform


![Java 21](https://img.shields.io/badge/Java-21-blue)
![SAP Integration](https://img.shields.io/badge/SAP-Integration-green)
![.NET 8](https://img.shields.io/badge/.NET-8-blue)
![Docker](https://img.shields.io/badge/Docker-Compose-green)
![Docker Tests](https://img.shields.io/badge/Docker-Tests-blue)
![Plataforma de Integração de Varejo](https://img.shields.io/badge/Plataforma-Varejo-green)
![Retail Integration](https://img.shields.io/badge/Retail-Integration-blue)

Autor: Francisco Silva.


A Retail Integration Platform (Plataforma para Integração de Varejo) é uma plataforma de integração multi-serviços que simula um ambiente de backend moderno, distribuído e resiliente.


Ela inclui:

* BFF (Backend For Frontend) para orquestração de serviços
* Order Service (Java) com CRUD de pedidos e integração com ERP
* ERP Adapter (Java) simulando um SAP
* Payment Service (.NET 8) para processamento de pagamentos
* Testes via PowerShell integrados à rede Docker

![Descrição da Imagem](https://github.com/Will-thom/retail-integration-platform/raw/main/docs/architecture/microservices-integration-diagram.png)

O projeto é perfeito para simular integrações complexas, testar comunicação entre containers e validar fluxos de pedido e pagamento.



⚙️ Tecnologias

* Java 21 + Spring Boot 3.2
* .NET 8 + ASP.NET Core
* Docker & Docker Compose
* REST APIs internas (BFF ↔ Serviços)
* Flexibilidade de URLs via application.properties e variáveis de ambiente
  


🧱 Estrutura do Projeto
````
retail-integration-platform/
│
├── .gitignore
├── docker-compose.yml
├── README.md
│
├── scripts/
│   └── test-retail-docker.ps1
│
└── services/
    │
    ├── bff-java/
    │   ├── Dockerfile
    │   ├── pom.xml
    │   └── src/
    │       └── main/
    │           ├── java/
    │           │   └── com/example/bff/
    │           │       ├── controller/
    │           │       │   └── OrderController.java
    │           │       └── BffApplication.java
    │           └── resources/
    │               └── application.properties
    │
    ├── order-service-java/
    │   ├── Dockerfile
    │   ├── pom.xml
    │   └── src/
    │       └── main/
    │           ├── java/
    │           │   └── com/example/orderservice/
    │           │       ├── controller/
    │           │       │   └── OrderServiceController.java
    │           │       └── OrderServiceApplication.java
    │           └── resources/
    │               └── application.properties
    │
    ├── erp-adapter-java/
    │   ├── Dockerfile
    │   ├── pom.xml
    │   └── src/
    │       └── main/
    │           ├── java/
    │           │   └── com/example/erpadapter/
    │           │       ├── controller/
    │           │       │   └── ErpController.java
    │           │       └── ErpAdapterApplication.java
    │           └── resources/
    │               └── application.properties
    │
    └── payment-service-dotnet/
        ├── Dockerfile
        ├── payment-service-dotnet.csproj
        ├── Program.cs
        └── Controllers/
            └── PaymentsController.cs
````            



🐳 Docker Compose

Alinhamento de portas:

* Serviço	Porta Host	Porta Container
* bff	    8080	    8080
* erp-sap	8081	    8081
* order-service	8082	8082
* payment-service 8083	8083


Subir os serviços:
docker-compose up -d

Rodar o tester
docker-compose run --rm tester

O tester vai se conectar automaticamente à rede Docker e testar health checks, POST e GET de todos os serviços.

Parar todos os containers
docker-compose down



🧪 Testes

O scripts/test-retail-docker.ps1 executa:

Health checks de todos os serviços
Criação e consulta de pedidos via BFF
Envio de pedidos para ERP Adapter
Processamento de pagamentos via Payment Service
Validação de respostas esperadas



🔄 Fluxo de Pedido
BFF --> Order Service --> ERP Adapter --> Payment Service

* Cliente envia pedido para BFF
* BFF chama Order Service para criar pedido
* Order Service envia pedido para ERP Adapter
* Opcional: Payment Service processa pagamento
* Resposta retorna via BFF ao cliente



📝 Próximos Passos / Melhorias Futuras

* Implementar retry/resiliência (Resilience4j / Polly)
* Adicionar logs estruturados
* Integrar mensageria (Kafka / RabbitMQ)
* Observabilidade completa (Micrometer / Prometheus / Grafana)
* Suporte multi-tenant e autenticação


📄 Licença

MIT © 2026
