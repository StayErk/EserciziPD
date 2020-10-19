package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Server extends Thread{

    private static Logger logger = Logger.getLogger("Global");
    private static int numInstances = 0;
    private Socket socket;


    public static void main(String[] args) {
        try {
            ServerSocket serverSocket  = new ServerSocket(9000);

            while(true){
                Server serverThread = new Server(serverSocket.accept());
                serverThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Server(Socket acceptedSocket) {
        this.socket = acceptedSocket;
        numInstances++;
    }

    @Override
    public void run() {
        logger.info("Accettata connessione socket num " + numInstances);
        try {

            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            String nome = (String) in.readObject();
            logger.info("input dal client: " + nome);
            out.writeObject("Hello " + nome);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                numInstances--;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
