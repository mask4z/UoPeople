package Unit6.textcollage;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
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
        directory = new File(directoryName, "test");

        if (!directory.isDirectory()) {
            if (!directory.exists())
                System.out.println("There is no such directory!");
            else
                System.out.println("That file is not a directory.");
        } else {
            files.add(String.valueOf(directory));
            System.out.println("Files in directory \"" + directory + "\":");
            getFiles(directory);
        }

    } // end main()

    public static void getFiles(File file) {

        String[] files = file.list();
        for (String anotherFile : Objects.requireNonNull(file.list())) {
            File newFile = new File(file, anotherFile);
            if (newFile.isDirectory()) {
                getFiles(newFile);
            }
            System.out.println("Files in directory \"" + file + "\":");
            for (String s : files) {
                System.out.println(s);
            }
        }

    }

} // end class DirectoryList
