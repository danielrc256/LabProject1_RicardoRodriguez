/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package labproject1_ricardorodriguez;
import java.util.Scanner;
/**
 *
 * @author rickr
 */
public class LabProject1_RicardoRodriguez {

    /**
     * @param args the command line arguments
     */
    public static Scanner input = new Scanner (System.in);
    public static void main(String[] args) {
        System.out.println("Bienvenid@ a la entrega de paquetes de DHL");
        System.out.println("");
        System.out.println("Debes hacer que Steeb 'S' entregue ");
        System.out.println("cada una de las cajas de dulce representadas con 'j'");
        System.out.println("a los almacenes de  DHL ('D, H, L'). ");
        System.out.println("Pero cuidado con los obstaculos 'X','O','o','0','^','/','|'");
        System.out.println("que te causaran un GAME OVER");
                
        // Validación de perder o ganar
        boolean ganar = false; // Verficiación de que no quedan regalos
        boolean perder= false; // Verificación de tocar algpun obstáculo
        
        // Coordenadas a eligir
        int coordenadaX = 0;
        int coordenadaY = 0;
        
        // Accion de 
        int accion = 0;
        
        // Tablero interno
        char[][] tablero = new char[24][24];
        
        // Generar el tablero
        GenerarTablero(tablero);
        
        // Imprimir el tablero
        ImprimirTablero(tablero);
        
        
        // Inicio de el juego en si
        do {
            
        } while (ganar==false||perder==false);
        
        // Mostrar ganar en consola
        if (ganar==true){
            System.out.println("Mission Accomplished");
            System.out.println("");
            ImprimirTablero(tablero);
            System.out.println("");
            System.out.println("Felicidades, ha logrado que Steeb entregue los dulceos para esta navidad!");
        }
        // Mostrar perder en consola
        if (perder==true){
            System.out.println("GAME OVER: Mission Failed");
            System.out.println("");
            ImprimirTablero(tablero);
            System.out.println("");
            System.out.println("Oops, parece que has chocado con un adorno del arbol de Navidad.");
        }
        
        
    }
    public static void ImprimirTablero(char[][]tablero){
        // Imprime el tablero
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 24; j++) {
                System.out.print("["+tablero[j][i]+"]");
            }
            System.out.println("");
        }
    }
    public static void GenerarTablero(char[][]tablero){
        // Genera todos los obstaculos y regalos en el tablero
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 24; j++) {
                System.out.print("["+tablero[j][i]+"]");
            }
            System.out.println("");
        }
    }
    public static boolean VerificarTablero(char[][]tablero){
        // Verifica que ya no hayan regalos en el tablero
        char caracter = ' ';
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 24; j++) {
                caracter = tablero[j][i];
                if (caracter=='j'){
                    return false; // Continua el juego
                }
            }
        }
        return true; // Finaliza con ganar=true
    }
    public static void MoverJugador(char[][]tablero){
        
    }
    
}
