package client;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientLauncher {
    public final static String INET_ADDRESS = "127.0.0.1";

    public static void launchClient(int port ){
        try {
            System.out.println("Client initialise");

            //initialise
            Socket clientSocket = new Socket(INET_ADDRESS, port);
            OutputStreamWriter osw = new OutputStreamWriter(clientSocket.getOutputStream());
            BufferedWriter printer = new BufferedWriter(osw);
            Scanner reader = new Scanner(System.in);

            String line = reader.nextLine();
            System.out.println("message envoye : " + line);
            printer.append(line).append("\n");
            printer.flush();

            printer.close();
            reader.close();
            clientSocket.close();
            System.out.println("Client ferme");
        } catch (IOException IOex){
            IOex.printStackTrace();
        }
    }

    public static void main(String[] args){
        launchClient();
    }
}
