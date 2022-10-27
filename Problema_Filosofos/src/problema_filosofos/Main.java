/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package problema_filosofos;

import java.util.concurrent.Semaphore;

/**
 *
 * @author usuario
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Creamos un semaforo
        Semaphore semaforo = new Semaphore(1);
        //Creamos la mesa con 5 palillos y le pasamos además el semaforo
        Mesa m = new Mesa(5, semaforo);
        for (int i = 1; i <= 5; i++) {
            //Creamos los 5 filosofos
            Filosofo f = new Filosofo(m, i, 0);
            //Empezamos los hilos
            f.start();
        }
    }
    
}
