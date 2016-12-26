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
class Reader implements Runnable {

   Controller c;

    public Reader(Controller c) {
        try {
            this.c = c;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        new Thread(this).start();
    }

    @Override
    public void run() {

        System.out.println("Started Reader Thread");
        try {
            while (!c.isFinished) {
                System.out.println("Calling - c.readerMethod();");
                c.readerMethod();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}