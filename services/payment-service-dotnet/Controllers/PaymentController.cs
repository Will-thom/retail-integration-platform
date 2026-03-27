using Microsoft.AspNetCore.Mvc;

namespace payment_service_dotnet.Controllers
{
    [ApiController]
    [Route("payments")]
    public class PaymentController : ControllerBase
    {
        // ❤️ Health check (bom pra Docker / k8s depois)
        [HttpGet("health")]
        public IActionResult Health()
        {
            return Ok(new
            {
                status = "UP",
                service = "payment-service",
                timestamp = DateTime.UtcNow
            });
        }

        // 🔎 GET simples pra debug/listagem mock
        [HttpGet]
        public IActionResult GetPayments()
        {
            return Ok(new[]
            {
                new {
                    id = Guid.NewGuid(),
                    status = "PAYMENT_APPROVED",
                    amount = 100,
                    currency = "USD"
                }
            });
        }

        // 💰 POST - processamento de pagamento
        [HttpPost]
        public IActionResult ProcessPayment([FromBody] PaymentRequest request)
        {
            if (request == null || request.Amount <= 0)
            {
                return BadRequest(new { error = "Invalid payment request" });
            }

            return Ok(new
            {
                status = "PAYMENT_APPROVED",
                transactionId = Guid.NewGuid(),
                amount = request.Amount,
                currency = request.Currency,
                processedAt = DateTime.UtcNow
            });
        }
    }

    // 📦 DTO (evita usar object genérico)
    public class PaymentRequest
    {
        public decimal Amount { get; set; }
        public string Currency { get; set; } = "USD";
    }
}