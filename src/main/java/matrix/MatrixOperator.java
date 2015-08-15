package main.java.matrix;

import java.util.ArrayList;

/**
 * Created by koushikkrishnan on 8/15/15.
 */
public class MatrixOperator {

    /**
     * Performs a matrix multiplication operation on the two given matrices.
     * The resulting matrix will be the product of (Matrix a) * (Matrix b).
     *
     * @param a left matrix
     * @param b right matrix
     * @return product of (Matrix a) * (Matrix b)
     */
    public static Matrix multiply(Matrix a, Matrix b) {

        if (a.getCols() != b.getRows()) {
            throw new IllegalArgumentException("Matrices of improper dimensions when multiplying. " +
                    "Left matrix: " + a.getRows() + ", " + a.getCols() +
                    "Right matrix: " + b.getRows() + ", " + b.getCols());
        }
        Matrix prod = new Matrix(a.getRows(), b.getCols());
        for (int x = 0; x < a.getRows(); x++) {
            for (int y = 0; y < b.getCols(); y++) {
                prod.set(x, y, dotProduct(a.getRow(x), b.getCol(y)));
            }
        }

        return prod;
    }

    /**
     * Helper method for MatrixBuilder::multiply. Will calculate the dot product
     * between the row vector of a left matrix and the column vector of a right
     * matrix.
     * 
     * @param v1 row vector of a left matrix
     * @param v2 column vector of a right matrix
     * @return dot product of the vectors
     */
    private static double dotProduct(ArrayList<Double> v1, ArrayList<Double> v2) {
        double dotProd = 0;
        if (v1.size() != v2.size()) {
            throw new IllegalArgumentException("mismatched input lengths");
        }
        for(int x = 0; x < v1.size() && x < v2.size(); x++) {
            dotProd += v1.get(x) * v2.get(x);
        }
        return dotProd;
    }

    public static Matrix add(Matrix a, Matrix b) {
        return null;
    }

}
