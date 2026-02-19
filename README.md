# Design Patterns Spring Boot

## Descrizione
Repository di esercitazione per l'implementazione dei design patterns in Spring Boot.

## Pattern Implementati

### ES1 - Adapter Pattern
- Adatta interfacce incompatibili
- Converte UserData in DataSource compatibile
- Utilizzo: trasformazioni di dati tra sistemi

### ES2 - Composite Pattern
- Struttura gerarchica di oggetti
- Libro → Sezione → Pagina
- Utilizzo: organizzazione albero di documenti

### ES3 - Chain of Responsibility
- Catena di handler per processare richieste
- Gerarchia militare (Tenente → Maggiore → Colonnello → Generale)
- Utilizzo: approvazioni gerarchiche

## Configurazione
- Java 17+
- Spring Boot 3.x
- PostgreSQL 14+
- Maven 3.8+

## Installazione e Avvio
1. Clone del repository
2. Configurazione env.properties
3. mvnw clean install
4. mvnw spring-boot:run
