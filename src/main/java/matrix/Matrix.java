package main.java.matrix;

import main.java.linearalgebra.strassen.Strassen;

import java.util.ArrayList;

/**
 * functions to implement:
 * - add 2 matricies
 * - multply 2 matricies
 * - rref
 * - subtract 2 matricies
 * - transpose matrix
 * - determinent
 * - inverse
 */
public class Matrix {

    private ArrayList<ArrayList<Double>> backing;
    private int rows, cols;

    /**
     * will create an empty 2D ArrayList
     */
    public Matrix() {
        this.backing = new ArrayList<ArrayList<Double>>();
        init();
    }

    public Matrix(int r, int c) {
        this.backing = new ArrayList<ArrayList<Double>>();
        this.rows = r;
        this.cols = c;
        init();
    }

    /**
     * Will add this matrix to other.
     * This matrix will be modified to be the sum of the two matrices
     */
    public static Matrix add(Matrix a, Matrix other) {
        // check the sizes
        if (a.getRows() != other.getRows() || a.getCols() != other.getCols()) {
            throw new IllegalArgumentException("Matrices of improper dims");
        }
        Matrix b = new Matrix(a.getRows(), a.getCols());
        // now do the thing
        for (int i = 0; i < a.getRows(); i++) {
            for (int j = 0; j < a.getCols(); j++) {
                double diff = a.get(i, j) + other.get(i, j);
                b.set(i, j, diff);
            }
        }
        return b;
    }

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

    public static Matrix multiply(Matrix m1, Matrix m2) {
        if (m1.cols != m2.rows) {
            throw new IllegalArgumentException("Matrices of improper dims");
        }
        Matrix prod = new Matrix(m1.rows, m2.cols);
        for (int x = 0; x < m1.rows; x++) {
            for (int y = 0; y < m2.cols; y++) {
                prod.set(x, y, dotProduct(m1.getRow(x), m2.getCol(y)));
            }
        }
        return prod;
    }

    /**
     * Will perform operation this - other.
     * This matrix will be modified to be the difference of the two matricies
     */
    public static Matrix subtract(Matrix a, Matrix other) {
        // check the sizes
        if (a.getRows() != other.getRows() || a.getCols() != other.getCols()) {
            throw new IllegalArgumentException("Matrices of improper dims");
        }
        Matrix b = new Matrix(a.getRows(), a.getCols());
        // now do the thing
        for (int i = 0; i < a.getRows(); i++) {
            for (int j = 0; j < a.getCols(); j++) {
                double diff = a.get(i, j) - other.get(i, j);
                b.set(i, j, diff);
            }
        }
        return b;
    }

    public static boolean equals(Matrix a, Matrix b) {
        if (a.getRows() != b.getRows() || a.getCols() != b.getCols()) {
            return false;
        }
        for (int i = 0; i < a.getRows(); i++) {
            for (int j = 0; j < b.getRows(); j++) {
                if (Math.abs(a.get(i, j) - b.get(i, j)) < 10E-02) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * helper method for no-args constructor
     */
    private void init() {
        for (int i = 0; i < rows; i++) {
            backing.add(new ArrayList<Double>());
            for (int j = 0; j < cols; j++) {
                backing.get(i).add(0.0);
            }
        }
        for (ArrayList<Double> a : backing) {
            a = new ArrayList<Double>();
        }
    }

    // BEGIN GETTERS/SETTERS
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;

    }

    // END GETTERS/SETTERS

    public ArrayList<Double> getRow(int x) {
        return backing.get(x);
    }

    public ArrayList<Double> getCol(int x) {
        ArrayList<Double> nums = new ArrayList<Double>();
        for (int y = 0; y < rows; y++) {
            nums.add(backing.get(y).get(x));
        }
        return nums;
    }

    // START THE GOOD STUFF

    public double get(int i, int j) {
        if (!isValid(i, j)) {
            throw new IllegalArgumentException("get out of bounds: " + i + ", " + j);
        }
        return backing.get(i).get(j);
    }

    public void set(int i, int j, double value) {
        if (!isValid(i, j)) {
            throw new IllegalArgumentException("set out of bounds: " + i + ", " + j);
        }
        backing.get(i).set(j, value);
    }

    public boolean equals(Matrix a) {
        if (a.getCols() != cols || a.getRows() != rows){
            return false;
        }

        if (!super.equals(a)) {
            return false;
        }
        else {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (get(i, j) != a.get(i, j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public String toFormattedString() {
        String representation = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                representation += String.format("%03.6f\t", get(i,j));
            }
            representation += "\n";
        }
        return representation;
    }

    public String toString() {
        String representation = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                representation += get(i,j);
            }
            representation += "\n";
        }
        return representation;
    }

    /**
     * @return true if (i, j) is in bounds
     */
    public boolean isValid(int i, int j) {
        return (i >= 0 && i < rows) && (j >= 0 && j < cols);
    }

    /**
     * will print out the 2D ArrayList to the console
     */
    public String print() {
        String s = "";
        for (ArrayList<Double> a : backing) {
            s += a;
            s += "\n";
        }
        return s;
    }

}
