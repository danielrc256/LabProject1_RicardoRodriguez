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
        System.out.println("Pero cuidado con los obstaculos 'X','O','o','0','^','/','\','|'");
        System.out.println("que te causaran un GAME OVER");
        
        // Validación de perder o ganar
        boolean ganar = false; // Verficiación de que no quedan regalos
        boolean perder= false; // Verificación de tocar algún obstáculo
        
        // Posicion del jugador
        int posCol = 12;
        int posFil = 3;
        
        // Tablero
        char[][] tablero = new char[24][24];
        
        // Generar el tablero
        tablero=GenerarTablero(tablero);
        
        // Imprimir el tablero
        ImprimirTablero(tablero);
        System.out.println("");
        
        // Validaciones de las cajas
        boolean tieneCaja=false;
        
        // Comandos del usuario
        String comandos = "";
        
        
        // Inicio de el juego en si
        do {
            System.out.println("");
            System.out.println("Comandos: ");
            System.out.println("U:Arriba");
            System.out.println("D:Abajo");
            System.out.println("L:Izquierda");
            System.out.println("R:Derecha");
            System.out.println("F:Recoger/Poner caja");
            comandos=ValidacionComandos(tablero);
            tablero=MoverJugador(tablero,comandos, posCol, posFil);
            
            ImprimirTablero(tablero);
            break;
        } while (ganar==false||perder==false);
        
        // Mostrar ganar en consola
        if (ganar==true){
            System.out.println("Mission Accomplished");
            System.out.println("");
            ImprimirTablero(tablero);
            System.out.println("");
            System.out.println("Felicidades, ha logrado que Steeb entregue los dulces para esta navidad!");
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
    public static char[][] MoverJugador(char[][]tablero, String comandos, int posCol, int posFil){
        char caracter=' ';
        for (int i = 0; i <= comandos.length()-1; i+=2) {
            caracter=comandos.charAt(i);
            switch (caracter){
                case 'U':
                    if (posFil>0){
                        posFil-=1;
                    }
                    break;
                case 'D':
                    if (posFil<23){
                        posFil+=1;
                    }
                    break;
                case 'L':
                    if (posCol>0){
                        posCol-=1;
                    }
                    break;
                case 'R':
                    if (posCol<23){
                        posCol+=1;
                    }
                    break;
                case 'F':
            }
        }
        return tablero;
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
    public static char[][] GenerarTablero(char[][]tablero){
        /*
        tablero[columna][fila]='caracter';
        tablero[][]='';
        */
        
        // Genera todos los obstaculos y regalos en el tablero
        
        //Espacios
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 24; j++) {
                tablero[j][i]=' ';
            }
        }
        // X
        tablero[1][1]='X';
        tablero[1][2]='x';
        tablero[1][3]='x';
        tablero[2][3]='x';
        tablero[3][1]='x';
        tablero[3][2]='x';
        tablero[3][3]='x';
        
        tablero[20][1]='x';
        tablero[21][1]='x';
        tablero[22][1]='x';
        tablero[23][1]='x';
        
        tablero[1][11]='x';
        tablero[1][12]='x';
        tablero[2][11]='x';
        tablero[3][11]='x';
        tablero[3][12]='x';
        
        tablero[23][8]='x';
        tablero[23][9]='x';
        tablero[23][10]='x';
        tablero[22][8]='x';
        tablero[22][10]='x';
        
        tablero[3][20]='x';
        tablero[3][21]='x';
        tablero[4][21]='x';
        
        tablero[21][18]='x';
        tablero[21][19]='x';
        tablero[20][19]='x';
        
        // DHL
        tablero[2][2]='D';
        tablero[23][0]='H';
        tablero[2][12]='L';
        tablero[22][9]='D';
        tablero[4][20]='H';
        tablero[20][18]='L';
        
        // \
        tablero[5][12]='/';
        tablero[4][13]='/';
        tablero[6][11]='/';
        tablero[7][10]='/';
        tablero[8][9]='/';
        tablero[9][8]='/';
        tablero[11][6]='/';
        
        // /
        tablero[12][6]='\\';
        tablero[13][7]='\\';
        tablero[14][8]='\\';
        tablero[15][9]='\\';
        tablero[16][10]='\\';
        tablero[17][11]='\\';
        tablero[19][13]='\\';
        tablero[18][12]='\\';
        
        // -
        for (int i = 4; i <=19; i++) {
            tablero[i][14]='-';
        }
        tablero[12][14]=' ';
        tablero[8][14]=' ';
        tablero[4][14]='-';
        
        // O
        tablero[7][12]='O';
        tablero[12][7]='O';
        tablero[11][12]='O';
        tablero[14][13]='O';
        
        // 0
        tablero[6][13]='0';
        tablero[9][10]='0';
        tablero[16][13]='0';
        
        // o
        tablero[10][9]='o';
        tablero[10][13]='o';
        tablero[12][11]='o';
        tablero[14][11]='o';
        
        // ^
        tablero[8][11]='^';
        tablero[10][11]='^';
        tablero[13][12]='^';
        
        // |
        tablero[11][15]='|';
        tablero[11][16]='|';
        tablero[11][17]='|';
        tablero[12][16]='|';
        tablero[12][17]='|';
        
        // j
        tablero[9][12]='j';
        tablero[12][13]='j';
        tablero[11][8]='j';
        tablero[11][10]='j';
        tablero[14][9]='j';
        tablero[15][10]='j';
        
        // jugador
        tablero[12][3]='S';
        
        return tablero;
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
    public static String ValidacionComandos(char[][] tablero) {
        boolean validez=false;
        char caracter = ' ';
        char caracter2 = ' ';
        char caracterFinal=' ';
        String comandos="";
        do {
            System.out.println("");
            System.out.println("Ingrese los comandos separados por ',': ");
            comandos = input.next();
            for (int i = 0; i < comandos.length() - 1; i += 2) {
                // Char final
                caracterFinal=comandos.charAt(comandos.length()-1);
                // Letras
                caracter = comandos.charAt(i);
                // Comas
                if (i <= comandos.length()-1) {
                    caracter2 = comandos.charAt(i + 1);
                    if (caracterFinal==','){
                        System.out.println("No deje comas al final.");
                        break;
                    }
                    if (caracter == 'u' || caracter == 'd' || caracter == 'l' || caracter == 'r' || caracter == 'f') {
                        System.out.println("Ingrese letras en mayusculas.");
                        break;
                    }
                    if (caracter == 'U' || caracter == 'D' || caracter == 'L' || caracter == 'R' || caracter == 'F') {
                        if (!(caracterFinal == 'U' || caracterFinal == 'D' || caracterFinal == 'L' || caracterFinal == 'R' || caracterFinal == 'F')) {
                            System.out.println("Letras no validas.");
                            validez=false;
                            break;
                        }
                        if (!(caracter2 == ',')) {
                            System.out.println("Posiciones de caracteres no validas.");
                            validez=false;
                            break;
                        }
                        validez=true;
                    } else {
                        System.out.println("Comandos invalidos.");
                        validez=false;
                        break;
                       
                    }
                }
            }
        } while (validez==false);
        return comandos;
    }

}
