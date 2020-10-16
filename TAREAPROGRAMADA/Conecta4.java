
/**
 * Es el juego 4 en línea.
 *
 * Andreas Sanabria B97190
 * 05/10/2020
 */

import java.util.*; 

public class Conecta4{
    // El tablero siempre tiene 6 filas y 7 columnas.
    final static int filas = 6;
    final static int columnas = 7;
    static int[][] tablero = new int[filas][columnas];
    static int[] llenado = new int[columnas];
    //Es el booleano que utiliza el menú para continuar o detener el juego.
    static boolean finJuego;
    //Es la variable que permite cambair de jugador.
    static int jugador;
    //Esta se utiliza para realizar el movimiento de cada juagdor.
    static int columna;
    /**
     * Imprime el tablero de juego y lo reimprime después de cada jugada.
     */
    static void campoJuego(){
        System.out.println("| 1 | 2 | 3 | 4 | 5 | 6 | 7 |");
        for(int i = 0; i < filas; i++){
            System.out.println("|   |   |   |   |   |   |   |");
            for(int j = 0; j < columnas; j++){
                if(tablero[i][j] == 0){
                    System.out.print("|   ");
                }
                else{
                    if (tablero[i][j] == 1){
                        System.out.print("| X ");
                    }
                    else{
                        System.out.print("| O ");
                    }
                }
            }
            System.out.print("|");
            System.out.println();
            System.out.println("|___|___|___|___|___|___|___|");
        }
        System.out.println();
    }

    /**
     * Cambia el valor de la variable jugador para que un jugador no pueda hacer dos movimientos seguidos.
     */
    static void cambioJugador(){
        if(jugador == 1){
            jugador = 2;
        }
        else{
            jugador = 1; 
        }
    }

    /**
     * Permite al usuario elegir la columna en la que quiere colocar la ficha. 
     * Le impide al usuario colocar una ficha en una columna inexistente o llena.
     */
    static void entradaColumna(){
        do{
            try{
                System.out.print("Jugador " + jugador + ", elija la columna.");
                columna = new java.util.Scanner(System.in).nextInt();
                if(columna < 1 || columna > columnas){
                    System.out.println("Columna inexistente.");
                }
                if(llenado[columna - 1] >= filas){
                    System.out.println("Columna llena.");  
                }
            }
            catch(Exception e){
                System.out.println("");
            }
        }
        while(columna < 1 || columna > columnas || llenado[columna - 1] >= filas);
        llenado[columna - 1]++;
    }

    /**
     * Coloca la ficha del jugador.
     */
    static void movimiento(){
        tablero[6 - llenado[columna - 1]][columna - 1] = jugador;
    }

    /**
     * Detecta la condición de victoria para los 3 casos ()diagonal, horizontal y vertical)
     * De no detectar alguno de esos tres casos, declara un empate.
     */
    static void condicionVictoria(){
        try{
            for(int i = 0; i <= 2; i++){
                for(int j = 0; j <= 6; j++){
                    if(tablero[i][j] == jugador && tablero[i + 1][j] == jugador && tablero[i + 2][j] == jugador && tablero[i + 3][j] == jugador){
                        finJuego = true;
                    }
                }
            }
            for(int i = 0; i <= 5; i++){
                for (int j = 0; j <= 3; j++){
                    if(tablero[i][j] == jugador && tablero [i][j + 1] == jugador && tablero[i][j + 2] == jugador && tablero[i][j + 3] == jugador){
                        finJuego = true;
                    }
                }
            }
            for(int i = 0; i < 3; i++){
                for(int j = 6; j > 2; j--){
                    if(tablero[i][j] == jugador && tablero[i + 1][j - 1] == jugador && tablero[i + 2][j - 2] == jugador && tablero[i + 3][j - 3] == jugador){
                        finJuego = true;
                    }
                }
            }
        }
        catch(Exception empate){
            System.out.println("Empate");
            finJuego = true;
        }
    } 

    /**
     * Es el menu que le permite al usuario jugar.
     * Le permite realizar otra partida o terminar el juego del todo.
     */
    public static void main(String[] args){
        int continuar = 0;
        do{
            finJuego = false;
            jugador = 2;
            for(int i = 0; i < filas; i++){
                for(int j = 0; j < columnas; j++){
                    tablero[i][j] = 0;
                }
            }
            for(int j = 0; j < columnas; j++){
                llenado[j] = 0;
            }
            System.out.println("4 en línea");
            campoJuego();
            while(finJuego == false){
                cambioJugador();
                entradaColumna();
                movimiento();
                campoJuego();
                condicionVictoria();
            }
            System.out.println("El jugador " + jugador + " ha ganado.");
            System.out.println("Desea jugar otra vez? (1 = sí, 2 = no)");
            continuar = new java.util.Scanner(System.in).nextInt();
        }while(continuar != 2);
    }

}