//Nathan Diepenbrock
//NLD200000
//Project 1

//this is old testing code used to test the methods without the pipe. NOT USED IN RUNNING ANYMORE

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        Directory dir1 = new Directory("/Users/nathan/Desktop/Test folder1");
        Directory dir2 = new Directory("/Users/nathan/Desktop/Test folder3");
        dir2.decode(dir1.encode());
        dir2.writeToDirectory();
        System.out.println("Done");
    }
}
