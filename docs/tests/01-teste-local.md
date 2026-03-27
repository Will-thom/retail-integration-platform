📝 Checklist: Teste completo local - V1


1️⃣ Payment Service (.NET)


Entrar na pasta do serviço
cd services/payment-service-dotnet
Rodar build e teste local
dotnet build
dotnet run

Verificar logs
Deve aparecer algo como:
Now listening on: http://0.0.0.0:8083
Application started. Press Ctrl+C to shut down.


2️⃣ ERP-SAP (Java)

Entrar na pasta do serviço
cd services/erp-adapter-java
Build do serviço
mvn clean package
Rodar serviço na porta 8081
java -jar target/erp-adapter-java-0.0.1-SNAPSHOT.jar --server.port=8081


3️⃣ Order Service (Java)

Entrar na pasta do serviço
cd services/order-service-java
Build do serviço
mvn clean package
Rodar serviço na porta 8082
java -jar target/order-service-java-0.0.1-SNAPSHOT.jar --server.port=8082


4️⃣ BFF (Java)

Entrar na pasta do BFF
cd services/bff-java
Build do serviço
mvn clean package
Rodar serviço na porta 8080
java -jar target/bff-java-0.0.1-SNAPSHOT.jar --server.port=8080


Testar TODOS os endpoints:

Abra o PowerShell na pasta do projeto.
Execute:
.\test-retail.ps1


5️⃣ Observações finais

Ordem de start local: Payment → ERP → Order → BFF (BFF depende dos outros serviços)
Todas as portas estão fixas e alinhadas com o Docker: 8080, 8081, 8082, 8083
Depois que todos funcionarem localmente, é só subir o docker-compose up --build e testar fluxo ponta a ponta