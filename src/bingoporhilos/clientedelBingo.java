//consumidor aqui S
package bingoporhilos;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

public class clientedelBingo extends Frame implements ActionListener {

    static Socket sfd = null;
    static DataInputStream EntradaSocket;
    static DataOutputStream SalidaSocket;
    static TextField salida;
    static TextArea entrada;
    static String texto;
    int prueba;
   

    public clientedelBingo() {
        setTitle("Chat");
        setSize(350, 200);
        salida = new TextField(30);
        salida.addActionListener(this);

        entrada = new TextArea();
        entrada.setEditable(false);

        add("South", salida);
        add("Center", entrada);
        setVisible(true);
    }

    public static void main(String[] args) {
        clientedelBingo clientedelBingo = new clientedelBingo();
        try {
            sfd = new Socket("LocalHost", 8000); // 172.20.10.5 : 8000
            EntradaSocket = new DataInputStream(new BufferedInputStream(sfd.getInputStream()));
            SalidaSocket = new DataOutputStream(new BufferedOutputStream(sfd.getOutputStream()));
            try {
                SalidaSocket.writeUTF("enoc");
                SalidaSocket.flush();
            } catch (IOException ioe) {
                System.out.println("Error: " + ioe);
            }
        } catch (UnknownHostException uhe) {
            System.out.println("No se puede acceder al servidor.");
            System.exit(1);
        } catch (IOException ioe) {
            System.out.println("Comunicación rechazada.");
            System.exit(1);
        }
        while (true) {
            try {
                String linea = EntradaSocket.readUTF();
                entrada.append(linea + "\n");
            } catch (IOException ioe) {
                System.exit(1);
            }
            
        }
    }

    public void actionPerformed(ActionEvent e) {
        Controlador hilo = new Controlador(75);
        Productor r = new Productor(hilo);
        Consumidor c = new Consumidor(hilo);
        r.start();
        
        texto = salida.getText();
        salida.setText("");
        try {
            SalidaSocket.writeUTF("enoc: " + prueba);
            SalidaSocket.flush();
            c.start();
        } catch (IOException ioe) {
            System.out.println("Error: " + ioe);
        }
    }

    public boolean handleEvent(Event e) {
        if ((e.target == this) && (e.id == Event.WINDOW_DESTROY)) {
            if (sfd != null) {
                try {
                    sfd.close();
                } catch (IOException ioe) {
                    System.out.println("Error: " + ioe);
                }
                this.dispose();
            }
        }
        return true;
    }

    private void Systeam(String pause) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public void valor(int i){
        prueba = i;
    }
}
