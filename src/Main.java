//Nathan Diepenbrock
//NLD200000
//Project 1

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;


public class Main {

    public static void main(String[] args) throws IOException {

        //create pipes
        final PipedOutputStream output1 = new PipedOutputStream();
        final PipedInputStream input1 = new PipedInputStream(output1);

        final PipedOutputStream output2 = new PipedOutputStream();
        final PipedInputStream input2 = new PipedInputStream(output2);
        //thread 1 sends to pipe1 first then receives at pipe2
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //OUT1 START:
                    Directory dir1 = new Directory("/Users/nathan/Desktop/Test folder1");
                    String s = dir1.encode();

                    output1.write(s.getBytes());
                    output1.close();
                    //OUT1 END

                    //IN2 START:
                    int data = input2.read();
                    String str = "";
                    System.out.println("THREAD 1:");
                    while (data != -1) {
                        System.out.print((char) data);
                        str = str + (char) data;
                        data = input2.read();
                    }
                    input2.close();
                    //IN2 END

                    //decode message and then write all files to dir1
                    dir1.decode(str);
                    dir1.writeToDirectory();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //IN1
                    int data = input1.read();
                    StringBuilder str = new StringBuilder();
                    System.out.println(" THREAD 2:");
                    while (data != -1) {
                        System.out.print((char) data);
                        str.append((char) data);
                        data = input1.read();
                    }
                    input1.close();
                    //IN1

                    //OUT2
                    Directory dir2 = new Directory("/Users/nathan/Desktop/Test folder2");
                    String s = dir2.encode();

                    output2.write(s.getBytes());
                    output2.close();

                    //OUT2

                    //decode msg and write all files to dir2
                    dir2.decode(str.toString());
                    dir2.writeToDirectory();


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        thread1.start();
        thread2.start();
    }

}










