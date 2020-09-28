package xin.liujiajun.thread.waitnotify;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @author liujiajun
 * @date 2020-08-12 19:17
 **/
public class Piped {

    public static void main(String[] args) throws IOException {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();
        out.connect(in);

        Thread print = new Thread(new Printer(in), "print");
        print.start();

        int receive = 0;
        try {
            while ((receive = System.in.read()) != -1) {

                out.write(receive);
            }

        }finally {
            out.close();
        }


    }

    static class Printer implements Runnable{
        private PipedReader in;

        public Printer(PipedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            int receive = 0;
            try {

                while ((receive = in.read()) != -1) {
                    System.out.print((char) receive);
                }
            }catch (IOException e) {

            }
        }
    }
}
