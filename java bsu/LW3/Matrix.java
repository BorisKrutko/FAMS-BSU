package LW3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Matrix {   
    public static void main(String[] arg) {
        String args = "C:/BORIS/j_projects/input.txt";
        try {
            //if (args.length != 1) throw new InvalidArgsException("erorr");
            Scanner scanner = new Scanner(new File(args));
            int size = scanner.nextInt();
            double[][] matrix = getMatrix(scanner, size);
            printMatrix(matrix, size);
            double unser = getDeterminant(matrix, size);
            System.out.println(unser);
            printMatrix(calculateInverseMatrix(matrix, size), size);
        } catch (NumberFormatException a) {
            System.out.println("Number format exception: " + a.getMessage());
        //} catch (InvalidArgsException e) {
        //    System.out.println("args exeption: " + e.getMessage());
        } catch (FileNotFoundException c) {
            System.out.println("file exeption: " + c.getMessage());
        }
        
    }

    
    public static double[][] getMatrix(Scanner scanner, int size) {
        double[][] res_matrix = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                res_matrix[i][j] = scanner.nextDouble()     ;
            }
        }
        try {
            if (scanner.hasNext()) throw new InvalidArgsException("eror whith matrix");
        } catch (InvalidArgsException e) {
            System.out.println("args exeption: " + e.getMessage());
        }
        return res_matrix;
    }


    public static double[][] calculateInverseMatrix(double[][] matrix, int size) {
        double[][] res_matrix = new double[size][size];
        double determinant = getDeterminant(matrix, size);
        if (determinant == 0) {
            throw new IllegalArgumentException("Matrix is not invertible");
        }
        
        // создаем матрицу алг. дополнений
        double[][] cofactorMatrix = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cofactorMatrix[i][j] = Math.pow(-1, i + j) * getDeterminant(getMinor(matrix, size, i, j), size - 1);
            }
        }

        // Транспонируем матрицу алг. дополнений
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                res_matrix[i][j] = Math.round(cofactorMatrix[j][i] / determinant);
            }
        }
    
        return res_matrix;
    }

    
    public static double[][] getMinor(double[][] matrix, int size, int row, int col) {
        double[][] minor = new double[size - 1][size - 1];
        int minorRow = 0, minorCol;
        for (int i = 0; i < size; i++) {
            if (i == row) continue;
            minorCol = 0;
            for (int j = 0; j < size; j++) {
                if (j == col) continue;
                minor[minorRow][minorCol] = matrix[i][j];
                minorCol++;
            }
            minorRow++;
        }
        return minor;
    }
    

    public static double getDeterminant(double[][] matrix, int size) {
        double result = 0;
        if (size == 1) return matrix[0][0];
        if (size == 2) return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
    
        double[][] minor_matrix = new double[size - 1][size - 1];
        for (int i = 0; i < size; i++) {
            int minorRow = 0;
            for (int j = 1; j < size; j++) {
                int minorCol = 0;
                for (int k = 0; k < size; k++) {
                    if (k == i) continue;
                    minor_matrix[minorRow][minorCol] = matrix[j][k];
                    minorCol++;
                }
                minorRow++;
            }
            result += Math.pow(-1, i) * matrix[0][i] * getDeterminant(minor_matrix, size - 1);
        }
        return result;
    }
    

    public static void printMatrix(double [][] matrix, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}