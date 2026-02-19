package it.epicode.spring.s7l3.designpatterns.patterns.es1Adapter;

import it.epicode.spring.s7l3.designpatterns.patterns.shared.PatternCommandLineRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * SPRING COMMAND LINE RUNNER PER ESERCIZIO 1 - ADAPTER PATTERN
 * 
 * Implementa CommandLineRunner di Spring: viene eseguito automaticamente
 * all'avvio dell'applicazione.
 * 
 * Questo runner dimostra l'utilizzo del pattern Adapter
 * risolvendo il problema di incompatibilità tra Info e DataSource.
 */
@Component
@Order(1)
public class Es1AdapterRunner implements PatternCommandLineRunner {

    @Override
    public int getExerciseNumber() {
        return 1;
    }

    @Override
    public String getExerciseName() {
        return "Esercizio 1 - Adapter Pattern";
    }

    /**
     * Implementazione di CommandLineRunner.run()
     * Viene chiamato automaticamente all'avvio di Spring
     * 
     * @param args argomenti da riga di comando (opzionali)
     */
    @Override
    public void run(String... args) {
        // Verifica se è stato passato un argomento specifico
        if (shouldRunThisExercise(args)) {
            executeExercise();
        }
    }

    /**
     * Controlla se questo runner deve essere eseguito
     * Se nessun argomento è passato, esegue tutto
     * Se è passato un numero, esegue solo se corrisponde
     */
    private boolean shouldRunThisExercise(String... args) {
        if (args.length == 0) {
            // Nessun argomento: esegui tutto
            return true;
        }
        try {
            int exerciseNumber = Integer.parseInt(args[0]);
            return exerciseNumber == getExerciseNumber();
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Esegue la demo dell'esercizio
     */
    private void executeExercise() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("  " + getExerciseName());
        System.out.println("=".repeat(70) + "\n");

        // STEP 1: Creiamo un'istanza di Info (Adaptee)
        System.out.println("STEP 1: Creazione di Info (Adaptee - incompatibile con DataSource)");
        System.out.println("-".repeat(70));
        Info info = new Info();
        info.setNome("Mario");
        info.setCognome("Rossi");
        info.setDataDiNascita(convertLocalDateToDate(LocalDate.of(1995, 5, 15)));

        System.out.println("✓ Info creato:");
        System.out.println("  - Nome: " + info.getNome());
        System.out.println("  - Cognome: " + info.getCognome());
        System.out.println("  - Data di nascita: " + info.getDataDiNascita());
        System.out.println("  - Implementa DataSource? NO ❌\n");

        // STEP 2: Creiamo l'Adapter
        System.out.println("STEP 2: Creazione dell'Adapter (mediatore)");
        System.out.println("-".repeat(70));
        InfoAdapter adapter = new InfoAdapter(info);
        System.out.println("✓ Adapter creato:");
        System.out.println("  - Implementa DataSource? SI ✓");
        System.out.println("  - Contiene riferimento a Info? SI ✓");
        System.out.println("  - Traduce i metodi? SI ✓\n");

        // STEP 3: UserData utilizza l'Adapter
        System.out.println("STEP 3: UserData utilizza l'Adapter tramite l'interfaccia DataSource");
        System.out.println("-".repeat(70));
        UserData userData = new UserData();
        userData.getData(adapter);  // Passing adapter as DataSource

        System.out.println("✓ UserData ha ricevuto i dati dall'Adapter:");
        System.out.println("  - Nome completo: " + userData.getNomeCompleto());
        System.out.println("  - Età: " + userData.getEta() + " anni");
        System.out.println("\n");

        // STEP 4: Dimostriamo la gestione dei null
        System.out.println("STEP 4: Test di gestione dei null");
        System.out.println("-".repeat(70));
        Info infoConNull = new Info();
        infoConNull.setNome("Luigi");
        infoConNull.setCognome(null);  // Cognome assente
        infoConNull.setDataDiNascita(null);  // Data di nascita assente

        InfoAdapter adapterConNull = new InfoAdapter(infoConNull);
        UserData userDataConNull = new UserData();
        userDataConNull.getData(adapterConNull);

        System.out.println("✓ Info con valori null:");
        System.out.println("  - Nome completo (cognome null): '" + userDataConNull.getNomeCompleto() + "'");
        System.out.println("  - Età (data di nascita null): " + userDataConNull.getEta() + "\n");

        // CONCLUSIONE
        System.out.println("CONCLUSIONE");
        System.out.println("-".repeat(70));
        System.out.println("✓ UserData ha potuto usare Info senza modificare il proprio codice");
        System.out.println("✓ Info ha mantenuto la sua interfaccia originale");
        System.out.println("✓ L'Adapter ha tradotto i metodi in modo trasparente");
        System.out.println("✓ Principio Open-Closed: nessuna classe è stata modificata");
        System.out.println("\n");
    }

    /**
     * Utility: Converte LocalDate a java.util.Date
     */
    private static Date convertLocalDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
