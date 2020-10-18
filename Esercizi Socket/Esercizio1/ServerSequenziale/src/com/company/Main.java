package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Main {

    private static Logger logger = Logger.getLogger("global");

    public static void main(String[] args) {

        Socket socket = null;

        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            logger.info("server partito");
            socket = serverSocket.accept();
            logger.info("ho accettato la connessione");
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            String nome = (String) in.readObject();
            logger.info("ricevuto " + nome);
            out.writeObject("Hello " + nome);
            out.flush();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
