package org.example.sudoku.Model;

import java.util.Random;

public class Sudoku {

    private int[][] grid;

    public Sudoku() {
        grid = new int[6][6];
        generateRandomSudoku();
    }

    // Devuelve la cuadrícula de Sudoku
    public int[][] getGrid() {
        return grid;
    }

    // Genera un Sudoku 6x6 con 2 números aleatorios en cada subcuadrícula 2x3
    public void generateRandomSudoku() {
        clearGrid();  // Limpiar la cuadrícula antes de generar un nuevo Sudoku
        Random random = new Random();

        // Iterar sobre cada bloque 2x3
        for (int blockRow = 0; blockRow < 6; blockRow += 2) {
            for (int blockCol = 0; blockCol < 6; blockCol += 3) {
                int numbersPlaced = 0;  // Contador de números colocados en este bloque

                while (numbersPlaced < 2) {
                    // Elegir una celda aleatoria dentro del bloque 2x3
                    int row = blockRow + random.nextInt(2);  // Fila dentro del bloque 2x3
                    int col = blockCol + random.nextInt(3);  // Columna dentro del bloque 2x3
                    int value = random.nextInt(6) + 1;  // Generar un valor aleatorio entre 1 y 6

                    // Verificar si la celda está vacía y si el valor es válido según las reglas
                    if (grid[row][col] == 0 && isValid(row, col, value)) {
                        grid[row][col] = value;  // Colocar el valor
                        numbersPlaced++;  // Incrementar el contador de números colocados en el bloque
                    }
                }
            }
        }
    }

    // Valida si un número puede colocarse en una fila, columna y bloque 2x3
    public boolean isValid(int row, int col, int value) {
        // Comprobamos la fila
        for (int i = 0; i < 6; i++) {
            if (grid[row][i] == value) {
                return false;
            }
        }

        // Comprobamos la columna
        for (int i = 0; i < 6; i++) {
            if (grid[i][col] == value) {
                return false;
            }
        }

        // Comprobamos el bloque 2x3
        int blockRowStart = (row / 2) * 2;
        int blockColStart = (col / 3) * 3;
        for (int i = blockRowStart; i < blockRowStart + 2; i++) {
            for (int j = blockColStart; j < blockColStart + 3; j++) {
                if (grid[i][j] == value) {
                    return false;
                }
            }
        }

        return true;  // Si no hay conflicto, el número es válido
    }

    // Actualiza el valor en una celda específica
    public void setValue(int row, int col, int value) {
        grid[row][col] = value;
    }

    // Limpia el tablero para una nueva partida
    public void clearGrid() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                grid[i][j] = 0;
            }
        }
    }
}
