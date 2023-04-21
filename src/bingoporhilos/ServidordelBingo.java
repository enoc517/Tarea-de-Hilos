package bingoporhilos;
//hacer vector numero aqui
//productorS
import java.io.*;
import java.net.*;
import java.util.*;

public class ServidordelBingo extends Thread {

    public static Vector usuarios = new Vector();

    public static void main(String args[]) {
        ServerSocket sfd = null;
        try {
            sfd = new ServerSocket(8000);

            System.out.println("ip: " + sfd.getLocalPort());
        } catch (IOException ioe) {
            System.out.println("Comunicación rechazada." + ioe);
            System.exit(1);
        }

        while (true) {
            try {
                Socket nsfd = sfd.accept();
                System.out.println("Conexion aceptada de: " + nsfd.getInetAddress());
                FludodelBingo flujo = new FludodelBingo(nsfd);
                Thread t = new Thread(flujo);
                t.start();
            } catch (IOException ioe) {
                System.out.println("Error: " + ioe);
            }
        }
    }
}
