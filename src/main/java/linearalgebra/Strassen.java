package main.java.linearalgebra;

import main.java.matrix.Matrix;
import main.java.matrix.MatrixOperator;

/**
 * Class will be able to perform a Strassen matrix multiplication operation
 * on two matrices. The class will perform a test again the standard matrix
 * multiplication.
 *
 * @author Koushik Krishnan
 */
public class Strassen {

    /**
     * Method will perform a strassen matrix multiplication on two matrices.
     */
    public static Matrix mult(Matrix A, Matrix B, int n) {
        double log = Math.log(n) / Math.log(2);
        if (Math.floor(log) != log)
            throw new IllegalArgumentException("invalid matrix size");

        if (n == 1) return MatrixOperator.multiply(A, B);

        // create partitions
        Matrix a_1_1 = new Matrix(n/2, n/2);
        Matrix a_1_2 = new Matrix(n/2, n/2);
        Matrix a_2_1 = new Matrix(n/2, n/2);
        Matrix a_2_2 = new Matrix(n/2, n/2);

        Matrix b_1_1 = new Matrix(n/2, n/2);
        Matrix b_1_2 = new Matrix(n/2, n/2);
        Matrix b_2_1 = new Matrix(n/2, n/2);
        Matrix b_2_2 = new Matrix(n/2, n/2);

        // fill partitions
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double val_a = A.get(i, j);
                double val_b = B.get(i, j);

                // if rows are top
                if (i < n/2) {
                    // if cols are left
                    if (j < n/2) {
                        // assign to 1,1
                        a_1_1.set(i, j, val_a);
                        b_1_1.set(i, j, val_b);
                    }
                    // if cols are right
                    else {
                        a_1_2.set(i, j - n/2, val_a);
                        b_1_2.set(i, j - n/2, val_b);
                    }
                }
                // if rows are bot
                else {
                    // if cols are left
                    if (j < n/2) {
                        // assign to 1,1
                        a_2_1.set(i - n/2, j, val_a);
                        b_2_1.set(i - n/2, j, val_b);
                    }
                    // if cols are right
                    else {
                        a_2_2.set(i - n/2, j - n/2, val_a);
                        b_2_2.set(i - n/2, j - n/2, val_b);
                    }
                }
            }
        }

        Matrix p_1 = mult( Matrix.subtract(a_1_2, a_2_2),       Matrix.add(b_2_1, b_2_2), n/2 );
        Matrix p_2 = mult( Matrix.add(a_1_1, a_2_2),            Matrix.add(b_1_1, b_2_2), n/2);
        Matrix p_3 = mult( Matrix.subtract(a_1_1, a_2_1),       Matrix.add(b_1_1, b_1_2), n/2 );
        Matrix p_4 = mult( Matrix.add(a_1_1, a_1_2),            b_2_2, n/2);
        Matrix p_5 = mult( a_1_1,                               Matrix.subtract(b_1_2, b_2_2), n/2);
        Matrix p_6 = mult( a_2_2,                               Matrix.subtract(b_2_1, b_1_1), n/2);
        Matrix p_7 = mult( Matrix.add(a_2_1, a_2_2),            b_1_1, n/2);

        Matrix c_1_1 = Matrix.add(Matrix.subtract(Matrix.add(p_1, p_2), p_4), p_6);
        Matrix c_1_2 = Matrix.add(p_4, p_5);
        Matrix c_2_1 = Matrix.add(p_6, p_7);
        Matrix c_2_2 = Matrix.subtract( Matrix.add( Matrix.subtract(p_2, p_3) , p_5) , p_7);

        // combine the quadrants
        Matrix c = new Matrix(n, n);

        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < n/2; j++) {
                c.set(i, j, c_1_1.get(i, j));

                c.set(i + n/2, j, c_2_1.get(i, j));

                c.set(i, j + n/2, c_1_2.get(i, j));

                c.set(i + n/2, j + n/2, c_2_2.get(i, j));
            }
        }

        return c;
    }

}

