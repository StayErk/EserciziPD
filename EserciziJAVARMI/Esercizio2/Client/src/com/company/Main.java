package com.company;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Logger;

public class Main {
    static Logger logger = Logger.getLogger("global");

    public static void main(String[] args) {
        try {
            logger.info("Sto cercando l'oggetto remoto");
            Somma somma = (Somma) Naming.lookup("rmi://localhost/SommaServer");
            logger.info("Trovato. Invoco il metodo somma");
            int risultato = somma.sommaERitorna(Integer.parseInt(args[0]));
            logger.info("Il risultato della somma attuale è: " + risultato);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }  catch (NotBoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            logger.severe(args[0] + " Not a Number");
        }

    }
}
