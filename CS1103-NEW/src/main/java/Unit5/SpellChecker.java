package Unit5;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This program is designed to serve as a spell checker.
 * The user is supposed to supply the program with a dictionary file (*.txt)
 * As well as a text file containing words that contains misspelled words.
 */
public class SpellChecker {

    public static void main(String[] args) {

        // Reading in the Dictionary
        try {
            /* Read in dictionary from path provided
            If file does not exist, user will be prompted with a JFileChooser dialog to input the dictionary file.
             */
            File newfile = new File("D:\\Edu Projects\\UoPeople\\CS1103-NEW\\src\\main\\java\\Unit5\\words.txt");
            Scanner dictionary = new Scanner(Objects.requireNonNull(newfile.exists()
                    ? newfile : getInputFileNameFromUser("Select dictionary")));
            HashSet<String> dictionarySet = new HashSet<>();


            Objects.requireNonNull(dictionary, "Could not find the words.txt file, specify rectify path.");

            while (dictionary.hasNext()) {
                String token = dictionary.next();
                //Adds words from the dictionary excluding duplicates.
                dictionarySet.add(token.toLowerCase());
            }
//            System.out.println(dictionarySet.size());

            // Allow user to choose a file to check for words and check their spelling.
            Scanner fileIn = new Scanner(Objects.requireNonNull(getInputFileNameFromUser("Select File for Input"), "No file was supplied from user."));
            fileIn.useDelimiter("[^a-zA-Z]+");
            ArrayList<String> inputWords = new ArrayList<>();

            while (fileIn.hasNext()) {
                inputWords.add(fileIn.next());
            }
            // iterate through words in fileIn and check if the word is not in the dictionary
            // If not, then it will be spell checked and a list of corrections will be printed if any
            // corrections are available.
            for (String word : inputWords) {
                if (!dictionarySet.contains(word)) {
                    TreeSet<String> corrections = corrections(word, dictionarySet);
                    if (corrections.isEmpty()) {
                        System.out.println(word + ": (no corrections)");
                    } else {
                        StringBuilder sb = new StringBuilder();
                        Iterator<String> iterator = corrections.iterator();
                        sb.append(iterator.next());
                        while (iterator.hasNext()) {
                            sb.append(", ").append(iterator.next());
                        }
                        System.out.println(word + ": " + sb.toString());
                    }
                }
            }

        } catch (FileNotFoundException fnfe) {
            System.out.println("Couldn't find the file, please check the path.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Lets the user select an input file using a standard file
     * selection dialog box.  If the user cancels the dialog
     * without selecting a file, the return value is null.
     * @param dialogTitle
     * @return File object
     */
    static File getInputFileNameFromUser(String dialogTitle) {
        JFileChooser fileDialog = new JFileChooser();
        fileDialog.setDialogTitle(dialogTitle);
        int option = fileDialog.showOpenDialog(null);
        if (option != JFileChooser.APPROVE_OPTION)
            return null;
        else
            return fileDialog.getSelectedFile();
    } // end of method

    /**
     * This method spell checks a misspelled word.
     * The misspelled word is passed through as an argument along with a dictionary Set.
     * The misspelled word is manipulated to create variants that might match a word
     * from the dictionary, if so then the variant will be added to a TreeSet named corrections.
     * @param badWord
     * @param dictionary
     * @return TreeSet<String> of possible corrections for the misspelled word.
     */
    static TreeSet<String> corrections(String badWord, HashSet<String> dictionary) {
        TreeSet<String> corrections = new TreeSet<>();

        // create a variant of badWord until it matches a word in the dictionary
        for (char ch = 'a'; ch <= 'z'; ch++) {
            int i = badWord.length() - 1;
            String variant = new String(badWord.substring(0, i) + ch + badWord.substring(i + 1));

            if (dictionary.contains(variant)) {
                corrections.add(variant);
            }
        }

        // iterate through each letter and add a space to check if it creates a variant that matches a word in the dictionary.
        for (int i = 0; i < badWord.length(); i++) {
            StringBuilder builder = new StringBuilder();
            builder.append(badWord);
            String spaceVariant = builder.insert(i, " ").toString();

            if (dictionary.contains(spaceVariant)) {
                corrections.add(spaceVariant);
            }
        }

        // iterate through each letter and delete a letter.
        for (int i = 0; i < badWord.length(); i++) {
            StringBuilder builder = new StringBuilder();
            builder.append(badWord);
            String deleteVariant = builder.deleteCharAt(i).toString();

            if (dictionary.contains(deleteVariant)) {
                corrections.add(deleteVariant);
            }
        }

        // iterate through each letter and replace it with another.
        for (int i = 0; i < badWord.length(); i++) {
            StringBuilder builder = new StringBuilder();
            builder.append(badWord);
            for (Character ch = 'a'; ch <= 'z'; ch++) {
                String replaceVariant = builder.replace(i, i + 1, ch.toString()).toString();

                if (dictionary.contains(replaceVariant)) {
                    corrections.add(replaceVariant);
                }
            }
        }

        // swap two neighboring characters
        for (int i = 0; i < badWord.length() - 1; i++) {
            StringBuilder builder = new StringBuilder();
            builder.append(badWord);
            char temp = builder.charAt(i);
            builder.setCharAt(i, builder.charAt(i + 1));
            builder.setCharAt(i + 1, temp);
            String swapVariant = builder.toString();

            if (dictionary.contains(swapVariant)) {
                corrections.add(swapVariant);
            }
        }

        if (corrections.isEmpty()) {
            corrections.add("(no suggestions)");
        }
        return corrections;
    } //end of method
}// end of class
