/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package problema_filosofos;

import java.util.concurrent.Semaphore;
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
     *
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
     *
     */
    public void comer() {
        System.out.println("Filosofo " + comensal + " esta comiendo");
        contador++;
        System.out.println("El filosofo " + comensal + " ha comido un total de " + contador + " veces");
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
     *
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

    private void pararComer() {
        parar = true;
    }
    
    
}
