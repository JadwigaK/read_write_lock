package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Jadwiga on 2016-12-01.
 */
public class Writer extends Thread {
    private File file;
    private int numberOfThread;
    ReentrantReadWriteLock rwl;

    public Writer(File file, int numberOfThread, ReentrantReadWriteLock rwl){
        this.file=file;
        this.numberOfThread=numberOfThread;
        this.rwl=rwl;
    }

    //on pisze tak ze nadpisuje ten plik
    @Override
    public void run(){
        int i=0;
        while(true) {
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(file, "UTF-8");
                rwl.writeLock().lock();
                writer.println("Here is reader writer problem."+i++);
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } finally {
                rwl.writeLock().unlock();
            }
        }


    }
}
