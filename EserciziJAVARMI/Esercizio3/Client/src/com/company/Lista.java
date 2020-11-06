package com.company;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Lista extends Remote {
    void addUtente(String nomeUtente)  throws RemoteException;

    String removeUtente(String nomeUtente)  throws RemoteException;

    List<String> list()  throws RemoteException;
}
