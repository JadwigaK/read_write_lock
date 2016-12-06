package logic;

import java.io.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Jadwiga on 2016-12-01.
 */
public class Reader extends Thread {
    private File file;
    private int numberOfThread;
    ReentrantReadWriteLock rwl;

    public Reader(File file, int numberOfThread, ReentrantReadWriteLock rwl){
        this.file=file;
        this.numberOfThread=numberOfThread;
        this.rwl=rwl;
    }

    @Override
    public void run(){
        while(true) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            StringBuilder sb = new StringBuilder();
            rwl.readLock().lock();
            try {
                String line = br.readLine();
                System.out.println(line);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                rwl.readLock().unlock();
            }
        }

    }

}
