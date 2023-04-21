package bingoporhilos;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;


public class Productor extends Thread {
    
    private Controlador controlador;
    private Controlador valor;
    private final int[] numeros = new int [75]; 
    clientedelBingo pasoNumeros = new clientedelBingo();
    
    public Productor(Controlador x){
        
        this.controlador = x;
     
    }
    
     public void LlenarCarton(int tamaño){

        
        for(int i=0; i<tamaño;i++){
        
            numeros[i]=i;
            //System.out.println("llenando vector: "+numeros[i]);
        }
    }
    
    public void run(){
        int valor = 0;
        int [] aux = new int[75];
        int i = 0;
        int numero = 0;
        Random r = new Random();
        while(true){

            try {
                while(i < 75){
                    numero = r.nextInt(75)+1;
                    if(! Busqueda(aux,i,numero)){
                        aux[i] = numero;
                        valor = aux[i];
                        i++;
                        sleep((int) (Math.random() * 3000));
                        controlador.producir(valor);
                        pasoNumeros.valor(valor);
                         System.out.println("Numero "+ valor +" producido");
                    }
                }
                 

                sleep((int) (Math.random() * 3000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
 public static boolean Busqueda(int [] aux,int i,int numero){
     int j;
     for(j = 0; j < i;j++){
        if(numero == aux[j]){
            return true;
        } 
     }
     return false;
 }
    
}
