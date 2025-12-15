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
        System.out.println("");
        
        // Validación de perder o ganar
        boolean ganar = false; // Verficiación de que no quedan regalos
        boolean perder= false; // Verificación de tocar algún obstáculo
        
        // Posicion del jugador
        int posCol = 12;
        int posFil = 3;
        int oldPosCol=posCol;
        int oldPosFil=posFil;
        
        // Acción del jugador
        boolean puedeAgarrar=true;
        boolean puedeDejar=false;
        boolean encimaDeRegalo=false;
        boolean tieneRegalo=false;
        
        // Tablero
        char[][] tablero = new char[24][24];
        
        // Tablero interno
        char[][] tableroInterno = new char[24][24];
        
        // Generar los tableros
        tablero=GenerarTablero(tablero);
        tableroInterno=GenerarTableroInterno(tableroInterno);
        
        // Imprimir el tablero
        ImprimirTablero(tablero);
        System.out.println("");
        
        // Validaciones de las cajas
        boolean tieneCaja=false;
        
        // Comandos del usuario
        String comandos = "";

        // Inicio de el juego en si
        do {
            System.out.println("Comandos: ");
            System.out.println("U:Arriba");
            System.out.println("D:Abajo");
            System.out.println("L:Izquierda");
            System.out.println("R:Derecha");
            System.out.println("F:Recoger/Poner caja");
            comandos = ValidacionComandos(tablero);

            // Posicion anterior del jugador
            oldPosCol = posCol;
            oldPosFil = posFil;

            // Posición del jugador
            posCol = MoverJugadorX(tablero, comandos, posCol, posFil);
            posFil = MoverJugadorY(tablero, comandos, posCol, posFil);

            char caracter = ' ';
            for (int i = 0; i <= comandos.length() - 1; i += 2) {
                caracter = comandos.charAt(i);
                
                if (caracter == 'F') {  
                    if (tieneRegalo == true) {
                        // Dejar un regalo en las estaciones
                        if (tableroInterno[posCol][posFil] == 'D' ||tableroInterno[posCol][posFil] == 'H' ||tableroInterno[posCol][posFil] == 'L') {
                            System.out.println("Ha dejado un regalo en la estacion");
                            System.out.println("");
                            tableroInterno[posCol][posFil] = ' ';
                            tieneRegalo = false; 
                        }
                        // Si esta en un espacio vacío
                        else if (tableroInterno[posCol][posFil] == ' ') { // Si quiere soltar un regalo
                            System.out.println("Ha soltado un regalo.");
                            System.out.println("");
                            tableroInterno[posCol][posFil] = 'j'; // Guardar en el tablero interno
                            tieneRegalo = false;
                             if (posCol == 0) {
                                tablero[posCol + 1][posFil] = 'S';
                                tablero[posCol][posFil] = 'j';
                                posCol += 1;
                            } else if (posCol <= 23) {
                                tablero[posCol - 1][posFil] = 'S';
                                tablero[posCol][posFil] = 'j';
                                posCol -= 1;
                            } else {
                                posCol -= 1;
                                tablero[posCol - 1][posFil] = 'S';
                                tablero[posCol][posFil] = 'j';
                            }
                        }
                        else { // Si ya tiene un regalo y quiere agarrar otro 
                            System.out.println("No puede agarrar otro regalo.");
                            System.out.println("");
                        }

                    } else { // Agarrar un regalo
                        if (tableroInterno[posCol][posFil] == 'j') {
                            tieneRegalo = true;
                            tableroInterno[posCol][posFil] = ' ';
                        } else { // Si intenta dejar un regalo sin tener uno
                            System.out.println("No tiene un regalo.");
                            System.out.println("");
                        }
                    }
                } 
            } // fin del for 

            // Tocar Obstáculo
            if ( // inicio del if
                    // Obstáculos
                    tablero[posCol][posFil] == 'X'
                    || tablero[posCol][posFil]=='O'||
                    tablero[posCol][posFil]=='o'||
                    tablero[posCol][posFil]=='0'||
                    tablero[posCol][posFil]=='^'||
                    // Árbol
                    tablero[posCol][posFil]=='/'||
                    tablero[posCol][posFil]=='\\'||
                    tablero[posCol][posFil]=='-'||
                    tablero[posCol][posFil]=='|'
               )    
            { 
                // adentro del if
                perder=true;
                tablero[posCol][posFil]='!';
                tablero[oldPosCol][oldPosFil]=' ';
                break;
            }   // cierre del if
            
            // Validación de ganar
            ganar=VerificarTablero(tableroInterno);
            if (ganar==true){
                break;
            }
            
            // Motrar a Steeb
            if (tieneRegalo==false){
                if (tableroInterno[posCol][posFil] == 'j'||tableroInterno[posCol][posFil] == 'D'||tableroInterno[posCol][posFil] == 'H'||tableroInterno[posCol][posFil] == 'L') {
                    tablero[oldPosCol][oldPosFil] = ' ';
                    tablero[posCol][posFil] = 'S';
                }
                else if (tableroInterno[oldPosCol][oldPosFil] == 'j'||tableroInterno[oldPosCol][oldPosFil] == 'D'||tableroInterno[oldPosCol][oldPosFil] == 'H'||tableroInterno[oldPosCol][oldPosFil] == 'L'){
                    tablero[oldPosCol][oldPosFil] = tableroInterno[oldPosCol][oldPosFil];
                    tablero[posCol][posFil] = 'S';
                }
                else if (tablero[posCol][posFil]==' '){
                    tablero[oldPosCol][oldPosFil] = ' ';
                    tablero[posCol][posFil] = 'S';
                }
                else {
                    tablero[oldPosCol][oldPosFil] = ' ';
                    tablero[posCol][posFil] = 'S';
                }
                
            } else {
                
                if (tableroInterno[posCol][posFil] == 'j' || tableroInterno[posCol][posFil] == 'D' || tableroInterno[posCol][posFil] == 'H' || tableroInterno[posCol][posFil] == 'L') {
                    tablero[oldPosCol][oldPosFil] = ' ';
                    tablero[posCol][posFil] = 'S';
                } else if (tableroInterno[oldPosCol][oldPosFil] == 'j' || tableroInterno[oldPosCol][oldPosFil] == 'D' || tableroInterno[oldPosCol][oldPosFil] == 'H' || tableroInterno[oldPosCol][oldPosFil] == 'L') {
                    tablero[oldPosCol][oldPosFil] = tableroInterno[oldPosCol][oldPosFil];
                    tablero[posCol][posFil] = 'S';
                } else if (tablero[posCol][posFil] == ' ') {
                    tablero[oldPosCol][oldPosFil] = ' ';
                    tablero[posCol][posFil] = 'S';
                } else {
                    tablero[oldPosCol][oldPosFil] = ' ';
                    tablero[posCol][posFil] = 'S';
                }

            }
            
            
            // Imprimir el tablero
            ImprimirTablero(tablero);
            System.out.println("");
            
            // Si el jugador tiene un regalo
            if (tieneRegalo == true) {
                System.out.println("Tiene un regalo.");
                System.out.println("");
            } 

            //Imprimir el tablero interno (Debug)
            //ImprimirTablero(tableroInterno);
            //System.out.println("");
            
        } while (ganar==false&&perder==false);
        
        // Mostrar ganar en consola
        if (ganar==true){
            System.out.println("");
            System.out.println("Mission Accomplished");
            System.out.println("");
            ImprimirTablero(tablero);
            System.out.println("");
            System.out.println("Felicidades, ha logrado que Steeb entregue los dulces para esta navidad!");
        }
        // Mostrar perder en consola
        if (perder==true){
            System.out.println("");
            System.out.println("GAME OVER: Mission Failed");
            System.out.println("");
            ImprimirTablero(tablero);
            System.out.println("");
            System.out.println("Oops, parece que has chocado con algo.");
        }
        
    }
    
    public static void AccionJugador(char[][] tablero, int posCol, int posFil, String comandos, boolean guardar, boolean recoger, boolean dejar, boolean encimaDeRegalo) {
        
        
    }
    public static int MoverJugadorX(char[][]tablero, String comandos, int posCol, int posFil){
        char caracter=' ';
        for (int i = 0; i <= comandos.length()-1; i+=2) {
            caracter=comandos.charAt(i);
            switch (caracter){
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
            }
        }
        return posCol;
    }
    public static int MoverJugadorY(char[][]tablero, String comandos, int posCol, int posFil){
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
            }
        }
        return posFil;
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
    public static char[][] GenerarTableroInterno(char[][]tableroInterno){
        /*
        tablero[columna][fila]='caracter';
        tablero[][]='';
        */
        
        // Genera todos los obstaculos y regalos en el tablero
        
        //Espacios
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 24; j++) {
                tableroInterno[j][i]=' ';
            }
        }
        // DHL
        tableroInterno[2][2]='D';
        tableroInterno[23][0]='H';
        tableroInterno[2][12]='L';
        tableroInterno[22][9]='D';
        tableroInterno[4][20]='H';
        tableroInterno[20][18]='L';
        
        // j
        tableroInterno[9][12]='j';
        tableroInterno[12][13]='j';
        tableroInterno[11][8]='j';
        tableroInterno[11][10]='j';
        tableroInterno[14][9]='j';
        tableroInterno[15][10]='j';
        
        return tableroInterno;
    }
    
    public static boolean VerificarTablero(char[][]tablero){
        // Verifica que ya no hayan estaciones en el tablero
        char caracter = ' ';
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 24; j++) {
                caracter = tablero[j][i];
                if (caracter=='D'){
                    return false; // Continua el juego
                }
                if (caracter=='H'){
                    return false; // Continua el juego
                }
                if (caracter=='L'){
                    return false; // Continua el juego
                }
            }
        }
        return true; // Finaliza con ganar=true
    }
    
    public static String ValidacionComandos(char[][] tableroInterno) {
        boolean validez=false;
        char caracter = ' ';
        char caracter2 = ' ';
        char caracterFinal=' ';
        String comandos="";
        do {
            System.out.println("");
            System.out.println("Ingrese los comandos separados por ',': ");
            comandos = input.next();
            System.out.println("");
            
            // Longitud de un solo char
            caracter = comandos.charAt(0);
            if ((comandos.length() - 1 == 0) && (caracter == 'U' || caracter == 'D' || caracter == 'L' || caracter == 'R' || caracter == 'F')) {
                return comandos;
            }
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

