# =========================================
# 🚀 Teste completo Retail Integration Platform (Docker Network)
# =========================================

# -------------------------------
# 1️⃣ Payloads
# -------------------------------
$orderPayload = '{
    "customer": "Ana Pereira",
    "items": [
        { "productId": "111", "quantity": 1 }
    ],
    "total": 200
}'

$paymentPayload = '{
    "orderId": "12345",
    "amount": 200
}'

# -------------------------------
# 2️⃣ Health Checks (via Docker internal names)
# -------------------------------
Write-Host "`n=== Health Checks via Docker Network ===`n"

Write-Host "BFF:"
irm http://bff:8080/orders/health

Write-Host "`nERP Adapter:"
irm http://erp-sap:8081/erp-sap/health

Write-Host "`nOrder Service:"
irm http://order-service:8082/orders/health

Write-Host "`nPayment Service:"
irm http://payment-service:8083/payments/health

# -------------------------------
# 3️⃣ Testar Order Service via Docker Network
# -------------------------------
Write-Host "`n=== Order Service Test POST via Docker Network ===`n"
$orderResponse = irm http://order-service:8082/orders -Method POST -Body $orderPayload -ContentType "application/json"
$orderResponse | Format-List

Write-Host "`nOrder Service GET by ID (simulado 12345) ===`n"
irm http://order-service:8082/orders/12345 | Format-List

# -------------------------------
# 4️⃣ Testar Payment Service via Docker Network
# -------------------------------
Write-Host "`n=== Payment Service Test POST via Docker Network ===`n"
$paymentResponse = irm http://payment-service:8083/payments -Method POST -Body $paymentPayload -ContentType "application/json"
$paymentResponse | Format-List

Write-Host "`nPayment Service GET ===`n"
irm http://payment-service:8083/payments | Format-List

# -------------------------------
# 5️⃣ Testar fluxo completo via BFF (interno Docker)
# -------------------------------
Write-Host "`n=== Fluxo Completo via BFF (Docker Network) ===`n"

Write-Host "BFF Health:"
irm http://bff:8080/orders/health

Write-Host "`nCriar pedido via BFF (chama Order + ERP):"
$bffCreate = irm http://bff:8080/orders -Method POST -Body $orderPayload -ContentType "application/json"
$bffCreate | Format-List

Write-Host "`nBuscar pedidos via BFF:"
$bffGet = irm http://bff:8080/orders
$bffGet | Format-List

Write-Host "`n=== Fim do teste completo via Docker Network ===`n"