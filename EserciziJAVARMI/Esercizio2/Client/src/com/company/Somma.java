package com.company;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Somma extends Remote {
    int sommaERitorna(int valore) throws RemoteException;
}
