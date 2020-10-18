package com.company;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Impiegato_Skeleton extends Thread{
    private ImpiegatoServer impiegato;

    public Impiegato_Skeleton(ImpiegatoServer impiegato) {
        this.impiegato = impiegato;
    }

    public static void main(String args[]) {
        ImpiegatoServer impiegatoServer = new ImpiegatoServer(1000, "Mario Rossi", "Via 11");
        Impiegato_Skeleton skeleton = new Impiegato_Skeleton(impiegatoServer);
        skeleton.start();
    }

    @Override
    public void run() {
        Socket socket = null;
        String metodo;
        int parametro;

        System.out.println("Attendo Connessioni");
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            socket = serverSocket.accept();

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            while (true) {
                metodo = (String) in.readObject();
                System.out.println(metodo);
                if (metodo.equals("getNome")) {
                    out.writeObject(impiegato.getNome());
                    out.flush();
                } else if (metodo.equals("getIndirizzo")) {
                    out.writeObject(impiegato.getIndirizzo());
                    out.flush();
                } else if (metodo.equals("getStipendio")) {
                    out.writeInt(impiegato.getStipendio());
                    out.flush();
                } else if (metodo.equals("aumentaStipendio")) {
                    parametro = in.readInt();
                    out.writeInt(impiegato.aumentaStipendio(parametro));
                    out.flush();
                } else break;
            }
        } catch (EOFException r) {
            System.out.println("Connessione terminata");
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            try{
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }
}
