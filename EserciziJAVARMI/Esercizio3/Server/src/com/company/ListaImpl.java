package com.company;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ListaImpl extends UnicastRemoteObject implements Lista {
    static Logger logger = Logger.getLogger("global");
    private static ArrayList<String> listaUtenti;

    protected ListaImpl() throws RemoteException {
        super();
        listaUtenti = new ArrayList<>();
    }

    public static void main(String[] args) {
        System.setSecurityManager(new SecurityManager());
        try {
            logger.info("Creazione dell'oggetto remoto");
            ListaImpl obj = new ListaImpl();
            logger.info("Rebind");
            Naming.rebind("ListaServer", obj);
            logger.info("Pronto!");
        } catch(RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUtente(String nomeUtente) throws RemoteException{
        synchronized (listaUtenti) {
            listaUtenti.add(nomeUtente);
        }
    }

    @Override
    public String removeUtente(String nomeUtente)  throws RemoteException{
        synchronized (listaUtenti) {
            listaUtenti.remove(nomeUtente);
            if(!listaUtenti.contains(nomeUtente)) {
                return nomeUtente + " rimosso correttamente";
            } else {
                return nomeUtente + " non rimosso";
            }
        }
    }

    @Override
    public List<String> list()   throws RemoteException{
        synchronized (listaUtenti) {
            return listaUtenti;
        }
    }
}
