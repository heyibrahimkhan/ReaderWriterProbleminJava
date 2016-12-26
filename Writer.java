/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threading;

/**
 *
 * @author MUHAMMADAZHARMUNIR
 */
class Writer implements Runnable {

    Controller c;

    public Writer(Controller c) {
        try {
            this.c = c;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Started Writer Thread");
        new Thread(this).start();
    }

    public void run() {
        try {
            while (!c.isFinished) {
                System.out.println("Calling - c.writerMethod()");
                c.writerMethod();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}