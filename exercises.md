# Esercizi S7/L3 - Design Patterns

## Esercizio #1 (Adapter)

Le classi `UserData` e `Info` sono incompatibili tra loro: scrivere il codice che implementa il pattern **Adapter** per renderle compatibili. In particolare:

- `UserData` chiama `getNomeCompleto`, mentre `Info` mette a disposizione `getNome` e `getCognome`.
- `UserData` chiama `getEta`, mentre `Info` mette a disposizione `getDataDiNascita`.
- `Info` non implementa l’interfaccia `DataSource`.

```java
public class UserData {
    private String nomeCompleto;
    private int eta;

    public void getData(DataSource ds) {
        nomeCompleto = ds.getNomeCompleto();
        eta = ds.getEta();
    }
}
```

```java
public class Info {
    private String nome;
    private String cognome;
    private Date dataDiNascita;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }

    public Date getDataDiNascita() { return dataDiNascita; }
    public void DataDiNascita(Date dataDiNascita) { this.dataDiNascita = dataDiNascita; }
}
```

```java
public interface DataSource {
    public String getNomeCompleto();
    public int getEta();
}
```

## Esercizio #2 (Composite)

Modellare questo dominio:

- Un libro e composto da pagine, eventualmente organizzate in sezioni. Ogni sezione puo contenere sottosezioni (una o piu) e pagine semplici.
- E possibile stampare una pagina singola, una sezione o l’intero libro.
- Il libro ha una lista di autori.
- Il libro ha un prezzo.
- Ogni sezione ha un numero di pagine che e dato dalla somma delle pagine dei suoi sottoelementi.
- Deve essere possibile chiedere al libro il numero totale di pagine.

## Esercizio #3 (Chain of Responsibility)

Realizzare le classi che modellano la seguente gerarchia militare (dal grado piu basso al piu alto):

- Tenente
- Capitano
- Maggiore
- Colonnello
- Generale

Valgono le seguenti regole:

- Ogni ufficiale conosce il proprio responsabile.
- Ogni ufficiale svolge una funzione diversa.
- Ogni ufficiale ha uno stipendio diverso.
- Ogni ufficiale e collocato gerarchicamente in modo diverso.

Gli ufficiali percepiscono i seguenti stipendi:

- Tenente: 1000
- Capitano: 2000
- Maggiore: 3000
- Colonnello: 4000
- Generale: 5000

Si realizzi un’applicazione utilizzando il pattern **Chain of Responsibility**, che dato un importo verifichi quale ufficiale percepisce almeno tale importo.
