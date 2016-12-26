/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threading;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author MUHAMMADAZHARMUNIR
 */
class Controller {

    String line;
    File read;
    File write;
    BufferedReader File_BR;
    BufferedWriter File_BW;
    boolean isFinished;
    boolean isBusy;

    public Controller() {
        line = "";
        isFinished = false;
        isBusy = false;
        read = new File("C:\\Users\\MUHAMMADAZHARMUNIR\\Desktop\\OS\\read.txt");
        write = new File("C:\\Users\\MUHAMMADAZHARMUNIR\\Desktop\\OS\\write.txt");
        try {
            System.out.println("Creating File Readers and Writers");
            File_BR = new BufferedReader(new FileReader(read));
            File_BW = new BufferedWriter(new FileWriter(write));
        } catch (IOException e) {
            System.out.println(e.getCause());
        }
    }

    synchronized public void readerMethod() {
        if (!isFinished) {
            System.out.println("Starting from a non empty file");
            if (isBusy) {
                try {
                    System.out.println("Reader Method Waiting!");
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("Interrupted.");
                }
            }
            try {
                String s = File_BR.readLine();
                System.out.println("Read line : " + s);
                if (s != null) {
                    line = s;
                } else {
                    isFinished = true;
                    File_BR.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            isBusy = true;
            System.out.println("Notify to Writer");
            notify();

        }
    }

    synchronized public void writerMethod() {
        if (!isFinished) {
            System.out.println("Starting write operation from a non empty file");
            if (!isBusy) {
                try {
                    System.out.println("Writer Method Waiting!");
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("Interrupted.");
                }
            }
            try {
                System.out.println("Wrote line : "+ line);
                File_BW.write(line);
                File_BW.newLine();
                
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            
            isBusy = false;
            notify();
            System.out.println("Notify to Reader");
        }else{
            try {
                File_BW.close();
            } catch (IOException e) {
                System.out.println(e.getCause());
            }
            
        }
    }

    
}