/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package problema_filosofos;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Filosofo extends Thread {
    private Mesa mesa;
    private int comensal;
    private int indiceComensal;
    private int contador;
    private boolean parar = false;

    /**
     * Constructor con argumentos
     *
     * @param mesa
     * @param comensal
     * @param contador
     */
    public Filosofo(Mesa mesa, int comensal, int contador) {
        this.mesa = mesa;
        this.comensal = comensal;
        this.indiceComensal = comensal - 1;
        this.contador = contador;
    }
    
    /**
     * Funcion de pensar. Simula que el filosofo está pensando
     */
    public void pensar() {
        System.out.println("Filosofo " + comensal + " esta pensando");
        try {
            sleep((long) (Math.random() * 4000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Funcion de comer. Simula que el filosofo come
     */
    public void comer() {
        System.out.println("Filosofo " + comensal + " esta comiendo");
        //Aumentamos el contador para cada vez que se llama la funcion
        contador++;
        System.out.println("El filosofo " + comensal + " ha comido un total de " + contador + " veces");
        //Este if es para parar el bucle o si no iria infinitamente cuando llega a 10 se llama a una funcion que hace que pare el hilo
        if (contador == 10) {
            pararComer();
        }
        try {
            sleep((long) (Math.random() * 4000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Funcion principal del hilo. Hace en bucle lo siguiente
     * Primero el filosofo piensa, coge los tenendores
     * Cuando tiene los tenedores, entonces come
     * Y por ultimo cuando termina de comer suelta los tenedores y vuelve a pensar
     */
    @Override
    public void run() {
        
        while (parar == false) {
            this.pensar();
            this.mesa.cogerTenedores(this.indiceComensal);
            this.comer();
            System.out.println("Filosofo deja de comer, tenedores libres: " + (this.mesa.tenedorIzquierda(this.indiceComensal) + 1) + ", " + (this.mesa.tenedorDerecha(this.indiceComensal) + 1));
            this.mesa.dejarTenedores(this.indiceComensal);
        }
    }
    /*
    * Funcion para que el hilo pare 
    */
    private void pararComer() {
        parar = true;
    }
    
    
}
