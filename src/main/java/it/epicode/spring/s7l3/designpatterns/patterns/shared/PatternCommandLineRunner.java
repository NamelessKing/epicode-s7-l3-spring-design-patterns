package it.epicode.spring.s7l3.designpatterns.patterns.shared;

import org.springframework.boot.CommandLineRunner;

/**
 * INTERFACCIA PER TUTTI I RUNNER DEGLI ESERCIZI (Spring CommandLineRunner)
 * 
 * Estende CommandLineRunner di Spring per permettere l'esecuzione
 * degli esercizi all'avvio dell'applicazione.
 * 
 * Ogni esercizio avrà il suo runner che implementa questa interfaccia.
 */
public interface PatternCommandLineRunner extends CommandLineRunner {
    
    /**
     * Ritorna il nome dell'esercizio (es. "Esercizio 1 - Adapter")
     */
    String getExerciseName();
    
    /**
     * Ritorna il numero dell'esercizio
     */
    int getExerciseNumber();
}
