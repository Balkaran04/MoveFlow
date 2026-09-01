# MoveFlow

MoveFlow è un mini-WMS che permette di gestire materiali, ubicazioni, movimentazioni, utenti e dashboard di magazzino.

## Avvio

Avviare il database con:

```bash
docker compose up -d
```

Poi avviare l'applicazione con:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=docker
```

Aprire:

```text
http://localhost:8080
```

## Login iniziale

```text
Username: admin
Password: admin
```

## Test

Per eseguire i test:

```bash
./mvnw clean test
```