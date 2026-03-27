# =========================
# STAGE 1 - BUILD
# =========================
FROM mcr.microsoft.com/dotnet/sdk:8.0 AS build

WORKDIR /src

# 🔹 Copia apenas o csproj e restaura dependências
COPY *.csproj .
RUN dotnet restore

# 🔹 Copia todo o código do serviço (dentro da pasta payment-service-dotnet)
COPY . .
RUN dotnet publish -c Release -o /app/publish

# =========================
# STAGE 2 - RUNTIME
# =========================
FROM mcr.microsoft.com/dotnet/aspnet:8.0

WORKDIR /app

# 🔹 Copia os binários publicados do build stage
COPY --from=build /app/publish .

# 🔹 Porta alinhada com o docker-compose
EXPOSE 8083

# 🔹 EntryPoint
ENTRYPOINT ["dotnet", "payment-service-dotnet.dll"]