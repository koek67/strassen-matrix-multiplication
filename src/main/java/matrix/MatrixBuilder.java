package main.java.matrix;

import java.util.Random;

public class MatrixBuilder {

    public static Matrix buildRandomMatrix(int rows, int cols, double limit) {
        Matrix m = new Matrix();
        Random numGen = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double randNum = Math.random() * limit;
                m.set(i, j, randNum);
            }
        }
        return m;
    }

}