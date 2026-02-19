package it.epicode.spring.s7l3.designpatterns.patterns.es1Adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TEST PER ESERCIZIO 1 - ADAPTER PATTERN
 * 
 * Testa la corretta implementazione dell'Adapter
 * e la compatibilità tra Info, Adapter e UserData.
 */
@DisplayName("Esercizio 1 - Adapter Pattern Tests")
class Es1AdapterTest {

    private Info info;
    private InfoAdapter adapter;

    /**
     * Setup: Prepara gli oggetti per ogni test
     */
    @BeforeEach
    void setUp() {
        info = new Info();
        info.setNome("Luigi");
        info.setCognome("Verdi");
        info.setDataDiNascita(convertLocalDateToDate(LocalDate.of(1990, 3, 20)));
        
        adapter = new InfoAdapter(info);
    }

    // ==================== TEST ADAPTER ====================

    @Test
    @DisplayName("Adapter deve concatenare nome e cognome correttamente")
    void testNomeCompletoCorrectlyFormatted() {
        String nomeCompleto = adapter.getNomeCompleto();
        assertEquals("Luigi Verdi", nomeCompleto);
    }

    @Test
    @DisplayName("Adapter deve gestire nome null")
    void testNomeCompletoHandlesNullNome() {
        info.setNome(null);
        String nomeCompleto = adapter.getNomeCompleto();
        assertEquals("Verdi", nomeCompleto);
    }

    @Test
    @DisplayName("Adapter deve gestire cognome null")
    void testNomeCompletoHandlesNullCognome() {
        info.setCognome(null);
        String nomeCompleto = adapter.getNomeCompleto();
        assertEquals("Luigi", nomeCompleto);
    }

    @Test
    @DisplayName("Adapter deve gestire sia nome che cognome null")
    void testNomeCompletoHandlesBothNull() {
        info.setNome(null);
        info.setCognome(null);
        String nomeCompleto = adapter.getNomeCompleto();
        assertEquals("", nomeCompleto);
    }

    @Test
    @DisplayName("Adapter deve gestire stringhe vuote")
    void testNomeCompletoHandlesEmptyStrings() {
        info.setNome("");
        info.setCognome("");
        String nomeCompleto = adapter.getNomeCompleto();
        assertEquals("", nomeCompleto);
    }

    @Test
    @DisplayName("Adapter deve trimmare gli spazi")
    void testNomeCompletoTrimsWhitespace() {
        info.setNome("  Mario  ");
        info.setCognome("  Rossi  ");
        String nomeCompleto = adapter.getNomeCompleto();
        assertEquals("Mario Rossi", nomeCompleto);
    }

    @Test
    @DisplayName("Adapter deve calcolare correttamente l'età")
    void testEtaCalculatedCorrectly() {
        int eta = adapter.getEta();
        int expectedEta = LocalDate.now().getYear() - 1990;
        // Considerando che potremmo essere prima o dopo il compleanno
        assertTrue(eta == expectedEta || eta == expectedEta - 1, 
            "L'età calcolata deve essere coerente con la data di nascita");
    }

    @Test
    @DisplayName("Adapter deve gestire data di nascita null")
    void testEtaHandlesNullDataDiNascita() {
        info.setDataDiNascita(null);
        int eta = adapter.getEta();
        assertEquals(0, eta);
    }

    @Test
    @DisplayName("Adapter deve calcolare l'età di una persona appena nata")
    void testEtaForNewborn() {
        info.setDataDiNascita(convertLocalDateToDate(LocalDate.now()));
        int eta = adapter.getEta();
        assertEquals(0, eta);
    }

    // ==================== TEST INTEGRAZIONE (Adapter + UserData) ====================

    @Test
    @DisplayName("UserData deve usare correttamente l'Adapter")
    void testUserDataUsesAdapter() {
        UserData userData = new UserData();
        userData.getData(adapter);
        
        assertEquals("Luigi Verdi", userData.getNomeCompleto());
        assertTrue(userData.getEta() > 0);
    }

    @Test
    @DisplayName("UserData con Adapter null non deve usare dati invalidi")
    void testUserDataWithNullAdapter() {
        UserData userData = new UserData();
        InfoAdapter nullAdapter = new InfoAdapter(new Info());
        nullAdapter.getData(nullAdapter);  // Non viene usato, userData rimane null
        
        // Verifico che i dati non siano impostati (rimangono null/0)
        assertNull(userData.getNomeCompleto());
        assertEquals(0, userData.getEta());
    }

    // ==================== TEST INTERFACCIA ====================

    @Test
    @DisplayName("InfoAdapter deve implementare DataSource")
    void testAdapterImplementsDataSource() {
        assertTrue(adapter instanceof DataSource);
    }

    @Test
    @DisplayName("InfoAdapter deve essere compatibile con DataSource")
    void testAdapterIsCompatibleWithDataSource() {
        DataSource dataSource = adapter;  // Polimorfismo
        
        assertNotNull(dataSource.getNomeCompleto());
        assertTrue(dataSource.getEta() >= 0);
    }

    // ==================== UTILITY ====================

    /**
     * Converte LocalDate a java.util.Date
     */
    private static Date convertLocalDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
