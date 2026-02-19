package it.epicode.spring.s7l3.designpatterns.patterns;

/**
 * SPRING COMMAND LINE RUNNERS - Sistema di esecuzione
 * 
 * I runner degli esercizi sono implementati come Spring CommandLineRunner (@Component).
 * Vengono eseguiti automaticamente all'avvio dell'applicazione.
 * 
 * UTILIZZO:
 * 
 * 1. Esegui l'applicazione normalmente (tutti i runner partono):
 *    mvnw.cmd spring-boot:run
 * 
 * 2. Esegui solo un esercizio specifico via program arguments:
 *    mvnw.cmd spring-boot:run -Dspring-boot.run.arguments="1"
 *    (esegue solo Es1AdapterRunner)
 * 
 * 3. Da IDE (IntelliJ):
 *    - Run > Edit Configurations
 *    - Program arguments: 1
 *    (per eseguire solo l'esercizio 1)
 * 
 * I runner sono definiti in ogni package es1Adapter/, es2Composite/, es3Chain/
 * e implementano l'interfaccia PatternCommandLineRunner.
 */
public class PatternsLauncher {
    
    // Questo file serve solo come documentazione.
    // I veri runner sono le classi @Component che implementano CommandLineRunner.
}

