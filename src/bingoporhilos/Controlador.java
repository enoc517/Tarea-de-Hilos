package bingoporhilos;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Controlador {

    private int [] numeros;
    private int siguiente;
    private boolean vacido;
    private boolean lleno;
    public int w;
    
    public Controlador(int tamaño){
        w=tamaño;
        this.numeros = new int[tamaño];
        this.siguiente = 0;
        this.vacido = true;
        this.lleno = false;
        
    }
    
   
    
    public synchronized int consumir(){
    
        int r=0;
        while(this.vacido){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        siguiente--;
        this.lleno=false;
        if(siguiente==0){
            
            this.vacido = true;
        }
        
        notifyAll();

       r++;
        return this.numeros[this.siguiente];
    }
    
    public synchronized void producir(int n){
        
        while(this.lleno){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        numeros[siguiente] = n;
        siguiente++;
        this.vacido = false;
        
        if(siguiente == this.numeros.length){
            
            this.lleno=true;                      
        }
        
        notifyAll();
        
    }
    
}
