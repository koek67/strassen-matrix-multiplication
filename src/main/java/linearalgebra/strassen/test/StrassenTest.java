package main.java.linearalgebra.strassen.test;

import main.java.linearalgebra.strassen.Strassen;
import main.java.matrix.Matrix;
import main.java.matrix.MatrixBuilder;
import main.java.matrix.MatrixOperator;
import main.java.util.Stopwatch;
import org.junit.Assert;

import static org.junit.Assert.*;

public class StrassenTest {

    private int dimension;
    private int numTries;

    @org.junit.Before
    public void setUp() throws Exception {
        dimension = 4;
        numTries = 10000;
    }

    @org.junit.Test
    public void test() {
        // run 100 multiplications and compare times for regular
        // and strassen multiplication

        Stopwatch watch = new Stopwatch();
        double avgDiff = 0;
        double avgReg = 0;
        double avgStrassen = 0;
        for (int i = 0; i < numTries; i++) {
            // create two 4x4 matrices
            Matrix a = MatrixBuilder.buildRandomMatrix(dimension, dimension, 500);
            Matrix b = MatrixBuilder.buildRandomMatrix(dimension, dimension, 500);

            watch.start();
            Matrix reg = MatrixOperator.multiply(a, b);
            watch.stop();

            double regElapsedTime = watch.elapsedTime();
            avgReg += regElapsedTime;

            watch.start();
            Matrix strassen = Strassen.mult(a, b, dimension);
            watch.stop();

            double strassenElapsedTime = watch.elapsedTime();
            avgStrassen += strassenElapsedTime;

            double diff = strassenElapsedTime - regElapsedTime;
            avgDiff += diff;

            Assert.assertEquals("Do not match", reg.print(), strassen.print());
        }

        System.out.println("Average time for strassen: " + avgStrassen / numTries);
        System.out.println("Average time for regular:  " + avgReg / numTries);
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }
}