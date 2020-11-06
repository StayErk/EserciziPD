package com.company;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Logger;

public class Client {

    static Logger logger = Logger.getLogger("global");

    public static void main(String[] args) {
        try {
            logger.info("Cercando l'oggetto remoto");
            Lista lista = (Lista) Naming.lookup("rmi://localhost/ListaServer");
            logger.info("Invochiamone i metodi");
            if(args[0].equals("add")) {
                lista.addUtente(args[1]);
            } else if (args[0].equals("remove")) {
                logger.info(lista.removeUtente(args[1]));
            } else if (args[0].equals("list")) {
                logger.info(lista.list() + "");
            } else {
                System.out.println("Usage: add");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
