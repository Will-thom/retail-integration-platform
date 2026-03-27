.\test-retail.ps1

=== Health Checks ===

BFF:
BFF is running 🚀

ERP Adapter:
ERP Adapter (SAP) is running 🚀

Order Service:
Order Service is running 🚀

Payment Service:


=== ERP Adapter Test POST ===

status service         timestamp
------ -------         ---------                   
UP     payment-service 2026-03-27T02:27:39.8373706Z




sapOrderId  : 1ed40fdf-4890-4494-abad-c7ee8fbaceb9
processedAt : 2026-03-27T02:27:40.085742700Z
received    : @{customer=Teste; total=10}
status      : RECEIVED_BY_ERP




=== Order Service Test POST ===



erpResponse : @{status=RECEIVED_BY_ERP; processedAt=2026-03-27T02:27:40.172317200Z; sapOrderId=09b7a1b4-ca96-40cc-87ea-0d844dfb81bb; received=}
order       : @{customer=Ana Pereira; items=System.Object[]; total=200; orderId=9f6621a2-8628-43d4-83ee-efe0b2531350;
createdAt=2026-03-27T02:27:40.170806700Z}




Order Service GET by ID (simulado 12345) ===
[BUG TO FIX]
irm : O servidor remoto retornou um erro: (404) Não Localizado.
No C:\Users\silva-dev\Downloads\retail-integration-platform\test-retail.ps1:58 caractere:1
+ irm http://localhost:8082/orders/12345 | Format-List
+ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    + CategoryInfo          : InvalidOperation: (System.Net.HttpWebRequest:HttpWebRequest) [Invoke-RestMethod], WebException
    + FullyQualifiedErrorId : WebCmdletWebResponseException,Microsoft.PowerShell.Commands.InvokeRestMethodCommand


=== Payment Service Test POST ===



status        : PAYMENT_APPROVED
transactionId : 7f30c409-8d95-4a51-85d4-77300948e62a
amount        : 200
currency      : USD
processedAt   : 2026-03-27T02:27:40.6649096Z




Payment Service GET ===



id       : 62e78c55-c71f-4b72-8adf-0e386cd90bf9
status   : PAYMENT_APPROVED
amount   : 100
currency : USD




=== Fluxo Completo via BFF ===

BFF Health:
BFF is running 🚀

Criar pedido via BFF (chama Order + ERP):


erpResponse : @{status=RECEIVED_BY_ERP; processedAt=2026-03-27T02:27:40.917766300Z; sapOrderId=d7f432ab-308d-4982-b26a-793fdf575a9a; received=}
order       : @{customer=Ana Pereira; items=System.Object[]; total=200; orderId=6df0cbcb-1674-4533-97f9-f71942829835;
createdAt=2026-03-27T02:27:40.917766300Z}




Buscar pedidos via BFF:


customer  : Ana Pereira
items     : {@{productId=111; quantity=1}}
total     : 200
orderId   : 6df0cbcb-1674-4533-97f9-f71942829835
createdAt : 2026-03-27T02:27:40.917766300Z

customer  : Ana Pereira
items     : {@{productId=111; quantity=1}}
total     : 200
orderId   : 9f6621a2-8628-43d4-83ee-efe0b2531350
createdAt : 2026-03-27T02:27:40.170806700Z




=== Fim do teste completo ===