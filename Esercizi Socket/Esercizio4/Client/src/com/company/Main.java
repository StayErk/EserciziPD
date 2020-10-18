package com.company;

import java.util.logging.Logger;

public class Main {
    static Logger logger = Logger.getLogger("global");

    public static void main(String[] args) {
        try {
            Impiegato impiegato = new Impiegato_Stub("localhost");
            System.out.println("nome: " + impiegato.getNome());
            System.out.println("Indirizzo: " + impiegato.getIndirizzo());
            System.out.println("Stipendio: " + impiegato.getStipendio());
            System.out.println("Aumento Stipendio: " + impiegato.aumentaStipendio(100));
            ((Impiegato_Stub) impiegato).close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
