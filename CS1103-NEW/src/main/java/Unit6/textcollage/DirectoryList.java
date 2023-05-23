package Unit6.textcollage;

import javax.swing.*;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 * This program lists the files in a directory specified by
 * the user.  The user is asked to type in a directory name.
 * If the name entered by the user is not a directory, a
 * message is printed and the program ends.
 */
public class DirectoryList {


    public static void main(String[] args) {

        String directoryName;  // Directory name entered by the user.
        File directory;        // File object referring to the directory.
        ArrayList<String> files = new ArrayList<>();        // Array of file names in the directory.
        Scanner scanner;       // For reading a line of input from the user.

        scanner = new Scanner(System.in);  // scanner reads from standard input.

        System.out.print("Enter a directory name: ");
        directoryName = scanner.nextLine().trim();
        directory = new File(directoryName, "TestOne"); // Please provide the name of the di

        if (!directory.isDirectory()) {
            if (!directory.exists())
                System.out.println("There is no such directory!");
            else
                System.out.println("That file is not a directory.");
        } else {
//            files.add(String.valueOf(directory));
            getFiles(directory);
        }

    } // end main()

    public static void getFiles(File file) {

        File[] files = file.listFiles();
        System.out.println("Files in directory \"" + file + "\":");
        for (File anotherFile : files) {

            if (anotherFile.isDirectory()) {
                getFiles(anotherFile);
            } else {
                System.out.println(anotherFile.getName());
            }
        }
    }
} // end class DirectoryList
