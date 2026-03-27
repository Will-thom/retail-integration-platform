# ================================
# 🚀 Teste completo Retail Integration Platform
# ================================

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

$erpPayload = '{
    "customer": "Teste",
    "total": 10
}'

$paymentPayload = '{
    "orderId": "12345",
    "amount": 200
}'

# -------------------------------
# 2️⃣ Health Checks
# -------------------------------
Write-Host "`n=== Health Checks ===`n"

Write-Host "BFF:"
irm http://localhost:8080/orders/health

Write-Host "`nERP Adapter:"
irm http://localhost:8081/erp-sap/health

Write-Host "`nOrder Service:"
irm http://localhost:8082/orders/health

Write-Host "`nPayment Service:"
irm http://localhost:8083/payments/health

# -------------------------------
# 3️⃣ Testar ERP Adapter isolado
# -------------------------------
Write-Host "`n=== ERP Adapter Test POST ===`n"
$erpResponse = irm http://localhost:8081/erp-sap/orders -Method POST -Body $erpPayload -ContentType "application/json"
$erpResponse | Format-List

# -------------------------------
# 4️⃣ Testar Order Service isolado
# -------------------------------
Write-Host "`n=== Order Service Test POST ===`n"
$orderResponse = irm http://localhost:8082/orders -Method POST -Body $orderPayload -ContentType "application/json"
$orderResponse | Format-List

Write-Host "`nOrder Service GET by ID (simulado 12345) ===`n"
irm http://localhost:8082/orders/12345 | Format-List

# -------------------------------
# 5️⃣ Testar Payment Service isolado
# -------------------------------
Write-Host "`n=== Payment Service Test POST ===`n"
$paymentResponse = irm http://localhost:8083/payments -Method POST -Body $paymentPayload -ContentType "application/json"
$paymentResponse | Format-List

Write-Host "`nPayment Service GET ===`n"
irm http://localhost:8083/payments | Format-List

# -------------------------------
# 6️⃣ Testar fluxo completo via BFF
# -------------------------------
Write-Host "`n=== Fluxo Completo via BFF ===`n"

Write-Host "BFF Health:"
irm http://localhost:8080/orders/health

Write-Host "`nCriar pedido via BFF (chama Order + ERP):"
$bffCreate = irm http://localhost:8080/orders -Method POST -Body $orderPayload -ContentType "application/json"
$bffCreate | Format-List

Write-Host "`nBuscar pedidos via BFF:"
$bffGet = irm http://localhost:8080/orders
$bffGet | Format-List

Write-Host "`n=== Fim do teste completo ===`n"