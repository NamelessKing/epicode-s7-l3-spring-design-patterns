package it.epicode.spring.s7l3.designpatterns.patterns.es1Adapter;

import java.util.Date;

/**
 * ADAPTEE CLASS - Info
 * 
 * Questa è la classe che contiene i dati ma ha un'interfaccia incompatibile
 * con quello che UserData (Client) si aspetta.
 * 
 * Info ha i dati necessari:
 * - nome e cognome (separati)
 * - data di nascita
 * 
 * MA:
 * - Non implementa DataSource
 * - Non ha il metodo getNomeCompleto() (ha getNome() e getCognome() separati)
 * - Non ha il metodo getEta() (ha solo getDataDiNascita())
 * 
 * È qui che entra in gioco l'Adapter: tradurrà questi metodi nel formato richiesto.
 */
public class Info {
    private String nome;
    private String cognome;
    private Date dataDiNascita;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }
}
