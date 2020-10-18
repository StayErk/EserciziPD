package com.company;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class Main {

    private static Logger logger = Logger.getLogger("private static Logger logger = Logger.getLogger(\"logger\");");
    private static BufferedReader in = null;

    public static void main(String[] args) {
        in = new BufferedReader(new InputStreamReader(System.in));
        Socket socket = null;
        String cmd;
        try {
            while( !(cmd = ask("8==D")).equals("quit")){
                if(cmd.equals("inserisci")){
                    System.out.println("inserisci i dati ");
                    String nome = ask("nome: ");
                    String indirizzo = ask("indirizzo: ");
                    RecordRegistro rec = new RecordRegistro(nome, indirizzo);
                    socket = new Socket("localhost", 9000);
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    out.writeObject(rec);
                    out.flush();
                    socket.close();
                } else if(cmd.equals("cerca")) {
                    System.out.println("Inserire il nome per la ricerca");
                    String nome = ask("Nome: ");
                    RecordRegistro rec = new RecordRegistro(nome, null);
                    socket = new Socket("localhost", 9000);
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    out.writeObject(rec);
                    out.flush();
                    RecordRegistro result = (RecordRegistro) in.readObject();
                    if(result ==  null) {
                        System.out.println("Il record " + nome + " non è presente");
                    } else {
                        System.out.println(result);
                    }
                    socket.close();
                } else {
                    System.out.println("Strunz devi inserire cerca o inserisci o quit");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String ask(String prompt) throws IOException {
        System.out.println(prompt + " ");
        return (in.readLine());
    }
}
