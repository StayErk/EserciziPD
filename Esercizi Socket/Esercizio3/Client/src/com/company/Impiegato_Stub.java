package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Impiegato_Stub implements Impiegato{
    //Lo stub si permette al Client di Invocare i metodi di Impiegato sul Server
    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;

    //Costruttore, si inizializzano le variabili di Istanza
    //Importante notare l'apertur del socket che consente la comunicazione
    public Impiegato_Stub(String host) throws IOException {
        socket = new Socket(host, 9000);
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
    }

    @Override
    public String getNome() throws Throwable {
        out.writeObject("getNome");
        out.flush();
        return (String) in.readObject();
    }

    @Override
    public String getIndirizzo() throws Throwable {
        out.writeObject("getIndirizzo");
        out.flush();
        return (String) in.readObject();
    }

    @Override
    public int getStipendio() throws Throwable {
        out.writeObject("getStipendio");
        out.flush();
        return in.readInt();
    }

    @Override
    public int aumentaStipendio(int amount) throws Throwable {
        out.writeObject("aumentaStipendio");
        out.writeInt(amount);
        out.flush();
        return in.readInt();
    }

    public void close() {
        try{
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
