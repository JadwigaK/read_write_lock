package main;

import logic.Reader;
import logic.Writer;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Jadwiga on 2016-12-01.
 */
public class Main {

    public static void main (String[] arg){

        File file = new File("test.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
        Reader r1 = new Reader(file, 1, rwl);
        Reader r2 = new Reader(file, 2, rwl);
        Writer w = new Writer(file, 1, rwl);

        r1.start();
        r2.start();
        w.start();

        try {
            r1.join();
            r2.join();
            w.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        file.delete();

    }
}
