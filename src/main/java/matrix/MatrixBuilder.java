package main.java.matrix;

import java.util.Random;

public class MatrixBuilder {

    public static Matrix buildRandomMatrix(int rows, int cols, double limit) {
        Matrix m = new Matrix(rows, cols);
        Random numGen = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int randNum = (int) ( Math.random() * limit);
                m.set(i, j, randNum);
            }
        }
        return m;
    }

    public static Matrix buildHilbertMatrix(int rows, int cols) {
        Matrix m = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double value = 1.0 / ((i + 1.0) + (j + 1.0) - 1);
                m.set(i, j, value);
            }
        }
        return m;
    }

}