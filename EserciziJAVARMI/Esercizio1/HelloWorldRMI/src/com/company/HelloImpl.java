package com.company;

import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

public class HelloImpl extends UnicastRemoteObject implements Hello {
    static Logger logger = Logger.getLogger("Global");

    public HelloImpl() throws RemoteException {
    }

    public String dimmiQualcosa(String daChi) throws RemoteException {
        logger.info("Sto salutando: " + daChi);
        return "Ciao";
    }

    public static void main(String[] args) {
        System.setSecurityManager(new SecurityManager());
        try {
            logger.info("Creo l'oggetto remoto");
            HelloImpl obj = new HelloImpl();
            logger.info("Effettuo il rebind");
            Naming.rebind("HelloServer", obj);
            logger.info("Pronto!");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
