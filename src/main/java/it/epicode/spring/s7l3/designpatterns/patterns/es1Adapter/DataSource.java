package it.epicode.spring.s7l3.designpatterns.patterns.es1Adapter;

/**
 * TARGET INTERFACE - DataSource
 * 
 * Questa è l'interfaccia attesa dal Client (UserData).
 * UserData sa come usare questa interfaccia e chiama i suoi metodi.
 * 
 * Nel pattern Adapter, il Target rappresenta il contratto che l'Adapter
 * dovrà implementare per risultare compatibile con il Client.
 */
public interface DataSource {
    /**
     * @return il nome completo della persona
     */
    String getNomeCompleto();
    
    /**
     * @return l'età della persona in anni
     */
    int getEta();
}
