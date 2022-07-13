//Nathan Diepenbrock
//NLD200000
//Project 1

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Doc {

    String name;
    String content;
    //constructor that takes in a FILE
    Doc(File file) throws IOException {
        name = file.getName();

        content = Files.readString(Path.of(file.getAbsolutePath()));

    }
    //Constructor that takes in 2 strings
    Doc(String nameInp, String contentInp) {
        name = nameInp;
        content = contentInp;
    }
}
