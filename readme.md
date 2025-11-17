## Comandos

docker compose down
docker compose up --build 


## Trabalho Prático — Microsserviços + DevOps (EngSoft 2)

Este projeto implementa uma arquitetura de microsserviços usando Spring Boot e Spring Cloud. A arquitetura é composta por 6 serviços:

naming-server: (Etapa 3) Servidor Eureka para Service Discovery.

api-gateway: (Etapa 4) Ponto de entrada único que roteia todas as requisições.

currency-report: (Req 1) Microsserviço que fornece dados de cotação.

currency-history: (Req 2) Microsserviço que fornece histórico de cotações.

currency-exchange-service: Serviço de câmbio.

currency-conversion-service: Serviço de conversão.

# Etapa 7.1: Como Subir o Ambiente

Este projeto usa Dockerfiles single-stage, o que significa que os ficheiros .jar de cada serviço devem ser construídos antes de iniciar o Docker Compose.

Pré-requisitos:

Java 17+

Maven

Docker e Docker Compose

Passo 1: Construir os Microsserviços (Maven)

Você deve compilar cada um dos 6 projetos Java para gerar os ficheiros .jar na pasta target de cada um.

Para cada uma das 6 pastas de serviço (ex: ./naming-server, ./api-gateway, etc.), execute:

# Se você tem o Maven Wrapper (mvnw)
./mvnw clean package -DskipTests

# Se você tem o Maven instalado globalmente
mvn clean package -DskipTests


Passo 2: Iniciar os Containers (Docker Compose)

Com todos os .jar construídos, agora você pode usar o docker compose para construir as imagens e iniciar todos os 6 containers.

Na raiz do projeto (onde está o compose.yaml), execute:

docker compose up --build


O ambiente estará pronto quando todos os serviços-cliente (como api-gateway, currency-report, etc.) se registrarem no Naming Server.

## Etapa 7.2: Como Testar (via cURL)

Toda a comunicação DEVE ser feita através do API Gateway, que está a rodar na porta 8765.

Painéis de Monitoramento:

Dashboard do Naming Server (Eureka): http://localhost:8761/

Saúde do API Gateway (Actuator): http://localhost:8765/actuator/health

Teste do currency-report (Req 1)

Os testes são feitos no caminho /currency-report/ através do Gateway.

# Teste de Saúde
curl http://localhost:8765/currency-report/health

# Obter Cotação (Exemplo do trabalho)
curl "http://localhost:8765/currency-report/quote?from=USD&to=BRL"


Teste do currency-history (Req 2)

Os testes são feitos no caminho /currency-history/ através do Gateway.

# Teste de Saúde
curl http://localhost:8765/currency-history/health

# Obter Histórico (Exemplo do trabalho)
curl "http://localhost:8765/currency-history/history?from=USD&to=BRL"


Teste dos Outros Serviços

# Teste do currency-exchange (ex: /currency-exchange/from/USD/to/BRL)
curl http://localhost:8765/currency-exchange-service/actuator/health

# Teste do currency-conversion (ex: /currency-conversion/from/USD/to/BRL/quantity/10)
curl http://localhost:8765/currency-conversion-service/actuator/health
