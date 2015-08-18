package main.java.util;

/**
 * Created by koushikkrishnan on 8/17/15.
 */
public class Stopwatch {

    private long start;
    private long stop;

    public Stopwatch() {
        start = System.currentTimeMillis();
    }

    public void start() {
        start = System.currentTimeMillis();
    }

    public void stop() {
        stop = System.currentTimeMillis();
    }

    public double elapsedTime() {
        long oldStop = stop;
        stop = start;
        return (oldStop - start);
    }
}
