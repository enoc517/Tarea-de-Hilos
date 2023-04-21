package bingoporhilos;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumidor extends Thread {
    
    private Controlador controlador;
    
    public Consumidor(Controlador x){
        
        this.controlador = x;
     
    }
    
    public void run(){
    
        
        int p=0;
        while(true){
            try {
                int valor =  this.controlador.consumir();
                System.out.println("Numero "+valor+" consumido");
                //controlador.produci(valor);
                sleep((int) (Math.random() * 3000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    } 
}
