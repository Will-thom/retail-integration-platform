(altere os *.properties para apontar para rede docker)

Subir os serviços principais (BFF, Order, ERP, Payment) no fundo:
docker-compose up -d

O -d significa “detach”, ou seja, eles sobem em segundo plano.


Todos os containers ficam na mesma rede Docker criada pelo Compose, então conseguem se comunicar pelos nomes (bff, order-service, erp-sap, payment-service).
Executar o tester em outro console quando quiser:

docker-compose run --rm tester

--rm → remove o container tester automaticamente após terminar.


Ele vai usar a rede Docker do Compose e conectar-se diretamente aos serviços pelo nome do container.
Você pode rodar o tester quantas vezes quiser, sem precisar subir os serviços de novo.


Ver os logs dos serviços (opcional):

docker-compose logs -f bff
docker-compose logs -f order-service

Útil se quiser acompanhar o que o BFF ou o Order Service estão respondendo durante o teste.

Parar e remover todos os containers quando terminar:

docker-compose down