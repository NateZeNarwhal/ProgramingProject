//Nathan Diepenbrock
//NLD200000
//Project 1

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;


public class Directory {


    public Doc[] fileList = new Doc[10];
    public String dirPath;
    private int size = 0;

    //Constructor that takes in the folder path as a String
    Directory(String path) throws IOException {
        File folder = new File(path);
        dirPath = path;
        createList(folder);
    }


    // takes in the folder and creates and populates the dir with DOC objects for each text file in the folder
    void createList(final File folder) throws IOException {
        size = 0;
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (!fileEntry.getName().equals(".DS_Store")) {
                fileList[size] = new Doc(fileEntry);
                size++;
            }
        }
        System.out.println("Done with folder 1");
    }

    //Return a string that has the files in a string that's form is "file 1 name,file 1 content!file 2 name, file 2 content!"
    public String encode() {
        String message = "";
        int i = 0;
        while (fileList[i] != null) {
            message = message.concat(fileList[i].name + "," + fileList[i].content + "!");
            i++;
        }
        return message;
    }

    //Takes in the encoded message from Encode and creates objects with the data and creates Docs for the data
    public void decode(String msg) {
        String[] arrayDocs;
        String[] array2;

        arrayDocs = msg.split("!");
        int i = 0;
        while (arrayDocs.length != i) {
            array2 = arrayDocs[i].split(",");
            fileList[size] = new Doc(array2[0], array2[1]);
            size++;
            i++;
        }
    }

    //create all doc as files and write their content to those files
    public void writeToDirectory() throws IOException {
        int i = 0;
        boolean b = false;
        while (fileList[i] != null) {
            Doc current = fileList[i];
            String currentPath = dirPath + "/" + current.name;
            File outFile = new File(currentPath);
            outFile.createNewFile();
            FileWriter writer = new FileWriter(currentPath);
            writer.write(current.content);
            i++;
            writer.close();
        }
    }
}
