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
public class Mesa {
    //Creamos un array de booleanos con tenedores
    private boolean[] tenedores;
    //Creamos un semaforo
    private Semaphore semaforo;
    
    /**
     * Constructor con argumentos 
     *
     * @param numTenedores
     * @param semaforo
     */
    public Mesa(int numTenedores, Semaphore semaforo) {
        this.tenedores = new boolean[numTenedores];
        this.semaforo = semaforo;
    }
    
    /**
     * Funcion para obtener el tenedor de la izquierda
     *
     * @param i
     * @return i
     */
    public int tenedorIzquierda(int i) {
        return i;
    }
    
    /**
     * Funcion para devolver el tenedor derecho
     *
     * @param i
     * @return Devuelve o la ultima posicion del tenendor - 1 si i es 0 o devuelve el anterior
     */
    public int tenedorDerecha(int i) {
        if (i == 0) {
            return this.tenedores.length - 1;
        } else {
            return i - 1;
        }
    }
    
    /**
     * Funcion para coger tenedores
     *
     * @param comensal
     */
    public void cogerTenedores(int comensal) {
        while (tenedores[tenedorIzquierda(comensal)] || tenedores[tenedorDerecha(comensal)]) {
            try {
                //Primera parte de la sección crítica, añadimos el semaforo
                semaforo.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(Mesa.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Cuando los adquirimos entonces ponemos ambos tenedores a true que significa que el filosofo ya los tiene
            tenedores[tenedorIzquierda(comensal)] = true;
            tenedores[tenedorDerecha(comensal)] = true;
        }
    }
    
    /**
     * Funcion para dejar los tenendores
     *
     * @param comensal
     */
    public void dejarTenedores(int comensal) {
        //2da parte de la seccion critica para dejar los tenedores
        //Esta funcion simplemente pone los tenedores a false
        tenedores[tenedorIzquierda(comensal)] = false;
        tenedores[tenedorDerecha(comensal)] = false;
        semaforo.release();
    }
}
