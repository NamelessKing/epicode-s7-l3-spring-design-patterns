package it.epicode.spring.s7l3.designpatterns.patterns.es1Adapter;

/**
 * CLIENT CLASS - UserData
 * 
 * Questa è la classe che utilizza l'interfaccia DataSource.
 * UserData conosce solo DataSource e i suoi metodi.
 * Non sa nulla di Info, né di come ottenere i dati.
 * 
 * Nel pattern Adapter:
 * - UserData è il Client
 * - getData(DataSource ds) rappresenta la dipendenza da DataSource
 * 
 * Grazie all'Adapter, UserData può lavorare sia con implementazioni
 * "native" di DataSource, che con Info (tramite l'Adapter).
 */
public class UserData {
    private String nomeCompleto;
    private int eta;

    /**
     * Riceve un DataSource e ne estrae i dati.
     * 
     * Questo metodo accetta cualsiasi implementazione di DataSource,
     * incluso l'Adapter che "traduce" Info a DataSource.
     * 
     * @param ds un'implementazione di DataSource (potrebbe essere un Adapter)
     */
    public void getData(DataSource ds) {
        nomeCompleto = ds.getNomeCompleto();
        eta = ds.getEta();
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public int getEta() {
        return eta;
    }
}
