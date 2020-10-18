package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Logger;

public class Main {

    private static Logger logger = Logger.getLogger("logger");

    public static void main(String[] args) {
        HashMap<String, RecordRegistro> map = new HashMap<>();
        logger.info("in attesa di connessione...");
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(9000);
            while(true){
                socket = serverSocket.accept();
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                RecordRegistro rr = (RecordRegistro) in.readObject();
                if(rr.getIndirizzo() != null){
                    logger.info("stiamo nell'inserimento");
                    map.put(rr.getNome(), rr);
                } else {
                    logger.info("stiamo facendo una ricerca");
                    RecordRegistro daCercare = map.get(rr.getNome());
                    out.writeObject(daCercare);
                    out.flush();
                }
            }
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
