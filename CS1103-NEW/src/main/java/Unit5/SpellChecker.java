package Unit5;

import Test.TextIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SpellChecker {

    public static void main(String[] args) {

        // Reading in the Dictionary
        try {
            Scanner fileIn = new Scanner(new File("\\words.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
