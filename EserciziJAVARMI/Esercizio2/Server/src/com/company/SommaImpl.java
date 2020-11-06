package com.company;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

public class SommaImpl extends UnicastRemoteObject implements Somma{
    static Logger logger = Logger.getLogger("global");
    private static Integer somma;
    public SommaImpl() throws RemoteException {
        somma = 0;
    }

    public static void main(String[] args) {
        System.setSecurityManager(new SecurityManager());
        SommaImpl obj = null;
        try {
            obj = new SommaImpl();
            logger.info("Rebind");
            Naming.rebind("SommaServer", obj);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        logger.info("Pronto!");
    }



    @Override
    public int sommaERitorna(int valore) throws RemoteException {
        synchronized (somma) {
            somma += valore;
            return somma;
        }
    }
}
