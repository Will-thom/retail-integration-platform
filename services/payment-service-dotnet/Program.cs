var builder = WebApplication.CreateBuilder(args);

// Controllers (equivalente ao Spring @RestController)
builder.Services.AddControllers();

// Swagger (opcional, mas útil em dev)
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();

// Swagger apenas em dev
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

builder.WebHost.UseUrls("http://0.0.0.0:8083");

// Mapear controllers
app.MapControllers();

app.Run();