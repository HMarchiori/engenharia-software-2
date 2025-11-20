# TDE #1 : Microsserviços de Câmbio
# Prof. Dr. Miguel Gomes Xavier
## Engenharia de Software II

---
## Nomes:

Eduardo dos Santos Barbosa\
Gabriel Fernandes Dalbem\
Henrique Corrales Marchiori

---

## Link do Repositório:

https://github.com/HMarchiori/engenharia-software-2

---

## 1. Subindo o Ambiente

Na raiz do repositório:

```bash
docker compose up --build
```

Isso irá:

- Construir todas as imagens dos serviços
- Subir o Eureka Naming Server
- Inicializar todos os microsserviços
- Registrar cada serviço no discovery server

---

## 2. Testando os Serviços

Após o ambiente estar em execução, é possível validar utilizando `curl`.

### 2.1 Healthchecks

Currency Conversion:
```bash
curl "http://localhost:8765/currency-report/health"
```

Currency History:
```bash
curl "http://localhost:8765/currency-history/health"
```

---

### 2.2 Endpoints Principais

Currency Report:
```bash
curl "http://localhost:8765/currency-report/quote?from=USD&to=BRL"
```

Currency History:
```bash
curl "http://localhost:8765/currency-history/history?from=USD&to=BRL"
```

---

## 3. Requisitos

- Docker
- Docker Compose
- Porta 8761 liberada (Eureka)
- Portas 8000, 8100, 8080, 8082, 8765 disponíveis

---
