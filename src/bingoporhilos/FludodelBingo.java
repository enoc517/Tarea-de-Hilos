package bingoporhilos;

import java.net.*;
import java.io.*;
import java.util.*;

public class FludodelBingo extends Thread {

    Socket nsfd;
    DataInputStream FlujoLectura;
    DataOutputStream FlujoEscritura;

    public FludodelBingo(Socket sfd) {
        nsfd = sfd;
        try {
            FlujoLectura = new DataInputStream(new BufferedInputStream(sfd.getInputStream()));
            FlujoEscritura = new DataOutputStream(new BufferedOutputStream(sfd.getOutputStream()));
        } catch (IOException ioe) {
            System.out.println("IOException(Flujo): " + ioe);
        }
    }

    public void run() {
        broadcast(nsfd.getInetAddress() + "> se ha conectado");
        ServidordelBingo.usuarios.add((Object) this);
        while (true) {
            try {
                String linea = FlujoLectura.readUTF();

                if (linea.contains(":") && !linea.equals("")) {
                    linea = linea.split(":")[0] + " > " + linea.split(":")[1];
                    broadcast(linea);
                }

            } catch (IOException ioe) {
                ServidordelBingo.usuarios.removeElement(this);
                broadcast(nsfd.getInetAddress() + "> se ha desconectado");
                break;
            }
        }
    }

    public void broadcast(String mensaje) {
        synchronized (ServidordelBingo.usuarios) {
            Enumeration e = ServidordelBingo.usuarios.elements();
            while (e.hasMoreElements()) {
                FludodelBingo f = (FludodelBingo) e.nextElement();

                try {
                    synchronized (f.FlujoEscritura) {
                        f.FlujoEscritura.writeUTF(mensaje);
                        f.FlujoEscritura.flush();
                    }
                } catch (IOException ioe) {
                    System.out.println("Error: " + ioe);
                }
            }
        }
    }
}
