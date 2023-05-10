package Unit5;

import Test.TextIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class SpellChecker {

    public static void main(String[] args) {

        // Reading in the Dictionary
        try {
            // Please change the full path to where you are keeping the file.
            File file = new File("D:\\Edu Projects\\UoPeople\\CS1103-NEW\\src\\main\\java\\Unit5\\words.txt");
            Scanner fileIn = new Scanner(file);
            HashSet<String> fileSet = new HashSet<>();

            while (fileIn.hasNext()) {
                String token = fileIn.next();
                //do something with token
                fileSet.add(token.toLowerCase());
            }
            System.out.println(fileSet.size());
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find the file, please check the path.");
        }
    }
}
