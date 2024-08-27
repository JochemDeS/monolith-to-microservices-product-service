# Product service

De Product Service is een microservice die verantwoordelijk is voor het beheer van producten binnen de webshop. 
Deze service biedt API-endpoints voor het opvragen, filteren, en sorteren van producten, evenals het opvragen van specifieke producten 
op basis van hun product-ID.

## Functionaliteiten

- **Producten opvragen**
- **Producten filteren op:**
  - Categorie
  - Merk
  - Minimum- en maximumprijs
- **Producten sorteren**
- **Specifiek product opvragen op basis van product ID**

## API Spec

De API-specificatie is te bekijken via de Swagger UI. Start de applicatie en navigeer naar [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html).

Voor het gebruik van de endpoints is geen authenticatie vereist.

## Implementeren microservice

De implementatie van deze microservice is uitgevoerd in de volgende stappen:

### 1. Kopiëren bestaande productfunctionaliteit

Dankzij de toepassing van de hexagonale architectuur in de monolithische applicatie, was het eenvoudig om de productfunctionaliteit 
over te zetten naar een nieuwe microservice. De productfunctionaliteit was al goed gescheiden van de rest van de applicatie.

### Applicatie configuratie

Het is belangrijk om in de `src/main/resources/application.yml` in te stellen dat de microservice op een andere poort draait dan de monolithische applicatie.
De microservice draait momenteel op poort 8081.

### Database configuratie

De Product Service maakt gebruik van een eigen database, wat vereist dat een nieuwe database wordt aangemaakt.

1. **Database aanmaken:** Kopieer het `docker-compose.yml` bestand uit de monolithische applicatie en pas de poort aan naar **5433**, 
omdat elke microservice zijn eigen database nodig heeft die op een andere poort draait. De database-URL is `jdbc:postgresql://localhost:5433/postgres`.
2. **Configuratie:** Zorg ervoor dat de databaseconfiguratie in het `application.yml` bestand in de `src/main/resources` map overeenkomt met de 
nieuwe database-instellingen.
3. **Docker:** Zorg ervoor dat Docker Desktop draait. Navigeer naar de root van het project in de terminal en voer het volgende commando uit:
    ```shell
    docker compose up -d
    ```
4. **Database schema's migreren:** Kopieer de Flyway-migratiebestanden (migraties 1 tot 6) vanuit de monolithische applicatie naar de 
`src/main/resources/db/migration` map van de microservice.
5. **Schema's toepassen:** Start de applicatie om de schema's automatisch aan de nieuwe database toe te voegen.

### Inter-servicecommunicatie opzetten

Om de microservice te integreren met de monolithische applicatie, moet de inter-servicecommunicatie worden opgezet. 
Dit wil zeggen dat andere microservices in staat zijn om data op te vragen van de microservice.

Hiervoor heb ik een extra endpoint toegevoegd dat een lijst van product ID's accepteert en de bijbehorende producten teruggeeft.

### Proxy/API-Gateway configuratie

Nu de microservice operationeel is, moet je je proxy of API-gateway configureren om de microservice aan te roepen in plaats van de monolithische applicatie.
