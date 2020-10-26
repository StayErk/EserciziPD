package com.company;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Logger;

public class HelloClient {
    static Logger logger = Logger.getLogger("global");

    public static void main(String args[]) {
        try {
            logger.info("Cercando l'oggetto remoto...");
            Hello obj = (Hello) Naming.lookup("rmi://localhost/HelloServer");
            logger.info("Trovato! Invochiamone il metodo...");
            String risultato = obj.dimmiQualcosa("Pippo");
            System.out.println("Ricevuto: " + risultato);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
