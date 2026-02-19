package it.epicode.spring.s7l3.designpatterns.patterns.es1Adapter;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

/**
 * ADAPTER PATTERN - InfoAdapter
 * 
 * Questa classe funge da "ponte" tra Info (Adaptee) e DataSource (Target).
 * 
 * Responsabilità:
 * 1. IMPLEMENTAZIONE: Implementa DataSource (Target) 
 *    → Permette a UserData di usarla come DataSource grazie al polimorfismo
 * 
 * 2. COMPOSIZIONE: Contiene un'istanza di Info (Adaptee)
 *    → Ha accesso ai dati originali (nome, cognome, data di nascita)
 * 
 * 3. TRADUZIONE: Adatta i metodi di Info ai metodi di DataSource
 *    → getNome() + getCognome() → getNomeCompleto()
 *    → getDataDiNascita() → getEta()
 */
public class InfoAdapter implements DataSource {
    
    // COMPOSIZIONE: Mantiene un riferimento all'Adaptee
    private final Info info;

    /**
     * Costruttore: riceve l'oggetto da adattare
     * @param info l'oggetto Info (Adaptee) da adattare
     */
    public InfoAdapter(Info info) {
        this.info = info;
    }

    /**
     * TRADUZIONE #1: getNomeCompleto()
     * 
     * Traduce la chiamata di UserData a getNomeCompleto()
     * in una richiesta a Info di nome e cognome separati.
     * 
     * Gestisce i casi di null per evitare NullPointerException.
     */
    @Override
    public String getNomeCompleto() {
        // Gestione di null per nome
        String nome = info.getNome() == null ? "" : info.getNome().trim();
        // Gestione di null per cognome
        String cognome = info.getCognome() == null ? "" : info.getCognome().trim();
        // Concatenazione e trim finale
        return (nome + " " + cognome).trim();
    }

    /**
     * TRADUZIONE #2: getEta()
     * 
     * Traduce la chiamata di UserData a getEta() (int)
     * in un calcolo preciso degli anni a partire da getDataDiNascita().
     * 
     * Utilizza java.time.Period per calcolare correttamente gli anni
     * considerando gli anni bisestili.
     */
    @Override
    public int getEta() {
        Date dataDiNascita = info.getDataDiNascita();
        
        // Gestione di null: se non c'è data di nascita, ritorna 0
        if (dataDiNascita == null) {
            return 0;
        }
        
        // Conversione da java.util.Date a java.time.LocalDate
        LocalDate birthDate = dataDiNascita.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        
        // Calcolo preciso degli anni usando Period
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
