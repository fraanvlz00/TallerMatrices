package org.example;

import java.util.Random;
import java.util.Scanner;

public class Matriz {
    private int[][] matriz;
    private static Scanner sc = new Scanner(System.in);

    public static boolean validarDimensiones(int filas, int cols) {
        return filas > 0 && cols > 0;
    }

    public int[][] crearMatriz(int filas, int cols) {
        matriz = new int[filas][cols];
        llenarMatriz(matriz);
        return matriz;
    }

    public void determinarMatriz() {
        int filas, columnas;
        System.out.print("Ingrese el número de filas: ");
        filas = sc.nextInt();
        System.out.print("Ingrese el número de columnas: ");
        columnas = sc.nextInt();
        if (!validarDimensiones(filas, columnas)) {
            System.out.println("Dimensiones inválidas.");
            return;
        } else {
            crearMatriz(filas, columnas);
        }
    }

    public static void llenarMatriz(int[][] matriz) {
        Random rand = new Random();
        for (int i = 0; i < matriz.length; i++) {
            int sumaFila = 0;
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = rand.nextInt(10); // 0 <= random <= 9
                sumaFila += matriz[i][j];
            }
            if (sumaFila >= 100) {
                System.out.println("Fila " + i + " suma " + sumaFila + ", regenerando...");
                i--; // Si la fila suma más de 100, se regenera
            }
        }
    }

    public void mostrarFila(int fila) {
        if (fila >= 0 && fila < matriz.length) {
            for (int val : matriz[fila]) {
                System.out.print(val + " ");
            }
            System.out.println();
        } else {
            System.out.println("Fila inválida.");
        }
    }

    public void mostrarColumna(int col) {
        if (col >= 0 && col < matriz[0].length) {
            for (int[] fila : matriz) {
                System.out.println(fila[col]);
            }
        } else {
            System.out.println("Columna inválida.");
        }
    }

    public boolean validarFila100() {
        for (int[] fila : matriz) {
            int suma = 0;
            for (int val : fila) {
                suma += val;
            }
            if (suma > 100) {
                return true;
            }
        }
        return false;
    }

    public boolean matrizCero() {
        int countCeros = 0;
        int totalElementos = matriz.length * matriz[0].length;
        for (int[] fila : matriz) {
            for (int val : fila) {
                if (val == 0) {
                    countCeros++;
                }
            }
        }
        return countCeros > totalElementos / 2;
    }

    public static void menuPrincipal() {
        System.out.println("Menú:");
        System.out.println("1. Mostrar fila");
        System.out.println("2. Mostrar columna");
        System.out.println("3. Verificar si alguna fila suma más de 100");
        System.out.println("4. Verificar si la matriz es de tipo cero");
        System.out.println("5. Salir");
    }

    public void menu() {
        determinarMatriz();

        int opcion;
        do {
            menuPrincipal();
            System.out.print("Ingrese una opción: ");
            int leerOpcion = sc.nextInt();
            opcion = leerOpcion;
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el número de fila a mostrar: ");
                    int fila = sc.nextInt();
                    mostrarFila(fila);
                    break;
                case 2:
                    System.out.print("Ingrese el número de columna a mostrar: ");
                    int col = sc.nextInt();
                    mostrarColumna(col);
                    break;
                case 3:
                    if (validarFila100()) {
                        System.out.println("Hay filas que suman más de 100.");
                    } else {
                        System.out.println("No hay filas que sumen más de 100.");
                    }
                    break;
                case 4:
                    if (matrizCero()) {
                        System.out.println("La matriz es de tipo cero.");
                    } else {
                        System.out.println("La matriz no es de tipo cero.");
                    }
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }
}

