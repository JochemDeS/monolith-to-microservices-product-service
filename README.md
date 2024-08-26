# Product service

De product service is een microservice die verantwoordelijk is voor het beheren van producten in de webshop. De service biedt endpoints 
voor het opvragen van producten.

## Functionaliteiten

- Producten opvragen
- Producten filteren op:
  - categorie
  - merk
  - minimum- en maximumprijs
- Producten sorteren
- Specifiek product opvragen op basis van product ID

## API Spec

Je kan de API specificatie bekijken in de Swagger UI door de applicatie te starten en 
naar [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html) te navigeren.

Er is geen authenticatie vereist om de endpoints te gebruiken.

## Implementeren microservice

De volgende stappen zijn genomen om deze microservice te implementeren:

1. **Kopiëren bestaande product functionaliteit**

    Doordat ik gebruik heb gemaakt van de hexagonale architectuur in de monolithische applicatie, was het eenvoudig om de product functionaliteit
    te kopiëren naar een nieuwe microservice. De product functionaliteit was al gescheiden van de rest van de applicatie.

2. **Toevoegen Spring Boot dependencies**
